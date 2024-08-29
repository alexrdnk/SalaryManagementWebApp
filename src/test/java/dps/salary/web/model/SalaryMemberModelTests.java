package dps.salary.web.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SalaryMemberModelTests {

    private SalaryMemberModel salaryMemberModel;

    @BeforeEach
    void setUp() {
        salaryMemberModel = new SalaryMemberModel();
    }

    @Test
    void testAddYearSalary() {
        salaryMemberModel.addYearSalary("John", 12000, "zl");
        assertEquals(12000, salaryMemberModel.salaryOfFamilyMembers.get("John"));

        salaryMemberModel.addYearSalary("Jane", 100000, "uah");
        // 100000 UAH to ZL conversion with rate 0.1149
        assertEquals(100000 * 0.1149, salaryMemberModel.salaryOfFamilyMembers.get("Jane"));
    }

    @Test
    void testCountMonthSalary() {
        salaryMemberModel.addYearSalary("John", 12000, "zl");
        String result = salaryMemberModel.countMonthSalary();
        assertTrue(result.contains("Monthly Average Salary: 1000.0 zl"));
    }

    @Test
    void testCalculateSalaryOneMemberOneMonth() {
        salaryMemberModel.addYearSalary("John", 12000, "zl");
        salaryMemberModel.addYearSalary("Jane", 24000, "zl");

        String result = salaryMemberModel.calculateSalaryOneMemberOneMonth();
        assertTrue(result.contains("Monthly Salary For One Member: 1500.0 zl"));
    }

    @Test
    void testPrintSalaries() {
        salaryMemberModel.addYearSalary("John", 12000, "zl");
        salaryMemberModel.addYearSalary("Jane", 24000, "zl");

        String result = salaryMemberModel.printSalaries();
        assertTrue(result.contains("Family member: John  ---  Salary for previous year: 12000.0 zl"));
        assertTrue(result.contains("Family member: Jane  ---  Salary for previous year: 24000.0 zl"));
    }

    @Test
    void addYearSalary_shouldConvertUahToZlAndStore() {
        // Arrange
        String familyMemberName = "John";
        double salaryForOneYearInUah = 100000;
        String currency = "uah";
        double expectedSalaryInZl = salaryForOneYearInUah * 0.1149;

        // Act
        salaryMemberModel.addYearSalary(familyMemberName, salaryForOneYearInUah, currency);

        // Assert
        assertEquals(expectedSalaryInZl, salaryMemberModel.salaryOfFamilyMembers.get(familyMemberName));
    }

    @Test
    void addYearSalary_shouldStoreSalaryInZl() {
        // Arrange
        String familyMemberName = "Jane";
        double salaryForOneYearInZl = 50000;
        String currency = "zl";

        // Act
        salaryMemberModel.addYearSalary(familyMemberName, salaryForOneYearInZl, currency);

        // Assert
        assertEquals(salaryForOneYearInZl, salaryMemberModel.salaryOfFamilyMembers.get(familyMemberName));
    }

    @Test
    void countMonthSalary_shouldReturnCorrectMonthlySalaries() {
        // Arrange
        salaryMemberModel.addYearSalary("John", 120000, "zl");
        salaryMemberModel.addYearSalary("Jane", 60000, "zl");

        // Act
        String result = salaryMemberModel.countMonthSalary();

        // Assert
        String expectedResult = """

                - - - - - - - - - - - - - Average month salary - - - - - - - - - - - - -
                Family member: John  ---  Monthly Average Salary: 10000.0 zl
                Family member: Jane  ---  Monthly Average Salary: 5000.0 zl
                """;
        assertEquals(expectedResult, result);
    }

    @Test
    void calculateSalaryOneMemberOneMonth_shouldReturnCorrectCalculation() {
        // Arrange
        salaryMemberModel.addYearSalary("John", 120000, "zl");
        salaryMemberModel.addYearSalary("Jane", 60000, "zl");

        // Act
        String result = salaryMemberModel.calculateSalaryOneMemberOneMonth();

        // Assert
        String expectedResult = """

                - - - - - - - - - - - Monthly Salary For One Member - - - - - - - - - - -
                Monthly Salary For One Member: 7500.0 zl

                Your Month Salary For One Member Is INCORRECT Due To Requirements
                Your Salary Must Be 5929.49 ZL less or more.""";
        assertEquals(expectedResult, result);
    }


}

