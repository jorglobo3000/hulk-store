/**
 * 
 */
package org.home.spring.modelo.enumerado;

/**
 * @author casa
 *
 */
public enum FormaPagoEnum {
	EFECTIVO("Pago en efectivo"), DEBITO("Debito bancario"), CREDITO("Tarjeta de credito"), CHEQUE("Cheque");

	private String descripcion;

	private FormaPagoEnum(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDecripcion() {
		return this.descripcion;
	}
}
