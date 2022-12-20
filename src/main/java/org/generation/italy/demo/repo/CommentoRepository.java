package org.generation.italy.demo.repo;

import org.generation.italy.demo.pojo.Commento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentoRepository extends JpaRepository<Commento, Integer>{

}
