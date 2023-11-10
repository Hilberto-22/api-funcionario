package com.api.funcionario.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.funcionario.exception.ResourceNotFoundException;
import com.api.funcionario.model.EntityNotFoundException;
import com.api.funcionario.model.Funcionario;
import com.api.funcionario.repository.FuncionarioRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/funcionario")
public class FuncionarioController {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@GetMapping("/form") 
	public String form() {
		return "Bem vindo";
	}
	
	@GetMapping("/list")
	public List<Funcionario> getAllFuncionario(){
		 return funcionarioRepository.findAllByOrderByIdAsc();
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Funcionario> getFuncionarioById(@PathVariable(value = "id") Long id) throws Exception {
		Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Funcionario não encontrado"));
		return ResponseEntity.ok().body(funcionario);
	}
	
	@PostMapping("/save")
	public Funcionario createFuncionario(@Valid @RequestBody Funcionario funcionario) {
		return funcionarioRepository.save(funcionario);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Funcionario> updateFuncionario(@PathVariable(value = "id") Long id, @Valid 
			@RequestBody Funcionario funcDetails) throws ResourceNotFoundException{
		Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow(() -> 
				new ResourceNotFoundException("Funcionario não encontrado"));
		funcionario.setEmail(funcDetails.getEmail());
		funcionario.setNome(funcDetails.getNome());
		funcionario.setSobreNome(funcDetails.getSobreNome());
		final Funcionario updateFuncionario = funcionarioRepository.saveAndFlush(funcionario);
		return ResponseEntity.ok(updateFuncionario);
	}
	
	@DeleteMapping("/delete/{id}")
	public Map<String,Boolean> deleteFuncionario(@PathVariable(value = "id") Long funcionarioId){
		Funcionario funcionario = funcionarioRepository.findById(funcionarioId).orElseThrow(() -> 
				new ResourceNotFoundException("Funcionario não encontrado"));
		
		funcionarioRepository.delete(funcionario);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return response;
	}
}
