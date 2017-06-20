package com.mplatform.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mplatform.domain.UserInfo;

@RestController
public class TestController {
    @RequestMapping("/users1")
    public String index() {
        return "1,2,3,4";
    }
    @RequestMapping("/users2")
    public UserInfo index2() {
       UserInfo user=new UserInfo();
       user.setUserName("gyz");
       user.setUserTel("110");
       return user;
    }
}
