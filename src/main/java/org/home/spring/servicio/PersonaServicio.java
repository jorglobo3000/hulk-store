/**
 * 
 */
package org.home.spring.servicio;

import java.util.List;

import org.home.spring.dao.PersonaDao;
import org.home.spring.modelo.Persona;
import org.home.spring.modelo.enumerado.TipoPersonaEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author casa
 *
 */

@Service
public class PersonaServicio {

	@Autowired
	private PersonaDao personaDao;

	@Transactional(readOnly = true)
	public Persona obtenerPorId(Long id) {
		return personaDao.findById(id).orElse(null);
	}

	@Transactional(readOnly = true)
	public List<Persona> listarPersonas() {
		return (List<Persona>) personaDao.findAll();
	}

	@Transactional(readOnly = true)
	public List<Persona> listarPorTipoPersona(TipoPersonaEnum tipoPersona) {
		return personaDao.findByTipoPersona(tipoPersona);
	}
	
	@Transactional
	public Persona guardar(Persona persona) {
		return personaDao.save(persona);
	}

}
