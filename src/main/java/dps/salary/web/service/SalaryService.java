package dps.salary.web.service;

import dps.salary.web.model.SalaryMemberModel;
import org.springframework.stereotype.Service;

@Service
public class SalaryService {
    private final SalaryMemberModel salaryMember = new SalaryMemberModel();

    public void addYearSalary(String familyMemberName, double salaryForOneYear, String currencyOfSalary) {
        salaryMember.addYearSalary(familyMemberName, salaryForOneYear, currencyOfSalary);
    }

    public String countMonthSalary() {
        return salaryMember.countMonthSalary();
    }

    public String calculateSalaryOneMemberOneMonth() {
        return salaryMember.calculateSalaryOneMemberOneMonth();
    }
    public String printSalaries() {
        return salaryMember.printSalaries();
    }

}


