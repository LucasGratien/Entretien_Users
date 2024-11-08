package edu.camupus.numerique.entretien.controller;
import edu.camupus.numerique.entretien.dto.Cars;
import edu.camupus.numerique.entretien.model.Entretien;
import edu.camupus.numerique.entretien.service.EntretienService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import edu.camupus.numerique.entretien.model.EntretienNotFound;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Entretien", description = "API entretien")
@RestController
@RequestMapping("/api/entretien")
public class EntretienController {
    private final EntretienService entretienService;
@Autowired
    public EntretienController(EntretienService entretienService) {
        this.entretienService = entretienService;
    }
    @GetMapping("/vehicles/{vehiclesId}")
    public Cars getCarsFromEntretien(@PathVariable int vehiclesId) {
        return entretienService.getVehiclesData(vehiclesId);
    }
    @Operation(summary = "Récupération de tous les entretien")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Opération réussie",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Entretien.class),
                            examples = @ExampleObject(
                                    name = "Exemple d'entretien",
                                    value = "[{\"id\": 1, \"id_vehicule\": \"1\", \"dateDebut\": \"2024.10.06\", \"dateFin\": \"2024.10.07\", \"typeEntretien\": \"complet\", \"Facture\": \"1250euro\"}]"
                            )
                    )),
            @ApiResponse(responseCode = "400", description = "Requête invalide",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Client non trouvé",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping
    public List<Entretien> getAllEntretien() {
        return entretienService.findAll();
    }
    @Operation(summary = "Récupère un entretien par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "entretien trouvé",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Entretien.class),
                            examples = @ExampleObject(
                                    value = "[{\"id\": 1, \"id_vehicule\": \"1\", \"dateDebut\": \"2024.10.06\", \"dateFin\": \"2024.10.07\", \"typeEntretien\": \"complet\", \"Facture\": \"1250euro\"}]"
                            ))),
            @ApiResponse(responseCode = "404", description = "Entretien non trouvé",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/{id}")
    public ResponseEntity<Entretien> getEntretienById(@PathVariable int id) {
        Entretien entretien = entretienService.findById(id).orElseThrow(EntretienNotFound::new);
        return ResponseEntity.ok(entretien);
    }
    @Operation(summary = "Ajoute un nouvel entretien")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Entretien créé avec succès",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Entretien.class),
                            examples = @ExampleObject(
                                    value = "[{\"id\": 1, \"id_vehicule\": \"1\", \"dateDebut\": \"2024.10.06\", \"dateFin\": \"2024.10.07\", \"typeEntretien\": \"complet\", \"Facture\": \"1250euro\"}]"
                            ))),
            @ApiResponse(responseCode = "400", description = "Requête invalide",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping
    public ResponseEntity<Entretien> createEntretien(@RequestBody Entretien entretien) {
    Entretien createdEntretien = entretienService.save(entretien);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEntretien);
    }
    @Operation(summary = "Met à jour un entretien existant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entretien mis à jour avec succès",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Entretien.class),
                            examples = @ExampleObject(
                                    value = "[{\"id\": 1, \"id_vehicule\": \"1\", \"dateDebut\": \"2024.10.06\", \"dateFin\": \"2024.10.07\", \"typeEntretien\": \"complet\", \"Facture\": \"1250euro\"}]"
                            ))),
            @ApiResponse(responseCode = "404", description = "Entretien non trouvé",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Requête invalide",
                    content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/{id}")
    public ResponseEntity<Entretien> updateEntretien(@PathVariable int id, @RequestBody Entretien entretien) {
        return ResponseEntity.ok(entretienService.update(id, entretien));
    }
    @Operation(summary = "Supprime un entretien par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Entretien supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Entretien non trouvé",
                    content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEntretien(@PathVariable int id) {
        entretienService.deleteById(id);
    }

}
