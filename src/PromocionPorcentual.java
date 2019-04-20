
public class PromocionPorcentual extends Promocion {
	
	private double porcentajeDeDescuento;

	PromocionPorcentual(String nombre, boolean estaVigente, Atraccion[] atracciones, double porcentajeDeDescuento) {
		super(nombre, estaVigente, atracciones);
		this.porcentajeDeDescuento = porcentajeDeDescuento;
	}

	@Override
	public int getPrecio() {
		// TODO: Precio debería ser double?
		return (int) (super.getPrecio() - (super.getPrecio() * this.porcentajeDeDescuento/100));
	}
	
}
