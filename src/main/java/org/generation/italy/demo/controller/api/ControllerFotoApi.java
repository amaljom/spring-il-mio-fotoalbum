package org.generation.italy.demo.controller.api;

import java.util.List;

import org.generation.italy.demo.pojo.Foto;
import org.generation.italy.demo.service.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/1/foto")
@CrossOrigin("*")
public class ControllerFotoApi {
	
	@Autowired
	private FotoService fotoServ;
	
	@GetMapping("/all")
	public List<Foto> getAll() {
		
		List<Foto> fotos = fotoServ.findAll();
		return fotos;
	}
	@GetMapping("/all/{q}")
	public List<Foto> getAllByName( @PathVariable("q") String q) {
		
		List<Foto> pizze = fotoServ.findByName(q);
		return pizze;
	}
}
