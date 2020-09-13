/**
 * 
 */
package org.home.spring.controlador;

import java.util.List;

import org.home.spring.modelo.Kardex;
import org.home.spring.modelo.Producto;
import org.home.spring.servicio.KardexServicio;
import org.home.spring.servicio.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author casa
 *
 */
@RestController
@RequestMapping(value = "api/kardex")
@CrossOrigin(origins = "http://localhost:4200")
public class KardexRestControlador {

	@Autowired
	private KardexServicio kardexServicio;
	
	@Autowired
	private ProductoServicio productoServicio;
	
	@GetMapping(value = "/listar/{id}")
	public List<Kardex> listar(@PathVariable Long id){
		Producto producto = productoServicio.obtenerPorId(id);
		return kardexServicio.obtenerKardexProducto(producto);
	}
}
