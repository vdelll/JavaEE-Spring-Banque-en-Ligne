package fr.vdelll.onlinebank.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.vdelll.onlinebank.entities.Compte;
import fr.vdelll.onlinebank.entities.Operation;
import fr.vdelll.onlinebank.metier.IBanqueMetier;

@Controller
public class BanqueController {

	@Autowired
	private IBanqueMetier banqueMetier;

	@RequestMapping("/operations")
	public String index() {
		return "comptes";
	}

	/**
	 * Récupération du compte s'il existe. Affichage des informations du compte.
	 * Affichage de la liste des opérations du compte.
	 * 
	 * @param model
	 * @param codeCpte
	 * @return
	 */
	@RequestMapping("/consultercompte")
	public String consulter(Model model, String codeCompte) {

		model.addAttribute("codeCompte", codeCompte);

		try {
			Compte cp = banqueMetier.consulterCompte(codeCompte);

			// Liste des opérations
			Page<Operation> pageOperations = banqueMetier.listOperation(codeCompte, 0, 4);
			model.addAttribute("listOperations", pageOperations.getContent());
			

			model.addAttribute("compte", cp);
		} catch (Exception e) {
			model.addAttribute("exception", e);
		}

		return "comptes";
	}

	/**
	 * Effectue les opérations de versement, de retrait ou de virement d'un compte.
	 * 
	 * @param model
	 * @param typeOperation
	 * @param codeCompte
	 * @param montant
	 * @param codeCompte2
	 * @return
	 */
	@RequestMapping("/saveOperation")
	public String saveOperation(Model model, String typeOperation, String codeCompte, double montant,
			String codeCompte2) {

		try {
			if (typeOperation.equals("VERS")) {
				banqueMetier.verser(codeCompte, montant);

			} else if (typeOperation.equals("RETR")) {
				banqueMetier.retirer(codeCompte, montant);

			} else if (typeOperation.equals("VIR")) {
				banqueMetier.virement(codeCompte, codeCompte2, montant);
			}
			
		} catch (Exception e) {
			model.addAttribute("erreur", e);
			return "redirect:/consultercompte?codeCompte="+codeCompte+"&erreur="+e.getMessage();
		}
		
		return "redirect:/consultercompte?codeCompte="+codeCompte;
	}
}
