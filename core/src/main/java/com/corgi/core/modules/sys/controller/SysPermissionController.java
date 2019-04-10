package com.corgi.core.modules.sys.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.corgi.core.common.toolkit.Md5Util;
import com.corgi.core.common.toolkit.ObjectUtil;
import com.corgi.core.common.vo.Result;
import com.corgi.core.modules.sys.entity.SysPermission;
import com.corgi.core.modules.sys.service.ISysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @description: 权限controller
 * @author: dengmiao
 * @create: 2019-04-09 15:12
 **/
@RestController
@RequestMapping("sys/permission")
public class SysPermissionController {

    @Autowired
    private ISysPermissionService iSysPermissionService;

    /**
     * 查询用户权限
     * @return
     */
    @RequestMapping(value = "queryByUser", method = RequestMethod.GET)
    public Result<JSONArray> queryByUser(HttpServletRequest req) {
        Result<JSONArray> result = new Result<>();
        try {
            String username = req.getParameter("username");
            List<SysPermission> metaList = iSysPermissionService.findByUser(username);
            JSONArray jsonArray = new JSONArray();
            this.getPermissionJsonArray(jsonArray, metaList, null);
            result.setResult(jsonArray);
            result.success("查询成功");
        } catch (Exception e) {
            result.error500("查询失败:"+e.getMessage());
            e.printStackTrace();
        }
        return result;
    }



    /**
     *  获取菜单JSON数组
     * @param jsonArray
     * @param metaList
     * @param parentJson
     */
    private void getPermissionJsonArray(JSONArray jsonArray, List<SysPermission> metaList, JSONObject parentJson) {
        for (SysPermission permission : metaList) {
            if(permission.getMenuType()==null) {
                continue;
            }
            Long tempPid = permission.getParentId();
            JSONObject json = getPermissionJsonObject(permission);
            if(parentJson==null && ObjectUtil.isEmpty(tempPid)) {
                jsonArray.add(json);
                if(permission.getIsLeaf()==0) {
                    getPermissionJsonArray(jsonArray, metaList, json);
                }
            }else if(parentJson!=null && ObjectUtil.isNotEmpty(tempPid) && String.valueOf(tempPid).equals(parentJson.getString("id"))){
                if(permission.getMenuType()==0) {
                    JSONObject metaJson = parentJson.getJSONObject("meta");
                    if(metaJson.containsKey("permissionList")) {
                        metaJson.getJSONArray("permissionList").add(json);
                    }else {
                        JSONArray permissionList = new JSONArray();
                        permissionList.add(json);
                        metaJson.put("permissionList", permissionList);
                    }

                }else if(permission.getMenuType()==1) {
                    if(parentJson.containsKey("children")) {
                        parentJson.getJSONArray("children").add(json);
                    }else {
                        JSONArray children = new JSONArray();
                        children.add(json);
                        parentJson.put("children", children);
                    }

                    if(permission.getIsLeaf()==0) {
                        getPermissionJsonArray(jsonArray, metaList, json);
                    }
                }
            }
        }
    }

    private JSONObject getPermissionJsonObject(SysPermission permission) {
        JSONObject json = new JSONObject();
        //类型(0：一级菜单 1：子菜单  2：按钮)
        if(permission.getMenuType()==2) {
            json.put("action", permission.getPerms());
            json.put("describe", permission.getName());
        }else if(permission.getMenuType()==0||permission.getMenuType()==1) {
            json.put("id", permission.getId());
            if(permission.getUrl()!=null&&(permission.getUrl().startsWith("http://")||permission.getUrl().startsWith("https://"))) {
                json.put("path", Md5Util.MD5Encode(permission.getUrl(), "utf-8"));
            }else {
                json.put("path", permission.getUrl());
            }

            //重要规则：路由name (通过URL生成路由name,路由name供前端开发，页面跳转使用)
            json.put("name", urlToRouteName(permission.getUrl()));

            //是否隐藏路由，默认都是显示的
            if(permission.isHidden()) {
                json.put("hidden",true);
            }
            //聚合路由
            if(permission.isAlwaysShow()) {
                json.put("alwaysShow",true);
            }
            json.put("component", permission.getComponent());
            JSONObject meta = new JSONObject();
            meta.put("title", permission.getName());
            if(ObjectUtil.isEmpty(permission.getParentId())) {
                //一级菜单跳转地址
                json.put("redirect",permission.getRedirect());
                meta.put("icon", ObjectUtil.getString(permission.getIcon(), ""));
            }else {
                meta.put("icon", ObjectUtil.getString(permission.getIcon(), ""));
            }
            if(permission.getUrl()!=null&&(permission.getUrl().startsWith("http://")||permission.getUrl().startsWith("https://"))) {
                meta.put("url", permission.getUrl());
            }
            json.put("meta", meta);
        }

        return json;
    }

    /**
     * 通过URL生成路由name（去掉URL前缀斜杠，替换内容中的斜杠‘/’为-）
     * 举例： URL = /isystem/role
     *     RouteName = isystem-role
     * @return
     */
    private String urlToRouteName(String url) {
        if(ObjectUtil.isNotEmpty(url)) {
            if(url.startsWith("/")) {
                url = url.substring(1);
            }
            url = url.replace("/", "-");
            return url;
        }else {
            return null;
        }
    }
}
