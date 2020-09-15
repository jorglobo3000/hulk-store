/**
 * 
 */
package org.home.spring.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.home.spring.modelo.Persona;
import org.home.spring.modelo.enumerado.TipoPersonaEnum;
import org.home.spring.servicio.PersonaServicio;
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
 * Controlador Rest que forma parte del api de la tienda, permite el acceso a
 * los servicios para manejo de personas
 * 
 * 
 * @author casa
 *
 */
@RestController
@RequestMapping(value = "api/clientes")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonaRestControlador {

	@Autowired
	private PersonaServicio personaServicio;

	@PostMapping(value = "/guardar")
	public ResponseEntity<?> crearPersona(@RequestBody Persona persona) {
		try {
			if(persona.getIdentificacion()==null) {
				Map<String, String> respuesta = new HashMap<>();
				respuesta.put("mensaje", "LLene todos los campos del usuario");
				return new ResponseEntity<Map<String, String>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			persona = personaServicio.guardar(persona);
			return new ResponseEntity<Persona>(persona, HttpStatus.OK);
		} catch (DataAccessException e) {
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("mensaje", "No se pudo guardar el usuario");
			return new ResponseEntity<Map<String, String>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Servicio que permite obetner una persona conociendo su identificacion
	 * 
	 * @param identificacion
	 * @return respuesa con la persona y el codigo de error, en caso de error
	 *         retorna mapa con el mensaje de error y el codigo respectivo
	 */
	@GetMapping(value = "listar/{identificacion}")
	public ResponseEntity<?> buscarPorIdentificacion(@PathVariable String identificacion) {
		try {
			Persona persona = personaServicio.obtenerPorIdentificacion(identificacion);
			return new ResponseEntity<Persona>(persona, HttpStatus.OK);
		} catch (DataAccessException e) {
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("mensaje", "Error al obtener registros");
			return new ResponseEntity<Map<String, String>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Servicio basico de autenticacion a la tienda usado para tener navegabilidad
	 * entre las opciones de administrador y opciones del cliente
	 * 
	 * @param persona
	 * @return respuesta con la persona y en caso de error retorna un mapa con el
	 *         mensaje de error y el codigo respectivo
	 */
	@PostMapping(value = "login")
	public ResponseEntity<?> obtenerPorLogin(@RequestBody Persona persona) {
		try {
			Persona per = personaServicio.login(persona.getUsername(), persona.getPassword());
			if (per == null) {
				Map<String, String> respuesta = new HashMap<>();
				respuesta.put("mensaje", "No se encuentra registrado en la base de datos");
				return new ResponseEntity<Map<String, String>>(respuesta, HttpStatus.NOT_FOUND);
			}

			per.setPassword(null);
			return new ResponseEntity<Persona>(per, HttpStatus.OK);

		} catch (Exception e) {
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("mensaje", "Usuario o contraseña incorrecta");
			return new ResponseEntity<Map<String, String>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Servicio que permite listar las personas conociento el tipo
	 * 
	 * @param tipoPersona
	 * @return lista de personas
	 */
	@GetMapping(value = "/listar-tipo/[tipoPersona]")
	public List<Persona> listarPorTipoPersona(@PathVariable String tipoPersona) {
		return personaServicio.listarPorTipoPersona(TipoPersonaEnum.valueOf(tipoPersona));
	}
}
