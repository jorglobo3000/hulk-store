/**
 * 
 */
package org.home.spring.modelo.enumerado;

/**
 * @author casa
 *
 */
public enum TipoMovimientoEnum {
	ING("Ingreso"), EGR("EGRESO");

	private String descripcion;

	private TipoMovimientoEnum(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
}
