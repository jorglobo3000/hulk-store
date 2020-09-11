/**
 * 
 */
package org.home.spring.controlador;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import org.home.spring.modelo.DetalleDocumento;
import org.home.spring.modelo.Documento;
import org.home.spring.modelo.enumerado.EstadoEnum;
import org.home.spring.modelo.enumerado.FormaPagoEnum;
import org.home.spring.modelo.enumerado.TipoDocumentoEnum;
import org.home.spring.servicio.DocumentoServicio;
import org.home.spring.servicio.PersonaServicio;
import org.home.spring.servicio.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author casa
 *
 */

@RestController
@RequestMapping(value = "api/documentos")
public class DocumentoRestControlador {

	@Autowired
	private DocumentoServicio documentoServicio;

	@Autowired
	private PersonaServicio personaServicio;

	@Autowired
	private ProductoServicio productoServicio;

	@PostMapping(value = "/guardar")
	public Documento guardar(@RequestBody Documento documento) {
		return documentoServicio.guardar(documento);
	}

	@PostMapping(value = "/comprar")
	public Documento comprar(@RequestBody Documento documento) {
		return documentoServicio.comprar(documento);
	}

	@PostMapping(value = "/vender")
	public Documento vender(@RequestBody Documento documento) {
		return documentoServicio.vender(documento);
	}

	@GetMapping(value = "/")
	public Documento obtenerDocumentoNuevo() {
		Documento documento = new Documento();
		documento.setEstado(EstadoEnum.ACT);
		documento.setFecha(new Date());
		documento.setFormaPago(FormaPagoEnum.EFECTIVO);
		documento.setPersona(personaServicio.obtenerPorId(1l));
		documento.setTipoDocumento(TipoDocumentoEnum.CAR);
		documento.setDetalle(new ArrayList<>());
		DetalleDocumento detalleDocumento = new DetalleDocumento();
		detalleDocumento.setCantidad(5l);
		detalleDocumento.setEstado(EstadoEnum.ACT);
		detalleDocumento.setProducto(productoServicio.obtenerPorId(2l));
		detalleDocumento.setSubtotalProducto(detalleDocumento.getProducto().getPrecioVenta()
				.multiply(new BigDecimal(detalleDocumento.getCantidad())));
		documento.getDetalle().add(detalleDocumento);
		return documento;
	}

}
