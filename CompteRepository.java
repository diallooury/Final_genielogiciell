package com.example.demo1.Reopsitory;

import com.example.demo1.Model.Comptes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompteRepository extends JpaRepository<Comptes, Long> {


    //  rechercher un compte par son nom
    //Comptes findByNom(String nom);
}
