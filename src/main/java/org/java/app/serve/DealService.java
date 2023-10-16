package org.java.app.serve;

import java.util.List;
import org.java.app.pojo.Deal;
import org.java.app.repo.DealRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DealService {
	
	@Autowired
	private DealRepo dealRepo;
	
	public void save(Deal deal) {
		
		dealRepo.save(deal);
	}
	public List<Deal> findAll() {
		
		return dealRepo.findAll();
	}
	
	public Deal findById(int id) {
		
		return dealRepo.findById(id).get();
	}
	
	public void deleteById(int id) {
		
		dealRepo.deleteById(id);
	}
 }