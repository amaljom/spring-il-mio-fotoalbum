package org.generation.italy.demo.service;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Commento;
import org.generation.italy.demo.repo.CommentoRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class CommentoService {
	
	@Autowired
	private CommentoRepository commentoRepository;
	
	public void save(Commento commento) {
		commentoRepository.save(commento);
	}
	
	public List<Commento> findAll() {
		return commentoRepository.findAll();
	}
	
	public Optional<Commento> getPromotionById(int id) {
		return commentoRepository.findById(id);
	}
	
	public void deletePromotionById(int id) {
		commentoRepository.deleteById(id);
	}
	/*
	@Transactional
	public List<Commento> findPizze() {
		
		List<Promozione> promos = promoRepo.findAll();
		
		for (Promozione promo : promos) {
			
			Hibernate.initialize(promo.getPizzas());
		}
		
		return promos;
	}
	*/
}
