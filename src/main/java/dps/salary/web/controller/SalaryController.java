package dps.salary.web.controller;

import dps.salary.web.service.SalaryService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SalaryController {

    private final SalaryService salaryService;

    public SalaryController(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

    @GetMapping("/")
    public String index() {
        return "index";  // Return the main page (index.html)
    }

    @PostMapping("/addYearSalary")
    public String addYearSalary(@RequestParam String familyMemberName, @RequestParam double salaryForOneYear, @RequestParam String currencyOfSalary, Model model) {
        salaryService.addYearSalary(familyMemberName, salaryForOneYear, currencyOfSalary);
        model.addAttribute("message", "Salary added successfully!");
        return "index";  // Redirect back to the main page
    }

    @GetMapping("/countMonthSalary")
    public String countMonthSalary(Model model) {
        String result = salaryService.countMonthSalary();
        model.addAttribute("result", result);
        return "result";  // Return a page showing the result
    }

    @GetMapping("/calculateSalaryOneMemberOneMonth")
    public String calculateSalaryOneMemberOneMonth(Model model) {
        String result = salaryService.calculateSalaryOneMemberOneMonth();
        model.addAttribute("result", result);
        return "result";  // Return a page showing the result
    }

    @GetMapping("/printSalaries")
    public String printSalaries(Model model) {
        String result = salaryService.printSalaries();
        model.addAttribute("result", result);
        return "result";  // Return a page showing the result
    }
}


