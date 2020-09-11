/**
 * 
 */
package org.home.spring.controlador;

import java.util.List;

import org.home.spring.modelo.Producto;
import org.home.spring.servicio.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author casa
 *
 */
@RestController
@RequestMapping(value = "/api/productos")
public class ProductoRestControlador {

	@Autowired
	private ProductoServicio productoServicio;

	@GetMapping(value = "/listar")
	public List<Producto> listarProductos() {
		return productoServicio.obtenerActivos();
	}

	@GetMapping(value = "/listar/{id}")
	public Producto obtenerPorId(@PathVariable Long id) {
		return productoServicio.obtenerPorId(id);
	}

	@PostMapping(value = "/guardar")
	public Producto guardar(@RequestBody Producto producto) {
		return productoServicio.guardar(producto);
	}

	@GetMapping(value = "/stock/{id}")
	public Long obtenerStock(@PathVariable Long id) {
		return productoServicio.obtenerStock(id);
	}
}
