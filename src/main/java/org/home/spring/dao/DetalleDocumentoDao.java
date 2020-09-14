/**
 * 
 */
package org.home.spring.dao;

import org.home.spring.modelo.DetalleDocumento;
import org.springframework.data.repository.CrudRepository;

/**
 * Interfaz que permite el acceso a datos de la entidad DetalleDocumento, esta
 * es dorma parte del detalle del documento, extiende de CrudRepository
 * 
 * @author casa
 *
 */
public interface DetalleDocumentoDao extends CrudRepository<DetalleDocumento, Long> {

}
