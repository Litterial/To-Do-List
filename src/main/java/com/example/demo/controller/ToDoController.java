package com.example.demo.controller;

import java.util.ArrayList; //imports ArrayList
import org.springframework.stereotype.Controller; //import controller
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping; //import requestMapping; receives request from the browser
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody; //import ResponseBody; returns response from browser

import javax.xml.ws.RequestWrapper;

@Controller //controller
@RequestMapping("") //used to map to index
public class ToDoController {
    private static ArrayList<String> name = new ArrayList<>();
    @RequestMapping(value="") //used to map to directory in 'index'
    //@ResponseBody // used to send text to the body
    public String index (Model model)
    {
//        name.add("Erin");
//        name.add("Kevin");
//        name.add("Kenn");
//        name.add("Meka");
//        name.add("Pam");
//        name.add("Virgina");

        //attributes to be read on the browser
        model.addAttribute("teacher",name);
        model.addAttribute("title","List of CodeCrew staff");

        return "index";

    }

    @RequestMapping(value="addStaff",method= RequestMethod.GET) // get request takes user to page with form
    public String staffForm(Model model)
    {
        model.addAttribute("title","Add staff");
        return "addStaff";
    }

    @RequestMapping(value="addStaff",method= RequestMethod.POST) //when the user submits the form, the staff member is add to the array and is redirect to the index
    public String submitForm( @RequestParam String staff)
    {
        name.add(staff);
        return "redirect:";
    }


}
