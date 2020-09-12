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
import javax.persistence.Transient;

import org.home.spring.modelo.enumerado.EstadoEnum;

/**
 * Clase que mapea la tabla productos, que permite el registro de productos para
 * realizar las diferentes ventas
 * 
 * 
 * @author casa
 *
 */

@Entity
@Table(name = "Productos")
public class Producto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;

	@Column(name = "nombre", length = 100, nullable = false)
	private String nombre;

	@Column(name = "stock_emergencia", nullable = false)
	private Long stockEmergencia;

	@Column(name = "precio_compra", precision = 15, scale = 4, nullable = false)
	private BigDecimal precioCompra;

	@Column(name = "precio_venta", precision = 15, scale = 4, nullable = false)
	private BigDecimal precioVenta;

	@Column(name = "stock", nullable = false)
	private Long stock;

	@Column(name = "estado", nullable = false)
	@Enumerated(EnumType.STRING)
	private EstadoEnum estado;

	@Transient
	private Boolean enStock;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getStockEmergencia() {
		return stockEmergencia;
	}

	public void setStockEmergencia(Long stockEmergencia) {
		this.stockEmergencia = stockEmergencia;
	}

	public BigDecimal getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(BigDecimal precioCompra) {
		this.precioCompra = precioCompra;
	}

	public BigDecimal getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(BigDecimal precioVenta) {
		this.precioVenta = precioVenta;
	}

	public EstadoEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	public Boolean getEnStock() {
		return this.stock > 0l;
	}

}
