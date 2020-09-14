/**
 * 
 */
package org.home.spring.dao;

import java.util.List;

import org.home.spring.modelo.Producto;
import org.home.spring.modelo.enumerado.EstadoEnum;
import org.springframework.data.repository.CrudRepository;

/**
 * Interfaz que permite el acceso a los datos de la entidad Producto, se
 * extiende de CrudRepository
 * 
 * @author casa
 *
 */
public interface ProductoDao extends CrudRepository<Producto, Long> {

	public List<Producto> findByEstado(EstadoEnum estado);

}
