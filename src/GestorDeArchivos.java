import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;


public abstract class GestorDeArchivos {
	
	public static void leerArchivoDeConfiguracion(ArrayList<Usuario> usuarios, ArrayList<Comprable> comprables) {
		
		int posicion = 0;
		
		try {
			FileReader archivo = new FileReader("src\\configuracion");
			BufferedReader lector = new BufferedReader(archivo);
			String unaLinea;
			
			System.out.println("Leyendo archivo de configuración...");
					
			while ((unaLinea = lector.readLine()) != null) {
				posicion++;
				
				StringTokenizer st = new StringTokenizer(unaLinea);
				String tipoDeObjeto = st.nextToken(":");
				String DatosDeObjeto = st.nextToken(":");
							
				if (tipoDeObjeto.equalsIgnoreCase("Usuario")) {
					
					crearUsuario(DatosDeObjeto, usuarios);
					
				} else if (tipoDeObjeto.equalsIgnoreCase("Atraccion")) {
					
					crearAtraccion(DatosDeObjeto, comprables);
					
				}
				
			}
			archivo.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("No se econtró el archivo");	
		} catch (NoSuchElementException e) {
			System.out.println("Linea mal formada en posición: " + posicion);	
		} catch (IOException e) {
			System.out.println("No se pudo interpretar la linea: " + posicion);
		} 	
	}
	
	private static void crearUsuario(String datosDeObjeto, ArrayList<Usuario> usuarios) {
		StringTokenizer st = new StringTokenizer(datosDeObjeto);
		
		String nombre = st.nextToken(",");
		int presupuesto = Integer.parseInt(st.nextToken(","));
		double tiempoDisponible = Double.parseDouble(st.nextToken(","));
		TipoDeAtraccion tipoDeAtraccionPreferida = TipoDeAtraccion.valueOf(st.nextToken());
		
		System.out.println("Cargando nuevo Usuario...");
		
		Usuario nuevoUsuario = new Usuario(nombre, presupuesto, tiempoDisponible, tipoDeAtraccionPreferida);
		usuarios.add(nuevoUsuario);	
	}
	
	private static void crearAtraccion(String datosDeObjeto, ArrayList<Comprable> comprables) {
		StringTokenizer st = new StringTokenizer(datosDeObjeto);
		
		String nombre = st.nextToken(",");
		int costo = Integer.parseInt(st.nextToken(","));
		double tiempo = Double.parseDouble(st.nextToken(","));
		int cupo = Integer.parseInt(st.nextToken(","));
		TipoDeAtraccion tipo = TipoDeAtraccion.valueOf(st.nextToken(","));
		
		System.out.println("Cargando nueva Atraccion...");
		
		Atraccion nuevaAtraccion = new Atraccion(nombre, costo, tiempo, cupo, tipo);
		comprables.add(nuevaAtraccion);	
	}
	
	public static void escribirArchivoDeSalida(ArrayList<Usuario> usuarios, ArrayList<Comprable> comprables) {
		
	}

}
