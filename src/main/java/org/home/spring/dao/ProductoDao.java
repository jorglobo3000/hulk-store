/**
 * 
 */
package org.home.spring.dao;

import java.util.List;

import org.home.spring.modelo.Producto;
import org.home.spring.modelo.enumerado.EstadoEnum;
import org.springframework.data.repository.CrudRepository;

/**
 * @author casa
 *
 */
public interface ProductoDao extends CrudRepository<Producto, Long> {

	public List<Producto> findByEstado(EstadoEnum estado);
	
}
