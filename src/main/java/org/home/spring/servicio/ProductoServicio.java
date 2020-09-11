/**
 * 
 */
package org.home.spring.servicio;

import java.util.List;

import org.home.spring.dao.ProductoDao;
import org.home.spring.modelo.Producto;
import org.home.spring.modelo.enumerado.EstadoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio que maneja la logica del producto
 * 
 * @author casa
 *
 */

@Service
public class ProductoServicio {

	@Autowired
	private ProductoDao productoDao;

	/**
	 * Metodo que permite registrar un producto
	 * 
	 * @param producto
	 * @return
	 */
	@Transactional
	public Producto guardar(Producto producto) {
		return productoDao.save(producto);
	}

	/**
	 * Metodo que permite obtener un producto conociendo su id
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public Producto obtenerPorId(Long id) {
		return productoDao.findById(id).orElse(null);
	}

	/**
	 * Metodo que permite conocer si un producto cuenta con un stock para poder
	 * realizar una venta
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public Long obtenerStock(Long id) {
		Producto producto = productoDao.findById(id).orElse(new Producto());
		return producto.getStock();
	}

	/**
	 * Metodo que lista unicamente los productos que se encuentren activos.
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Producto> obtenerActivos() {
		return productoDao.findByEstado(EstadoEnum.ACT);
	}

	/**
	 * Metodo que disminuye el inventario segun el documento ingresado
	 * 
	 * @param idProducto
	 * @param cantidad
	 * @return
	 */
	public Producto disminuirInventario(Long idProducto, Long cantidad) {
		Producto producto = obtenerPorId(idProducto);
		Long stock = 0L;
		if (producto != null) {
			stock = producto.getStock();
		}
		if (stock > 0l && stock >= cantidad) {
			producto.setStock(producto.getStock() - cantidad);
		}
		return producto;
	}

	/**
	 * Metodo que permite registrar un aumento de stock en un producto cuando se
	 * realiza una compra a un proveedor
	 * 
	 * @param idProducto
	 * @param cantidad
	 * @return
	 */
	public Producto aumentarInventario(Long idProducto, Long cantidad) {
		Producto producto = obtenerPorId(idProducto);
		producto.setStock(producto.getStock() + cantidad);
		return producto;
	}

}
