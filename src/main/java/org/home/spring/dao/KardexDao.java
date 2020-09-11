/**
 * 
 */
package org.home.spring.dao;

import java.util.List;

import org.home.spring.modelo.Kardex;
import org.home.spring.modelo.Producto;
import org.springframework.data.repository.CrudRepository;

/**
 * @author casa
 *
 */
public interface KardexDao extends CrudRepository<Kardex, Long>{
	
	public List<Kardex> findByProducto(Producto producto);
	
}
