package sugerencias;
import java.util.ArrayList;

public class Usuario {

	private int presupuesto;
	private double tiempoDisponible;
	private TipoDeAtraccion tipoDeAtraccionPreferida;
	private ArrayList<Comprable> comprados;
	private String nombre;
	private int dineroGastado;
	private double tiempoGastado;
	
	public Usuario(String nombre, int presupuesto, double tiempoDisponible, TipoDeAtraccion tipoDeAtraccionPreferida) {
		this.comprados = new ArrayList<Comprable>();
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
		this.tipoDeAtraccionPreferida = tipoDeAtraccionPreferida;
		dineroGastado=0;
		tiempoGastado=0;
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

	public void getNombresDeComprados() {
		for (Comprable comprable : comprados) {
			System.out.println(comprable.getNombre());
		}
	}
	//agregue atributos de tiempo y dinero gastado, con sus get
	public void agregarComprable(Comprable compra) throws UsuarioNoPuedeAdquirirComprable {
		if (compra.puedeAdquirir(this)) {
			this.comprados.add(compra);
			restarRecursosUtilizados(compra.getPrecio(), compra.getTiempoTotal());
			dineroGastado+=compra.getPrecio();
			tiempoGastado+=compra.getTiempoTotal();
		} else {
			throw new UsuarioNoPuedeAdquirirComprable("El usuario no tiene recursos suficientes para comprar el comprable o comprable no tiene cupo disponible");
		}
	}
	
	public double getTiempoGastado() {
		return this.tiempoGastado;
	}
	
	public int getDineroGastado() {
		return this.dineroGastado;
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
