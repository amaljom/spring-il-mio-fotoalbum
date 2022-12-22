package org.generation.italy.demo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.generation.italy.demo.pojo.Categoria;
import org.generation.italy.demo.pojo.Commento;
import org.generation.italy.demo.pojo.Foto;
import org.generation.italy.demo.pojo.Role;
import org.generation.italy.demo.pojo.User;
import org.generation.italy.demo.service.CategoriaService;
import org.generation.italy.demo.service.CommentoService;
import org.generation.italy.demo.service.FotoService;
import org.generation.italy.demo.service.RoleService;
import org.generation.italy.demo.service.UserService;
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
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CommentoService commentServ;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringIlMioFotoalbumApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria c1 = new Categoria("natura");
		Categoria c2 = new Categoria("arte");
		Categoria c3 = new Categoria("gaming");
		Categoria c4 = new Categoria("fumetti");
		
		List<Categoria> list1 = new ArrayList<>();
		list1.add(c1);
		list1.add(c2);
		
		List<Categoria> list2 = new ArrayList<>();
		list2.add(c3);
		list2.add(c2);
		
		List<Categoria> list3 = new ArrayList<>();
		list3.add(c4);
		
		cateServ.save(c1);
		cateServ.save(c2);
		cateServ.save(c3);
		cateServ.save(c4);
		
		Foto f1 = new Foto("Moss", "moss moss moss", "https://www.mossroma.it/wp-content/uploads/2021/10/flexymoss-5.jpg", "natura", true, list1);
		Foto f2 = new Foto("Dark Souls", "souls like", "https://image.api.playstation.com/cdn/EP0700/CUSA03365_00/OFMeAw2KhrdaEZAjW1f3tCIXbogkLpTC.png", "gioco", true, list2);
		Foto f3 = new Foto("superman", "s stands for hope", "https://www.panini.it/media/catalog/product/cache/a5b5dd3adfe0d321084804c738f29601/M/1/M1REHC030ISBN_0.jpg", "superman", true, list3);
		Foto f4 = new Foto("DeathStranding", "Bartolini simulator", "https://cdn1.epicgames.com/offer/0a9e3c5ab6684506bd624a849ca0cf39/EGS_DeathStrandingDirectorsCut_KOJIMAPRODUCTIONS_S4_1200x1600-5f99e16507795f9b497716b470cfd876", "gioco", true, list1);
		Foto f5 = new Foto("Cyberpunk", "Cyberpunk", "https://cdn1.epicgames.com/offer/77f2b98e2cef40c8a7437518bf420e47/EGS_Cyberpunk2077_CDPROJEKTRED_S1_03_2560x1440-359e77d3cd0a40aebf3bbc130d14c5c7", "gioco", true, list2);
		Foto f6 = new Foto("batman", "Bat-signal", "https://pad.mymovies.it/filmclub/2016/04/093/coverlg_home.jpg", "batman", false, list3);
		
		fotoServ.save(f1);
		fotoServ.save(f2);
		fotoServ.save(f3);
		fotoServ.save(f4);
		fotoServ.save(f5);
		fotoServ.save(f6);
		
		Commento cm1 = new Commento("Bellissimo!", f2);
		Commento cm2 = new Commento("Come editi le foto?!", f1);
		Commento cm3 = new Commento("Superman!", f3);
		Commento cm4 = new Commento("GOTY", f2);
		
		commentServ.save(cm2);
		commentServ.save(cm1);
		
		Role adminRole = new Role("ADMIN");
		
		roleService.save(adminRole);
	
		User adminUser = new User("admin", "{noop}password2", adminRole);
		
		userService.save(adminUser);
		
	}

}
