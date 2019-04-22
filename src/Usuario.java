import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;

public class Usuario {
	
	private int presupuesto;
	private double tiempoDisponible;
	private TipoDeAtraccion tipoDeAtraccionPreferida;
	private ArrayList<Comprable> comprados;
	
	public Usuario(int presupuesto, double tiempoDisponible, TipoDeAtraccion tipoDeAtraccionPreferida) {
		this.presupuesto=presupuesto;
		this.tipoDeAtraccionPreferida=tipoDeAtraccionPreferida;
		this.tiempoDisponible=tiempoDisponible;
	}
	
	public int getPresupuesto() {
		return presupuesto;
	}
	
	public double getTiempoDisponible() {
		return tiempoDisponible;
	}
	
	public TipoDeAtraccion getTipoDeAtraccion() {
		return tipoDeAtraccionPreferida;
	}
	
	public void agregarComprable(Comprable compra) {
		if(compra.puedeAdquirir(this)) {
			comprados.add(compra);	
			restarRecursosUtilizados(compra.getPrecio(),compra.getTiempoTotal());
		}
	}
	
	private void restarRecursosUtilizados(int precio, double tiempoNecesario) {
		presupuesto-=precio;
		tiempoDisponible-=tiempoNecesario;
	}
}
