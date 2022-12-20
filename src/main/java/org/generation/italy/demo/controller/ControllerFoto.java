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
@RequestMapping("/foto")
public class ControllerFoto {
	
	@Autowired
	private FotoService fotoServ;
	
	@Autowired
	private CategoriaService categServ;
	
	@GetMapping()
	public String getFoto(Model model) {
		
		List<Foto> fotos = fotoServ.findAll();
		model.addAttribute("fotos", fotos);
		
		return "foto-index";
	}
	
	@GetMapping("/create")
	public String createFoto(Model model) {

		Foto foto = new Foto();
		model.addAttribute("foto", foto);
		
		List<Categoria> categorie = categServ.findAll();
		model.addAttribute("categorie", categorie);
		
		return "foto-create";
	}
	
	// Create
	@PostMapping("/create")
	public String storeFoto(@Valid @ModelAttribute("pizza") Foto foto,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			
			return "redirect:/";
		}
		fotoServ.save(foto);
		
		return "redirect:/foto";
	}
	// Edit and Update
	@GetMapping("/update/{id}")
	public String editFoto(@PathVariable("id") int id, Model model) {
		
		Optional<Foto> chosenFoto = fotoServ.findPhotoById(id);
		Foto foto = chosenFoto.get();
		
		List<Categoria> categorie = categServ.findAll();
		model.addAttribute("categorie", categorie);
		/*
		List<Promozione> promozione = promoService.findAll();
		model.addAttribute("promozione", promozione);
		
		List<Ingrediente> ingredienti = ingredienteService.findAll();
		model.addAttribute("ingredienti", ingredienti);
		*/
		model.addAttribute("foto", foto);
		return "foto-edit";
	}
	@PostMapping("/update")
	public String updateFoto(@Valid Foto foto,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			
			return "redirect:/pizza/update/" + foto.getId();
		}
		
		fotoServ.save(foto);		
		return "redirect:/foto";
	}
	// Delete
	@GetMapping("/delete/{id}")
	public String deleteFoto(@PathVariable("id") int id) {
		
		Optional<Foto> chosenFoto = fotoServ.findPhotoById(id);
		Foto foto = chosenFoto.get();
		
		fotoServ.delete(foto);
		
		return "redirect:/foto";
	}
}
