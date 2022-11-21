package seisma.ethan.restapi.model;

public class EmployeeOutput {
    private String name;
    private String payPeriod;
    private int grossIncome;
    private int incomeTax;
    private int netIncome;
    private int superAnnual;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPayPeriod() {
        return payPeriod;
    }

    public void setPayPeriod(String payPeriod) {
        this.payPeriod = payPeriod;
    }

    public int getGrossIncome() {
        return grossIncome;
    }

    public void setGrossIncome(int grossIncome) {
        this.grossIncome = grossIncome;
    }

    public int getIncomeTax() {
        return incomeTax;
    }

    public void setIncomeTax(int incomeTax) {
        this.incomeTax = incomeTax;
    }

    public int getNetIncome() {
        return netIncome;
    }

    public void setNetIncome(int netIncome) {
        this.netIncome = netIncome;
    }

    public int getSuperAnnual() {
        return superAnnual;
    }

    public void setSuperAnnual(int superAnnual) {
        this.superAnnual = superAnnual;
    }
}
