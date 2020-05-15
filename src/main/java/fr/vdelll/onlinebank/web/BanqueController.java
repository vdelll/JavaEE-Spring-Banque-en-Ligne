package fr.vdelll.onlinebank.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
