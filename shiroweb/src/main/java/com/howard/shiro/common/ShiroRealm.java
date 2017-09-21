package com.howard.shiro.common;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.howard.shiro.service.SysPermissionsService;
import com.howard.shiro.service.SysRolesService;
import com.howard.shiro.service.SysUsersService;
import com.howard.shiro.vo.SysPermissions;
import com.howard.shiro.vo.SysRoles;
import com.howard.shiro.vo.SysUser;

public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private SysUsersService sysUsersService;
    @Autowired
    private SysRolesService sysRolesService;
    @Autowired
    private SysPermissionsService sysPermissionsService;
   /**
    * 权限认证
    */
   @Override
   protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    System.out.println("权限认证");
    System.out.println("doGetAuthorizationInfo");
    String name = (String) getAvailablePrincipal(principals);
    List<SysRoles> roles = sysRolesService.getRoleByUserId(name);
    List<String> rolesName = new ArrayList<>();
    //使用hashset为了使获取资源路径是不重复
    HashSet<String> set = new HashSet<>();
    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
    if (null != roles) {
          List<SysPermissions> permissionses = sysPermissionsService.getAllPermissionsByRoles(roles);
          for(SysRoles role:roles) {
        	  System.out.println(role);
               //获取用户角色信息
               rolesName.add(role.getRole());
               System.out.println("角色不为空!!");
               info.addRoles(rolesName);
          }
          if (permissionses != null) {
               for(SysPermissions p:permissionses) {
            	   System.out.println(permissionses);
                    set.add(p.getPermission());
               }
               //获取权限资源路径
               List<String> permissions = new ArrayList<>(set);
               for(String s: permissions) {
                    System.out.println(s);
               }
               info.addStringPermissions(permissions);
               System.out.println("权限不为空!");
          }

          return info;
    }
	   return null;
   }

   /**
    * 用户认证
    */
   @Override
   protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
           throws AuthenticationException {
       UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
       System.out.println("doGetAuthenticationInfo");
       //通过数据库获取 根据用户输入的用户名获取用户
       SysUser user = sysUsersService.getByUsername(token.getUsername());
       System.out.println(user);
       if (user != null ) {
    	  //带盐加密验证 具体验证方式配置在xml文件
    	  SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
    	  //不带盐加密验证
          //SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
//          //清除之前的缓存 如果开启了缓存 最好加上这步用于清空上次登录的缓存
//          clearCache(info.getPrincipals());
          //将session托管与shiro 所以这里获取shiro的session，其跟一直在用的session没什么不同
          Session session = SecurityUtils.getSubject().getSession();
          session.setAttribute("user", info);
          //不代表成功 可能密码错误 在contoller里会判定
          return info;
       }else {
          //这里会当作账号不存在处理
          return null;
       }

      /* User user = new User("shiro", "123456");
       if (user == null) {
          System.out.println("----");
           throw new AuthorizationException();
       }
       if(token != null) {
          System.out.println(token.getUsername());
       }
       SimpleAuthenticationInfo info = null;
       if (user.getName().equals(token.getUsername())) {
           info = new SimpleAuthenticationInfo(user.getName(), user.getPassword(), getName());
       }  */
//       System.out.println("2222");
//       return info;
   }
   public void clearCached() {
       PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
       super.clearCache(principals);
   }
}
