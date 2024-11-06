package edu.camupus.numerique.entretien.model;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntretienNotFound extends RuntimeException {
    public EntretienNotFound() {super ("ENTRETIEN NOT FOUND");}
}
