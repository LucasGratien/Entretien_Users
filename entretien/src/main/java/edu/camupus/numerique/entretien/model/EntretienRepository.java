package edu.camupus.numerique.entretien.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EntretienRepository extends CrudRepository<Entretien, Integer> {
    List<Entretien> findAll();
}
