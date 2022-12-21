package org.generation.italy.demo.controller.api;

import java.util.Optional;

import org.generation.italy.demo.pojo.Commento;
import org.generation.italy.demo.pojo.Foto;
import org.generation.italy.demo.service.CommentoService;
import org.generation.italy.demo.service.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/1/commento")
@CrossOrigin("*")
public class ControllerCommentiApi {
	
	@Autowired
	private CommentoService commentServ;
	@Autowired
	private FotoService fotoService;
	
	@PostMapping("/create/{id}/{new_comment}")
	public Commento createCommento( @PathVariable("id") int id, @PathVariable("new_comment") String new_comment
	) {
		
		System.err.println(id);
		System.err.println(new_comment);
		
		Optional<Foto> foto = fotoService.findPhotoById(id);
		
		Foto chosenFoto = foto.get();
		System.out.println(chosenFoto);
		
		Commento newCommento = new Commento(new_comment, chosenFoto);
		commentServ.save(newCommento);
		
		return newCommento;
	}
	
}
