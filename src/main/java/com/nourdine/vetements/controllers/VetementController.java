package com.nourdine.vetements.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nourdine.vetements.entities.Boutique;
import com.nourdine.vetements.entities.Vetement;
import com.nourdine.vetements.service.VetementService;

import jakarta.validation.Valid;

@Controller
public class VetementController {
	@Autowired
	VetementService vetementService;
	
	@GetMapping("/accessDenied")
	public String error()
	{
	return "accessDenied";
	}


	@RequestMapping("/ListeVetements")
	public String listeVetement(ModelMap modelMap, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size) {

		Page<Vetement> vets = vetementService.getAllVetementsParPage(page, size);
		modelMap.addAttribute("vetements", vets);
		modelMap.addAttribute("pages", new int[vets.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		return "listeVetements";
	}

	@RequestMapping("/showCreate")
	public String showCreate(ModelMap modelMap) {

		List<Boutique> bouts = vetementService.getAllBoutiques();

		modelMap.addAttribute("vetement", new Vetement());
		modelMap.addAttribute("mode", "new");

		modelMap.addAttribute("boutiques", bouts);
		return "formVetement";
	}

	@RequestMapping("/saveVetement")
	public String saveProduit(@Valid Vetement vetement, BindingResult bindingResult,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size) {
		int currentPage;
		boolean isNew =false;
		if (bindingResult.hasErrors())
			return "formVetement";
		
		if (vetement.getIdVet()==null)
			isNew=true;
		
		vetementService.saveVetement(vetement);
		if (isNew) //ajout
		{
		Page<Vetement> vets = vetementService.getAllVetementsParPage(page, size);
		currentPage = vets.getTotalPages()-1;
		}
		else //modif
		currentPage=page;
		return ("redirect:/ListeVetements?page="+currentPage+"&size="+size);

	}

	@RequestMapping("/supprimerVetement")
	public String supprimerProduit(@RequestParam("id") Long id, ModelMap modelMap,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size) {
		vetementService.deleteVetementById(id);
		Page<Vetement> vets = vetementService.getAllVetementsParPage(page, size);
		modelMap.addAttribute("vetements", vets);
		modelMap.addAttribute("pages", new int[vets.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("size", size);
		return "listeVetements";
	}

	@RequestMapping("/modifierVetement")
	public String editerProduit(@RequestParam("id") Long id, ModelMap modelMap,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size) {
		Vetement v = vetementService.getVetement(id);
		List<Boutique> bouts = vetementService.getAllBoutiques();

		modelMap.addAttribute("vetement", v);
		modelMap.addAttribute("mode", "edit");

		modelMap.addAttribute("boutiques", bouts);

		modelMap.addAttribute("page", page);
		modelMap.addAttribute("size", size);

		return "formVetement";
	}

	@RequestMapping("/updateVetement")
	public String updateProduit(@ModelAttribute("produit") Vetement vetement, @RequestParam("date") String date,
			ModelMap modelMap) throws ParseException {
//conversion de la date
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateAchat = dateformat.parse(String.valueOf(date));
		vetement.setDateAchat(dateAchat);

		vetementService.updateVetement(vetement);
		List<Vetement> vets = vetementService.getAllVetements();
		modelMap.addAttribute("vetement", vets);
		return "listeVetements";
	}
}
