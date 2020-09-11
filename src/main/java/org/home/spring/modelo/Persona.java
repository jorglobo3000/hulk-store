/**
 * 
 */
package org.home.spring.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.home.spring.modelo.enumerado.EstadoEnum;
import org.home.spring.modelo.enumerado.TipoPersonaEnum;

/**
 * Clase que mapea la tabla personas que permite el registro de personas tanto
 * proveedores, asi como tambien clientes
 * 
 * @author casa
 *
 */

@Entity
@Table(name = "Personas")
public class Persona implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "identificacion", length = 13, nullable = false)
	private String identificacion;

	@Column(name = "nombre", length = 150, nullable = false)
	private String nombre;

	@Column(name = "direccion", length = 250, nullable = false)
	private String direccion;

	@Column(name = "ciudad", length = 50)
	private String ciudad;

	@Column(name = "telefono", length = 15, nullable = true)
	private String telefono;

	@Column(name = "correo_electronico", length = 120, nullable = true)
	private String correoElectronico;

	@Column(name = "tipo_persona", nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoPersonaEnum tipoPersona;

	@Column(name = "estado", nullable = false)
	@Enumerated(EnumType.STRING)
	private EstadoEnum estado;

	@Column(name = "username", length = 30, nullable = true)
	private String username;

	@Column(name = "password", nullable = true, length = 150)
	private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public TipoPersonaEnum getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(TipoPersonaEnum tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	public EstadoEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
