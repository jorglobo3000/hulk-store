/**
 * 
 */
package org.home.spring.controlador;

import java.util.List;

import org.home.spring.modelo.Persona;
import org.home.spring.modelo.enumerado.TipoPersonaEnum;
import org.home.spring.servicio.PersonaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author casa
 *
 */
@RestController
@RequestMapping(value = "api/clientes")
public class PersonaRestControlador {

	@Autowired
	private PersonaServicio personaServicio;

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public List<Persona> listarClientes() {
		return personaServicio.listarPorTipoPersona(TipoPersonaEnum.CLI);
	}

	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public Persona crearPersona(@RequestBody Persona persona) {
		return personaServicio.guardar(persona);
	}
}
