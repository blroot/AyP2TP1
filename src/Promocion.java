
public class Promocion extends Comprable {
	
	private boolean estaVigente;
	private Atraccion[] atracciones;
	
	Promocion(String nombre, boolean estaVigente, Atraccion[] atracciones) {
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void vender(Usuario usuario) {
		// TODO Auto-generated method stub
		
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
