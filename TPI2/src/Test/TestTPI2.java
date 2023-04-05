package Test;

import Modelos.Pronostico;
import Modelos.Resultados;

public class TestTPI2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Resultados resultado=new Resultados("src\\Archivos\\resultados.txt");
		resultado.setResultados();
		resultado.mostrar_Rondas();
		
		
		System.out.println("----------------------------------------");
		
		Pronostico pronosticos=new Pronostico("src\\Archivos\\pronosticos.txt");
		pronosticos.setResultados();
		pronosticos.mostrar_Usuarios();
		
		System.out.println("------------------------------------------");
		for(int i=0;i<pronosticos.getUsuarios().size();i++) {
			pronosticos.getUsuarios().get(i).calcular_Puntaje(resultado.getRondas());
			System.out.println(pronosticos.getUsuarios().get(i).getNombreCompleto()+" puntaje: "+pronosticos.getUsuarios().get(i).getPuntaje());
		}
	}

}
