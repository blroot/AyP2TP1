
public abstract class Comprable {

	private TipoDeComprable tipoDeComprable;
	private String nombre;
	
	Comprable(String nombre, TipoDeComprable tipoDeComprable) {
		this.nombre = nombre;
		this.tipoDeComprable = tipoDeComprable;
	}
	
	public TipoDeComprable getTipoDeComprable() {
		return this.tipoDeComprable;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public abstract int getPrecio();
	public abstract boolean puedeAdquirir(Usuario usuario);
	public abstract void vender(Usuario usuario);
	public abstract double getTiempoTotal();
	public abstract TipoDeAtraccion getTipoDeAtraccion();
	public abstract String toString();
		
}
