package mx.com.stsystems.cmh.beta.util;

public interface Constantes {
	//CONSULTA HOSPITALES (CONSTANTES DE CRITERIOS)
	public static final String CONSULTA_HOSPITAL_CRITERIO_ESTADO = "ESTADO";
	public static final String CONSULTA_HOSPITAL_CRITERIO_CIUDAD = "CIUDAD";
	public static final String CONSULTA_HOSPITAL_CRITERIO_COORDENADAS = "COORDENADAS";
	
	//CONSULTA HOSPITALES (CONSTANTES VARIOS)
	public static final String CONSULTA_HOSPITAL_JSON_INVALIDO = "{\n\t\"hospitales\": null,\n\t\"estatus\": 3,\n\t\"mensaje\": \"Error de conversión del mensaje JSON\"\n}";
	
	//ESTATUS CONSULTA DE POLIZA
	public static enum EstatusConsultaPoliza {
		OK(0, "Póliza encontrada"),
		NO_EXISTE_ID_FILIACION(1, "No existe el id de filiación"),
		NO_EXISTE_POLIZA_ASOCIADA(2, "El afiliado no tiene una póliza asociada"),
		ERROR_EN_CONSULTA_MEMBRESIA(99, "Error en la consulta de la membresía");

		private int codigo;
		private String mensaje;

		EstatusConsultaPoliza(int codigo,String mensaje){
			this.codigo = codigo;
			this.mensaje = mensaje;
		}

		public static String getDay(int codigo){
			for (EstatusConsultaPoliza estatusConsulta : EstatusConsultaPoliza.values()) {
				if (estatusConsulta.getCodigo() == codigo) {
					return estatusConsulta.getMensaje();
				}
			}

			return null;
		}

		public int getCodigo() {
			return this.codigo;
		}

		public void setCodigo(int codigo) {
			this.codigo = codigo;
		}

		public String getMensaje() {
			return this.mensaje;
		}

		public void setMensaje(String mensaje) {
			this.mensaje = mensaje;
		}
	};

	//ESTATUS CONSULTA DE HOSPITALES
	public static enum EstatusConsultaHospital {
		OK(0, "Elementos encontrados"),
		NO_EXISTEN_ELEMENTOS(1, "La consulta no devolvió elementos"),
		CRITERIO_CONSULTA_INVALIDO(2, "El criterio de consulta es inválido"),
		ERROR_CONVERSION_JSON(3, "Error de conversión del mensaje JSON"),
		ERROR_EN_CONSULTA_HOSPITAL(99, "Error en la consulta de hospitales");

		private int codigo;
		private String mensaje;

		EstatusConsultaHospital(int codigo,String mensaje){
			this.codigo = codigo;
			this.mensaje = mensaje;
		}

		public static String getDay(int codigo){
			for (EstatusConsultaPoliza estatusConsulta : EstatusConsultaPoliza.values()) {
				if (estatusConsulta.getCodigo() == codigo) {
					return estatusConsulta.getMensaje();
				}
			}

			return null;
		}

		public int getCodigo() {
			return this.codigo;
		}

		public void setCodigo(int codigo) {
			this.codigo = codigo;
		}

		public String getMensaje() {
			return this.mensaje;
		}

		public void setMensaje(String mensaje) {
			this.mensaje = mensaje;
		}
	};
	
}
