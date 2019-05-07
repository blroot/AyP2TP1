package sugerencias;

public class Atraccion extends Comprable {
	
	private int costo;
	private double promedioDeTiempo;
	private int cupoDiario;
	private TipoDeAtraccion tipoDeAtraccion;

	public Atraccion(String nombre, int costo, double tiempo, int cupo, TipoDeAtraccion tipo) {
		super(nombre, Prioridad.Baja);
		this.costo = costo;
		this.promedioDeTiempo = tiempo;
		this.cupoDiario = cupo;
		this.tipoDeAtraccion = tipo;
	}
	
	public void disminuirCupoDiario() {
		this.cupoDiario--;
	}

	@Override
	public int getPrecio() {
		return this.costo;
	}

	@Override
	public boolean puedeAdquirir(Usuario usuario) {
		if (usuario.getPresupuesto() < this.getPrecio() 
				|| usuario.getTiempoDisponible() < this.getTiempoTotal()
				|| usuario.getComprados().contains(this)) {
			return false;
		}
				
		// Si ya adquirió la atracción retorna false
		for (Comprable comprable: usuario.getComprados()) {
			if (comprable.tieneOEsAtraccion(this)) {
				return false;
			}
		}
		
		return true;
	}

	@Override
	public void vender(Usuario usuario) throws UsuarioNoPuedeAdquirirComprable {
		if(puedeAdquirir(usuario)) {
			usuario.agregarComprable(this);
			this.cupoDiario--;
		} else {
			throw new UsuarioNoPuedeAdquirirComprable("El usuario no tiene recursos suficientes para comprar el comprable o comprable no tiene cupo disponible");
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

	@Override
	public boolean tieneOEsAtraccion(Atraccion atraccion) {
		return this == atraccion;
	}

}
