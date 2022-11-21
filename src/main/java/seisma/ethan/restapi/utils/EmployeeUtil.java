package seisma.ethan.restapi.utils;

import seisma.ethan.restapi.model.Employee;
import seisma.ethan.restapi.model.EmployeeOutput;

public class EmployeeUtil {
    public int incomeTax(int annualSalary) {
        if (annualSalary <= 18200) {
            return 0;
        }

        if (18201 <= annualSalary && annualSalary <= 37000) {
            return (int) (Math.round((annualSalary - 18200) * 0.19)/12);
        }

        if (37001 <= annualSalary && annualSalary <= 87000) {
            return (int) (Math.round((3572 + (annualSalary - 37000) * 0.325)/12));
        }

        if (87001 <= annualSalary && annualSalary <= 180000) {
            return (int) (Math.round(19822 + ((annualSalary - 87000) * 0.37)/12));
        }
        return (int) (Math.round(annualSalary * 0.45)/12);
    }

    public int grossIncome(int annualSalary) {
        return (int) Math.floor(annualSalary / 12);
    }

    public int netIncome(int annualSalary) {
        return grossIncome(annualSalary) - incomeTax(annualSalary);
    }

    public int superAnnual(int annualSalary, float superRate) {
        return (int) Math.floor((grossIncome(annualSalary) * superRate) / 100);
    }

    public EmployeeOutput calculateEmployeeDetail(Employee employee) {
        EmployeeOutput eo = new EmployeeOutput();
        eo.setName(employee.getFirstName() + " " + employee.getLastName());
        eo.setGrossIncome(grossIncome(employee.getAnnualSalary()));
        eo.setIncomeTax(incomeTax(employee.getAnnualSalary()));
        eo.setNetIncome(netIncome(employee.getAnnualSalary()));
        eo.setSuperAnnual(superAnnual(employee.getAnnualSalary(), employee.getSuperRate()));
        eo.setPayPeriod(employee.getPaymentStartDate());
        return eo;
    }
}
