/**
 * 
 */
package org.home.spring.controlador;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author casa
 *
 */

@SpringBootTest
public class CategoriaRestControladorTest {

	@Autowired
	CategoriaRestControlador categoriaRestControlador;

	@Test
	void deberiaListar() {
		Assertions.assertNotNull(categoriaRestControlador.listarCategorias());
	}
}
