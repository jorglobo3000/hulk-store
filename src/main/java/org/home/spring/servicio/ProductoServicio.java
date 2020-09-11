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
 * @author casa
 *
 */

@Service
public class ProductoServicio {

	@Autowired
	private ProductoDao productoDao;

	@Transactional
	public Producto guardar(Producto producto) {
		return productoDao.save(producto);
	}

	@Transactional(readOnly = true)
	public Producto obtenerPorId(Long id) {
		return productoDao.findById(id).orElse(null);
	}

	@Transactional(readOnly = true)
	public Long obtenerStock(Long id) {
		Producto producto = productoDao.findById(id).orElse(new Producto());
		return producto.getStock();
	}

	@Transactional(readOnly = true)
	public List<Producto> obtenerActivos() {
		return productoDao.findByEstado(EstadoEnum.ACT);
	}

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

	public Producto aumentarInventario(Long idProducto, Long cantidad) {
		Producto producto = obtenerPorId(idProducto);
		producto.setStock(producto.getStock() + cantidad);
		return producto;
	}

}
