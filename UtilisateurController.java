package com.example.demo1.Controller;
import com.example.demo1.Model.Utilisateur;
import com.example.demo1.Reopsitory.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utilisateur")
public class UtilisateurController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @PostMapping("")
    public Utilisateur createUtilisateur(@RequestBody Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }


    @GetMapping("")
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }


    @GetMapping("/{id}")
    public Utilisateur getUtilisateurById(@PathVariable Long id) {
        return utilisateurRepository.findById(id).orElse(null);
    }


    @PutMapping("{id}")
    public Utilisateur updateUtilisateur(@PathVariable Long id, @RequestBody Utilisateur updatedUtilisateur) {
        Utilisateur existingUtilisateur = utilisateurRepository.findById(id).orElse(null);

        if (existingUtilisateur != null) {

            existingUtilisateur.setNom(updatedUtilisateur.getNom());
            existingUtilisateur.setPrenom(updatedUtilisateur.getPrenom());
            existingUtilisateur.setTelephone(updatedUtilisateur.getTelephone());

            return utilisateurRepository.save(existingUtilisateur);
        } else {
            return null;
        }
    }


    @DeleteMapping("/{id}")
    public void deleteUtilisateur(@PathVariable Long id) {
        utilisateurRepository.deleteById(id);
    }
}
