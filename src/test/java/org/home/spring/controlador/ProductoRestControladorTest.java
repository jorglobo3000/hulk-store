/**
 * 
 */
package org.home.spring.controlador;

import org.home.spring.modelo.Producto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author casa
 *
 */
@SpringBootTest
public class ProductoRestControladorTest {

	@Autowired
	private ProductoRestControlador productoRestControlador;

	@Test
	public void deberiaListarProductos() {
		Assertions.assertNotNull(productoRestControlador.listarProductos());
	}

	@Test
	public void deberiaObtenerPorId() {
		Assertions.assertNotNull(productoRestControlador.obtenerPorId(1l));
	}

	@Test
	public void deberiaObtenerStock() {
		Assertions.assertTrue(productoRestControlador.obtenerStock(1l) > 0l);
	}
	
	@Test
	public void deberiaGuardar() {
		Producto producto = productoRestControlador.obtenerPorId(1l);
		producto.setStock(10000l);
		Assertions.assertNotNull(productoRestControlador.guardar(producto));
	}
}
