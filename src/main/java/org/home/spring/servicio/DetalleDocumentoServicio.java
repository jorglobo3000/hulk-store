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
 * Clase que maneja los metodos de guardar para los items de un documento
 * 
 * @author casa
 *
 */
@Service
public class DetalleDocumentoServicio {

	@Autowired
	private DetalleDocumentoDao detalleDocumentoDao;

	/**
	 * Metodo que permite persistir el detalle en la base de datos
	 * 
	 * @param detalleMovimiento
	 * @return
	 */
	@Transactional
	public DetalleDocumento guardar(DetalleDocumento detalleMovimiento) {
		return detalleDocumentoDao.save(detalleMovimiento);
	}

	/**
	 * Metodo que permite persistir en la base de datos todos los items de un
	 * documento
	 * 
	 * @param listaDetalle
	 * @return
	 */
	@Transactional
	public List<DetalleDocumento> guardarTodos(List<DetalleDocumento> listaDetalle) {
		return (List<DetalleDocumento>) detalleDocumentoDao.saveAll(listaDetalle);
	}
}
