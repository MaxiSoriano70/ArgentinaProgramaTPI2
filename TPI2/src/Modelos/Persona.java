package Modelos;

public class Persona {
	private String dni;
	private String nombreCompleto;
	
	public Persona(String nombreCompleto, String dni) {
		super();
		this.nombreCompleto = nombreCompleto;
		this.dni = dni;
	}
	
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	
	public String getDni() {
		return dni;
	}
	
	public void setDni(String dni) {
		this.dni = dni;
	}
}
