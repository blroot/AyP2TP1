package sugerencias;

public class PromocionPorcentual extends Promocion {
	
	private double porcentajeDeDescuento;

	public PromocionPorcentual(String nombre, boolean estaVigente, Atraccion[] atracciones, double porcentajeDeDescuento) {
		super(nombre, estaVigente, atracciones);
		this.porcentajeDeDescuento = porcentajeDeDescuento;
	}

	@Override
	public int getPrecio() {
		// TODO: Precio deber�a ser double?
		return (int) (super.getPrecio() - (super.getPrecio() * this.porcentajeDeDescuento/100));
	}
	
}
