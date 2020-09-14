/**
 * 
 */
package org.home.spring.dao;

import java.util.List;

import org.home.spring.modelo.Kardex;
import org.home.spring.modelo.Producto;
import org.springframework.data.repository.CrudRepository;

/**
 * 
 * Interfaz que permite el acceso a datos de la entidad Kardex, extiende de
 * CrudRepository
 * 
 * @author casa
 *
 */
public interface KardexDao extends CrudRepository<Kardex, Long> {

	public List<Kardex> findByProducto(Producto producto);

}
