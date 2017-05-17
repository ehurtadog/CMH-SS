package mx.com.stsystems.cmh.beta.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import mx.com.stsystems.cmh.beta.dao.PolizaDAO;
import mx.com.stsystems.cmh.beta.dto.Beneficio;
import mx.com.stsystems.cmh.beta.dto.Poliza;
import mx.com.stsystems.cmh.beta.dto.Programa;
import mx.com.stsystems.cmh.beta.dto.mappers.BeneficioMapper;
import mx.com.stsystems.cmh.beta.dto.mappers.PolizaMapper;
import mx.com.stsystems.cmh.beta.dto.mappers.ProgramaMapper;
import mx.com.stsystems.cmh.beta.exceptions.SumarSaludException;
import mx.com.stsystems.cmh.beta.json.messages.response.MensajePolizaMembresia;

public class PolizaDAOImpl implements PolizaDAO {
	private JdbcTemplate jdbcTemplate;
	private final static Logger LOGGER = LoggerFactory.getLogger(PacienteDAOImpl.class);

    @Autowired
    public PolizaDAOImpl(DataSource dataSource) {
    	this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    @Override
    public boolean buscaPolizaPorIdFiliacion(long idFiliacion) {
    	boolean resultado = false;
		StringBuilder qryBuscaPolizaPorIdFiliacion = new StringBuilder()
			.append("SELECT COUNT(pp.IDPOLIZA) ")
			.append(" FROM paciente p ")
			.append("  LEFT JOIN paciente_poliza pp ")
			.append("   ON p.IDPACIENTE = pp.IDPACIENTE ")
			.append(" WHERE p.IDFILIACION = ? ");
		
		try {
			int contador = jdbcTemplate.queryForObject(qryBuscaPolizaPorIdFiliacion.toString(), 
				new Object[] { idFiliacion }, Integer.class);
			
			if (contador == 1) {
				resultado = true;
			}
		} catch (DataAccessException dae) {
			LOGGER.error("Ocurrió un error al consultar el la poliza del paciente: ", dae);
		}
		
		return resultado;
    }
    
	@Override
	public MensajePolizaMembresia consultaPolizaPorIdFiliacion(long idFiliacion) throws SumarSaludException {
		StringBuilder qryObtienePolizaPorIdFiliacion = new StringBuilder()
			.append("SELECT po.IDPOLIZA, po.IDASEGURADORA, po.NUMEROPOLIZA AS NUMEROPOLIZA, po.COBERTURA AS COBERTURA, po.DEDUCIBLE AS DEDUCIBLE, ")
			.append("       po.COPAGO AS COPAGO, po.VIGENCIA AS VIGENCIA, a.DESCRIPCION AS ASEGURADORA ")
			.append(" FROM paciente p ")
			.append("  LEFT JOIN paciente_poliza pp ")
			.append("   ON p.IDPACIENTE = pp.IDPACIENTE ")
			.append("  LEFT JOIN poliza po ")
			.append("   ON pp.IDPOLIZA = po.IDPOLIZA ")
			.append("  LEFT JOIN aseguradora a ")
			.append("   ON po.IDASEGURADORA = a.IDASEGURADORA ")
			.append(" WHERE p.IDFILIACION = ? ");
		MensajePolizaMembresia mensajePolizaMemebresia = new MensajePolizaMembresia();
		mensajePolizaMemebresia.setIdFiliacion(idFiliacion);
		
		try {
			Poliza poliza = jdbcTemplate.queryForObject(qryObtienePolizaPorIdFiliacion.toString(), new Object[] { idFiliacion }, new PolizaMapper());
			mensajePolizaMemebresia.setPoliza(poliza);
			mensajePolizaMemebresia.setBeneficios(consultaBeneficiosPorIdFiliacion(idFiliacion));
			mensajePolizaMemebresia.setProgramas(consultaProgramasPorIdFiliacion(idFiliacion));
		} catch (DataAccessException | SumarSaludException e) {
			LOGGER.error("Error en la consulta de la poliza: " + e.getMessage());
			throw new SumarSaludException("");
		} 
		
		return mensajePolizaMemebresia;
	}
	
	private List<Beneficio> consultaBeneficiosPorIdFiliacion(long idFiliacion) throws SumarSaludException {
		StringBuilder qryObtieneBeneficiosPorIdFiliacion = new StringBuilder()
			.append("SELECT b.IDBENEFICIO AS IDBENEFICIO, b.DESCRIPCION AS DESCRIPCION, b.VALOR AS VALOR, b.ORDEN AS ORDEN, ")
			.append("       b.FECHAHORAREGISTRO AS FECHAHORAREGISTRO, b.FECHAHORAMODIFICACION AS FECHAHORAMODIFICACION, ")
			.append("       b.FECHAHORABAJA AS FECHAHORABAJA ")
			.append("FROM paciente p ")
			.append(" LEFT JOIN paciente_membresia pm ")
			.append("  ON p.IDPACIENTE = pm.IDPACIENTE ")
			.append(" LEFT JOIN membresia_beneficio mb ")
			.append("  ON pm.IDMEMBRESIA = mb.IDMEMBRESIA ")
			.append(" LEFT JOIN beneficio b ")
			.append("  ON mb.IDBENEFICIO = b.IDBENEFICIO ")
			.append(" WHERE p.IDFILIACION = ? ")
			.append(" ORDER BY b.ORDEN ");
		List<Beneficio> beneficios = null;
		
		try {
			beneficios = jdbcTemplate.query(qryObtieneBeneficiosPorIdFiliacion.toString(), new Object[] { idFiliacion }, new BeneficioMapper());
		} catch (DataAccessException dae) {
			LOGGER.error("Exception", dae);
			throw new SumarSaludException("Error en la consulta de los beneficios de la membresia: " + dae.getMessage());
		}
		
		return beneficios;
	}
	
	private List<Programa> consultaProgramasPorIdFiliacion(long idFiliacion) throws SumarSaludException {
		StringBuilder qryObtieneProgramasPorIdFiliacion = new StringBuilder()
			.append("SELECT pr.IDPROGRAMA AS IDPROGRAMA, pr.DESCRIPCION AS DESCRIPCION, pr.ORDEN AS ORDEN, ")
			.append("       pr.FECHAHORAREGISTRO AS FECHAHORAREGISTRO, pr.FECHAHORAMODIFICACION AS FECHAHORAMODIFICACION, ")
			.append("       pr.FECHAHORABAJA AS FECHAHORABAJA ")
			.append("FROM paciente p ")
			.append(" LEFT JOIN paciente_membresia pm ")
			.append("  ON p.IDPACIENTE = pm.IDPACIENTE ")
			.append(" LEFT JOIN membresia_programa mpr ")
			.append("  ON pm.IDMEMBRESIA = mpr.IDMEMBRESIA ")
			.append(" LEFT JOIN programa pr ")
			.append("  ON mpr.IDPROGRAMA = pr.IDPROGRAMA ")
			.append(" WHERE p.IDFILIACION = ? ")
			.append(" ORDER BY pr.ORDEN ");
		List<Programa> programas = null;
		
		try {
			programas = jdbcTemplate.query(qryObtieneProgramasPorIdFiliacion.toString(), new Object[] { idFiliacion }, new ProgramaMapper());
		} catch (DataAccessException dae) {
			LOGGER.error("Exception", dae);
			throw new SumarSaludException("Error en la consulta de los programas de la membresia: " + dae.getMessage());
		}
		
		return programas;
	}

}
