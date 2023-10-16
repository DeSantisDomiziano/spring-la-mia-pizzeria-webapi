package org.java.app;

import java.time.LocalDate;
import org.java.app.controller.auth.pojo.Role;
import org.java.app.controller.auth.pojo.User;
import org.java.app.controller.auth.service.RoleService;
import org.java.app.controller.auth.service.UserService;
import org.java.app.pojo.Deal;
import org.java.app.pojo.Ingredient;
import org.java.app.pojo.Pizza;
import org.java.app.serve.PizzaService;
import org.java.app.serve.DealService;
import org.java.app.serve.IngredientSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner {
	
	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private DealService dealService;
	
	@Autowired
	private IngredientSerivce ingredientService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Ingredient i1 = new Ingredient("olio");
		Ingredient i2 = new Ingredient("sale");
		Ingredient i3 = new Ingredient("farina");
		Ingredient i4 = new Ingredient("acqua");
		Ingredient i5 = new Ingredient("mozzarella");
		Ingredient i6 = new Ingredient("pomodoro");
		Ingredient i7 = new Ingredient("salame piccante");
		Ingredient i8 = new Ingredient("alici");
		
		ingredientService.save(i1);
		ingredientService.save(i2);
		ingredientService.save(i3);
		ingredientService.save(i4);
		ingredientService.save(i5);
		ingredientService.save(i6);
		ingredientService.save(i7);
		ingredientService.save(i8);
		
		Pizza p1 = new Pizza("p11", "overview1",
							"https://images.pexels.com/photos/1566837/pexels-photo-1566837.jpeg?auto=compress&cs=tinysrgb&w=400",
							10.10f,
							i1, i2, i3, i4, i5, i8);
		
		Pizza p2 = new Pizza("p22", "overview1",
							"https://images.pexels.com/photos/7947152/pexels-photo-7947152.jpeg?auto=compress&cs=tinysrgb&w=400",
							12.10f,
							i1, i2, i3, i4, i6, i7);
		
		Pizza p3 = new Pizza("p33", "overview1",
							"https://images.pexels.com/photos/5903262/pexels-photo-5903262.jpeg?auto=compress&cs=tinysrgb&w=400",
							13.10f,
							i1, i2, i3, i4, i5, i6, i7, i8);
		
		pizzaService.save(p1);
		pizzaService.save(p2);
		pizzaService.save(p3);
		
		Deal d1 = new Deal(LocalDate.now(), LocalDate.parse("2024-10-04"), "super offerta!!!", p3);
		Deal d2 = new Deal(LocalDate.now(), LocalDate.parse("2024-09-27"), "super offerta!!!", p3);
		Deal d3 = new Deal(LocalDate.now(), LocalDate.parse("2024-11-12"), "super offerta!!!", p3);
		
		dealService.save(d1);
		dealService.save(d2);
		dealService.save(d3);
		
		Role admin = new Role("ADMIN");
		Role user = new Role("USER");
		
		roleService.save(admin);
		roleService.save(user);
		
		final String pwAdmin = new BCryptPasswordEncoder().encode("dom");
		final String pwUser = new BCryptPasswordEncoder().encode("mod");
		
		User testAdmin = new User("admin", pwAdmin, admin);
		User testUser = new User("user", pwUser, user);
		
		userService.save(testAdmin);
		userService.save(testUser);
	}

}