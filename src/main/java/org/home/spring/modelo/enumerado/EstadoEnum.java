/**
 * 
 */
package org.home.spring.modelo.enumerado;

/**
 * Enumerado que permite manejar los estados de los registros de la base de
 * datos
 * 
 * @author casa
 *
 */
public enum EstadoEnum {
	ACT("Activo"), INA("Inactivo");

	private String descripcion;

	private EstadoEnum(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

}
