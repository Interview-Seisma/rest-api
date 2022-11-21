# Introduction

This is Seisma's interview exercise that working in calculate the employee monthly pay slip

---
# Project Structure

### Controller
Create POST endpoint for employee calculation
### Model
### Utils
Have some functions that handle the details calculation of Gross Income, Income Tax, Net Income, SuperAnnual.
### Testing
Junit test in RestApiApplicationTests

### API

POST https://seisma-backend.herokuapp.com/employee/calculate
```
[
    {
        "id": 123,
        "firstName": "Ethan",
        "lastName": "Nguyen",
        "annualSalary": 60050,
        "superRate": 9,
        "paymentStartDate": "01 March - 31 March"
    }
]
```

Result

```
[
    {
        "name": "Ethan Nguyen",
        "payPeriod": "01 March - 31 March",
        "grossIncome": 5004,
        "incomeTax": 922,
        "netIncome": 4082,
        "superAnnual": 450
    }
]
```

---
# Deployment
Auto deploy on Heroku from Github
