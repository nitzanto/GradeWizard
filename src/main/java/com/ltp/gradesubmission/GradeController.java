package com.ltp.gradesubmission;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // Handles Get Requests (All of the web requests)
public class GradeController {

    List<Grade> studentsGrades = new ArrayList<>();

    @GetMapping("/grades") // Handler which responds to the GET request
    public String getGrades(Model model) {

        model.addAttribute("grades", studentsGrades); // Storing the data in the model
        return "grades"; // Goes to the templates folder to get the HTML file
    }

    @GetMapping("/")
    public String getForm(Model model, @RequestParam(required = false) String id) {

        int index = getGradeIndex(id);

        model.addAttribute("grade", index == Constants.NOT_FOUND ? new Grade() : studentsGrades.get(index));
        return "form";
    }

    @PostMapping("/handleSubmit")
    public String submmitForm(Grade grade) {
        int index = getGradeIndex(grade.getId());

        if (index == Constants.NOT_FOUND)
            studentsGrades.add(grade); // If the object of student doesn't exist
        else
            studentsGrades.set(index, grade); // Object already exists. Therefore it updates it

        return "redirect:/grades"; // After user submits the form, GET requests to GRADES
    }

    public int getGradeIndex(String id) {

        for (int i = 0; i < studentsGrades.size(); i++)
            if (studentsGrades.get(i).getId().equals(id))
                return i;

        return Constants.NOT_FOUND;
    }

}
