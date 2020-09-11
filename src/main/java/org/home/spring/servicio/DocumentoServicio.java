/**
 * 
 */
package org.home.spring.servicio;

import org.home.spring.dao.DocumentoDao;
import org.home.spring.modelo.DetalleDocumento;
import org.home.spring.modelo.Documento;
import org.home.spring.modelo.enumerado.TipoDocumentoEnum;
import org.home.spring.modelo.enumerado.TipoOperacionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author casa
 *
 */
@Service
public class DocumentoServicio {

	@Autowired
	private DocumentoDao documentoDao;

	@Autowired
	private DetalleDocumentoServicio detalleServicio;

	@Autowired
	private KardexServicio kardexServicio;

	@Transactional
	public Documento guardar(Documento documento) {
		documento = documentoDao.save(documento);
		detalleServicio.guardarTodos(documento.getDetalle());
		return documento;
	}

	@Transactional
	public Documento vender(Documento documento) {
		documento.setTipoDocumento(TipoDocumentoEnum.FAC);
		ponerDocumento(documento);
		documento = documentoDao.save(documento);
		documento.setNumeroDocumento("001-001-" + documento.getId().toString());
		documento.getDetalle().forEach(item -> kardexServicio.registrarKardexEgreso(item, TipoOperacionEnum.SALC));
		return documento;
	}

	@Transactional
	public Documento comprar(Documento documento) {
		documento.setTipoDocumento(TipoDocumentoEnum.FAC);
		ponerDocumento(documento);
		documento = documentoDao.save(documento);
		
		documento.getDetalle().forEach(item -> {
			kardexServicio.registrarKardexIngreso(item, TipoOperacionEnum.INGC);
		});
		return documento;
	}

	private Documento ponerDocumento(Documento documento) {
		for (DetalleDocumento detalle : documento.getDetalle()) {
			detalle.setDocumento(documento);
		}
		return documento;
	}

}
