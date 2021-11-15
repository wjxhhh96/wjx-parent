package com.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * <b>TestController</b>
 * </p>
 *
 * @author Wjx
 * @Description: TODO
 * @since 2021/11/12
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {




    @GetMapping("/one")
    public String getOne(){
        return "one";
    }


    @GetMapping("/two")
    public String getTwo(){
        return "two";
    }




}
