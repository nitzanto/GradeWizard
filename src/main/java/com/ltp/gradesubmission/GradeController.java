package com.ltp.gradesubmission;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // Handles Get Requests (All of the web requests)
public class GradeController {

    @GetMapping("/grades") // Handler which responds to the GET request
    public String getGrades(Model model) {
        Grade grade = new Grade("Harry", "Potions", "C-");
        model.addAttribute("grade", grade); // Storing the data in the model
        return "grades"; // Goes to the templates folder to get the HTML file
    }

}
