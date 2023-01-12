package com.example.multithreading_all.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * module: 应用模块名称<br/>
 * <p>
 * description: 描述<br/>
 *
 * @author XiaoDong.Yang
 * @date: 2023/1/12 17:33
 */
@RestController
@Slf4j
public class FilterController {


    @RequestMapping("/api/show")
    public String showName(String userName,Model model){
        log.info("进入controller层了!!!"+userName);
        model.addAttribute("name",userName);
        return "index";
    }


}
