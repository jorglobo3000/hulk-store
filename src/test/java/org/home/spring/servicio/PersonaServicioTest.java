/**
 * 
 */
package org.home.spring.servicio;

import java.util.ArrayList;
import java.util.List;

import org.home.spring.modelo.Persona;
import org.home.spring.modelo.enumerado.TipoPersonaEnum;
import org.home.spring.servicio.PersonaServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author casa
 *
 */
@SpringBootTest
public class PersonaServicioTest {

	@Autowired
	private PersonaServicio personaServicio;

	@Test
	public void deberiaObtenerPorId() {
		Assertions.assertNotNull(personaServicio.obtenerPorId(1l), "Hay respuesta");
	}

	@Test
	public void deberiaListarPersonas() {
		List<Persona> personas = new ArrayList<>();
		Persona per = personaServicio.obtenerPorId(1l);
		personas.add(per);
		Assertions.assertTrue(personaServicio.listarPersonas().size() > 0l);
	}

	@Test
	public void deberiaListarPorTipoPersona() {
		List<Persona> personas = new ArrayList<>();
		Persona per = personaServicio.obtenerPorId(1l);
		personas.add(per);
		Assertions.assertTrue(personaServicio.listarPorTipoPersona(TipoPersonaEnum.CLI).size() > 0l);
	}

	@Test
	public void deberiaGuardar() {
		Persona persona = personaServicio.obtenerPorId(1L);
		persona.setNombre(persona.getNombre().toUpperCase());
		personaServicio.guardar(persona);
		Assertions.assertEquals("CONSUMIDOR FINAL", persona.getNombre());
	}
}
