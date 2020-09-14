/**
 * 
 */
package org.home.spring.dao;

import org.home.spring.modelo.Documento;
import org.springframework.data.repository.CrudRepository;

/**
 * Interfaz que permite el acceso a datos de la entidad Documento, extiende de
 * CrudRepository
 * 
 * @author casa
 *
 */
public interface DocumentoDao extends CrudRepository<Documento, Long> {

}
