package com.srm.formulario.services;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srm.formulario.domain.Formulario;
import com.srm.formulario.domain.enums.Riscos;
import com.srm.formulario.repositories.FormularioRepository;

@Service
public class DBService {

	@Autowired
	private FormularioRepository repo;
	
	public void instantiateTestDatabase() throws ParseException {
		Formulario form = new Formulario(null, "ALAN JUNQUEIRA FRANCO", 10000.0, 10, Riscos.RISCO_A);
		Formulario form2= new Formulario(null, "RAFAELA RODRIGUES BOCARDI FRANCO", 20000.0, 10, Riscos.RISCO_B);
		Formulario form3= new Formulario(null, "FATIMA JUNQUEIRA FRANCO", 30000.0, 20, Riscos.RISCO_C);
		repo.saveAll(Arrays.asList(form, form2, form3));
	}
		
}