package org.java.app.serve;

import java.util.List;
import org.java.app.pojo.Ingredient;
import org.java.app.repo.IngredientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientSerivce {
	
	@Autowired
	IngredientRepo ingredientRepo;
	
	public void save(Ingredient ingredient) {
		ingredientRepo.save(ingredient);
	}
	
	public List<Ingredient> findAll() {
		return ingredientRepo.findAll();
	}
	
	public Ingredient findById(int id) {
		
		return ingredientRepo.findById(id).get();
	}
	
	public List<Ingredient> findByName(String name) {
		return ingredientRepo.findByName(name);
	}
}
