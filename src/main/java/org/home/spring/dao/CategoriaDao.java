/**
 * 
 */
package org.home.spring.dao;

import org.home.spring.modelo.Categoria;
import org.springframework.data.repository.CrudRepository;

/**
 * Interfaz que permite el acceso a datos de la entidad Categoria, extiende de
 * CrudRepository
 * 
 * @author casa
 *
 */
public interface CategoriaDao extends CrudRepository<Categoria, Long> {

}
