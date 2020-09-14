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
 * Interfaz que permite el acceso a datos de la entidad Persona, extiende de
 * CrudRepository
 * 
 * @author casa
 *
 */

public interface PersonaDao extends CrudRepository<Persona, Long> {

	@Transactional(readOnly = true)
	public List<Persona> findByTipoPersona(TipoPersonaEnum tipoPersona);

	public Persona findByIdentificacion(String identificacion);

	public Persona findByUsernameAndPassword(String username, String password);

}
