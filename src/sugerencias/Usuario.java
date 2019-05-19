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
	private VerificarDeEntradas verificador;
	
	public Usuario(String nombre, int presupuesto, double tiempoDisponible, TipoDeAtraccion tipoDeAtraccionPreferida) throws EntradaDeDatosException {
		this.comprados = new ArrayList<Comprable>();
		this.nombre = nombre;
		verificador=new VerificarDeEntradas();
		setPresupuesto(presupuesto);
		setTiempoDisponible(tiempoDisponible);
		setTipoDeAtraccionPreferida(tipoDeAtraccionPreferida);		
		dineroGastado=0;
		tiempoGastado=0;
	}

	public TipoDeAtraccion getTipoDeAtraccionPreferida() {
		return tipoDeAtraccionPreferida;
	} 
	
	public void setTipoDeAtraccionPreferida(TipoDeAtraccion tipoDeAtraccionPreferida) {
			this.tipoDeAtraccionPreferida = tipoDeAtraccionPreferida;
	}

	public void setPresupuesto(int presupuesto) throws DineroIncorrectoException {
		//tambien le paso el nombre para que arroje bien la excepcion
		if(verificador.comprobacionInicialDineroIngresado(presupuesto)){
			this.presupuesto=presupuesto;
		}
	}

	public void setTiempoDisponible(double tiempoDisponible) throws TiempoIncorrectoException {
		if(verificador.comprobacionInicialTiempoIngresado(tiempoDisponible)) {
			this.tiempoDisponible=tiempoDisponible;
		}
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
