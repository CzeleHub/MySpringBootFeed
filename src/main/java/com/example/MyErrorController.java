package com.example;

import org.springframework.boot.webmvc.error.ErrorController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, HttpServletResponse response, Model model) {
        Object errorStatusCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object errorMessage = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);

        if (errorStatusCode == null || errorMessage == null) {
            response.setStatus(500);
            model.addAttribute("error_status_code", "500");
            model.addAttribute("error_message", "Internal Server Error");
            return "error";
        }

        model.addAttribute("error_status_code", errorStatusCode);
        model.addAttribute("error_message", errorMessage);

        try {
            response.setStatus(Integer.parseInt(errorStatusCode.toString()));
        } catch (NumberFormatException e) {
            response.setStatus(500);
        }

        return "error";
    }
}
