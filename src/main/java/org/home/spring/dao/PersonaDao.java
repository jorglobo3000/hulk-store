/**
 * 
 */
package org.home.spring.dao;

import java.util.List;

import org.home.spring.modelo.Persona;
import org.home.spring.modelo.enumerado.TipoPersonaEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author casa
 *
 */

public interface PersonaDao extends CrudRepository<Persona, Long> {

	@Transactional(readOnly = true)
	public List<Persona> findByTipoPersona(TipoPersonaEnum tipoPersona);
	
	public Persona findByIdentificacion(String identificacion);
	
	public Persona findByUsernameAndPassword(String username, String password);
	
}
