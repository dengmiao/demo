package com.corgi.core.modules.sys.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.corgi.base.base.BaseController;
import com.corgi.base.base.IBaseJpaService;
import com.corgi.base.base.IBaseMybatisService;
import com.corgi.base.toolkit.ObjectUtil;
import com.corgi.base.vo.Result;
import com.corgi.core.modules.sys.entity.SysUser;
import com.corgi.core.modules.sys.entity.SysUserRole;
import com.corgi.core.modules.sys.service.ISysUserRoleService;
import com.corgi.core.modules.sys.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 用户controller
 * @author: dengmiao
 * @create: 2019-04-10 14:20
 **/
@Slf4j
@RestController
@RequestMapping("sys/user")
public class SysUserController extends BaseController<SysUser, Long> {

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysUserRoleService sysUserRoleService;

    @Override
    public IBaseJpaService<SysUser, Long> getService() {
        return null;
    }

    @Override
    public IBaseMybatisService<SysUser> getMybatisService() {
        return sysUserService;
    }

    /**
     * 查询用户角色集合
     * @param userid
     * @return
     */
    @RequestMapping(value = "/queryUserRole", method = RequestMethod.GET)
    public Result<List<String>> queryUserRole(@RequestParam(name="userid",required=true) String userid) {
        Result<List<String>> result = new Result<>();
        List<String> list = new ArrayList<String>();
        List<SysUserRole> userRole = sysUserRoleService.list(new QueryWrapper<SysUserRole>().lambda().eq(SysUserRole::getUserId,userid));
        if(userRole==null || userRole.size()<=0) {
            result.error500("未找到用户相关角色信息");
        }else {
            for (SysUserRole sysUserRole : userRole) {
                list.add(sysUserRole.getRoleId());
            }
            result.setSuccess(true);
            result.setResult(list);
        }
        return result;
    }

    /**
     * 添加
     * @param jsonObject
     * @return
     */
    @Override
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Result<SysUser> add(@RequestBody JSONObject jsonObject) {
        Result<SysUser> result = new Result<>();
        String selectedRoles = jsonObject.getString("selectedroles");
        SysUser sysUser = JSON.parseObject(jsonObject.toJSONString(), SysUser.class);
        String salt = ObjectUtil.randomGen(8);
        sysUser.setSalt(salt);
        String passwordEncode = new BCryptPasswordEncoder().encode(sysUser.getPassword());
        sysUser.setPassword(passwordEncode);
        sysUser.setStatus(1);
        boolean ok = sysUserService.save(sysUser);
        if(ok) {
            result.success("操作成功");
        } else {
            result.error500("操作失败");
        }
        return result;
    }

    /**
     * 用户名唯一校验
     * @param sysUser
     * @return
     */
    @RequestMapping(value = "checkOnlyUser", method = RequestMethod.GET)
    public Result<Boolean> checkUsername(SysUser sysUser) {
        Result<Boolean> result = new Result<>();
        //如果此参数为false则程序发生异常
        result.setResult(true);
        Long id = sysUser.getId();
        log.info("--验证用户信息是否唯一---id:"+id);
        try {
            SysUser oldUser = null;
            if(ObjectUtil.isNotEmpty(id)) {
                oldUser = sysUserService.getById(id);
            }else {
                sysUser.setId(null);
            }
            //通过传入信息查询新的用户信息
            SysUser newUser = sysUserService.getOne(new QueryWrapper<SysUser>(sysUser));
            if(newUser!=null) {
                //如果根据传入信息查询到用户了，那么就需要做校验了。
                if(oldUser==null) {
                    //oldUser为空=>新增模式=>只要用户信息存在则返回false
                    result.setSuccess(false);
                    result.setMessage("用户账号已存在");
                    return result;
                }else if(!id.equals(newUser.getId())) {
                    //否则=>编辑模式=>判断两者ID是否一致-
                    result.setSuccess(false);
                    result.setMessage("用户账号已存在");
                    return result;
                }
            }

        } catch (Exception e) {
            result.setSuccess(false);
            result.setResult(false);
            result.setMessage(e.getMessage());
            return result;
        }
        result.setSuccess(true);
        return result;
    }

    /**
     * 冻结&解冻用户
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/frozenBatch", method = RequestMethod.PUT)
    public Result<SysUser> frozenBatch(@RequestBody JSONObject jsonObject) {
        Result<SysUser> result = new Result<SysUser>();
        try {
            String ids = jsonObject.getString("ids");
            String status = jsonObject.getString("status");
            String[] arr = ids.split(",");
            for (String id : arr) {
                if(ObjectUtil.isNotEmpty(id)) {
                    this.sysUserService.update(new SysUser().setStatus(Integer.parseInt(status)),
                            new UpdateWrapper<SysUser>().lambda().eq(SysUser::getId,id));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.error500("操作失败"+e.getMessage());
        }
        result.success("操作成功!");
        return result;
    }

    /**
     * 修改密码
     */
    @RequestMapping(value = "/changPassword", method = RequestMethod.PUT)
    public Result<SysUser> changPassword(@RequestBody SysUser sysUser) {
        Result<SysUser> result = new Result<SysUser>();
        String password = sysUser.getPassword();
        sysUser = this.sysUserService.getOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, sysUser.getUsername()));
        if (sysUser == null) {
            result.error500("未找到对应实体");
        } else {
            String salt = ObjectUtil.randomGen(8);
            sysUser.setSalt(salt);
            String passwordEncode = new BCryptPasswordEncoder().encode(password);
            sysUser.setPassword(passwordEncode);
            this.sysUserService.updateById(sysUser);
            result.setResult(sysUser);
            result.success("密码修改完成！");
        }
        return result;
    }
}
