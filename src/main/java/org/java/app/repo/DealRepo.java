package org.java.app.repo;

import org.java.app.pojo.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealRepo extends JpaRepository<Deal, Integer>{
	
}