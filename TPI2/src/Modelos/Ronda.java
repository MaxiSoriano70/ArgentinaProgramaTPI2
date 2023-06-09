package Modelos;

import java.util.ArrayList;

public class Ronda {
	private int numero;
	private ArrayList<Partido> partidos = new ArrayList<Partido>();
	
	
	public Ronda(int numero) {
		super();
		this.numero = numero;
	}
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}

	public ArrayList<Partido> getPartidos() {
		return partidos;
	}

	public void setPartidos(ArrayList<Partido> partidos) {
		this.partidos = partidos;
	}
	
	public void mostrar_Resultados() {
		ArrayList<Partido> partidos = this.getPartidos();
		for(int i=0;i<partidos.size();i++) {
			System.out.println(partidos.get(i).getLocal().getNombre()+" vs "+partidos.get(i).getVisitante().getNombre()+" Ganador: "+partidos.get(i).getResultado());
		}
	}
	
}
