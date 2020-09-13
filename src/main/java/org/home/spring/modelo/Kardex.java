/**
 * 
 */
package org.home.spring.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.home.spring.modelo.enumerado.TipoMovimientoEnum;
import org.home.spring.modelo.enumerado.TipoOperacionEnum;


/**
 * Clase que representa a la tabla kardex y que permite llevar el historico de
 * movimientos realizados en un producto
 * 
 * @author casa
 *
 */
@Entity
@Table(name = "kardex")
public class Kardex implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "fecha", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	@ManyToOne
	@JoinColumn(name = "id_documento")
	private Documento documento;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_operacion", nullable = false)
	private TipoOperacionEnum tipoOperacion;

	@ManyToOne
	@JoinColumn(name = "id_producto")
	private Producto producto;

	@Column(name = "cantidad", nullable = false)
	private Long cantidad;

	@Column(name = "precio_unitario", nullable = false, precision = 15, scale = 4)
	private BigDecimal precioUnitario;

	@Column(name = "saldo_cantidad", nullable = false)
	private Long saldoCantidad;

	@Column(name = "saldo_precio_unitario", nullable = false, precision = 15, scale = 4)
	private BigDecimal saldoPrecioUnitario;

	@Column(name = "tipo_movimiento")
	@Enumerated(EnumType.STRING)
	private TipoMovimientoEnum tipoMovimiento;
	
	@Transient
	private String detalle;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public TipoOperacionEnum getTipoOperacion() {
		return tipoOperacion;
	}

	public void setTipoOperacion(TipoOperacionEnum tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
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

	public BigDecimal getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public Long getSaldoCantidad() {
		return saldoCantidad;
	}

	public void setSaldoCantidad(Long saldoCantidad) {
		this.saldoCantidad = saldoCantidad;
	}

	public BigDecimal getSaldoPrecioUnitario() {
		return saldoPrecioUnitario;
	}

	public void setSaldoPrecioUnitario(BigDecimal saldoPrecioUnitario) {
		this.saldoPrecioUnitario = saldoPrecioUnitario;
	}

	public TipoMovimientoEnum getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(TipoMovimientoEnum tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public String getDetalle() {
		return this.tipoOperacion.getDescripcion()+" con documento: "+this.documento.getNumeroDocumento();
	}
}
