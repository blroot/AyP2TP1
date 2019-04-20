
public class PromocionAbsoluta extends Promocion {
	
	private int costoTotal;

	PromocionAbsoluta(String nombre, boolean estaVigente, Atraccion[] atracciones, int costoTotal) {
		super(nombre, estaVigente, atracciones);
		this.costoTotal = costoTotal;
	}
	
	@Override
	public int getPrecio() {
		return this.costoTotal;
	}

}
