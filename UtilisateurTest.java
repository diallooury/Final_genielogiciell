package com.example.demo1;

import com.example.demo1.Controller.UtilisateurController;
import com.example.demo1.Model.Utilisateur;
//import com.example.demo1.Repository.UtilisateurRepository;
import com.example.demo1.Reopsitory.UtilisateurRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class UtilisateurTest {

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @InjectMocks
    private UtilisateurController utilisateurController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllUtilisateurs() {
        // Créez une liste factice d'utilisateurs pour simuler la réponse de la base de données
        List<Utilisateur> utilisateurs = new ArrayList<>();
        utilisateurs.add(new Utilisateur("John", "Doe", "123-456-7890"));
        utilisateurs.add(new Utilisateur( "Jane", "Smith", "987-654-3210"));

        // Configurez le comportement du repository mock pour retourner cette liste
        when(utilisateurRepository.findAll()).thenReturn(utilisateurs);

        // Appelez la méthode du contrôleur pour obtenir la liste d'utilisateurs
        List<Utilisateur> result = utilisateurController.getAllUtilisateurs();

        // Vérifiez si la liste retournée par le contrôleur correspond à celle simulée
        assertEquals(utilisateurs, result);
    }

    @Test
    public void testGetUtilisateurById() {
        // Créez un utilisateur factice pour simuler la réponse de la base de données
        Utilisateur utilisateur = new Utilisateur("John", "Doe", "123-456-7890");

        // Configurez le comportement du repository mock pour retourner cet utilisateur
        when(utilisateurRepository.findById(1L)).thenReturn(Optional.of(utilisateur));

        // Appelez la méthode du contrôleur pour obtenir l'utilisateur par ID
        Utilisateur result = utilisateurController.getUtilisateurById(1L);

        // Vérifiez si l'utilisateur retourné par le contrôleur correspond à celui simulé
        assertEquals(utilisateur, result);
    }

    @Test
    public void testCreateUtilisateur() {
        // Créez un utilisateur factice à ajouter
        Utilisateur nouvelUtilisateur = new Utilisateur("Alice", "Johnson", "555-123-4567");

        // Configurez le comportement du repository mock pour retourner l'utilisateur après l'enregistrement
        when(utilisateurRepository.save(any(Utilisateur.class))).thenReturn(nouvelUtilisateur);

        // Appelez la méthode du contrôleur pour créer un nouvel utilisateur
        Utilisateur result = utilisateurController.createUtilisateur(nouvelUtilisateur);

        // Vérifiez si l'utilisateur retourné par le contrôleur correspond à celui simulé
        assertEquals(nouvelUtilisateur, result);
    }

    // Vous pouvez ajouter d'autres tests pour d'autres méthodes du contrôleur si nécessaire
}
