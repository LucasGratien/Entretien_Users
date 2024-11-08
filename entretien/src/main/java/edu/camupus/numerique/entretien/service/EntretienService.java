package edu.camupus.numerique.entretien.service;
import edu.camupus.numerique.entretien.dto.Cars;
import edu.camupus.numerique.entretien.model.Entretien;
import edu.camupus.numerique.entretien.model.EntretienNotFound;
import edu.camupus.numerique.entretien.model.EntretienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EntretienService {

    private final EntretienRepository entretienRepository;
    private final RestTemplate restTemplate;
    @Autowired
    public EntretienService(EntretienRepository entretienRepository, RestTemplate restTemplate) {
        this.entretienRepository = entretienRepository;
        this.restTemplate = restTemplate;
    }
    public Cars getVehiclesData(int vehiclesId) {
        String url = "http://CarLocation/vehicles/" + vehiclesId;
        return restTemplate.getForObject(url, Cars.class);
    }


    public Optional<Entretien> findById(int id) {
        return entretienRepository.findById(id);
    }

    public List<Entretien> findAll() {
        return entretienRepository.findAll();
    }

    public Entretien save(Entretien entretien) {
        if (entretien.getIdVehicule() == 1) {
            calculateMotoIndisponibility(entretien);
        } else if (entretien.getIdVehicule() == 2 || entretien.getIdVehicule() == 3) {
            calculateCarVanIndisponibility(entretien);
        }
        return entretienRepository.save(entretien);
    }

    private void calculateMotoIndisponibility(Entretien entretien) {
        LocalDate dateFin = LocalDate.now().plusDays(1);
        entretien.setDateFinEntretien(dateFin.toString());
        entretien.setFactureEntretien(125);
        entretien.setTempsIndisponibilite(1);
    }

    private void calculateCarVanIndisponibility(Entretien entretien) {
        LocalDate dateFin = LocalDate.now().plusDays(3);
        entretien.setDateFinEntretien(dateFin.toString());
        entretien.setFactureEntretien(300);
        entretien.setTempsIndisponibilite(3);
    }

    public Entretien update(int id, Entretien entretien) {
        if (!entretienRepository.existsById(id)) {
            throw new EntretienNotFound();
        }
        entretien.setId(id);
        if (entretien.getIdVehicule() == 1) {
            calculateMotoIndisponibility(entretien);
        } else if (entretien.getIdVehicule() == 2 || entretien.getIdVehicule() == 3) {
            calculateCarVanIndisponibility(entretien);
        }
        return entretienRepository.save(entretien);
    }

    public void deleteById(int id) {
        entretienRepository.deleteById(id);
    }

    public boolean VehiculeOccupé(int vehicleId) {
        Optional<Entretien> entretien = entretienRepository.findById(vehicleId);
        if (entretien.isPresent()) {
            LocalDate now = LocalDate.now();
            LocalDate dateFinEntretien = LocalDate.parse(entretien.get().getDateFinEntretien());
            return !now.isBefore(dateFinEntretien);
        }
        return true;
    }
}
