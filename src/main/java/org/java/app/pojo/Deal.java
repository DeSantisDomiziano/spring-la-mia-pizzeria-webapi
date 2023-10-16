package org.java.app.pojo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Deal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message = "La data non può essere nulla")
	private LocalDate startDate;
	
	@NotNull(message = "La data non può essere nulla")
    @Future(message = "La data deve essere nel futuro")
	private LocalDate endDate;
	
	@Column(length = 60, nullable = false)
	@NotBlank
	private String name;
	
	public Deal() {}
	
	@ManyToOne
	@JoinColumn(name = "pizza_id", nullable = false)
	private Pizza pizza;
	
	public Pizza getPizza() {
		return pizza;
	}


	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}


	public Deal(LocalDate startDate, LocalDate endDate, String name, Pizza pizza) {
		
		setStartDate(startDate);
		setEndDate(endDate);
		setName(name);
		setPizza(pizza);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id)  {
		this.id = id;
	}


	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String dateFormatter(LocalDate formatDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ITALIAN);
        return  formatDate.format(formatter);
	}


	@Override
	public String toString() {
		return "Deal [getStartDate()=" + getStartDate() + ", getEndDate()=" + getEndDate() + ", getName()=" + getName()
				+ "]" + getId();
	}
	
}
