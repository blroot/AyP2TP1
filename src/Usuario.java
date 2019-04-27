import java.util.ArrayList;

public class Usuario {

	private int presupuesto;
	private double tiempoDisponible;
	private TipoDeAtraccion tipoDeAtraccionPreferida;
	private ArrayList<Comprable> comprados;
	private String nombre;
	
	Usuario(String nombre, int presupuesto, double tiempoDisponible, TipoDeAtraccion tipoDeAtraccionPreferida) {
		this.comprados = new ArrayList<Comprable>();
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
		this.tipoDeAtraccionPreferida = tipoDeAtraccionPreferida;
	}

	public String getNombre() {
		return this.nombre;
	}

	public int getPresupuesto() {
		return this.presupuesto;
	}

	public double getTiempoDisponible() {
		return this.tiempoDisponible;
	}

	public TipoDeAtraccion getTipoDeAtraccion() {
		return this.tipoDeAtraccionPreferida;
	}
	
	public ArrayList<Comprable> getComprados() {
		return this.comprados;
	}

	public void agregarComprable(Comprable compra) {
		if (compra.puedeAdquirir(this)) {
			this.comprados.add(compra);
			restarRecursosUtilizados(compra.getPrecio(), compra.getTiempoTotal());
		}
	}

	private void restarRecursosUtilizados(int precio, double tiempoNecesario) {
		this.presupuesto -= precio;
		this.tiempoDisponible -= tiempoNecesario;
	}

	public String toString() {
		String usuario = "Usuario: " + this.nombre 
				+ "\nPresupuesto: " + this.presupuesto 
				+ "\nTiempo Disponible: " + this.tiempoDisponible
				+ "\nAtraccion Favorita: " + this.tipoDeAtraccionPreferida;
		return usuario;
	}
	
	
}
