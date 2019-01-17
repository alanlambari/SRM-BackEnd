package com.srm.formulario.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.srm.formulario.domain.Formulario;
import com.srm.formulario.domain.enums.Riscos;
import com.srm.formulario.dto.FormularioDTO;
import com.srm.formulario.repositories.FormularioRepository;
import com.srm.formulario.services.exceptions.DataIntegrityException;

@Service
public class FormularioService {
	
	@Autowired
	private FormularioRepository repo;
	
	public Formulario findId(Integer id) {
		Optional<Formulario> form = repo.findById(id);
		return form.orElse(null);
	}
	
	public List<Formulario> findAll() {
		return repo.findAll();
	}
	
	public Formulario insert(Formulario obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Formulario update(Formulario obj) {
		Formulario newObj = findId(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	private void updateData(Formulario newObj, Formulario obj) {
		newObj.setNomeCliente(obj.getNomeCliente());
		newObj.setLimiteCredito(obj.getLimiteCredito());
		newObj.setTaxaJuros(obj.getTaxaJuros());
	}

	
	public void delete(Integer id) {
		findId(id);
		try {
			repo.deleteById(id);	
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir o formulario");
		}
	}
	
	public Formulario fromDTO(FormularioDTO objDto) {
		objDto = this.atribuirTaxaJuros(objDto);
		return new Formulario(null, objDto.getNomeCliente(), objDto.getLimiteCredito(), objDto.getTaxaJuros(), objDto.getRisco());
	}
	
	
	public Page<Formulario> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	private FormularioDTO atribuirTaxaJuros(FormularioDTO objDto) {
		if(objDto.getRisco().getCod() == Riscos.RISCO_A.getCod()) {
			objDto.setTaxaJuros(0);
		}else if(objDto.getRisco().getCod() == Riscos.RISCO_B.getCod()) {
			objDto.setTaxaJuros(10);
		}else if(objDto.getRisco().getCod() == Riscos.RISCO_C.getCod()) {
			objDto.setTaxaJuros(20);
		}
		return objDto;
	}



}
