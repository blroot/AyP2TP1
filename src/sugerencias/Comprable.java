package sugerencias;

public abstract class Comprable {

	private Prioridad prioridad;
	private String nombre;
	
	public Comprable(String nombre, Prioridad prioridad) {
		this.nombre = nombre;
		this.prioridad = prioridad;
	}
	
	public Prioridad getPrioridad() {
		return this.prioridad;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public abstract int getPrecio();
	public abstract boolean puedeAdquirir(Usuario usuario);
	public abstract boolean tieneOEsAtraccion(Atraccion atraccion);
	public abstract void vender(Usuario usuario) throws UsuarioNoPuedeAdquirirComprable;
	public abstract double getTiempoTotal();
	public abstract TipoDeAtraccion getTipoDeAtraccion();
	public abstract String toString();
		
}
