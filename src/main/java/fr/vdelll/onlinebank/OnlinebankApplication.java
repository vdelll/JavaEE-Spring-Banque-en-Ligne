package fr.vdelll.onlinebank;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import fr.vdelll.onlinebank.dao.ClientRepository;
import fr.vdelll.onlinebank.dao.CompteRepository;
import fr.vdelll.onlinebank.dao.OperationRepository;
import fr.vdelll.onlinebank.entities.Client;
import fr.vdelll.onlinebank.entities.Compte;
import fr.vdelll.onlinebank.entities.CompteCourant;
import fr.vdelll.onlinebank.entities.CompteEpargne;
import fr.vdelll.onlinebank.entities.Operation;
import fr.vdelll.onlinebank.entities.Retrait;
import fr.vdelll.onlinebank.entities.Versement;
import fr.vdelll.onlinebank.metier.IBanqueMetier;

@SpringBootApplication
public class OnlinebankApplication implements CommandLineRunner {

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private CompteRepository compteRepository;
	
	@Autowired
	private OperationRepository operationRepository;
	
	@Autowired
	private IBanqueMetier banqueMetier;
	
	public static void main(String[] args) {
		SpringApplication.run(OnlinebankApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*Client c1 = clientRepository.save(new Client("jean", "jean@gmail.com"));
		Client c2 = clientRepository.save(new Client("paul", "paul@gmail.com"));
		
		Compte cp1 = compteRepository.save(new CompteCourant("c1", new Date(), 90000, c1, 6000));
		Compte cp2 = compteRepository.save(new CompteEpargne("c2", new Date(), 19000, c2, 5.5));
		
		operationRepository.save(new Retrait(new Date(), 400, cp1));
		operationRepository.save(new Retrait(new Date(), 600, cp1));
		operationRepository.save(new Retrait(new Date(), 1400, cp1));
		operationRepository.save(new Versement(new Date(), 900, cp1));
		
		operationRepository.save(new Retrait(new Date(), 700, cp2));
		operationRepository.save(new Retrait(new Date(), 40, cp2));
		operationRepository.save(new Versement(new Date(), 4200, cp2));
		operationRepository.save(new Versement(new Date(), 400, cp2));
		
		banqueMetier.verser("c1", 600); */
		 
	}

}
