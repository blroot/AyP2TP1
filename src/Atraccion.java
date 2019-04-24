
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
		if(usuario.getPresupuesto()>getPrecio()&&usuario.getTiempoDisponible()>getTiempoTotal()) {
			
		} else if(!(usuario.getPresupuesto()>getPrecio())) {
			System.err.println();//falta la excepcion, en general
		} else {
			
		}
		return false;
	}

	@Override
	public void vender(Usuario usuario) {
		if(puedeAdquirir(usuario)) {
			usuario.agregarComprable(this);
		}
		// TODO Auto-generated method stub
		
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
		return super.getNombre() + " " + this.tipoDeAtraccion + " " + this.getPrecio() + " " + this.promedioDeTiempo;
	}

}
