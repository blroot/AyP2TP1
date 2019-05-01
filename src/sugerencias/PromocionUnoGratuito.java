package sugerencias;

public class PromocionUnoGratuito extends Promocion {
	
	private Atraccion obtieneGratis;

	public PromocionUnoGratuito(String nombre, boolean estaVigente, Atraccion[] atracciones, Atraccion obtieneGratis) {
		super(nombre, estaVigente, atracciones);
		this.obtieneGratis = obtieneGratis;
	}
	
	@Override
	public int getPrecio() {
		return super.getPrecio() - this.obtieneGratis.getPrecio();	
	}
	
}
