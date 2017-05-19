package mx.com.stsystems.cmh.beta.util;

public interface Constantes {
	//CONSULTA HOSPITALES (CONSTANTES DE CRITERIOS)
	public static final String CONSULTA_HOSPITAL_CRITERIO_ESTADO = "ESTADO";
	public static final String CONSULTA_HOSPITAL_CRITERIO_CIUDAD = "CIUDAD";
	public static final String CONSULTA_HOSPITAL_CRITERIO_COORDENADAS = "COORDENADAS";
	
	//CONSULTA HOSPITALES (CONSTANTES VARIOS)
	public static final String CONSULTA_HOSPITAL_JSON_INVALIDO = "{\n\t\"hospitales\": null,\n\t\"estatus\": 3,\n\t\"mensaje\": \"Error de conversión del mensaje JSON\"\n}";
	public static final Double CONSULTA_HOSPITAL_COORDENADAS_RADIO = 20D;
	public static final Double CONSULTA_HOSPITAL_COORDENADAS_UN_GRADO = 111D;
	public static final double CONSULTA_HOSPITAL_COORDENADAS_ANGULO = CONSULTA_HOSPITAL_COORDENADAS_RADIO / CONSULTA_HOSPITAL_COORDENADAS_UN_GRADO;
	
	//CONSULTA FOTO (CONSTANTES VARIOS)
	public static final String CONSULTA_FOTO_JSON_INVALIDO = "{\n\t\"foto\": null,\n\t\"estatus\": 3,\n\t\"mensaje\": \"Error de conversión del mensaje JSON\"\n}";
		
	
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
			for (EstatusConsultaHospital estatusConsulta : EstatusConsultaHospital.values()) {
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
	public static enum EstatusConsultaFoto {
		OK(0, "Fotografía encontrada"),
		NO_EXISTEN_ELEMENTOS(1, "La consulta no devolvió elementos"),
		NO_EXISTE_LA_FOTO(2, "No existe la fotografía"),
		ERROR_LEER_FOTO(3, "Error al leer la fotografía"),
		ERROR_CONVERSION_JSON(4, "Error de conversión del mensaje JSON"),
		ERROR_EN_CONSULTA_FOTO(99, "Error en la consulta de fotografías");

		private int codigo;
		private String mensaje;

		EstatusConsultaFoto(int codigo,String mensaje){
			this.codigo = codigo;
			this.mensaje = mensaje;
		}

//		public static String getDay(int codigo){
//			for (EstatusConsultaFoto estatusConsulta : EstatusConsultaFoto.values()) {
//				if (estatusConsulta.getCodigo() == codigo) {
//					return estatusConsulta.getMensaje();
//				}
//			}
//
//			return null;
//		}

		public int getCodigo() {
			return this.codigo;
		}

//		public void setCodigo(int codigo) {
//			this.codigo = codigo;
//		}

		public String getMensaje() {
			return this.mensaje;
		}

//		public void setMensaje(String mensaje) {
//			this.mensaje = mensaje;
//		}
	};
	
}
