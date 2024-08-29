package dps.salary.web.model;

import java.util.HashMap;
import java.util.Map;

public class SalaryMemberModel {

    public Map<String, Double> salaryOfFamilyMembers;

    public SalaryMemberModel() {
        salaryOfFamilyMembers = new HashMap<>();
    }

    public void addYearSalary(String familyMemberName, double salaryForOneYear, String currencyOfSalary) {
        if (currencyOfSalary.equalsIgnoreCase("uah")) {
            double ZlToUah = 0.1149;
            double newSalaryForOneYear = salaryForOneYear * ZlToUah;
            salaryOfFamilyMembers.put(familyMemberName, newSalaryForOneYear);
        } else {
            salaryOfFamilyMembers.put(familyMemberName, salaryForOneYear);
        }
    }

    public String countMonthSalary() {
        StringBuilder result = new StringBuilder("\n- - - - - - - - - - - - - Average month salary - - - - - - - - - - - - -\n");
        for (Map.Entry<String, Double> entry : salaryOfFamilyMembers.entrySet()) {
            double salaryForYear = entry.getValue();
            double salaryForMonth = salaryForYear / 12;
            result.append("Family member: ").append(entry.getKey()).append("  ---  Monthly Average Salary: ").append(salaryForMonth).append(" zl\n");
        }
        return result.toString();
    }

    public String calculateSalaryOneMemberOneMonth() {
        double sumOfMonthSalaries = 0;
        double salaryForOneInMonth;
        StringBuilder result = new StringBuilder("\n- - - - - - - - - - - Monthly Salary For One Member - - - - - - - - - - -\n");
        for (Map.Entry<String, Double> entry : salaryOfFamilyMembers.entrySet()) {
            sumOfMonthSalaries += entry.getValue() / 12;
        }
        salaryForOneInMonth = sumOfMonthSalaries / salaryOfFamilyMembers.size();
        result.append("Monthly Salary For One Member: ").append(salaryForOneInMonth).append(" zl\n");
        if (salaryForOneInMonth < 1570.50) {
            result.append("\nYour Month Salary For One Member Is CORRECT Due To Requirements!");
        } else {
            result.append("\nYour Month Salary For One Member Is INCORRECT Due To Requirements\nYour Salary Must Be ")
                    .append(salaryForOneInMonth - 1570.51).append(" ZL less or more.");
        }
        return result.toString();
    }

    public String printSalaries() {
        StringBuilder result = new StringBuilder("\n- - - - - - - - - - - - - - Last year salary - - - - - - - - - - - - - -\n");
        for (Map.Entry<String, Double> salaryForMember : this.salaryOfFamilyMembers.entrySet()) {
            result.append("Family member: ").append(salaryForMember.getKey()).append("  ---  Salary for previous year: ").append(salaryForMember.getValue()).append(" zl\n");
        }
        return result.toString();
    }

}
