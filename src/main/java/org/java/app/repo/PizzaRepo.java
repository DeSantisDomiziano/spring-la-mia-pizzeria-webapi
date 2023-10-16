package org.java.app.repo;

import java.util.List;
import java.util.Optional;

import org.java.app.pojo.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepo extends JpaRepository<Pizza, Integer>{
	
	public List<Pizza> findByNameContainingOrOverviewContaining(String name, String overview);
}
