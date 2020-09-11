/**
 * 
 */
package org.home.spring.servicio;

import org.home.spring.modelo.Producto;
import org.home.spring.servicio.ProductoServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author casa
 *
 */

@SpringBootTest
public class ProductoServicioTest {

	@Autowired
	private ProductoServicio productoServicio;

	@Test
	public void deberiaObtenerPorId() {
		Assertions.assertNotNull(productoServicio.obtenerPorId(1l), "Si ingresa ");
	}

	@Test
	public void deberiaGuardar() {
		Producto producto = productoServicio.obtenerPorId(1l);
		producto.setNombre(producto.getNombre().toUpperCase());
		productoServicio.guardar(producto);
		Assertions.assertTrue(
				productoServicio.obtenerPorId(1l).getNombre().equals("Camiseta Hulk talla M".toUpperCase()));
	}

	@Test
	public void deberiaObtenerStock() {
		Long stock = productoServicio.obtenerStock(1l);
		Assertions.assertNotNull(stock, "Tiene un stock valido");
	}

	@Test
	public void deberiaObtenerActivos() {
		Assertions.assertTrue(productoServicio.obtenerActivos().size() > 0);
	}

	@Test
	public void deberiaDisminuirInventario() {
		Assertions.assertTrue(productoServicio.disminuirInventario(1l, 5l).getStock() > 0l);
	}

	@Test
	public void deberiaAumentarInventario() {
		Assertions.assertTrue(productoServicio.aumentarInventario(3l, 5l).getStock().equals(25L));
	}

}
