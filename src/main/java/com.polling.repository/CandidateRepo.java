package com.Polling.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Polling.Entity.Candidate;


public interface CandidateRepo extends JpaRepository<Candidate, Integer> {
	
	public Candidate findById(int id);

}
