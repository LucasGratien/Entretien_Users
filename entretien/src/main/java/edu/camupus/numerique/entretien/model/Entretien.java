package edu.camupus.numerique.entretien.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
public class Entretien {
    @Id
    @GeneratedValue
    private int id;
    private int idVehicule;
    private String dateDebutEntretien;
   private String dateFinEntretien;
    private String typeEntretien;
    private int factureEntretien;
    private int tempsIndisponibilite;
}
