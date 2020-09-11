/**
 * 
 */
package org.home.spring.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.home.spring.modelo.enumerado.EstadoEnum;
import org.home.spring.modelo.enumerado.FormaPagoEnum;
import org.home.spring.modelo.enumerado.TipoDocumentoEnum;

/**
 * Entidad que mapea a la tabla Documentos, que permite registrar los documentos
 * que generan un movimiento en el kardex de productos
 * 
 * @author casa
 *
 */

@Table(name = "Documentos")
@Entity
public class Documento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "numero_documento", nullable = true)
	private String numeroDocumento;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha", nullable = false)
	private Date fecha;

	@ManyToOne
	@JoinColumn(name = "id_përsona")
	private Persona persona;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_documento")
	private TipoDocumentoEnum tipoDocumento;

	@Column(name = "valor_subtotal", nullable = false, precision = 15, scale = 4)
	private BigDecimal valorSubtotal;

	@Column(name = "valor_total", nullable = false, precision = 15, scale = 4)
	private BigDecimal valorTotal;

	@Enumerated(EnumType.STRING)
	@Column(name = "estado")
	private EstadoEnum estado;

	@Column(name = "forma_pago")
	@Enumerated(EnumType.STRING)
	private FormaPagoEnum formaPago;

	@Column(name = "numero_tarjeta", nullable = true, length = 25)
	private String numeroTarjeta;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "documento", cascade = CascadeType.ALL)
	private List<DetalleDocumento> detalle;

	@Column(name = "subtotal", nullable = false, precision = 15, scale = 4)
	private BigDecimal subtotal;

	@Column(name = "iva", nullable = false, precision = 15, scale = 4)
	private BigDecimal iva;

	@Column(name = "total", nullable = false, precision = 15, scale = 4)
	private BigDecimal total;

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

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public TipoDocumentoEnum getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumentoEnum tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public BigDecimal getValorSubtotal() {
		return valorSubtotal;
	}

	public void setValorSubtotal(BigDecimal valorSubtotal) {
		this.valorSubtotal = valorSubtotal;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public EstadoEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}

	public FormaPagoEnum getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(FormaPagoEnum formaPago) {
		this.formaPago = formaPago;
	}

	public List<DetalleDocumento> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<DetalleDocumento> detalle) {
		this.detalle = detalle;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public BigDecimal getIva() {
		return iva;
	}

	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

}
