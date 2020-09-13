/**
 * 
 */
package org.home.spring.controlador;

import java.util.Date;

import org.home.spring.modelo.Documento;
import org.home.spring.servicio.DocumentoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "http://localhost:4200")
public class DocumentoRestControlador {

	@Autowired
	private DocumentoServicio documentoServicio;

	@PostMapping(value = "/guardar")
	public Documento guardar(@RequestBody Documento documento) {
		return documentoServicio.guardar(documento);
	}

	@PostMapping(value = "/comprar")
	public Documento comprar(@RequestBody Documento documento) {
		documento.setFecha(new Date());
		return documentoServicio.comprar(documento);
	}

	@PostMapping(value = "/vender")
	public Documento vender(@RequestBody Documento documento) {
		documento.setFecha(new Date());
		return documentoServicio.vender(documento);
	}


}
