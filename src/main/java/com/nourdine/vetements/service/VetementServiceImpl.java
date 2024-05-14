package com.nourdine.vetements.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.nourdine.vetements.entities.Boutique;
import com.nourdine.vetements.entities.Vetement;
import com.nourdine.vetements.repos.VetementRepository;
import com.nourdine.vetements.repos.BoutiqueRepository;


@Service
public class VetementServiceImpl implements VetementService {
	@Autowired
	VetementRepository vetementRepository;
	
	@Autowired
	BoutiqueRepository boutiqueRepository;
	
	@Override
	public Vetement saveVetement(Vetement v) {
		
		return vetementRepository.save(v);
	}

	@Override
	public Vetement updateVetement(Vetement v) {
		return vetementRepository.save(v);

	}

	@Override
	public void deleteVetement(Vetement v) {
		vetementRepository.delete(v);
	}

	@Override
	public void deleteVetementById(Long id) {
		vetementRepository.deleteById(id);
		
	}

	@Override
	public Vetement getVetement(Long id) {
		return vetementRepository.findById(id).get();
	}

	@Override
	public List<Vetement> getAllVetements() {
		
		return vetementRepository.findAll();
	}

	@Override
	public Page<Vetement> getAllVetementsParPage(int page, int size) {
		return vetementRepository.findAll(PageRequest.of(page, size));
	}

	@Override
	public List<Vetement> findByMarqueVetement(String marque) {
		// TODO Auto-generated method stub
		return vetementRepository.findByMarqueVet(marque);
	}

	@Override
	public List<Vetement> findByMarqueVetementContains(String marque) {
		// TODO Auto-generated method stub
		return vetementRepository.findByMarqueVetContains(marque);
	}

	@Override
	public List<Vetement> findByMarquePrix(String marque, Double prix) {
		// TODO Auto-generated method stub
		return vetementRepository.findByMarquePrix(marque, prix);
	}

	@Override
	public List<Vetement> findByBoutique(Boutique boutique) {
		// TODO Auto-generated method stub
		return vetementRepository.findByBoutique(boutique);
	}

	@Override
	public List<Vetement> findByBoutiqueIdBou(Long id) {
		// TODO Auto-generated method stub
		return vetementRepository.findByBoutiqueIdBou(id);
	}

	@Override
	public List<Vetement> findByOrderByMarqueVetementAsc() {
		// TODO Auto-generated method stub
		return vetementRepository.findByOrderByMarqueVetAsc();
	}

	@Override
	public List<Vetement> trierVetementsMarquesPrix() {
		// TODO Auto-generated method stub
		return vetementRepository.trierVetementMarquePrix();
	}
	


	@Override
	public List<Boutique> getAllBoutiques() {
		
		return boutiqueRepository.findAll();
	}

}
