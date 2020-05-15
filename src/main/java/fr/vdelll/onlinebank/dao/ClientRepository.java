package fr.vdelll.onlinebank.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.vdelll.onlinebank.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
