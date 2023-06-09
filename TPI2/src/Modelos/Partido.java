package Modelos;

public class Partido {
	private Equipo local;
	private Equipo visitante;
	private EGanador ganador;
	
	public Partido(Equipo local, Equipo visitante) {
		this.local = local;
		this.visitante = visitante;
	}

	public Equipo getLocal() {
		return local;
	}

	public void setLocal(Equipo local) {
		this.local = local;
	}

	public Equipo getVisitante() {
		return visitante;
	}

	public void setVisitante(Equipo visitante) {
		this.visitante = visitante;
	}

	public EGanador getResultado() {
		return ganador;
	}

	public void setResultado(EGanador resultado) {
		this.ganador = resultado;
	}

}
