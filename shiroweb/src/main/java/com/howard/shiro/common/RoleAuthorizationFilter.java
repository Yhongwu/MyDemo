package com.howard.shiro.common;

import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
/**
 * 自定义过滤器
 * 主要用来将需要同时满足的条件改为只需要满足一种即可
 * 比如 roles["member,admin"]需要满足admin和member角色，经过配置过滤器后只需要满足一个角色即可
 * 过滤器需要在shiro里进行配置
 * 注解方式不需要此过滤器
 * 2017年9月19日
 * @author hongwu
 */
public class RoleAuthorizationFilter extends AuthorizationFilter{
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
          Subject subject = getSubject(request, response);
       String[] rolesArray = (String[]) mappedValue;

       if (rolesArray == null || rolesArray.length == 0) {
           // no roles specified, so nothing to check - allow access.
           return true;
       }

       Set<String> roles = CollectionUtils.asSet(rolesArray);
       for (String role : roles) {
           if (subject.hasRole(role)) {
               System.out.println("自定义过滤器 角色认证成功");
               return true;
           }
       }
       System.out.println("自定义过滤器 角色认证失败");
       return false;
    }

}
