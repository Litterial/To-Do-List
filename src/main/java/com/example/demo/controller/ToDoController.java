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

import javax.servlet.http.HttpServletRequest;
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

        //attributes to be read on the browser via key-value
        model.addAttribute("task", todo.findAll()); //findall() searches for all instances of the ToDoDAO interface
        model.addAttribute("title","List of task");

        return "index"; // sends information to the index page
    }

    @RequestMapping(value="addTask",method= RequestMethod.GET) // get request takes user to page with form
    public String taskForm(Model model)
    {
        model.addAttribute("title","Add new task");
        model.addAttribute(new ToDo());
        model.addAttribute("importance", Importance.values()); //returns the values of the Enum Importance
        model.addAttribute("urgency", Urgency.values()); // returns the values of the Enum Urgency
        return "addTask"; //sends info to the add task page
    }

    @RequestMapping(value="addTask",method= RequestMethod.POST) //when the user submits the form, the staff member is add to the array and is redirect to the index
    // @ModelAttribute indicates an argument should be retrieved from the model @Valid checks for validation
    public String submitForm(@ModelAttribute @Valid ToDo new_task, Model model, Errors err)
    {
        if (err.hasErrors()) //if errors return to the add task page
        {
            model.addAttribute("title","Add new task");
            return "addTask";
        }
        todo.save(new_task); //method in CrudRepo that allows the 'entity' to be saved from the frorm
        return "redirect:"; //redirects to the parent directory (index)
    }

    @RequestMapping(value="updateTask/{id}", method=RequestMethod.GET)
    //@PathVariable grabs data from uri
    public String updateForm(Model model, @PathVariable int id, ArrayList<Integer> ids)
    {
        model.addAttribute(new ToDo());
        model.addAttribute("title","Update task");
        model.addAttribute("importance", Importance.values()); //grabs values from enum Importance
        model.addAttribute("urgency", Urgency.values()); // grabs values from enum Urgency
        if (todo.existsById(id)) // checks to see if the id exist in the database
        {

            ids.add(id); // adds id in the array list
            model.addAttribute("task",todo.findAllById(ids)); //finds all instances of todo with the given id

           return "updateTask";
       }
       else // if the id does not exist,
        {
            System.out.println("No ID");
           return "noID";
        }

    }

    @RequestMapping(value="updateTask/{id}",method=RequestMethod.POST)
    // provides request information for HTTP servlets
    public String submitUpdate (@ModelAttribute @Valid ToDo update_task, @PathVariable int id, Model model, HttpServletRequest request, Errors err )
    {
        if (err.hasErrors())
        {
            System.out.println("Error");
            model.addAttribute("title","Update Task");
            return "updateTask";
        }
        else
        {
            ToDo oldInstance=todo.findById(id).get(); //gets the instance of the object based off the id
            System.out.println(request.getParameter("task"));
            oldInstance.setTask(request.getParameter("task"));//gets value "task" from the body of the form
            System.out.println(request.getParameter("importance"));
            //in this example since Important and Urgent are enums, they accept enum values
            //Enum.valueOf() accepts a string and converts it to an enum
            //gets value "importance" from the body of the form
            oldInstance.setImportant(Importance.valueOf(request.getParameter("importance")));
            oldInstance.setUrgent(Urgency.valueOf(request.getParameter("urgency")));
            todo.save(oldInstance); //saves old instance


            return "redirect:../"; //redirects back two directories (index)
        }
    }
    @RequestMapping(value="delete/{id}",method = RequestMethod.GET)
    public String deleteTask(Model model,@PathVariable int id) {
        model.addAttribute("title", "Task not found");
        if (todo.existsById(id)) {
            ToDo currentInstance = todo.findById(id).get();
            String taskName = currentInstance.getTask(); // callback from ToDo
            System.out.println(taskName);
            model.addAttribute("title", "Deleting task");
            model.addAttribute("delete", "Are you sure you want to delete the task:" + taskName);
            return "delete";
        } else {
            System.out.println("noID");
            return "noID";
        }
    }
        @RequestMapping(value="delete/{id}",method = RequestMethod.POST)
        public String confirmDelete(@PathVariable int id)
        {
            if(todo.existsById(id))
            {
                todo.deleteById(id); //deletes entity from database
                return "redirect:../";
            }
            else
            {
                return "noID";
            }

    }



}
