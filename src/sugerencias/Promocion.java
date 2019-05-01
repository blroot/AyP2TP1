package sugerencias;

public class Promocion extends Comprable {
	
	private boolean estaVigente;
	private Atraccion[] atracciones;
	
	public Promocion(String nombre, boolean estaVigente, Atraccion[] atracciones) {
		super(nombre, Prioridad.Alta);
		this.atracciones = atracciones;
		this.estaVigente = estaVigente;
	}

	@Override
	public int getPrecio() {
		
		int precioTotal = 0;
		
		for (int i = 0; i < this.atracciones.length; i++) {
			precioTotal += this.atracciones[i].getPrecio();
		}

		return precioTotal;
	}

	@Override
	public boolean puedeAdquirir(Usuario usuario) {
		if (!this.estaVigente) {
			return false;
		}
		
		// Se recorre el arreglo de atracciones y se comprueba si ya lo adquiri� antes
		for (int i = 0; i < this.atracciones.length; i++) {
			if (usuario.getPresupuesto() < this.atracciones[i].getPrecio() 
					|| usuario.getTiempoDisponible() < this.atracciones[i].getTiempoTotal()
					|| usuario.getComprados().contains(this.atracciones[i])) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void vender(Usuario usuario) throws UsuarioNoPuedeAdquirirComprable {
		for (int i = 0; i < this.atracciones.length; i++) {
			if(puedeAdquirir(usuario)) {
				usuario.agregarComprable(this);
				this.atracciones[i].disminuirCupoDiario();
			} else {
				throw new UsuarioNoPuedeAdquirirComprable("El usuario no tiene recursos suficientes para comprar el comprable o comprable no tiene cupo disponible");
			}
		}
	}

	@Override
	public double getTiempoTotal() {
		
		double tiempoTotal = 0.0;
		
		for (int i = 0; i < this.atracciones.length; i++) {
			tiempoTotal += this.atracciones[i].getTiempoTotal();
		}

		return tiempoTotal;
	}

	@Override
	public TipoDeAtraccion getTipoDeAtraccion() {
		// TODO: Revisar si esto cumple
		return this.atracciones[0].getTipoDeAtraccion();
	}

	@Override
	public String toString() {
		return super.getNombre() + " " + this.getTipoDeAtraccion() + " " + this.getPrecio() + " " + this.getTiempoTotal();
	}

	public Atraccion[] getAtracciones() {
		return this.atracciones;
	}

}
