package com.attornatus.projetopessoas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attornatus.projetopessoas.model.Endereco;
import com.attornatus.projetopessoas.repository.EnderecoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/endereco")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EnderecoController {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@GetMapping("/{id}")
	public ResponseEntity<List<Endereco>> getAllById(@PathVariable Long id){
		return ResponseEntity.ok(enderecoRepository.findAllByPessoaId(id));
	}
	
	@PostMapping
	public ResponseEntity<Endereco> post(@Valid @RequestBody Endereco endereco){
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(enderecoRepository.save(endereco));
	}
}