/**
 * 
 */
package org.home.spring.servicio;

import java.util.List;

import org.home.spring.dao.DetalleDocumentoDao;
import org.home.spring.modelo.DetalleDocumento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author casa
 *
 */
@Service
public class DetalleDocumentoServicio {

	@Autowired
	private DetalleDocumentoDao detalleDocumentoDao;

	@Transactional
	public void guardar(DetalleDocumento detalleMovimiento) {
		detalleDocumentoDao.save(detalleMovimiento);
	}

	@Transactional
	public List<DetalleDocumento> guardarTodos(List<DetalleDocumento> listaDetalle) {
		return (List<DetalleDocumento>) detalleDocumentoDao.saveAll(listaDetalle);
	}
}
