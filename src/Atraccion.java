
public class Atraccion extends Comprable {
	
	private int costo;
	private double promedioDeTiempo;
	private int cupoDiario;
	private TipoDeAtraccion tipoDeAtraccion;

	Atraccion(String nombre, int costo, double tiempo, int cupo, TipoDeAtraccion tipo) {
		super(nombre, Prioridad.Baja);
		this.costo = costo;
		this.promedioDeTiempo = tiempo;
		this.cupoDiario = cupo;
		this.tipoDeAtraccion = tipo;
	}

	@Override
	public int getPrecio() {
		return this.costo;
	}

	@Override
	public boolean puedeAdquirir(Usuario usuario) {
		if (usuario.getPresupuesto() < this.getPrecio() || usuario.getTiempoDisponible() < this.getTiempoTotal()) {
			return false;
		}
		
		return true;
	}

	@Override
	public void vender(Usuario usuario) {
		// TODO: si no se le puede vender lanzar excepcion
		if(puedeAdquirir(usuario)) {
			usuario.agregarComprable(this);
			this.cupoDiario--;
		}
	}

	@Override
	public double getTiempoTotal() {
		return this.promedioDeTiempo;
	}

	@Override
	public TipoDeAtraccion getTipoDeAtraccion() {
		return this.tipoDeAtraccion;
	}

	@Override
	public String toString() {
		return "Nombre: " + super.getNombre() 
			+ "\nTipo: " + this.tipoDeAtraccion 
			+ "\nPrecio: " + this.getPrecio() 
			+ "\nPromedio de Tiempo: " + this.promedioDeTiempo 
			+ "\nCupo: " + this.cupoDiario;
	}

}
