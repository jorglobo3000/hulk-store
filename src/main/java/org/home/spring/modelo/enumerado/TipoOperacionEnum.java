/**
 * 
 */
package org.home.spring.modelo.enumerado;

/**
 * @author casa
 *
 */
public enum TipoOperacionEnum {
	INGC("Ingreso por compra a proveedor"), INGD("Ingreso por devolucion cliente"),
	SALC("Salida por compra de cliente"), SALD("Salida por devolucion a proveedor"), INVI("Inventario inicial");

	private String descripcion;

	private TipoOperacionEnum(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}
}
