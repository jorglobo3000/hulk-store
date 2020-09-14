/**
 * 
 */
package org.home.spring.modelo.enumerado;

/**
 * Enumerado que permite manejar los diferentes tipos de operaciones de la
 * tienda
 * 
 * @author casa
 *
 */
public enum TipoOperacionEnum {
	INGC("Compra"), INGD("Devolucion en ventas"), SALC("Venta"), SALD("Devolucion en compras"),
	INVI("Inventario inicial");

	private String descripcion;

	private TipoOperacionEnum(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}
}
