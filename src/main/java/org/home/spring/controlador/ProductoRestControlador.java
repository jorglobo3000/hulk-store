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
 * Controlador Rest que forma parte del api de la tienda, para manejo de
 * productos y sus interacciones
 * 
 * @author casa
 *
 */
@RestController
@RequestMapping(value = "/api/productos")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductoRestControlador {

	@Autowired
	private ProductoServicio productoServicio;

	/**
	 * Servicio que permite listar los productos para ser utilizados en el carrito
	 * de compras
	 * 
	 * @return respuesta con la lista de productos, en caso de error retorna un mapa
	 *         con el error y el estado del error
	 */
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

	/**
	 * Servicio que permite obtener un producto conociento su id por url
	 * 
	 * @param id
	 * @return respuesta con el producto y el estado Ok, en caso de error retorna un
	 *         mapa con el error y su codigo de error
	 */
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

	/**
	 * Servicio que permite persistir en la base de datos un producto
	 * 
	 * @param producto
	 * @return respuesta con el objeto persistido y su codigo de exito, en caso de
	 *         error retorna el mapa con el error y el codigo de errro
	 */
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

	/**
	 * Servicio que permite conocer el stock de un producto conociendo su id
	 * 
	 * @param id
	 * @return respuesta con el stock solicitado, en caso de error retorna un mapa
	 *         con el error y el codigo de error presentado
	 */
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
