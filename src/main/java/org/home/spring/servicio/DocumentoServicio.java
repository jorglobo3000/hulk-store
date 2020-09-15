/**
 * 
 */
package org.home.spring.servicio;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.home.spring.dao.DocumentoDao;
import org.home.spring.excepcion.StockExcepcion;
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
	private ProductoServicio productoServicio;

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
	 * Metodo que la venta de productos al cliente
	 * Genera movimientos en kardex
	 * 
	 * @param documento
	 * @return
	 * @throws StockExcepcion 
	 */
	@Transactional
	public Documento vender(Documento documento) throws StockExcepcion {

		String validar = validarExistencias(documento);
		if(!validar.isEmpty()) {
			throw new StockExcepcion(validar);
		}
		documento.setTipoDocumento(TipoDocumentoEnum.FAC);
		ponerDocumento(documento);
		documento = documentoDao.save(documento);
		documento.setNumeroDocumento("001-001-" + documento.getId().toString());
		documento.getDetalle().forEach(item -> kardexServicio.registrarKardexEgreso(item, TipoOperacionEnum.SALC));
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
		return documento;
	}

	/**
	 * Metodo utilitario que setear la cabecera en caso de venir sin el dato desde
	 * el frontend
	 * 
	 * @param documento
	 * @return
	 */
	private Documento ponerDocumento(Documento documento) {
		for (DetalleDocumento detalle : documento.getDetalle()) {
			detalle.setDocumento(documento);
		}
		return documento;
	}

	private String validarExistencias(Documento documento) {
		StringBuffer string = new StringBuffer();
//		productoServicio.obtenerStock(item.getProducto().getId()
		List<DetalleDocumento> detalles = documento.getDetalle().stream()
				.filter(item -> item.getCantidad() > productoServicio.obtenerStock(item.getProducto().getId()))
				.collect(Collectors.toList());
		if (detalles.size()==0 || detalles.isEmpty()) {
			return "";
		}
		else {
			detalles.forEach(item -> string.append(item.getProducto().getNombre()).append(", "));
		}
		return string.toString();
	}

}
