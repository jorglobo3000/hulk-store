/**
 * 
 */
package org.home.spring.modelo.enumerado;

/**
 * Enumerado que permite registrar los diferentes tipos de documentos
 * 
 * @author casa
 *
 */
public enum TipoDocumentoEnum {

	FAC("Factura"), GREM("Guia remision"), NCRE("Nota de credito"), CAR("Carrito compra");

	private String descripcion;

	private TipoDocumentoEnum(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}
}
