package com.srm.formulario.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.srm.formulario.domain.Formulario;
import com.srm.formulario.dto.FormularioDTO;
import com.srm.formulario.services.FormularioService;

@RestController
@RequestMapping(value="/formularios")
public class FormularioResource {
	
		@Autowired
		private FormularioService service;
		
		@RequestMapping(value="/{id}", method=RequestMethod.GET)
		@CrossOrigin
		public ResponseEntity<?> find(@PathVariable Integer id) {
			Formulario form = service.findId(id);
			return ResponseEntity.ok().body(form);			
		}
		
		@RequestMapping(method=RequestMethod.GET)
		@CrossOrigin
		public ResponseEntity<List<FormularioDTO>> findAll() {
			List<Formulario> list = service.findAll();
			List<FormularioDTO> listDto = list.stream().map(obj -> new FormularioDTO(obj)).collect(Collectors.toList());
			return ResponseEntity.ok().body(listDto);
		}
		
		@RequestMapping(value="/page",  method=RequestMethod.GET)
		public ResponseEntity<Page<FormularioDTO>> findPage(
				@RequestParam(value="page", defaultValue="0") Integer page, 
				@RequestParam(value="linesPerPage", defaultValue="1") Integer linesPerPage, 
				@RequestParam(value="orderBy", defaultValue="nomeCliente") String orderBy, 
				@RequestParam(value="direction", defaultValue="ASC") String direction) {
			Page<Formulario> list = service.findPage(page, linesPerPage, orderBy, direction);
			Page<FormularioDTO> listDto = list.map(obj -> new FormularioDTO(obj));
			return ResponseEntity.ok().body(listDto);
		}
		
		@RequestMapping(method=RequestMethod.POST)
		@CrossOrigin
		public ResponseEntity<Void> insert(@Valid @RequestBody FormularioDTO objDto){
			Formulario obj = service.fromDTO(objDto);
			obj = service.insert(obj);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
			return ResponseEntity.created(uri).build();
		}
		
		@RequestMapping(value="/{id}",  method=RequestMethod.PUT)
		@CrossOrigin
		public ResponseEntity<Void> update(@Valid @RequestBody FormularioDTO objDto, @PathVariable Integer id){
			Formulario obj = service.fromDTO(objDto); 
			obj.setId(id);
			obj = service.update(obj);
			return ResponseEntity.noContent().build();
		}
		
		@RequestMapping(value="/{id}",  method=RequestMethod.DELETE)
		@CrossOrigin
		public ResponseEntity<Void> delete(@PathVariable Integer id) {
			service.delete(id);
			return ResponseEntity.noContent().build();
		}
		
		
	
	

}
