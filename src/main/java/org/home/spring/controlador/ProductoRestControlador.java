/**
 * 
 */
package org.home.spring.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.home.spring.modelo.Producto;
import org.home.spring.servicio.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author casa
 *
 */
@RestController
@RequestMapping(value = "/api/productos")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductoRestControlador {

	@Autowired
	private ProductoServicio productoServicio;

	@GetMapping(value = "/listar")
	public ResponseEntity<?> listarProductos() {
		try {
			List<Producto> productos = productoServicio.obtenerActivos();
			return new ResponseEntity<List<Producto>>(productos, HttpStatus.OK);
		} catch (DataAccessException e) {
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("mensaje", "Ocurrio un error al listar los productos");
			return new ResponseEntity<Map<String, String>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/listar/{id}")
	public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
		try {
			Producto producto = productoServicio.obtenerPorId(id);
			if (producto == null) {
				Map<String, String> respuesta = new HashMap<>();
				respuesta.put("mensaje", "El producto no existe en la base de datos");
				return new ResponseEntity<Map<String, String>>(respuesta, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Producto>(producto, HttpStatus.OK);
		} catch (DataAccessException e) {
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("mensaje", "Ocurrio un error al listar los productos");
			return new ResponseEntity<Map<String, String>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/guardar")
	public ResponseEntity<?> guardar(@RequestBody Producto producto) {
		try {
			Producto product = productoServicio.guardar(producto);
			return new ResponseEntity<Producto>(product, HttpStatus.OK);
		} catch (DataAccessException e) {
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("mensaje", "Ocurrio un error al listar los productos");
			return new ResponseEntity<Map<String, String>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/stock/{id}")
	public ResponseEntity<?> obtenerStock(@PathVariable Long id) {
		try {
			Long stock = productoServicio.obtenerStock(id);
			return new ResponseEntity<Long>(stock, HttpStatus.OK);
		} catch (DataAccessException e) {
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("mensaje", "Ocurrio un error al obtener stock");
			return new ResponseEntity<Map<String, String>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
