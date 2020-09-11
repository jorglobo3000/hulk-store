/**
 * 
 */
package org.home.spring.dao;

import org.home.spring.modelo.Categoria;
import org.springframework.data.repository.CrudRepository;

/**
 * @author casa
 *
 */
public interface CategoriaDao extends CrudRepository<Categoria, Long> {

	
}
