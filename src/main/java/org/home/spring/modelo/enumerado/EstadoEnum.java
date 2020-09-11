/**
 * 
 */
package org.home.spring.modelo.enumerado;

/**
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
