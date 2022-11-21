package seisma.ethan.restapi.model;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private int annualSalary;
    private float superRate;
    private String paymentStartDate;
}
