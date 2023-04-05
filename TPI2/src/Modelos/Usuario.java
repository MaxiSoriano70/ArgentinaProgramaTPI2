package Modelos;

import java.util.ArrayList;

public class Usuario extends Persona{
	
	private ArrayList<Ronda> rondas = new ArrayList<Ronda>();
	private int puntaje;

	public Usuario(String nombreCompleto, String dni) {
		super(nombreCompleto, dni);
	}
	
	public ArrayList<Ronda> getRondas() {
		return rondas;
	}

	public void setRondas(ArrayList<Ronda> rondas) {
		this.rondas = rondas;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	
	public void calcular_Puntaje(ArrayList<Ronda> resultados) {
		for(int i=0;i<resultados.size();i++) {
			for(int j=0;j<this.getRondas().size();j++) {
				if(resultados.get(i).getNumero()==this.getRondas().get(j).getNumero()) {
					verificar_Partido(resultados.get(i).getPartidos(),this.getRondas().get(j).getPartidos());
				}
			}
		}
	}
	
	public void verificar_Partido(ArrayList<Partido> partidosResultados,ArrayList<Partido> partidosPronosticos) {
		for(int i=0;i<partidosResultados.size();i++) {
			if(partidosResultados.get(i).getResultado()==partidosPronosticos.get(i).getResultado()) {
				int aux=this.getPuntaje()+1;
				this.setPuntaje(aux);
			}
		}
	}

	public void mostrar_Pronosticos() {
		ArrayList<Ronda> rondas = this.getRondas();
		for(int i=0;i<rondas.size();i++) {
			System.out.print(this.getNombreCompleto()+" ronda "+rondas.get(i).getNumero()+" ");
			rondas.get(i).mostrar_Resultados();
		}
	}
}
