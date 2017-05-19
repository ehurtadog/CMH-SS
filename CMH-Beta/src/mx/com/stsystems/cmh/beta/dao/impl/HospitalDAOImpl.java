package mx.com.stsystems.cmh.beta.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import mx.com.stsystems.cmh.beta.dao.HospitalDAO;
import mx.com.stsystems.cmh.beta.dto.Hospital;
import mx.com.stsystems.cmh.beta.dto.mappers.HospitalMapper;
import mx.com.stsystems.cmh.beta.exceptions.SumarSaludException;
import mx.com.stsystems.cmh.beta.util.Constantes;

public class HospitalDAOImpl implements HospitalDAO, Constantes {
	private final static Logger LOGGER = LoggerFactory.getLogger(HospitalDAOImpl.class);
	private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public HospitalDAOImpl(DataSource dataSource) {
    	this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
	@Override
	public List<Hospital> consultaHospitalesPorEstado(String estado) throws SumarSaludException {
		final StringBuilder QRY_CONSULTA_HOSPITAL_POR_ESTADO = new StringBuilder()
				.append("SELECT IDHOSPITAL, DESCRIPCION, URL, IDCIUDAD, IDMUNICIPIO, IDESTADO, LATITUD, LONGITUD ")
				.append(" FROM hospital ")
				.append(" WHERE IDESTADO = ( ")
				.append("  SELECT IDESTADO ")
				.append("   FROM estado ")
				.append("   WHERE DESCRIPCION = ?)");
		List<Hospital> hospitales = null;
			
		try {
			hospitales = jdbcTemplate.query(QRY_CONSULTA_HOSPITAL_POR_ESTADO.toString(),	new Object[] { estado }, new HospitalMapper());
			
			if ((hospitales == null) || (hospitales.isEmpty())) {
				LOGGER.debug(EstatusConsultaHospital.NO_EXISTEN_ELEMENTOS.getMensaje());
				throw new SumarSaludException(EstatusConsultaHospital.NO_EXISTEN_ELEMENTOS);
			}
		} catch (DataAccessException dae) {
			LOGGER.warn("[BUG] - Error en la consulta de hospitales por estado", dae);
			throw new SumarSaludException(EstatusConsultaHospital.ERROR_EN_CONSULTA_HOSPITAL);
		} 
			
		return hospitales;
	}
	
	@Override
	public List<Hospital> consultaHospitalesPorCiudad(String ciudad) throws SumarSaludException {
		final StringBuilder QRY_CONSULTA_HOSPITAL_POR_CIUDAD = new StringBuilder()
				.append("SELECT IDHOSPITAL, DESCRIPCION, URL, IDCIUDAD, IDMUNICIPIO, IDESTADO, LATITUD, LONGITUD ")
				.append(" FROM hospital ")
				.append(" WHERE CIUDAD = ? ");
		List<Hospital> hospitales = null;
			
		try {
			hospitales = jdbcTemplate.query(QRY_CONSULTA_HOSPITAL_POR_CIUDAD.toString(),	new Object[] { ciudad }, new HospitalMapper());
			
			if ((hospitales == null) || (hospitales.isEmpty())) {
				LOGGER.debug(EstatusConsultaHospital.NO_EXISTEN_ELEMENTOS.getMensaje());
				throw new SumarSaludException(EstatusConsultaHospital.NO_EXISTEN_ELEMENTOS);
			}
		} catch (DataAccessException dae) {
			LOGGER.warn("[BUG] - Error en la consulta de hospitales por ciudad", dae);
			throw new SumarSaludException(EstatusConsultaHospital.ERROR_EN_CONSULTA_HOSPITAL);
		} 
			
		return hospitales;
	}
	
	@Override
	public List<Hospital> consultaHospitalesPorLatitudLongitud(double latitud, double longitud) throws SumarSaludException {
		final StringBuilder QRY_CONSULTA_HOSPITAL_POR_LATITUD_LONGITUD = new StringBuilder()
				.append("SELECT IDHOSPITAL, DESCRIPCION, URL, IDCIUDAD, IDMUNICIPIO, IDESTADO, LATITUD, LONGITUD ")
				.append(" FROM hospital ")
				.append(" WHERE LATITUD BETWEEN ? AND ? AND ")
				.append("       LONGITUD BETWEEN ? AND ? ");
		List<Hospital> hospitales = null;
		double latitudMinima = latitud - CONSULTA_HOSPITAL_COORDENADAS_ANGULO;
		double latitudMaxima = latitud + CONSULTA_HOSPITAL_COORDENADAS_ANGULO;
		double longitudMinima = longitud - CONSULTA_HOSPITAL_COORDENADAS_ANGULO;
		double longitudMaxima = longitud + CONSULTA_HOSPITAL_COORDENADAS_ANGULO;
			
		try {
			hospitales = jdbcTemplate.query(QRY_CONSULTA_HOSPITAL_POR_LATITUD_LONGITUD.toString(),	new Object[] { latitudMinima, 
				latitudMaxima, longitudMinima, longitudMaxima }, new HospitalMapper());
			
			if ((hospitales == null) || (hospitales.isEmpty())) {
				LOGGER.debug(EstatusConsultaHospital.NO_EXISTEN_ELEMENTOS.getMensaje());
				throw new SumarSaludException(EstatusConsultaHospital.NO_EXISTEN_ELEMENTOS);
			}
		} catch (DataAccessException dae) {
			LOGGER.warn("[BUG] - Error en la consulta de hospitales por latitud/longitud", dae);
			throw new SumarSaludException(EstatusConsultaHospital.ERROR_EN_CONSULTA_HOSPITAL);
		} 
			
		return hospitales;
	}
}
