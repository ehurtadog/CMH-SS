package mx.com.stsystems.cmh.beta.dto;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnore;

public class Poliza implements Serializable {
	private static final long serialVersionUID = 3788536439139813161L;

	@JsonIgnore
	private String idPoliza;
	@JsonIgnore
	private String idAseguradora;
	private String aseguradora;
	private long numeroPoliza;
	private float cobertura;
	private float deducible;
	private float copago;
	private int vigencia;

	public String getIdPoliza() {
		return idPoliza;
	}

	public void setIdPoliza(String idPoliza) {
		this.idPoliza = idPoliza;
	}

	public String getIdAseguradora() {
		return idAseguradora;
	}

	public void setIdAseguradora(String idAseguradora) {
		this.idAseguradora = idAseguradora;
	}

	public String getAseguradora() {
		return aseguradora;
	}

	public void setAseguradora(String aseguradora) {
		this.aseguradora = aseguradora;
	}

	public long getNumeroPoliza() {
		return numeroPoliza;
	}

	public void setNumeroPoliza(long numeroPoliza) {
		this.numeroPoliza = numeroPoliza;
	}

	public float getCobertura() {
		return cobertura;
	}

	public void setCobertura(float cobertura) {
		this.cobertura = cobertura;
	}

	public float getDeducible() {
		return deducible;
	}

	public void setDeducible(float deducible) {
		this.deducible = deducible;
	}

	public float getCopago() {
		return copago;
	}

	public void setCopago(float copago) {
		this.copago = copago;
	}

	public int getVigencia() {
		return vigencia;
	}

	public void setVigencia(int vigencia) {
		this.vigencia = vigencia;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Poliza [idPoliza=");
		builder.append(idPoliza);
		builder.append(", idAseguradora=");
		builder.append(idAseguradora);
		builder.append(", aseguradora=");
		builder.append(aseguradora);
		builder.append(", numeroPoliza=");
		builder.append(numeroPoliza);
		builder.append(", cobertura=");
		builder.append(cobertura);
		builder.append(", deducible=");
		builder.append(deducible);
		builder.append(", copago=");
		builder.append(copago);
		builder.append(", vigencia=");
		builder.append(vigencia);
		builder.append("]");
		return builder.toString();
	}
}
