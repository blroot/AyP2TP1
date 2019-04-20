
public class Atraccion extends Comprable {
	
	private int costo;
	private double promedioDeTiempo;
	private int cupoDiario;
	private TipoDeAtraccion tipoDeAtraccion;

	Atraccion(String nombre, int costo, double tiempo, int cupo, TipoDeAtraccion tipo) {
		super(nombre, TipoDeComprable.Atraccion);
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void vender(Usuario usuario) {
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
		// TODO Auto-generated method stub
		return null;
	}

}
