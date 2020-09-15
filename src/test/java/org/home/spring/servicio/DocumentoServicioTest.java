/**
 * 
 */
package org.home.spring.servicio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import org.home.spring.excepcion.StockExcepcion;
import org.home.spring.modelo.DetalleDocumento;
import org.home.spring.modelo.Documento;
import org.home.spring.modelo.enumerado.EstadoEnum;
import org.home.spring.modelo.enumerado.TipoDocumentoEnum;
import org.home.spring.servicio.DetalleDocumentoServicio;
import org.home.spring.servicio.DocumentoServicio;
import org.home.spring.servicio.PersonaServicio;
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
public class DocumentoServicioTest {

	@Autowired
	private DocumentoServicio documentoServicio;

	@Autowired
	private PersonaServicio personaServicio;

	@Autowired
	private ProductoServicio productoServicio;

	@Autowired
	private DetalleDocumentoServicio detalleDocumentoServicio;

	private Documento documento;

	private void conf() {
		documento = new Documento();
		documento.setEstado(EstadoEnum.ACT);
		documento.setFecha(new Date());
		documento.setIva(BigDecimal.ZERO);
		documento.setPersona(personaServicio.obtenerPorId(1l));
		documento.setSubtotal(BigDecimal.ZERO);
		documento.setTotal(BigDecimal.ZERO);
		documento.setTipoDocumento(TipoDocumentoEnum.CAR);
		documento.setDetalle(new ArrayList<>());
		DetalleDocumento detalleDocumento = new DetalleDocumento();
		detalleDocumento.setCantidad(2l);
		detalleDocumento.setDocumento(documento);
		detalleDocumento.setEstado(EstadoEnum.ACT);
		detalleDocumento.setProducto(productoServicio.obtenerPorId(1l));
		detalleDocumento.setSubtotalProducto(detalleDocumento.getProducto().getPrecioCompra()
				.multiply(new BigDecimal(detalleDocumento.getCantidad())));
		documento.getDetalle().add(detalleDocumento);
	}

	@Test
	public void guardar() {
		conf();
		this.documento.setId(null);
		Assertions.assertTrue(documentoServicio.guardar(documento).getId() != null);
	}

	@Test
	public void vender() throws StockExcepcion {
		conf();
		Assertions.assertTrue(documentoServicio.vender(documento).getTipoDocumento().equals(TipoDocumentoEnum.FAC));
	}

	@Test
	public void comprar() {
		conf();
		DetalleDocumento detalleDocumento = documento.getDetalle().get(0);
		detalleDocumento.setDocumento(null);
		Assertions.assertNotNull(documentoServicio.comprar(detalleDocumento));
	}

	@Test
	public void deberiaGuardarDetalleDocumento() {
		conf();
		documentoServicio.guardar(documento);
		DetalleDocumento detalle = documento.getDetalle().get(0);
		detalle.setCantidad(12345L);
		Assertions.assertTrue(detalleDocumentoServicio.guardar(detalle).getCantidad().equals(12345l));
	}

}
