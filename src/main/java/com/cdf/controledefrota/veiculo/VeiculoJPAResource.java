package com.cdf.controledefrota.veiculo;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;;

@RestController
public class VeiculoJPAResource {
	
	@Autowired
	private VeiculoRepository veiculoRepository;
	
	@GetMapping("/jpa/veiculos")
	public List<Veiculo> retriveAllVeiculos(){
		List<Veiculo> veiculos = veiculoRepository.findAll();
		
		return veiculos;
		
	}
	
//	@GetMapping("/jpa/veiculos/{id}")
//	public EntityModel <Veiculo> retriveVeiculo(@PathVariable int id){
//		Optional<Veiculo> veiculo = veiculoRepository.findById(id);
//		
//		
//		
//		EntityModel<Veiculo> resource = EntityModel.of(veiculo.get());
//		WebMvcLinkBuilder linkto = linkTo(methodOn(this.getClass()).retriveAllVeiculos());
//		resource.add(linkTo.withRel("all-veiculos"));
//		
//		return resource;		
//	}

	@DeleteMapping("/jpa/veiculos/{id}")
	public void deleteUser(@PathVariable int id) {
		veiculoRepository.deleteById(id);	
	}
	
	@PostMapping("/jpa/veiculos")
	public ResponseEntity<Object> createUser(@RequestBody Veiculo veiculo){
		Veiculo savedVeiculo = veiculoRepository.save(veiculo);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedVeiculo.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}
