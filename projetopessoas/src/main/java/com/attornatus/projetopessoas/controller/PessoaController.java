package com.attornatus.projetopessoas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attornatus.projetopessoas.model.Pessoa;
import com.attornatus.projetopessoas.repository.PessoaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PessoaController {
	
	@Autowired
	private PessoaRepository pessoaRepository;

	@GetMapping
	public ResponseEntity<List<Pessoa>> getAll() {
		return ResponseEntity.ok(pessoaRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> getById(@PathVariable Long id) {
		return pessoaRepository.findById(id)
			.map(resposta -> ResponseEntity.ok(resposta))
			.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<List<Pessoa>> getByName(@PathVariable String name){
		return ResponseEntity.ok(pessoaRepository.findAllByNameContainingIgnoreCase(name));
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> post(@Valid @RequestBody Pessoa pessoa) {
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(pessoaRepository.save(pessoa));
	}
	
	@PutMapping
	public ResponseEntity<Pessoa> put(@Valid @RequestBody Pessoa pessoa){
		return pessoaRepository.findById(pessoa.getId())
			.map(resposta -> ResponseEntity.status(HttpStatus.OK)
				.body(pessoaRepository.save(pessoa)))
			.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
}
