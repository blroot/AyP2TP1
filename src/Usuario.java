import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.junit.platform.commons.util.ToStringBuilder;

public class Usuario {

	private static int presupuesto;
	private static double tiempoDisponible;
	private static String tipoDeAtraccionPreferida;
	private ArrayList<Comprable> comprados;
	private static String nombre;
	private static String usuario;
	private static TipoDeAtraccion atraccion;

	public static String getNombre() {
		return nombre;
	}

	public static int getPresupuesto() {
		return presupuesto;
	}

	public static double getTiempoDisponible() {
		return tiempoDisponible;
	}

	public static TipoDeAtraccion getTipoDeAtraccion() {

		atraccion = TipoDeAtraccion.valueOf(tipoDeAtraccionPreferida);
		return atraccion;
	}

	public void agregarComprable(Comprable compra) {
		if (compra.puedeAdquirir(this)) {
			comprados.add(compra);
			restarRecursosUtilizados(compra.getPrecio(), compra.getTiempoTotal());
		}
	}

	private void restarRecursosUtilizados(int precio, double tiempoNecesario) {
		presupuesto -= precio;
		tiempoDisponible -= tiempoNecesario;
	}

	public static String User() {
		usuario = "Usuario: " + nombre + "\nPresupuesto: " + presupuesto + "\nTiempo Disponible: " + tiempoDisponible
				+ "\nAtraccion Favorita: " + tipoDeAtraccionPreferida;
		return usuario;
	}

	public static void main(String[] args) throws IOException {
		FileReader archivo = new FileReader("src\\usuarios");
		BufferedReader lector = new BufferedReader(archivo);

		String primeraLinea = lector.readLine();
		StringTokenizer st = new StringTokenizer(primeraLinea, ",");
		if (st.hasMoreTokens()) {
			nombre = st.nextToken();
			presupuesto = Integer.parseInt(st.nextToken());
			tiempoDisponible = Double.parseDouble(st.nextToken());
			tipoDeAtraccionPreferida = st.nextToken();

		}

		lector.close();
		System.out.println(User());
		System.out.println(getTipoDeAtraccion());

	}
}

//Lee todos los usuarios
/*
 * while (lector.ready()) { String primeraLinea = lector.readLine();
 * StringTokenizer st = new StringTokenizer(primeraLinea, ","); while
 * (st.hasMoreTokens()) { System.out.println(st.nextToken());
 * 
 * } System.out.println(); }
 * 
 * lector.close();
 * 
 * }
 */
