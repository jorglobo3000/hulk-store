/**
 * 
 */
package org.home.spring.servicio;

import java.math.BigDecimal;
import java.util.List;

import org.home.spring.dao.KardexDao;
import org.home.spring.modelo.DetalleDocumento;
import org.home.spring.modelo.Kardex;
import org.home.spring.modelo.Producto;
import org.home.spring.modelo.enumerado.TipoMovimientoEnum;
import org.home.spring.modelo.enumerado.TipoOperacionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author casa
 *
 */
@Service
public class KardexServicio {

	@Autowired
	private KardexDao kardexDao;

	@Autowired
	private ProductoServicio productoServicio;

	@Transactional
	public void registrarKardexIngreso(DetalleDocumento detalleDocumento, TipoOperacionEnum tipoOperacion) {
		Kardex kardex = registrarKardex(detalleDocumento);
		kardex.setTipoMovimiento(TipoMovimientoEnum.ING);
		Producto producto = productoServicio.aumentarInventario(detalleDocumento.getProducto().getId(),
				detalleDocumento.getCantidad());
		if (producto.getPrecioCompra() == BigDecimal.ZERO) {
			producto.setPrecioCompra(detalleDocumento.getProducto().getPrecioCompra());
			producto.setPrecioVenta(producto.getPrecioCompra().multiply(new BigDecimal(1.15)));
		}
		kardex.setSaldoCantidad(producto.getStock());
		kardex.setSaldoPrecioUnitario(producto.getPrecioVenta().multiply(new BigDecimal(kardex.getCantidad())));
		kardex.setTipoOperacion(tipoOperacion);
		kardexDao.save(kardex);
		productoServicio.guardar(producto);
	}

	@Transactional
	public void registrarKardexEgreso(DetalleDocumento detalleDocumento, TipoOperacionEnum tipoOperacion) {
		Kardex kardex = registrarKardex(detalleDocumento);
		kardex.setTipoMovimiento(TipoMovimientoEnum.EGR);
		Producto producto = productoServicio.disminuirInventario(detalleDocumento.getProducto().getId(),
				detalleDocumento.getCantidad());
		kardex.setSaldoCantidad(producto.getStock());
		kardex.setSaldoPrecioUnitario(producto.getPrecioVenta().multiply(new BigDecimal(kardex.getCantidad())));
		kardex.setTipoOperacion(tipoOperacion);
		kardexDao.save(kardex);
		productoServicio.guardar(producto);
	}

	private Kardex registrarKardex(DetalleDocumento detalleDocumento) {
		Kardex kardex = new Kardex();
		kardex.setCantidad(detalleDocumento.getCantidad());
		kardex.setDocumento(detalleDocumento.getDocumento());
		kardex.setFecha(detalleDocumento.getDocumento().getFecha());
		kardex.setPrecioUnitario(detalleDocumento.getProducto().getPrecioVenta()
				.multiply(new BigDecimal(detalleDocumento.getCantidad())));
		kardex.setProducto(detalleDocumento.getProducto());

		return kardex;
	}

	@Transactional(readOnly = true)
	public List<Kardex> obtenerKardexProducto(Producto producto) {
		return kardexDao.findByProducto(producto);

	}
}
