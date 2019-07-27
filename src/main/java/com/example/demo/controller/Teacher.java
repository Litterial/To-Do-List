package com.example.demo.controller;

import java.util.ArrayList; //imports ArrayList
import org.springframework.stereotype.Controller; //import controller
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping; //import requestMapping; receives request from the browser
import org.springframework.web.bind.annotation.ResponseBody; //import ResponseBody; returns response from browser

import javax.xml.ws.RequestWrapper;

@Controller //controller
@RequestMapping("") //used to map to index
public class Teacher {
    @RequestMapping(value="") //used to map to directory in 'index'
    //@ResponseBody // used to send text to the body
    public String index (Model model)
    {
        ArrayList<String> name = new ArrayList<>();
        name.add("Erin");
        name.add("Kevin");
        name.add("Kenn");
        name.add("Meka");
        name.add("Pam");
        name.add("Virgina");

        //attributes to be read on the browser
        model.addAttribute("teacher",name);
        model.addAttribute("title","List of CodeCrew staff");

        return "index";

    }

}
