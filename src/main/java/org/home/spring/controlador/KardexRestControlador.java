/**
 * 
 */
package org.home.spring.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.home.spring.modelo.Kardex;
import org.home.spring.modelo.Producto;
import org.home.spring.servicio.KardexServicio;
import org.home.spring.servicio.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador rest que forma parte del api de la tienda, permite la consulta
 * respectiva a los kardex de productos
 * 
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

	/**
	 * Servicio que permite conocer la lista de movimientos de un producto
	 * conociendo su id
	 * 
	 * @param id
	 * @return retorna la respuesta con la lista de movimientos y de ocurrir error
	 *         al consultar retorna un mapa con el mensaje de error y el respectivo
	 *         codigo
	 */
	@GetMapping(value = "/listar/{id}")
	public ResponseEntity<?> listar(@PathVariable Long id) {
		try {
			Producto producto = productoServicio.obtenerPorId(id);
			if (producto == null) {
				Map<String, String> respuesta = new HashMap<>();
				respuesta.put("mensaje", "El producto no existe en la base de datos");
				return new ResponseEntity<Map<String, String>>(respuesta, HttpStatus.NOT_FOUND);
			}
			List<Kardex> listaKardex = kardexServicio.obtenerKardexProducto(producto);
			return new ResponseEntity<List<Kardex>>(listaKardex, HttpStatus.OK);

		} catch (DataAccessException e) {
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("mensaje", "Error al obtener registros");
			return new ResponseEntity<Map<String, String>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
