package com.example.demo.controller;
import java.util.ArrayList;
import com.example.demo.models.Data.ToDoDAO;
import com.example.demo.models.Importance;
import com.example.demo.models.ToDo;
import com.example.demo.models.Urgency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; //import controller
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller //controller
@RequestMapping("") //used to map to index
public class ToDoController {
    @Autowired // Framework gives an instance of class CheeseDao and populate field
    private ToDoDAO todo; //Instance of interface (crud repository)
    @RequestMapping(value="") //used to map to directory in 'index'
    //@ResponseBody // used to send text to the body
    public String index (Model model)
    {

        //attributes to be read on the browser
        model.addAttribute("task", todo.findAll());
        model.addAttribute("title","List of task");

        return "index";
    }

    @RequestMapping(value="addTask",method= RequestMethod.GET) // get request takes user to page with form
    public String taskForm(Model model)
    {
        model.addAttribute("title","Add new task");
        model.addAttribute(new ToDo());
        model.addAttribute("importance", Importance.values());
        model.addAttribute("urgency", Urgency.values());
        return "addTask";
    }

    @RequestMapping(value="addTask",method= RequestMethod.POST) //when the user submits the form, the staff member is add to the array and is redirect to the index
    public String submitForm(@ModelAttribute @Valid ToDo new_task, Model model, Errors err)
    {
        if (err.hasErrors())
        {
            model.addAttribute("title","Add new task");
            return "addTask";
        }
        todo.save(new_task); //method in CrudRepo that allows the form to be saved
        return "redirect:";
    }

    @RequestMapping(value="updateTask/{id}", method=RequestMethod.GET)
    public String updateForm(Model model, @PathVariable int id, ArrayList<Integer> ids)
    {
        model.addAttribute(new ToDo());
        model.addAttribute("title","Update task");
        model.addAttribute("importance", Importance.values());
        model.addAttribute("urgency", Urgency.values());
        if (todo.existsById(id)) // checks to see if the id exist in the database
        {

            ids.add(id);
            model.addAttribute("task",todo.findAllById(ids));

           return "updateTask";
       }
       else
        {
            System.out.println("No ID");
           return "noID";
        }

    }




}
