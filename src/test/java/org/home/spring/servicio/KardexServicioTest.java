/**
 * 
 */
package org.home.spring.servicio;

import org.home.spring.servicio.KardexServicio;
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
public class KardexServicioTest {

	@Autowired
	private KardexServicio kardexServicio;

	@Autowired
	private ProductoServicio productoServicio;

	@Test
	public void deberiaObtenerKardexProducto() {
		Assertions.assertNotNull(kardexServicio.obtenerKardexProducto(productoServicio.obtenerPorId(1l)));
	}
}
