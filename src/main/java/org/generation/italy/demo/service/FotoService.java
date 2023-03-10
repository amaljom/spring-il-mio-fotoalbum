package org.generation.italy.demo.service;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Commento;
import org.generation.italy.demo.pojo.Foto;
import org.generation.italy.demo.repo.FotoRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class FotoService {
	
	@Autowired
	private FotoRepository fotoRepo;
	
	
	public Foto save(Foto foto) {
		
		return fotoRepo.save(foto);
	}
	
	public List<Foto> findAll() {
		
		return fotoRepo.findAll();
	}
	public List<Foto> findByName(String titolo) {
		
		return fotoRepo.findByTitoloContainingOrTagContainingIgnoreCase(titolo, titolo);
	}
	public Optional<Foto> findPhotoById(int id) {
		
		return fotoRepo.findById(id);
	}
	
	public void delete(Foto foto) {
		
		fotoRepo.delete(foto);
	}
	
	public void deleteById(int id) {
		
		fotoRepo.deleteById(id);
	}
	
	@Transactional
	public List<Foto> findAllComments() {
		
		List<Foto> fotos = fotoRepo.findAll();
		
		for (Foto foto : fotos) {
			
			Hibernate.initialize(foto.getCommenti());
		}
		
		return fotos;
	}
}
