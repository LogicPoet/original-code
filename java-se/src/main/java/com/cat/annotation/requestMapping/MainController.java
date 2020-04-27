package com.cat.annotation.requestMapping;

/**
 * <p>Title: MainController</p>
 * <p>description: </p>
 * <p>Company: </p>
 *
 * @author LogicPoet
 * @version V1.0
 * @date 2019/9/23 20:39
 **/
@Controller
@RequestMapping(value = "/main")
public class MainController {

    @RequestMapping(value = "/login")
    public void login(){
        System.out.println("login successfully");
    }

    @RequestMapping(value = "/logout")
    public void logout(){
        System.out.println("logout successfully");
    }
}
