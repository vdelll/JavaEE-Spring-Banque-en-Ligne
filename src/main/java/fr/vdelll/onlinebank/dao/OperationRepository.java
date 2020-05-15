package fr.vdelll.onlinebank.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.vdelll.onlinebank.entities.Operation;

public interface OperationRepository extends JpaRepository<Operation, Long>{

}
