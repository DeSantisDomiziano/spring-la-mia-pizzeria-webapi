package org.java.app.controller;


import java.util.List;
import org.java.app.pojo.Deal;
import org.java.app.pojo.Pizza;
import org.java.app.serve.DealService;
import org.java.app.serve.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class MainController {
	
	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private DealService dealService;
	
	@GetMapping("/pizze")
	public String getIndex(Model model, 
						   @RequestParam(required = false) String name) {
		
		List<Pizza> pizze = name == null 
							? pizzaService.findAll()
							: pizzaService.searchPizzaByNameOrOverview(name, name);
		String Oldname = name;
		
		model.addAttribute("pizze", pizze);
		model.addAttribute("name", Oldname);
		
		return "index";
	}
	
	@GetMapping("/pizza/{id}")
    public String getMovieDetails(@PathVariable int id, Model model) {
			Pizza pizza = pizzaService.findById(id);
        
            model.addAttribute("pizza", pizza);
            return "show";
    }
	
	@GetMapping("/create")
	public String getCreate(Model model) {
		
		model.addAttribute("pizza", new Pizza());
		
		return "create";
	}
	
	@PostMapping("/create")
	public String storePizza(@Valid @ModelAttribute Pizza pizza,
							BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			bindingResult.getAllErrors().forEach(System.out::println);
			
			return "create";
		}
		
		pizzaService.save(pizza);
		
		return "redirect:/pizze";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id,
						Model model) {
		model.addAttribute("pizza", pizzaService.findById(id));
		
		return "edit";		
	}
	
	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute Pizza pizza,
						BindingResult bindingResult,
						Model model) {
		
		if(bindingResult.hasErrors()) {
			bindingResult.getAllErrors().forEach(System.out::println);
			
			return "edit";
		}
		
		pizzaService.save(pizza);
		return "redirect:/pizze";
	}
	
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		pizzaService.deleteById(id);
		return "redirect:/pizze";
	}
	
	/*############################  DEAL  ########################### */
	
	@GetMapping("/create/deal/{pizza_id}")
	public String deal(
			@PathVariable("pizza_id") int id, Model model
			) {
		
		Pizza pizza = pizzaService.findById(id);
		Deal deal = new Deal();
		model.addAttribute("pizza", pizza);
		model.addAttribute("deal", deal);
			
		return "create_deal";
		
	}
	
	@PostMapping("/create/deal/{pizza_id}")
	public String storeDeal(@Valid @ModelAttribute Deal deal,
			BindingResult bindingResult,
			@PathVariable("pizza_id") int id,
			Model model
	) {
		
		Pizza pizza = pizzaService.findById(id);
		deal.setPizza(pizza);
		
		dealService.save(deal);
		
		return "redirect:/pizza/" + id;
		
	}
	
	@GetMapping("/edit/deal/{id}")
	public String getCreateDeal(Model model, @PathVariable int id
			) {
		
		model.addAttribute("deal", dealService.findById(id));
		
		
		return "edit_deal";
	}
	
	@PostMapping("/edit/deal/{id}")
	public String storeDeal(@Valid @ModelAttribute Deal deal,
			BindingResult bindingResult,
			@PathVariable int id
							) {
		
		if(bindingResult.hasErrors()) {
			bindingResult.getAllErrors().forEach(System.out::println);
			
			return "edit_deal";
		}
		
		int idPizza = dealService.findById(id).getPizza().getId();
		Pizza pizza = pizzaService.findById(idPizza);
		System.out.println(pizza);
		
		deal.setPizza(pizza);
		dealService.save(deal);
		
		return "redirect:/pizza/" + idPizza;
	}
}