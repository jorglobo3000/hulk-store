/**
 * 
 */
package org.home.spring.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.home.spring.modelo.enumerado.EstadoEnum;

/**
 * Entidad que mapea la tabla detalles_documento, donde se registrara el detalle
 * del documento(cabecera)
 * 
 * @author casa
 *
 */
@Entity
@Table(name = "Detalles_documento")
public class DetalleDocumento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_documento")
	private Documento documento;

	@ManyToOne
	@JoinColumn(name = "id_producto")
	private Producto producto;

	@Column(name = "cantidad")
	private Long cantidad;

	@Column(name = "subtotal_producto")
	private BigDecimal subtotalProducto;

	@Enumerated(EnumType.STRING)
	@Column(name = "estado")
	private EstadoEnum estado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getSubtotalProducto() {
		return subtotalProducto;
	}

	public void setSubtotalProducto(BigDecimal subtotalProducto) {
		this.subtotalProducto = subtotalProducto;
	}

	public EstadoEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}

}
