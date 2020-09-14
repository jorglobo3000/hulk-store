/**
 * 
 */
package org.home.spring.controlador;

import java.math.BigDecimal;

import org.home.spring.modelo.DetalleDocumento;
import org.home.spring.modelo.enumerado.EstadoEnum;
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
public class DocumentoRestControladorTest {

	@Autowired
	DocumentoRestControlador documentoRestControlador;

	@Autowired
	ProductoServicio productoServicio;

	@Test
	void deberiaComprar() {
		DetalleDocumento detalleDocumento = new DetalleDocumento();
		detalleDocumento.setCantidad(2l);
		detalleDocumento.setDocumento(null);
		detalleDocumento.setEstado(EstadoEnum.ACT);
		detalleDocumento.setProducto(productoServicio.obtenerPorId(1l));
		detalleDocumento.setSubtotalProducto(detalleDocumento.getProducto().getPrecioCompra()
				.multiply(new BigDecimal(detalleDocumento.getCantidad())));
		Assertions.assertNotNull(documentoRestControlador.comprar(detalleDocumento));
	}
}
