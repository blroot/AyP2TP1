package sugerencias;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import org.omg.CORBA.DoubleSeqHelper;


public abstract class GestorDeArchivos {
	
	public static void leerArchivoDeConfiguracion(ArrayList<Usuario> usuarios, ArrayList<Comprable> comprables, 
			HashMap<String, Atraccion> mapaDeAtracciones) {
		
		int posicion = 0;
		
		try {
			FileReader archivo = new FileReader("src//configuracion");
			BufferedReader lector = new BufferedReader(archivo);
			String unaLinea;
			
			// Solo debug
			//System.out.println("Leyendo archivo de configuraci�n...");
					
			while ((unaLinea = lector.readLine()) != null) {
				posicion++;
				
				StringTokenizer st = new StringTokenizer(unaLinea);
				String tipoDeObjeto = st.nextToken(":");
				String DatosDeObjeto = st.nextToken(":");
							
				if (tipoDeObjeto.equalsIgnoreCase("Usuario")) {
					
					crearUsuario(DatosDeObjeto, usuarios);
					
				} else if (tipoDeObjeto.equalsIgnoreCase("Atraccion")) {
					
					crearAtraccion(DatosDeObjeto, comprables, mapaDeAtracciones);
					
				} else if (tipoDeObjeto.equalsIgnoreCase("PromocionPorcentual")) {
					
					crearPromocionPorcentual(DatosDeObjeto, comprables, mapaDeAtracciones);
					
				} else if (tipoDeObjeto.equalsIgnoreCase("PromocionTotal")) {
					
					crearPromocionTotal(DatosDeObjeto, comprables, mapaDeAtracciones);
					
				} else if (tipoDeObjeto.equalsIgnoreCase("PromocionUnoGratisl")) {
					
					crearPromocionUnoGratis(DatosDeObjeto, comprables, mapaDeAtracciones);
					
				}
				
			}
			archivo.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("No se encontro el archivo de configuracion");	
		} catch (NoSuchElementException e) {
			System.out.println("Linea mal formada en posicion: " + posicion);	
		} catch (IOException e) {
			System.out.println("No se pudo interpretar la linea: " + posicion);
		} 	
	}
	
	private static void crearPromocionPorcentual(String datosDeObjeto, ArrayList<Comprable> comprables, HashMap<String, Atraccion> mapaDeAtracciones) {
		StringTokenizer st = new StringTokenizer(datosDeObjeto);
		
		String estaVigente = st.nextToken(",");
		String nombre = st.nextToken(",");
		String arg = st.nextToken(",");
		ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>(); 
		
		// Guardo las atracciones en una lista
		while (st.hasMoreTokens()) {
			Atraccion atraccion = mapaDeAtracciones.get(st.nextToken(","));
			atracciones.add(atraccion);
		}
		
		try {
			Comprable nuevoComprable = new PromocionPorcentual(nombre, Boolean.parseBoolean(estaVigente), 
					atracciones, Double.parseDouble(arg));
			comprables.add(nuevoComprable);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (PromocionTieneUnSoloTipoDeAtraccion e) {
			System.err.println("Promocion mal configurada");
		}
		
	}
	
	private static void crearPromocionTotal(String datosDeObjeto, ArrayList<Comprable> comprables, HashMap<String, Atraccion> mapaDeAtracciones) {
		StringTokenizer st = new StringTokenizer(datosDeObjeto);
		
		String estaVigente = st.nextToken(",");
		String nombre = st.nextToken(",");
		String arg = st.nextToken(",");
		ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>(); 
		
		// Guardo las atracciones en una lista
		while (st.hasMoreTokens()) {
			Atraccion atraccion = mapaDeAtracciones.get(st.nextToken(","));
			atracciones.add(atraccion);
		}
		
		try {
			Comprable nuevoComprable = new PromocionAbsoluta(nombre, Boolean.parseBoolean(estaVigente), 
					atracciones, Integer.parseInt(arg));
			comprables.add(nuevoComprable);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (PromocionTieneUnSoloTipoDeAtraccion e) {
			System.out.println("Promocion mal configurada");
		}
		
	}
	
	private static void crearPromocionUnoGratis(String datosDeObjeto, ArrayList<Comprable> comprables, HashMap<String, Atraccion> mapaDeAtracciones) {
		StringTokenizer st = new StringTokenizer(datosDeObjeto);
		
		String estaVigente = st.nextToken(",");
		String nombre = st.nextToken(",");
		String arg = st.nextToken(",");
		ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>(); 
		
		// Guardo las atracciones en una lista
		while (st.hasMoreTokens()) {
			Atraccion atraccion = mapaDeAtracciones.get(st.nextToken(","));
			atracciones.add(atraccion);
		}
		
		try {
			Comprable nuevoComprable = new PromocionUnoGratuito(nombre, Boolean.parseBoolean(estaVigente), 
					atracciones, mapaDeAtracciones.get(arg));
			comprables.add(nuevoComprable);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (PromocionTieneUnSoloTipoDeAtraccion e) {
			System.out.println("Promocion mal configurada");
		}
		
	}

	private static void crearUsuario(String datosDeObjeto, ArrayList<Usuario> usuarios) {
		StringTokenizer st = new StringTokenizer(datosDeObjeto);
		
		String nombre = st.nextToken(",");
		int presupuesto = Integer.parseInt(st.nextToken(","));
		double tiempoDisponible = Double.parseDouble(st.nextToken(","));
		TipoDeAtraccion tipoDeAtraccionPreferida = TipoDeAtraccion.valueOf(st.nextToken());
		
		
		// Solo debug
		//System.out.println("Cargando nuevo Usuario...");
		
		Usuario nuevoUsuario = new Usuario(nombre, presupuesto, tiempoDisponible, tipoDeAtraccionPreferida);
		usuarios.add(nuevoUsuario);	
	}
	
	private static void crearAtraccion(String datosDeObjeto, ArrayList<Comprable> comprables, HashMap<String, Atraccion> mapaDeComprables) {
		StringTokenizer st = new StringTokenizer(datosDeObjeto);
		
		String nombre = st.nextToken(",");
		int costo = Integer.parseInt(st.nextToken(","));
		double tiempo = Double.parseDouble(st.nextToken(","));
		int cupo = Integer.parseInt(st.nextToken(","));
		TipoDeAtraccion tipo = TipoDeAtraccion.valueOf(st.nextToken(","));
		
		// Solo debug
		//System.out.println("Cargando nueva Atraccion...");
		
		Atraccion nuevaAtraccion = new Atraccion(nombre, costo, tiempo, cupo, tipo);
		comprables.add(nuevaAtraccion);
		mapaDeComprables.put(nuevaAtraccion.getNombre(), nuevaAtraccion);
	}
	
	public static void escribirArchivoDeSalida(ArrayList<Usuario> usuarios) {
		try {
			FileWriter escritor = new FileWriter("src//resumen");
			for (Usuario usuario: usuarios) {
				escritor.write("--- Resumen de compra de usuario " + usuario.getNombre() + "---\n");
				escritor.write(usuario.toString());
				escritor.write("\nDetalle de las atracciones/promociones compradas: \n");
				for (Comprable comprable: usuario.getComprados()) {
					escritor.write(comprable.toString()+"\n");
				}
				escritor.write("\nTotal a pagar: " + usuario.getDineroGastado());
				escritor.write("\nTotal de tiempo estimado para el itinerario: " + usuario.getTiempoGastado());
				escritor.write("\n");
			}
			escritor.close();
		} catch (FileNotFoundException e) {
			System.out.println("No se encontro el archivo de salida");	
		} catch (IOException e) {
			System.out.println("Error al escribir linea");
		} 	
	}

}
