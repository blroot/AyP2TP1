package sugerencias;

import java.util.ArrayList;

public class PromocionUnoGratuito extends Promocion {
	
	private Atraccion obtieneGratis;

	public PromocionUnoGratuito(String nombre, boolean estaVigente, ArrayList<Atraccion> atracciones, Atraccion obtieneGratis) 
			throws PromocionTieneUnSoloTipoDeAtraccion, AtraccionesNoContieneObtieneGratis {
		
		super(nombre, estaVigente, atracciones);
		
		if (!atracciones.contains(obtieneGratis)) {
			throw new AtraccionesNoContieneObtieneGratis("No se encontró la atracción gratuita en la lista de atracciones");
		}
		
		this.obtieneGratis = obtieneGratis;
	}
	
	@Override
	public int getPrecio() {
		return super.getPrecio() - this.obtieneGratis.getPrecio();	
	}
	
}
