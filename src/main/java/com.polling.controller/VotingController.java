package com.Polling.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Polling.Entity.Candidate;
import com.Polling.Entity.Citizen;
import com.Polling.Repository.CandidateRepo;
import com.Polling.Repository.CitizenRepo;

@Controller
public class VotingController {

	@Autowired
	CitizenRepo citizenRepo;
	
	@Autowired
	CandidateRepo candidateRepo;
	
	
	
	@RequestMapping("/")
	public String gotoVote()
	{
		return "vote.html";
	}
	
	@RequestMapping("/doLogin")
	public String doLogin(@RequestParam String name,Model model,HttpSession session)
	{
		Citizen citizen=citizenRepo.findByName(name);
		session.setAttribute("citizen",citizen );
		if(!citizen.getHasVoted())
		{
			List<Candidate> candidates= candidateRepo.findAll();
			
				model.addAttribute("candidates", candidates);
			
			return "/performVoted.html";
		}
		else
		{
			return "/alreadyVoted.html";
		}
		
	}
	
	@RequestMapping("/voteFor")
   public String voteFor(@RequestParam Integer id, HttpSession session)
   {
		Citizen citizen=(Citizen)session.getAttribute("citizen");
		if(!citizen.getHasVoted()) {
		citizen.setHasVoted(true);
			
		Candidate c= candidateRepo.findById((int)id);
		c.setNumberOfVotes(c.getNumberOfVotes()+1);
		
		candidateRepo.save(c);
		citizenRepo.save(citizen);
		return "/voted.html";
		}
		return "/alreadyVoted.html";
   }
	
}
