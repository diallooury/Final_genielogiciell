package com.example.demo1;

import ServiceCompte.UtilisateurService;
import com.example.demo1.Model.Utilisateur;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilisateurStepDefinitions {

    @Autowired
    private UtilisateurService utilisateurService; // Assurez-vous d'avoir un service Utilisateur approprié

    private ResponseEntity<String> response;

    private Utilisateur utilisateur;

    @Given("que je suis sur la page de création d'utilisateur")
    public void queJeSuisSurLaPageDeCreationDUtilisateur() {
        // Vous pouvez implémenter ici le code pour accéder à la page de création d'utilisateur dans votre application
    }

    @When("je saisis le nom {string}")
    public void jeSaisisLeNom(String nom) {
        utilisateur = new Utilisateur();
        utilisateur.setNom(nom);
    }

    @And("je saisis le prénom {string}")
    public void jeSaisisLePrenom(String prenom) {
        utilisateur.setPrenom(prenom);
    }

    @And("je saisis le téléphone {string}")
    public void jeSaisisLeTelephone(String telephone) {
        utilisateur.setTelephone(telephone);
    }

    @And("je clique sur le bouton de création")
    public void jeCliqueSurLeBoutonDeCreation() {
        response = utilisateurService.creerUtilisateur(utilisateur); // Appel à votre service pour créer l'utilisateur
    }

    @Then("je devrais voir un message de confirmation {string}")
    public void jeDevraisVoirUnMessageDeConfirmation(String messageAttendu) {
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(messageAttendu, response.getBody());
    }
}
