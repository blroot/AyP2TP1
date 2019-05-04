package sugerencias;

import java.util.ArrayList;

public class PromocionPorcentual extends Promocion {
	
	private double porcentajeDeDescuento;

	public PromocionPorcentual(String nombre, boolean estaVigente, ArrayList<Atraccion> atracciones, double porcentajeDeDescuento) throws PromocionTieneUnSoloTipoDeAtraccion {
		super(nombre, estaVigente, atracciones);
		this.porcentajeDeDescuento = porcentajeDeDescuento;
	}

	@Override
	public int getPrecio() {
		return (int) (super.getPrecio() - (super.getPrecio() * this.porcentajeDeDescuento/100));
	}
	
}
