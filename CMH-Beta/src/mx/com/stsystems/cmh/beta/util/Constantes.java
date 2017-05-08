package mx.com.stsystems.cmh.beta.util;

public interface Constantes {
	//ESTATUS CONSULTA DE POLIZA
	public static enum EstatusConsultaPoliza {
	    OK(0, "Poliza encontrada"),
	    NO_EXISTE_ID_FILIACION(1, "No existe el id de filiación"),
	    NO_EXISTE_POLIZA_ASOCIADA(2, "El afiliado no tiene una poliza asociada"),
	    ERROR_EN_CONSULTA_MEMBRESIA(3, "Error en la consulta de la membresia");

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

}
