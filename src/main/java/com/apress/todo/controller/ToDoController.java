package com.apress.todo.controller;
import com.apress.todo.domain.ToDo;
import com.apress.todo.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class ToDoController {
    private ToDoRepository repository;

    @Autowired
    public ToDoController(ToDoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ModelAndView index(ModelAndView modelAndView, HttpServletRequest request){
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/toDos", method = {RequestMethod.GET},
        produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE,
                MediaType.TEXT_XML_VALUE })
    public ResponseEntity<Iterable<ToDo>> getToDos(@RequestHeader HttpHeaders httpHeaders){
        return new ResponseEntity<Iterable<ToDo>>(this.repository.findAll(),httpHeaders, HttpStatus.OK);

    }
}
