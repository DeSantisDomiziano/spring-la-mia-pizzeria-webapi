package org.java.app.controller;


import java.util.List;
import org.java.app.pojo.Ingredient;
import org.java.app.serve.IngredientSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {
	
	@Autowired
	private IngredientSerivce ingredientsService;
	
	@GetMapping("/list")
	public String indexIngredients(Model model) {
		
		List<Ingredient> ingredients = ingredientsService.findAll();
		model.addAttribute("ingredients", ingredients);

		
		return "ingredients";
	}
}
