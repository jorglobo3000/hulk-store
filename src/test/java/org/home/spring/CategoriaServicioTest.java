/**
 * 
 */
package org.home.spring;

import java.util.ArrayList;
import java.util.List;

import org.home.spring.dao.CategoriaDao;
import org.home.spring.modelo.Categoria;
import org.home.spring.servicio.CategoriaServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author casa
 *
 */
@SpringBootTest
public class CategoriaServicioTest {

	@Autowired
	private CategoriaServicio categoriaServicio;
	@Mock
	private CategoriaDao categoriaDao;

	@Test
	public void deberiaListarCategorias() {
		List<Categoria> listaCategorias = new ArrayList<>();
		listaCategorias.add(new Categoria());
		Iterable<Categoria> categorias = listaCategorias;
		categoriaDao = Mockito.mock(CategoriaDao.class);
		Mockito.when(categoriaDao.findAll()).thenReturn(categorias);
		Assertions.assertNotNull(categoriaServicio.listarCategorias(), "correcto");
	}
	
	@Test
	public void deberiaObtenerPorId() {
		Assertions.assertNotNull(categoriaServicio.obtenerPorId(1l), "el objeto no es nulo");
	}
}
