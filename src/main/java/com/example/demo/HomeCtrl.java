package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.naming.Binding;
import javax.validation.Valid;

@Controller
public class HomeCtrl {
    @Autowired
    TodoRepository todoRepository;

    // find all todo list and pass it to "list.html" as "todos" variable
    @RequestMapping("/")
    public String listTodo(Model model){
        model.addAttribute("todos", todoRepository.findAll());
        return "list";
    }


    // create an empty todo and pass it to "addTodo.html" as "todo" variable
    @GetMapping("/add")
    public String addTodo(Model model){
        model.addAttribute("todo", new Todo());
        return "addTodo";
    }

    // if result has any error then send it back to "addToDo.html" to display error message
    // otherwise save todo object in the todoRepository
    @PostMapping("/process")
    public String processTodo(@Valid Todo todo, BindingResult result){
        if (result.hasErrors()) {
            return "addTodo";
        }
        todoRepository.save(todo);
        return "redirect:/";
    }
}
