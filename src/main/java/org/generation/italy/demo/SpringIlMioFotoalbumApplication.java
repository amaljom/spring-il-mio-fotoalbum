package org.generation.italy.demo;

import org.generation.italy.demo.pojo.Categoria;
import org.generation.italy.demo.pojo.Foto;
import org.generation.italy.demo.service.CategoriaService;
import org.generation.italy.demo.service.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringIlMioFotoalbumApplication implements CommandLineRunner{

	@Autowired
	private CategoriaService cateServ;
	
	@Autowired
	private FotoService fotoServ;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringIlMioFotoalbumApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
		Categoria c1 = new Categoria("natura");
		Categoria c2 = new Categoria("arte");
		Categoria c3 = new Categoria("gaming");
		Categoria c4 = new Categoria("fumetti");
		
		cateServ.save(c1);
		cateServ.save(c2);
		cateServ.save(c3);
		cateServ.save(c4);
		
		Foto f1 = new Foto("Moss", "mos mos mos", "https://www.mossroma.it/wp-content/uploads/2021/10/flexymoss-5.jpg", "natura", true);
		Foto f2 = new Foto("Dark Souls", "souls like", "https://image.api.playstation.com/cdn/EP0700/CUSA03365_00/OFMeAw2KhrdaEZAjW1f3tCIXbogkLpTC.png", "gioco", true);
		Foto f3 = new Foto("superman", "s stands for hope", "https://www.panini.it/media/catalog/product/cache/a5b5dd3adfe0d321084804c738f29601/M/1/M1REHC030ISBN_0.jpg", "superman", true);
		
		fotoServ.save(f1);
		fotoServ.save(f2);
		fotoServ.save(f3);
		
	}

}
