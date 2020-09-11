/**
 * 
 */
package org.home.spring.servicio;

import java.util.List;

import org.home.spring.dao.CategoriaDao;
import org.home.spring.modelo.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Clase encargada de proveer los metodos para acceder a los listados de
 * categorias y su busqueda conociendo el id de la misma
 * 
 * @author casa
 *
 */

@Service
public class CategoriaServicio {

	@Autowired
	private CategoriaDao categoriaDao;

	@Transactional(readOnly = true)
	public List<Categoria> listarCategorias() {
		return (List<Categoria>) categoriaDao.findAll();
	}

	@Transactional(readOnly = true)
	public Categoria obtenerPorId(Long id) {
		return categoriaDao.findById(id).orElse(null);
	}
}
