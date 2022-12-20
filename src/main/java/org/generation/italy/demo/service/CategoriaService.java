package org.generation.italy.demo.service;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Categoria;
import org.generation.italy.demo.repo.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository cateRepo;
	
	public void save(Categoria categoria) {
		
		cateRepo.save(categoria);
	}
	
	public List<Categoria> findAll() {
		return cateRepo.findAll();
	}
	
	public Optional<Categoria> getIngredientById(int id) {
		return cateRepo.findById(id);
	}

	public void deleteIngredieteById(int id) {
		cateRepo.deleteById(id);
	}
}
