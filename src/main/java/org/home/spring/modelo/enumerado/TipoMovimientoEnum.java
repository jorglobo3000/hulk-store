/**
 * 
 */
package org.home.spring.modelo.enumerado;

/**
 * Enumerado que permite manejar los tipos de movimientos de la tienda, sea
 * ingreso o egreso
 * 
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
