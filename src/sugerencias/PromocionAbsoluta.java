package sugerencias;

import java.util.ArrayList;

public class PromocionAbsoluta extends Promocion {
	
	private int costoTotal;

	public PromocionAbsoluta(String nombre, boolean estaVigente, ArrayList<Atraccion> atracciones, int costoTotal) throws PromocionTieneUnSoloTipoDeAtraccion {
		super(nombre, estaVigente, atracciones);
		this.costoTotal = costoTotal;
	}
	
	@Override
	public int getPrecio() {
		return this.costoTotal;
	}

}
