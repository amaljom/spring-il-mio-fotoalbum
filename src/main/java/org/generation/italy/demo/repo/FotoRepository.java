package org.generation.italy.demo.repo;

import java.util.List;

import org.generation.italy.demo.pojo.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoRepository extends JpaRepository<Foto, Integer>{

	List<Foto> findByTitoloContainingOrTagContainingIgnoreCase(String titolo, String tag);
	
}
