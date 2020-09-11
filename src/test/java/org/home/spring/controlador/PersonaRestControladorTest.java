/**
 * 
 */
package org.home.spring.controlador;

import org.home.spring.modelo.Persona;
import org.home.spring.modelo.enumerado.EstadoEnum;
import org.home.spring.modelo.enumerado.TipoPersonaEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author casa
 *
 */

@SpringBootTest
public class PersonaRestControladorTest {

	@Autowired
	private PersonaRestControlador personaRestControlador;

	@Test
	public void deberiaListarClientes() {
		Assertions.assertNotNull(personaRestControlador.listarClientes());
	}

	@Test
	public void deberiaCrearPersona() {
		Persona persona = new Persona();
		persona.setCiudad("Quito");
		persona.setCorreoElectronico("prueba@prueba.com");
		persona.setDireccion("Quito - Centro historico");
		persona.setEstado(EstadoEnum.ACT);
		persona.setIdentificacion("1002976981");
		persona.setNombre("Jorge Villarruel");
		persona.setTelefono("0");
		persona.setTipoPersona(TipoPersonaEnum.CLI);
		Assertions.assertNotNull(personaRestControlador.crearPersona(persona));
	}

}
