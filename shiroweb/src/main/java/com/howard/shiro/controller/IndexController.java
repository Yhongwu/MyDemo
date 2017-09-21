package com.howard.shiro.controller;

import java.util.List;

import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.howard.shiro.service.SysUsersService;
import com.howard.shiro.vo.SysUser;

@Controller
public class IndexController {

     @Autowired
     private SysUsersService userService;
     
//   @RequiresAuthentication  必须登陆 因为这里采用了混合方式 登录配置在xml里已经统一配置
     @RequiresPermissions(value={"user:list"})
     @RequestMapping("/members/list")
     public String test(Model model){
           System.out.println("用户列表...");
           List<SysUser> all = userService.getAll();
//           for (SysUser u : all) {
//        	   System.out.println(u);
//           }
           model.addAttribute("list", all);
           return "members/list";
     }
     @RequestMapping("/members/detail/{id}")
     @RequiresRoles(value={"member","admin"},logical= Logical.OR) //or表示或
     @RequiresPermissions(value={"user:list","user:detail"},logical=Logical.AND) //and表示and
     public String test2(@PathVariable Integer id){
           System.out.println("用户详情...");
           return "/members/user_detail";
     }
     
     /**
      * 注解方式可能抛出异常 使用spring的方式捕获处理
      */
//    @ExceptionHandler({UnauthorizedException.class})
    @ExceptionHandler({UnauthenticatedException.class})
    @ResponseStatus(value=HttpStatus.UNAUTHORIZED)
    public ModelAndView processUnauthenticatedException(UnauthenticatedException e) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", "未登录");
		mv.setViewName("error");;
		return mv;
    }
    @ExceptionHandler({UnauthorizedException.class})
    @ResponseStatus(value=HttpStatus.UNAUTHORIZED)
    public ModelAndView processUnauthorizedException(UnauthorizedException e) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg", "权限不够");
        mv.setViewName("error");;
        return mv;
    }


}
