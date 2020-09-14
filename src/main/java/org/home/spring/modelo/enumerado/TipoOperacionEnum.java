/**
 * 
 */
package org.home.spring.modelo.enumerado;

/**
 * @author casa
 *
 */
public enum TipoOperacionEnum {
	INGC("Compra"), INGD("Devolucion en ventas"),
	SALC("Venta"), SALD("Devolucion en compras"), INVI("Inventario inicial");

	private String descripcion;

	private TipoOperacionEnum(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}
}
