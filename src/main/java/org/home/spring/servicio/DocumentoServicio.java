/**
 * 
 */
package org.home.spring.servicio;

import java.math.BigDecimal;

import org.home.spring.dao.DocumentoDao;
import org.home.spring.modelo.DetalleDocumento;
import org.home.spring.modelo.Documento;
import org.home.spring.modelo.enumerado.TipoDocumentoEnum;
import org.home.spring.modelo.enumerado.TipoOperacionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Clase encargada de manejar la logica del documento, ya sea un carrito de
 * compra, una venta o una compra
 * 
 * 
 * @author casa
 *
 */
@Service
public class DocumentoServicio {

	@Autowired
	private DocumentoDao documentoDao;

	@Autowired
	private DetalleDocumentoServicio detalleServicio;

	@Autowired
	private KardexServicio kardexServicio;

	/**
	 * Metodo que permite registar un documentos con todos sus items, usado para
	 * manejar el carrito de compras, no afecta al inventario
	 * 
	 * @param documento
	 * @return
	 */
	@Transactional
	public Documento guardar(Documento documento) {
		documento = documentoDao.save(documento);
		detalleServicio.guardarTodos(documento.getDetalle());
		return documento;
	}

	/**
	 * Metodo encargado para vender mediante un documento con sus items, en este
	 * caso se afecta el inventario
	 * 
	 * @param documento
	 * @return
	 */
	@Transactional
	public Documento vender(Documento documento) {
		documento.setTipoDocumento(TipoDocumentoEnum.FAC);
		ponerDocumento(documento);
		documento = documentoDao.save(documento);
		documento.setNumeroDocumento("001-001-" + documento.getId().toString());
		documento.getDetalle().forEach(item -> kardexServicio.registrarKardexEgreso(item, TipoOperacionEnum.SALC));
	System.out.println(documento);
		return documento;
	}

	/**
	 * Metodo que permite mediante un documento comprar productos para proveer un
	 * stock a la tienda
	 * 
	 * @param documento
	 * @return
	 */
	@Transactional
	public DetalleDocumento comprar(DetalleDocumento documento) {
		documento.getProducto()
				.setPrecioVenta(documento.getSubtotalProducto()
						.add(documento.getSubtotalProducto()
								.multiply(new BigDecimal(documento.getProducto().getPorcentajeUtilidad()))
								.divide(new BigDecimal(100))));
		kardexServicio.registrarKardexIngreso(documento, TipoOperacionEnum.INGC);
		System.out.println(documento);
		return documento;
	}

	private Documento ponerDocumento(Documento documento) {
		for (DetalleDocumento detalle : documento.getDetalle()) {
			detalle.setDocumento(documento);
		}
		return documento;
	}

}
