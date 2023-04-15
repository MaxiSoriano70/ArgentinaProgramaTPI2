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
	
	public Usuario buscarPorUsuario(ArrayList<Usuario> usuarios, String nombreBuscado) {
	    for (Usuario usuario : usuarios) {
	        if (usuario.getNombreCompleto().equals(nombreBuscado)) {
	            return usuario;
	        }
	    }
	    return null;
	}
	
	public boolean isUsuario(ArrayList<Usuario> usuarios, String nombreBuscado) {
		int is=0;
	    for (Usuario usuario : usuarios) {
	        if (usuario.getNombreCompleto().equals(nombreBuscado)) {
	            is=1;
	        }
	    }
	    if(is==1) {
	    	return true;
	    }
	    else {
	    	return false;
	    }
	}
	
	public void setResultados(){
		try {
			for(String linea:Files.readAllLines(Paths.get(this.ruta))) {
				String nombreCompleto=linea.split(";")[1];
				String dni=linea.split(";")[2];
				//VEMOS SI EL USUARIO YA ESTA EN LA LISTA DE USUARIO
				if(isUsuario(getUsuarios(), nombreCompleto)) {
					//SI ESTA LO BUSCAMOS
					Usuario usuarioEncontrado=buscarPorUsuario(getUsuarios(), nombreCompleto);
					int auxRonda=Integer.parseInt(linea.split(";")[0]);
					//VEMOS SI LA RONADA YA ESTA EN SU LISTA DE RONDAS
					if(usuarioEncontrado.isRonda(usuarioEncontrado.getRondas(), auxRonda)) {
						//BUSCAMOS LA RONDA PARA AGREGAR EL PARTIDO
						Ronda rondaEncontrada=usuarioEncontrado.buscarRonda(usuarioEncontrado.getRondas(),auxRonda);
						boolean[] pronostico=new boolean[3];
						String l=linea.split(";")[3];
						pronostico[0]=Boolean.valueOf(linea.split(";")[4]);
						pronostico[1]=Boolean.valueOf(linea.split(";")[5]);
						pronostico[2]=Boolean.valueOf(linea.split(";")[6]);
						String v=linea.split(";")[7];
						Partido partido=generar_partido(l,v,pronostico);
						rondaEncontrada.getPartidos().add(partido);
					}
					// SI ES UNA NUEVA RONDA LA CREAMOS Y LA AGREGAMOS
					else {
						Ronda ronda=new Ronda(auxRonda);
						usuarioEncontrado.getRondas().add(ronda);
						//LA BUSCAMOS Y LE AGREGAMOS EL PARTIDO
						Ronda rondaEncontrada=usuarioEncontrado.buscarRonda(usuarioEncontrado.getRondas(),auxRonda);
						boolean[] pronostico=new boolean[3];
						String l=linea.split(";")[3];
						pronostico[0]=Boolean.valueOf(linea.split(";")[4]);
						pronostico[1]=Boolean.valueOf(linea.split(";")[5]);
						pronostico[2]=Boolean.valueOf(linea.split(";")[6]);
						String v=linea.split(";")[7];
						Partido partido=generar_partido(l,v,pronostico);
						rondaEncontrada.getPartidos().add(partido);
					}
				}
				
				else {
					//SI NO ESTA EL USUARIO LO CREAMOS Y LO AGREGAMOS A LISTA DE USUARIOS
					Usuario usuario=new Usuario(nombreCompleto,dni);
					usuarios.add(usuario);
					int auxRonda=Integer.parseInt(linea.split(";")[0]);
					//VERIFICAMOS SI LA RONDA ESTA O NO
					if(usuario.isRonda(usuario.getRondas(), auxRonda)) {
						//LA TRAEMOS Y AGREGAMOS EL PARTIDO
						Ronda rondaEncontrada=usuario.buscarRonda(usuario.getRondas(),auxRonda);
						boolean[] pronostico=new boolean[3];
						String l=linea.split(";")[3];
						pronostico[0]=Boolean.valueOf(linea.split(";")[4]);
						pronostico[1]=Boolean.valueOf(linea.split(";")[5]);
						pronostico[2]=Boolean.valueOf(linea.split(";")[6]);
						String v=linea.split(";")[7];
						Partido partido=generar_partido(l,v,pronostico);
						rondaEncontrada.getPartidos().add(partido);
					}
					else {
						//SI NO ESTA CREAMOS LA RONDA
						Ronda ronda=new Ronda(auxRonda);
						usuario.getRondas().add(ronda);
						//LA BUSCAMOS Y LE AGREGAMOS EL PARTIDO
						Ronda rondaEncontrada=usuario.buscarRonda(usuario.getRondas(),auxRonda);
						boolean[] pronostico=new boolean[3];
						String l=linea.split(";")[3];
						pronostico[0]=Boolean.valueOf(linea.split(";")[4]);
						pronostico[1]=Boolean.valueOf(linea.split(";")[5]);
						pronostico[2]=Boolean.valueOf(linea.split(";")[6]);
						String v=linea.split(";")[7];
						Partido partido=generar_partido(l,v,pronostico);
						rondaEncontrada.getPartidos().add(partido);
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
