package org.formation.controller;

import java.util.Date;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.formation.model.Member;
import org.formation.model.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;



/**
 * A controller for login, registration and documents.
 * 
 * @author David Thibau
 */
@Controller
@RequestMapping("/web")
@SessionAttributes("loggedUser")
public class MembersController {

	protected Logger logger = Logger.getLogger(MembersController.class
			.getName());
	protected MemberRepository memberRepository;

	/**
	 * Create an instance plugging in the respository of Members.
	 * 
	 * @param MemberRepository
	 *            An Member repository implementation.
	 */
	@Autowired
	public MembersController(MemberRepository MemberRepository) {
		this.memberRepository = MemberRepository;

		logger.info("MemberRepository says system has "
				+ memberRepository.count() + " Members");
	}
	
	@GetMapping(path = "/register")
	public String showRegisterForm(Model model) {
		model.addAttribute("user", new Member());
		
		return "register";
		
	}
	
	@RequestMapping(path = "/register", method = RequestMethod.POST, consumes="application/x-www-form-urlencoded;charset=UTF-8")
	public String register(@Valid Member member, Model model) {
		member.setRegisteredDate(new Date());
		member = memberRepository.save(member);
		
		model.addAttribute("loggedUser",member);

		return "redirect:documents";
		
	}
}
