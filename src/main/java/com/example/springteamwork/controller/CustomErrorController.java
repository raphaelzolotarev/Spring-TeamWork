package com.example.springteamwork.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                model.addAttribute("statusCode", statusCode);
                model.addAttribute("errorMessage", "The page you are looking for might have been removed, had its name changed, or is temporarily unavailable.");
                return "error";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                model.addAttribute("statusCode", statusCode);
                model.addAttribute("errorMessage", "An unexpected error occurred. Please try again later.");
                return "error";
            }
        }

        model.addAttribute("statusCode", status);
        model.addAttribute("errorMessage", "An unexpected error occurred.");
        return "error";
    }

}
