package sugerencias;

import java.util.ArrayList;

public class PromocionAbsoluta extends Promocion {
	
	private int costoTotal;

	public PromocionAbsoluta(String nombre, boolean estaVigente, ArrayList<Atraccion> atracciones, int costoTotal) throws PromocionTieneUnSoloTipoDeAtraccion, CostoNegativoNoPermitido {
		super(nombre, estaVigente, atracciones);
		
		if (costoTotal < 0) {
			throw new CostoNegativoNoPermitido("No se permite ingresar un costo negativo");
		}
		
		this.costoTotal = costoTotal;
	}
	
	@Override
	public int getPrecio() {
		return this.costoTotal;
	}

}
