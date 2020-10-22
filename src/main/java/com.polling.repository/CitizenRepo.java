package com.Polling.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Polling.Entity.Citizen;

@Repository
public interface CitizenRepo extends JpaRepository<Citizen,Integer> {

	public Citizen findByName(String name);
	
}
