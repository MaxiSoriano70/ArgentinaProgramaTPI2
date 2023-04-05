package Modelos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Pronostico{
	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	private String ruta;

	public Pronostico(String ruta) {
		// TODO Auto-generated constructor stub
		this.ruta=ruta;
	}
	
	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public void setResultados(){
		try {
			for(String linea:Files.readAllLines(Paths.get(this.ruta))) {
				String nombreCompleto=linea.split(";")[1];
				String dni=linea.split(";")[2];
				Usuario usuario=new Usuario(nombreCompleto,dni);
				if(usuarios.contains(usuario)) {
					int auxRonda=Integer.parseInt(linea.split(";")[0]);
					Ronda ronda=new Ronda(auxRonda);
					if(usuario.getRondas().contains(ronda)) {
						int lugarDeLaRonda=usuario.getRondas().indexOf(ronda);
						boolean[] pronostico=new boolean[3];
						String l=linea.split(";")[3];
						pronostico[0]=Boolean.valueOf(linea.split(";")[4]);
						pronostico[1]=Boolean.valueOf(linea.split(";")[5]);
						pronostico[2]=Boolean.valueOf(linea.split(";")[6]);
						String v=linea.split(";")[7];
						Partido partido=generar_partido(l,v,pronostico);
						usuario.getRondas().get(lugarDeLaRonda).getPartidos().add(partido);
					}
					else {
						usuario.getRondas().add(ronda);
						int lugarDeLaRonda=usuario.getRondas().indexOf(ronda);
						boolean[] pronostico=new boolean[3];
						String l=linea.split(";")[3];
						pronostico[0]=Boolean.valueOf(linea.split(";")[4]);
						pronostico[1]=Boolean.valueOf(linea.split(";")[5]);
						pronostico[2]=Boolean.valueOf(linea.split(";")[6]);
						String v=linea.split(";")[7];
						Partido partido=generar_partido(l,v,pronostico);
						usuario.getRondas().get(lugarDeLaRonda).getPartidos().add(partido);
					}
				}
				else {
					usuarios.add(usuario);
					int auxRonda=Integer.parseInt(linea.split(";")[0]);
					Ronda ronda=new Ronda(auxRonda);
					if(usuario.getRondas().contains(ronda)) {
						int lugarDeLaRonda=usuario.getRondas().indexOf(ronda);
						boolean[] pronostico=new boolean[3];
						String l=linea.split(";")[3];
						pronostico[0]=Boolean.valueOf(linea.split(";")[4]);
						pronostico[1]=Boolean.valueOf(linea.split(";")[5]);
						pronostico[2]=Boolean.valueOf(linea.split(";")[6]);
						String v=linea.split(";")[7];
						Partido partido=generar_partido(l,v,pronostico);
						usuario.getRondas().get(lugarDeLaRonda).getPartidos().add(partido);
					}
					else {
						usuario.getRondas().add(ronda);
						int lugarDeLaRonda=usuario.getRondas().indexOf(ronda);
						boolean[] pronostico=new boolean[3];
						String l=linea.split(";")[3];
						pronostico[0]=Boolean.valueOf(linea.split(";")[4]);
						pronostico[1]=Boolean.valueOf(linea.split(";")[5]);
						pronostico[2]=Boolean.valueOf(linea.split(";")[6]);
						String v=linea.split(";")[7];
						Partido partido=generar_partido(l,v,pronostico);
						usuario.getRondas().get(lugarDeLaRonda).getPartidos().add(partido);
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Partido generar_partido(String l,String v,boolean[] pronostico) {
		Equipo local=new Equipo(l);
		Equipo visitante=new Equipo(v);
		Partido prode=new Partido(local,visitante);
		resultado(prode,pronostico);
		return prode;
	}

	public void resultado(Partido partido,boolean[] pronostico) {
		if(pronostico[0]==true) {
			partido.setResultado(EGanador.LOCAL);
		}
		else if(pronostico[1]==true) {
			partido.setResultado(EGanador.EMPATE);
		}
		else if(pronostico[2]==true) {
			partido.setResultado(EGanador.VISITANTE);
		}
	}
	
	public void mostrar_Usuarios() {
		ArrayList<Usuario> usuarios = this.getUsuarios();
		for(int i=0;i<usuarios.size();i++) {
			usuarios.get(i).mostrar_Pronosticos();
		}
	}
}
