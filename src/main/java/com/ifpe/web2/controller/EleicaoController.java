package com.ifpe.web2.controller;
import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ifpe.web2.model.Candidatos;
import com.ifpe.web2.model.CandidatosDAO;


@Controller
public class EleicaoController {

	private List<Candidatos> candidatos = new ArrayList<>();
	
	@Autowired
	private CandidatosDAO candidatosDAO;
	
	@GetMapping("/index")
	public String index() {
		return "index";	
	}
	
	@GetMapping("/exibirCadastro")
	public String exibirForm(Candidatos candidatos) {
		return "cadastro-form";
	}
	
	@PostMapping("/salvarCandidato")
	public String salvarContato(Candidatos candidatos) {
		this.candidatosDAO.save(candidatos);
		return "redirect:/listarCandidatos";
	}
	
	@GetMapping("/listarCandidatos")
	public String listarContatos(Model model) {
		model.addAttribute("lista", this.candidatosDAO.findAll());
		return "cadastro-list";
	}
	
	@GetMapping("/removerCandidato")
	public String removerCandidato(int id) {
		 this.candidatosDAO.deleteById(id);
		 return "redirect:/listarCandidatos";
	}
	
	@GetMapping("/editarCandidato")
	public String editarCandidato(int id, Model model) {
		 model.addAttribute("candidatos", this.candidatosDAO.findById(id));
		 return "cadastro-form";
		 
	}
	     
}

