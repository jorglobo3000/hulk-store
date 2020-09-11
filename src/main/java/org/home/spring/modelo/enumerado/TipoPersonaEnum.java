/**
 * 
 */
package org.home.spring.modelo.enumerado;

/**
 * @author casa
 *
 */
public enum TipoPersonaEnum {
	CLI("Cliente"), PRO("Proveedor");

	private String descripcion;

	private TipoPersonaEnum(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
}
