package seisma.ethan.restapi.utils;

import seisma.ethan.restapi.model.Employee;
import seisma.ethan.restapi.model.EmployeeOutput;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class EmployeeUtil {
    private String TAX_INCOME_URL = "https://vietrek.s3.ap-southeast-1.amazonaws.com/tax_income.json";

    JsonObject taxIncomeJson;
    JsonArray taxBracket;

    public EmployeeUtil (){
        try {
            taxIncomeJson = getTaxIncome();
            taxBracket = taxIncomeJson.getAsJsonArray("taxIncome");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public int incomeTax(int annualSalary) {
        int taxBracketIndex = findTaxBracketIndex(annualSalary);
        if (taxBracketIndex == -1) {
            return -1;
        }

        JsonObject t = taxBracket.get(taxBracketIndex).getAsJsonObject();

        int low = Integer.parseInt(t.get("low").toString());
        int tax = Integer.parseInt(t.get("tax").toString());
        float accumulated = Float.parseFloat(t.get("accumulated").toString());

        return (int) (Math.round((tax + (annualSalary - low) * accumulated)/12));
    }

    private int findTaxBracketIndex(int annualSalary) {
        for (int i = 0; i < taxBracket.size(); i++) {
            int low = Integer.parseInt(taxBracket.get(i).getAsJsonObject().get("low").toString());
            int high = Integer.parseInt(taxBracket.get(i).getAsJsonObject().get("high").toString());
            if(low <= annualSalary && annualSalary <= high) {
                return i;
            }
        }
        return -1;
    }

    private JsonObject getTaxIncome() throws IOException {
        String data = null;
        StringBuilder responseData = new StringBuilder();
        JsonObject jsonObject = null;

        URL url = null;
        url = new URL(TAX_INCOME_URL);

        HttpURLConnection con
                = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();

        System.out.println(
                "\nSending 'GET' request to URL : " + url);

        try (BufferedReader in
                     = new BufferedReader(new InputStreamReader(
                con.getInputStream()))) {

            String line;

            while ((line = in.readLine()) != null) {
                responseData.append(line);
            }

            jsonObject = new Gson().fromJson(
                    responseData.toString(), JsonObject.class);
        }
        return jsonObject;
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
