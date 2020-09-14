/**
 * 
 */
package org.home.spring.controlador;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.home.spring.modelo.DetalleDocumento;
import org.home.spring.modelo.Documento;
import org.home.spring.servicio.DocumentoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador rest que forma parte del api de la tienda, permite registrar los
 * 2 movimientos de los productos, mediante una compra y una venta
 * 
 * @author casa
 *
 */

@RestController
@RequestMapping(value = "api/documentos")
@CrossOrigin(origins = "http://localhost:4200")
public class DocumentoRestControlador {

	@Autowired
	private DocumentoServicio documentoServicio;

	/**
	 * Servicio que permite al usuario administrador de la tienda, comprar productos
	 * aumentando su stock en las existencias
	 * 
	 * @param documento
	 * @return la respuesta con el detalle de la compra, en caso de ocurrir un error
	 *         retorna un mapa con el mensaje de error y el codigo respectivo
	 */
	@PostMapping(value = "/comprar")
	public ResponseEntity<?> comprar(@RequestBody DetalleDocumento documento) {
		try {

			if (documento.getCantidad() <= 0l || documento.getSubtotalProducto().compareTo(BigDecimal.ZERO) < 1) {
				Map<String, String> respuesta = new HashMap<>();
				respuesta.put("mensaje", "Debe añadir una cantidad y precio mayor a 0");
				return new ResponseEntity<Map<String, String>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			DetalleDocumento detalleDocumento = documentoServicio.comprar(documento);
			if (detalleDocumento == null) {
				Map<String, String> respuesta = new HashMap<>();
				respuesta.put("mensaje", "Error al registrar el stock");
				return new ResponseEntity<Map<String, String>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<DetalleDocumento>(detalleDocumento, HttpStatus.OK);
		} catch (Exception e) {
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("mensaje", "Ocurrio un error al intentar aumentar el stock del producto");
			return new ResponseEntity<Map<String, String>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * Servicio que permite al cliente registrado realizar una compra de los
	 * productos de su carrito de compras
	 * 
	 * @param documento
	 * @return retorna la factura de la compra, en caso de error retorna un mapa con
	 *         el mensaje de error y el codigo respectivo
	 */
	@PostMapping(value = "/vender")
	public ResponseEntity<?> vender(@RequestBody Documento documento) {
		try {
			documento.setFecha(new Date());
			Documento doc = documentoServicio.vender(documento);
			return new ResponseEntity<Documento>(doc, HttpStatus.OK);
		} catch (Exception e) {
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("mensaje", "Ocurrio un error al realizar la venta");
			return new ResponseEntity<Map<String, String>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
