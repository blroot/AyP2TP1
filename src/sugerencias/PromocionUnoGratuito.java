package sugerencias;

import java.util.ArrayList;

public class PromocionUnoGratuito extends Promocion {
	
	private Atraccion obtieneGratis;

	public PromocionUnoGratuito(String nombre, boolean estaVigente, ArrayList<Atraccion> atracciones, Atraccion obtieneGratis) throws PromocionTieneUnSoloTipoDeAtraccion {
		super(nombre, estaVigente, atracciones);
		this.obtieneGratis = obtieneGratis;
	}
	
	@Override
	public int getPrecio() {
		return super.getPrecio() - this.obtieneGratis.getPrecio();	
	}
	
}
