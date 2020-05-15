package fr.vdelll.onlinebank.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.vdelll.onlinebank.entities.Compte;

public interface CompteRepository extends JpaRepository<Compte, String> {

}
