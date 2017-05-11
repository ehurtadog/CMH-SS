package mx.com.stsystems.cmh.beta.util;

public interface Constantes {
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
