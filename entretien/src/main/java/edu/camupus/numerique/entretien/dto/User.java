package edu.camupus.numerique.entretien.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor
public class User {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String numLicense;
    private LocalDate dateLicense;
}
