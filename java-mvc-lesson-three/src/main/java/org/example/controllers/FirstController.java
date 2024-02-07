package org.example.controllers;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/number")
public class FirstController {
    @GetMapping("/hello")
    public String helloMethod(){
        return "First_page";
    }

    @GetMapping("/first")
    public String firstMethod(){
        return "first";
    }

    @GetMapping("/fourth")
    public String fourthMethod(HttpServletRequest request, Model model){
        String line = request.getParameter("line");
        model.addAttribute("line", line);
        return "number-page/fourth";
    }

    @GetMapping("/fifth")
    public String fifthMethod(@RequestParam(value = "line", required = false) String line, Model model){
        model.addAttribute("line", line);
        return "number-page/fourth";
    }
}
