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
 * Clase encargada de manejar la logica de las personas
 * 
 * @author casa
 *
 */

@Service
public class PersonaServicio {

	@Autowired
	private PersonaDao personaDao;

	/**
	 * Metodo que dado un id permite conocer a la persona
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public Persona obtenerPorId(Long id) {
		return personaDao.findById(id).orElse(null);
	}

	/**
	 * Metodo que lista todas las personas registradas
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Persona> listarPersonas() {
		return (List<Persona>) personaDao.findAll();
	}

	/**
	 * Metodo que permite listar las personas conociento su tipo, ya sea cliente o
	 * proveedor
	 * 
	 * @param tipoPersona
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Persona> listarPorTipoPersona(TipoPersonaEnum tipoPersona) {
		return personaDao.findByTipoPersona(tipoPersona);
	}

	/**
	 * Metodo que permite registrar a una nueeva persona
	 * 
	 * @param persona
	 * @return
	 */
	@Transactional
	public Persona guardar(Persona persona) {
		return personaDao.save(persona);
	}

	
	@Transactional(readOnly = true)
	public Persona obtenerPorIdentificacion(String identificacion) {
		return personaDao.findByIdentificacion(identificacion);
	}
}
