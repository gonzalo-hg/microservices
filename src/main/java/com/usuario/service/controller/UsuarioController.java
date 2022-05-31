package com.usuario.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuario.service.entidades.Usuario;
import com.usuario.service.modelos.Carro;
import com.usuario.service.modelos.Moto;
import com.usuario.service.servicio.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> listarUsuario(){
		List<Usuario> usuarios = usuarioService.getAll();
		
		if(usuarios.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(usuarios);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> obtenerUsuario(
			@PathVariable( value = "id") int id){
		Usuario usuario = usuarioService.getUSuarioById(id);
		
		if(usuario == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usuario);
	}
	
	@PostMapping
	public ResponseEntity<Usuario> guardarUsuario(
			@RequestBody Usuario usuario){
		Usuario nuevoUsuario = usuarioService.save(usuario);
		
		return ResponseEntity.ok(nuevoUsuario);
	}
	
	@GetMapping(path ="/carro/{usuarioId}")
	public ResponseEntity<List<Carro>> getCarros(
			@PathVariable(value ="usuarioId") int usuarioId){
		Usuario usuario = usuarioService.getUSuarioById(usuarioId);
		
		if(usuario ==  null) {
			return ResponseEntity.notFound().build();
		}
		List<Carro> carros = usuarioService.getCarros(usuarioId);
		return ResponseEntity.ok(carros);
	}	
	
	@GetMapping(path ="/moto/{usuarioId}")
	public ResponseEntity<List<Moto>> getMotos(
			@PathVariable(value ="usuarioId") int usuarioId){
		Usuario usuario = usuarioService.getUSuarioById(usuarioId);
		
		if(usuario ==  null) {
			return ResponseEntity.notFound().build();
		}
		List<Moto> motos = usuarioService.getMotos(usuarioId);
		return ResponseEntity.ok(motos);
	}
}
