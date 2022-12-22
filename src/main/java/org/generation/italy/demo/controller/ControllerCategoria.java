package org.generation.italy.demo.controller;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Categoria;
import org.generation.italy.demo.pojo.Foto;
import org.generation.italy.demo.service.CategoriaService;
import org.generation.italy.demo.service.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/categoria")
public class ControllerCategoria {
	
	@Autowired
	private CategoriaService categserv;
	
	@GetMapping()
	public String getCategorie(Model model) {
		
		List<Categoria> categorie = categserv.findAll();
		model.addAttribute("categorie", categorie);
		
		return "categoria-index";
	}
	
	@GetMapping("/create")
	public String createCategorie(Model model) {

		Categoria categoria = new Categoria();
		model.addAttribute("categoria", categoria);
		
		
		return "categoria-create";
	}
	// Create
	@PostMapping("/create")
	public String storeCategorie(@Valid @ModelAttribute("pizza") Categoria categoria,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			
			return "redirect:/categoria/create";
		}
		
		categserv.save(categoria);
		
		return "redirect:/categoria";
	}
	
	// Edit and Update
		@GetMapping("/update/{id}")
		public String editCategorie(@PathVariable("id") int id, Model model) {
			
			Optional<Categoria> chosenCategoria = categserv.getCategoryById(id);
			Categoria categoria = chosenCategoria.get();
			model.addAttribute("categoria", categoria);
			
			return "categoria-edit";
		}
		@PostMapping("/update")
		public String updateCategorie(@Valid Categoria categoria,
				BindingResult bindingResult, RedirectAttributes redirectAttributes) {
			
			if (bindingResult.hasErrors()) {
				
				redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
				
				return "redirect:/categoria/update/" + categoria.getId();
			}
			
			categserv.save(categoria);		
			return "redirect:/categoria";
		}
		
		@GetMapping("/delete/{id}")
		public String deleteCategorie(@PathVariable("id") int id) {
			
			Optional<Categoria> chosenCategory = categserv.getCategoryById(id);
			Categoria categoria = chosenCategory.get();
			
			List<Foto> fotos = categoria.getFoto();
			for (Foto foto : fotos)
				foto.getCategorie().remove(categoria);
			
			categserv.deleteCategoriaById(categoria.getId());
			
			return "redirect:/categoria";
		}
}
