package fr.vdelll.onlinebank.metier;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.vdelll.onlinebank.dao.CompteRepository;
import fr.vdelll.onlinebank.dao.OperationRepository;
import fr.vdelll.onlinebank.entities.Compte;
import fr.vdelll.onlinebank.entities.CompteCourant;
import fr.vdelll.onlinebank.entities.Operation;
import fr.vdelll.onlinebank.entities.Retrait;
import fr.vdelll.onlinebank.entities.Versement;

@Service
@Transactional
public class BanqueMetierImpl implements IBanqueMetier {

	@Autowired
	private CompteRepository compteRepository;

	@Autowired
	private OperationRepository operationRepository;

	@Override
	public Compte consulterCompte(String codeCpte) {
		Compte cp = compteRepository.findById(codeCpte).orElse(null);
		if (cp == null)
			throw new RuntimeException("Compte introuvable");
		return cp;
	}

	/**
	 * Effectue un versement. Verse la somme sur le compte concerné.
	 */
	@Override
	public void verser(String codeCpte, double montant) {
		Compte cp = consulterCompte(codeCpte);
		Versement v = new Versement(new Date(), montant, cp);
		operationRepository.save(v);
		cp.setSolde(cp.getSolde() + montant);
		compteRepository.save(cp);
	}

	/**
	 * Retire de l'argent d'un compte. Si le compte est un compte courant,
	 * le decouvert autorisé du compte est ajouté au solde du compte. 
	 * Déclenche une exception si le solde du compte est insuffisant.
	 */
	@Override
	public void retirer(String codeCpte, double montant) {
		
		Compte cp = consulterCompte(codeCpte);
		double decouvertAutorise = 0;

		if (cp instanceof CompteCourant) {
			decouvertAutorise = ((CompteCourant) cp).getDecouvert();
		}

		if (cp.getSolde() + decouvertAutorise < montant) {
			throw new RuntimeException("Solde insuffisant");
		}
		
		Retrait r = new Retrait(new Date(), montant, cp);
		operationRepository.save(r);
		cp.setSolde(cp.getSolde() - montant);
		compteRepository.save(cp);

	}

	/**
	 * Retire un montant du compte 1 pour le verser sur le compte 2.
	 */
	@Override
	public void virement(String codeCpte1, String codeCpte2, double montant) {
		if(codeCpte1.equals(codeCpte2)) {
			throw new RuntimeException("Impossible de virer sur le compte courant");
		}
		retirer(codeCpte1, montant);
		verser(codeCpte2, montant);
	}

	@Override
	public Page<Operation> listOperation(String codeCpte, int page, int size) {

		return operationRepository.listOperation(codeCpte, PageRequest.of(page, size));
		
	}

}
