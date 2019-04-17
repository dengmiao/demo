package com.corgi.core.common.toolkit;

import com.corgi.core.config.security.SecurityUserDetails;
import com.corgi.core.config.security.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;

/**
 * @program: demo
 * @description:
 * @author: dengmiao
 * @create: 2019-04-06 15:47
 **/
@Slf4j
public class SecurityUtil {

    public static SecurityUserDetails getLoginUser() {
        refresh();
        //获取当前用户信息
        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (StringUtils.isEmpty(details)) {
            details = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        if (details instanceof UserDetails) {
            return ((SecurityUserDetails) details);
        } else {
            details = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return ((SecurityUserDetails) details);
        }
    }

    /**
     * 刷新当前登录用户的 LoginUserDetails
     *
     */
    public static void refresh() {
        UserDetailsService detailsService = getUserDetailsService();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //重新查询载入 SecurityUserDetails
        SecurityUserDetails userDetails = (SecurityUserDetails) detailsService
                .loadUserByUsername(((User) auth.getPrincipal()).getUsername());
        //设置权限
        UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(),
                auth.getCredentials(), userDetails.getAuthorities());

        //设置setDetails
        newAuth.setDetails(userDetails);
        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }

    /**
     * 获取 UserDetailsService
     * @return {@link UserDetailsService}
     */
    private static UserDetailsService getUserDetailsService() {
        return (UserDetailsService) SpringContextUtil.getBean(UserDetailsServiceImpl.class);
    }

    /**
     * 获取  用户ID
     * @return
     */
    public static Long getUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((SecurityUserDetails) principal).getId();
        }
        return null;
    }

    /**
     * 获取当前登录用户
     * @return
     */
    /*public SysUser getCurrUser(){
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return iSysUserService.findByUsername(user.getUsername());
    }*/

    /**
     * 获取当前用户数据权限 null代表具有所有权限
     */
    /*public List<String> getDeparmentIds(){

        List<String> deparmentIds = new ArrayList<>();
        SysUser u = getCurrUser();
        // 用户角色
        List<SysRole> userRoleList = iSysUserRoleService.findByUserId(String.valueOf(u.getId()));
        // 判断有无全部数据的角色
        Boolean flagAll = false;
        for(SysRole r : userRoleList){
            if(r.getDataType()==null||r.getDataType().equals(CommonConstant.DATA_TYPE_ALL)){
                flagAll = true;
                break;
            }
        }
        if(flagAll){
            return null;
        }
        // 查找自定义
        return iSysUserRoleService.findDepIdsByUserId(u.getId());
        return null;
    }*/
}
