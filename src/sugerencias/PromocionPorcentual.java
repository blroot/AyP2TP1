package sugerencias;

import java.util.ArrayList;

public class PromocionPorcentual extends Promocion {
	
	private double porcentajeDeDescuento;

	public PromocionPorcentual(String nombre, boolean estaVigente, ArrayList<Atraccion> atracciones, double porcentajeDeDescuento) throws PromocionTieneUnSoloTipoDeAtraccion, PorcentajeFueraDeRango {
		super(nombre, estaVigente, atracciones);
		
		if (porcentajeDeDescuento < 0 || porcentajeDeDescuento > 100) {
			throw new PorcentajeFueraDeRango("El porcentaje debe estar entre 0 y 100");
		}
		
		this.porcentajeDeDescuento = porcentajeDeDescuento;
	}

	@Override
	public int getPrecio() {
		return (int) (super.getPrecio() - (super.getPrecio() * this.porcentajeDeDescuento/100));
	}
	
}
