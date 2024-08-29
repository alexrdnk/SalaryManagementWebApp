package dps.salary.web.controller;

import dps.salary.web.service.SalaryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SalaryControllerTests {

    @Mock
    private SalaryService salaryService;

    @Mock
    private Model model;

    @InjectMocks
    private SalaryController salaryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void index_shouldReturnIndexPage() {
        // Act
        String viewName = salaryController.index();

        // Assert
        assertEquals("index", viewName);
    }

    @Test
    void addYearSalary_shouldAddSalaryAndReturnIndex() {
        // Arrange
        String familyMemberName = "John";
        double salaryForOneYear = 50000.0;
        String currencyOfSalary = "zl";

        // Act
        String viewName = salaryController.addYearSalary(familyMemberName, salaryForOneYear, currencyOfSalary, model);

        // Assert
        verify(salaryService).addYearSalary(familyMemberName, salaryForOneYear, currencyOfSalary);
        verify(model).addAttribute("message", "Salary added successfully!");
        assertEquals("index", viewName);
    }

    @Test
    void countMonthSalary_shouldReturnResultPageWithResult() {
        // Arrange
        String mockResult = "Monthly salary result";
        when(salaryService.countMonthSalary()).thenReturn(mockResult);

        // Act
        String viewName = salaryController.countMonthSalary(model);

        // Assert
        verify(model).addAttribute("result", mockResult);
        assertEquals("result", viewName);
    }

    @Test
    void calculateSalaryOneMemberOneMonth_shouldReturnResultPageWithResult() {
        // Arrange
        String mockResult = "Monthly salary for one member";
        when(salaryService.calculateSalaryOneMemberOneMonth()).thenReturn(mockResult);

        // Act
        String viewName = salaryController.calculateSalaryOneMemberOneMonth(model);

        // Assert
        verify(model).addAttribute("result", mockResult);
        assertEquals("result", viewName);
    }

    @Test
    void printSalaries_shouldReturnResultPageWithResult() {
        // Arrange
        String mockResult = "Salaries list";
        when(salaryService.printSalaries()).thenReturn(mockResult);

        // Act
        String viewName = salaryController.printSalaries(model);

        // Assert
        verify(model).addAttribute("result", mockResult);
        assertEquals("result", viewName);
    }

}

