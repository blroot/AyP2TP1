package sugerencias;

import java.util.ArrayList;

public class Promocion extends Comprable {
	
	private boolean estaVigente;
	private ArrayList<Atraccion> atracciones;
	
	public Promocion(String nombre, boolean estaVigente, ArrayList<Atraccion> atracciones) throws PromocionTieneUnSoloTipoDeAtraccion {
		super(nombre, Prioridad.Alta);
		
		TipoDeAtraccion tipoDeAtraccionPrimaria = atracciones.get(0).getTipoDeAtraccion();
		
		for (Atraccion atraccion: atracciones) {
			if (!tipoDeAtraccionPrimaria.equals(atraccion.getTipoDeAtraccion())) {
				throw new PromocionTieneUnSoloTipoDeAtraccion("Solo se permiten atracciones de un solo tipo");
			}
		}
		
		this.atracciones = atracciones;
		this.estaVigente = estaVigente;
	}

	@Override
	public int getPrecio() {
		
		int precioTotal = 0;
		
		for (Atraccion atraccion: this.atracciones) {
			precioTotal += atraccion.getPrecio();
		}

		return precioTotal;
	}

	@Override
	public boolean puedeAdquirir(Usuario usuario) {
		if (!this.estaVigente) {
			return false;
		}
		
		// Si no tiene presupuesto o tiempo disponible retorna false
		if (usuario.getPresupuesto() < this.getPrecio() 
				|| usuario.getTiempoDisponible() < this.getTiempoTotal()) {
			return false;
		}
		
		// Si ya adquirió la atracción no puede volver a adquirir
		for (Atraccion atraccion: this.atracciones) {
			if (usuario.getComprados().contains(atraccion)) {
				return false;
			}
		}
		
		return true;
	}

	@Override
	public void vender(Usuario usuario) throws UsuarioNoPuedeAdquirirComprable {
		if(this.puedeAdquirir(usuario)) {
			usuario.agregarComprable(this);
			for (Atraccion atraccion: atracciones) {
				atraccion.disminuirCupoDiario();
			}
		} else {
			throw new UsuarioNoPuedeAdquirirComprable("El usuario no tiene recursos suficientes para comprar el comprable o comprable no tiene cupo disponible");
		}
	}

	@Override
	public double getTiempoTotal() {
		
		double tiempoTotal = 0.0;
		
		for (Atraccion atraccion: this.atracciones) {
			tiempoTotal += atraccion.getTiempoTotal();
		}

		return tiempoTotal;
	}

	@Override
	public TipoDeAtraccion getTipoDeAtraccion() {
		return this.atracciones.get(0).getTipoDeAtraccion();
	}

	@Override
	public String toString() {
		return "Promocion: " + super.getNombre() + " " 
				+ "\nTipo: " + this.getTipoDeAtraccion() + " " 
				+ "\nPrecio total " + this.getPrecio() + " " 
				+ "\nTiempo promedio total: " + this.getTiempoTotal() + " "
				+ "\nAtracciones incluidas: \n" + this.getAtracciones().toString() ;
	}

	public ArrayList<Atraccion> getAtracciones() {
		return this.atracciones;
	}

}
