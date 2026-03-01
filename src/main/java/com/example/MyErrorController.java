package com.example;


import org.springframework.boot.webmvc.error.ErrorController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object errorStatusCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object errorMessage = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);

        if (errorStatusCode != null && errorMessage != null) {
            model.addAttribute("error_status_code", errorStatusCode);
            model.addAttribute("error_message", errorMessage);
        }

        return "error";
    }
}
