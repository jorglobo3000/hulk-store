/**
 * 
 */
package org.home.spring.controlador;

import java.util.List;

import org.home.spring.modelo.Categoria;
import org.home.spring.servicio.CategoriaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REst controlaor que forma parte del api y permite acceso a listar las
 * categorias de los productos
 * 
 * @author casa
 *
 */
@RestController
@RequestMapping(value = "api/categorias")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoriaRestControlador {

	@Autowired
	private CategoriaServicio categoriaServicio;

	/**
	 * Servicio que permite listar las categorias
	 * 
	 * @return
	 */
	@GetMapping(value = "/listar")
	public List<Categoria> listarCategorias() {
		return categoriaServicio.listarCategorias();
	}
}
