/**
 * 
 */
package org.home.spring.modelo.enumerado;

import java.util.Arrays;

/**
 * Enumerador que permite manejar los diferentes tipos de personas
 * 
 * @author casa
 *
 */
public enum TipoPersonaEnum {
	CLI("Cliente"), PRO("Proveedor"), ADM("Administrador");

	private String descripcion;

	private TipoPersonaEnum(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public TipoPersonaEnum obtenerPorCodigo(String codigo) {
		return Arrays.stream(TipoPersonaEnum.values()).filter(item -> item.equals(codigo)).findFirst().orElse(null);
	}
}
