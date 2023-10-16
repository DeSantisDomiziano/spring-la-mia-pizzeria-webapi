package org.java.app.api;

import java.util.List;
import java.util.Optional;

import org.java.app.pojo.Pizza;
import org.java.app.serve.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1.0")
public class PizzaRestController {

		@Autowired
		private PizzaService pizzaService;
		
		@GetMapping
		public ResponseEntity<List<Pizza>> getAll() {
			List<Pizza> pizza = pizzaService.findAll();
			
			return new ResponseEntity<>(pizza, HttpStatus.OK);
		}
		
		@PostMapping
		public ResponseEntity<Integer> save(
				@RequestBody Pizza pizza) {
			
			Pizza pizzaNuova = new Pizza(pizza);
			
			System.out.println("Api pizza SAVE:\n" + pizzaNuova);
			pizzaService.save(pizzaNuova);
			
			return new ResponseEntity<>(pizzaNuova.getId(), HttpStatus.OK);
		}
		
		@GetMapping("{id}")
		public ResponseEntity<Pizza> getPizza(
				@PathVariable Integer id
			) {
			
			Pizza pizza = id == null
							? null
							: pizzaService.findById(id);
			
			if (pizza == null) {
				
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
			
			Optional<Pizza> pizzaOpt = Optional.ofNullable(pizza);
			
			return new ResponseEntity<>(pizzaOpt.get(), HttpStatus.OK);
		}
		
		@PutMapping("{id}")
		public ResponseEntity<Pizza> updatePizza(
				@PathVariable Integer id,
				@RequestBody Pizza pizza) {
			
			Optional<Pizza> pizzaOpt = Optional.ofNullable(pizzaService.findById(id));
			
			if (pizzaOpt.isEmpty()) {
				
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
			
			Pizza pizza1 = pizzaOpt.get();
			pizza1.fillPizza(pizza1);
		
			try {
				
				pizzaService.save(pizza1);
				
				return new ResponseEntity<>(pizza1, HttpStatus.OK);
			} catch (Exception e) {
				
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}		
		}
		
		@DeleteMapping("{id}")
		public ResponseEntity<Boolean> deletePizza(
				@PathVariable int id
			) {
			
			Optional<Pizza> optPizza = Optional.ofNullable(pizzaService.findById(id));
			
			if (optPizza.isEmpty()) {
				
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
			
			Pizza pizza = optPizza.get();
			pizzaService.deletePizza(pizza);
			
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
}
