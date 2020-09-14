/**
 * 
 */
package org.home.spring.servicio;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
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
 * Clase encargada de proveeer los metodos para registrar los diferentes
 * movimientos de un producto
 * 
 * @author casa
 *
 */
@Service
public class KardexServicio {

	@Autowired
	private KardexDao kardexDao;

	@Autowired
	private ProductoServicio productoServicio;

	/**
	 * Metodo encargado de registrar un ingreso de un producto
	 * 
	 * @param detalleDocumento
	 * @param tipoOperacion
	 */
	@Transactional
	public void registrarKardexIngreso(DetalleDocumento detalleDocumento, TipoOperacionEnum tipoOperacion) {
		
		Kardex kardex = registrarKardex(detalleDocumento);
		kardex.setTipoMovimiento(TipoMovimientoEnum.ING);
		Producto producto = productoServicio.aumentarInventario(detalleDocumento.getProducto().getId(),
				detalleDocumento.getCantidad());
		if (producto.getStock() == 0l) {
			producto.setPrecioCompra(detalleDocumento.getProducto().getPrecioCompra());
			producto.setPrecioVenta(detalleDocumento.getProducto().getPrecioVenta());
		} else {
			producto.setPrecioVenta(calcularPromedio(detalleDocumento, producto));
		}
		kardex.setCantidad(detalleDocumento.getCantidad());
		kardex.setPrecioUnitario(detalleDocumento.getSubtotalProducto());
		kardex.setTotal(kardex.getPrecioUnitario().multiply(new BigDecimal(kardex.getCantidad())));
		kardex.setSaldoCantidad(producto.getStock());
		kardex.setSaldoPrecioUnitario(producto.getPrecioVenta());
		kardex.setSaldoTotal(kardex.getSaldoPrecioUnitario().multiply(new BigDecimal(kardex.getSaldoCantidad())));
		kardex.setTipoOperacion(tipoOperacion);
		kardexDao.save(kardex);
		productoServicio.guardar(producto);
	}

	/**
	 * Metodo que calcula el promedio ponderado cuando se realzia un movimiento para
	 * aumentar el stock
	 * 
	 * @param detalleDocumento
	 * @param producto
	 * @return
	 */
	private BigDecimal calcularPromedio(DetalleDocumento detalleDocumento, Producto producto) {
		Long cantidadTotal = producto.getStock();
		BigDecimal totalIngresos = detalleDocumento.getSubtotalProducto()
				.multiply(new BigDecimal(detalleDocumento.getCantidad()));
		BigDecimal totalExistencias = producto.getPrecioVenta()
				.multiply(new BigDecimal(detalleDocumento.getProducto().getStock()));
		totalExistencias = totalExistencias.add(totalIngresos);

		return totalExistencias.divide(new BigDecimal(cantidadTotal), RoundingMode.HALF_UP);
	}

	/**
	 * Metodo encargado de registrar un egreso de un producto
	 * 
	 * @param detalleDocumento
	 * @param tipoOperacion
	 */
	@Transactional
	public void registrarKardexEgreso(DetalleDocumento detalleDocumento, TipoOperacionEnum tipoOperacion) {
		Kardex kardex = registrarKardex(detalleDocumento);
		kardex.setTipoMovimiento(TipoMovimientoEnum.EGR);
		Producto producto = productoServicio.disminuirInventario(detalleDocumento.getProducto().getId(),
				detalleDocumento.getCantidad());
		kardex.setSaldoCantidad(producto.getStock());
		kardex.setPrecioUnitario(producto.getPrecioVenta());
		kardex.setSaldoPrecioUnitario(producto.getPrecioVenta());
		kardex.setTotal(producto.getPrecioVenta().multiply(new BigDecimal(kardex.getCantidad())));
		kardex.setSaldoTotal(kardex.getSaldoPrecioUnitario().multiply(new BigDecimal(kardex.getSaldoCantidad())));
		kardex.setTipoOperacion(tipoOperacion);
		kardexDao.save(kardex);
		productoServicio.guardar(producto);
	}

	private Kardex registrarKardex(DetalleDocumento detalleDocumento) {
		Kardex kardex = new Kardex();
		kardex.setCantidad(detalleDocumento.getCantidad());
		if (detalleDocumento.getDocumento() == null) {
			kardex.setDocumento(null);
			kardex.setFecha(new Date());
		} else {
			kardex.setDocumento(detalleDocumento.getDocumento());
			kardex.setFecha(detalleDocumento.getDocumento().getFecha());
		}
		kardex.setPrecioUnitario(detalleDocumento.getProducto().getPrecioCompra());
		kardex.setProducto(detalleDocumento.getProducto());
		kardex.setTotal(kardex.getPrecioUnitario().multiply(new BigDecimal(kardex.getCantidad())));
		return kardex;
	}

	/**
	 * Metodo que permite obtener la lista de movimientos de un producto conocido
	 * 
	 * @param producto
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Kardex> obtenerKardexProducto(Producto producto) {
		return kardexDao.findByProducto(producto);

	}
}
