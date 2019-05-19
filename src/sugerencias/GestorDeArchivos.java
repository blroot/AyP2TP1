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


public class GestorDeArchivos {
	
	private ArrayList<Usuario> usuarios;
	private ArrayList<Comprable> comprables;
	private HashMap<String, Atraccion> mapaDeAtracciones;
	private final String rutaConfiguracionUsuarios = "src//usuarios";
	private final String rutaConfiguracionAtracciones = "src//atracciones";
	private final String rutaConfiguracionPromociones = "src//promociones";
	
	public GestorDeArchivos() {
		this.usuarios = new ArrayList<Usuario>();
		this.comprables = new ArrayList<Comprable>();
		this.mapaDeAtracciones = new HashMap<String, Atraccion>();
	}
	
	public void leerArchivosDeConfiguracion() {
		
		leerArchivoDeUsuarios();
		leerArchivoDeAtracciones();
		leerArchivoDePromociones();
		// Una vez leídas las promociones ya no necesito el diccionario
		this.mapaDeAtracciones = null;
	}
	
	private void leerArchivoDeUsuarios() {
		
		int posicion = 0;
		
		try {
			FileReader archivo = new FileReader(rutaConfiguracionUsuarios);
			BufferedReader lector = new BufferedReader(archivo);
			String unaLinea;
							
			while ((unaLinea = lector.readLine()) != null) {
				posicion++;
				crearUsuario(unaLinea);
			}
			archivo.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("No se encontro el archivo de usuarios");	
		} catch (NoSuchElementException e) {
			System.out.println("Linea mal formada en posicion: " + posicion);	
		} catch (IOException e) {
			System.out.println("No se pudo interpretar la linea: " + posicion);
		} 	
	}
	
	private void leerArchivoDeAtracciones() {
		
		int posicion = 0;
		
		try {
			FileReader archivo = new FileReader(rutaConfiguracionAtracciones);
			BufferedReader lector = new BufferedReader(archivo);
			String unaLinea;
								
			while ((unaLinea = lector.readLine()) != null) {
				posicion++;	
				crearAtraccion(unaLinea);		
			}
			archivo.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("No se encontro el archivo de atracciones");	
		} catch (NoSuchElementException e) {
			System.out.println("Linea mal formada en posicion: " + posicion);	
		} catch (IOException e) {
			System.out.println("No se pudo interpretar la linea: " + posicion);
		} 	
	}
	
	private void leerArchivoDePromociones() {
		
		int posicion = 0;
		
		try {
			FileReader archivo = new FileReader(rutaConfiguracionPromociones);
			BufferedReader lector = new BufferedReader(archivo);
			String unaLinea;
			
			while ((unaLinea = lector.readLine()) != null) {
				posicion++;
				
				StringTokenizer st = new StringTokenizer(unaLinea);
				String tipoDeObjeto = st.nextToken(":");
				String DatosDeObjeto = st.nextToken(":");
							
				if (tipoDeObjeto.equalsIgnoreCase("Porcentual")) {
					
					crearPromocionPorcentual(DatosDeObjeto,posicion);
					
				} else if (tipoDeObjeto.equalsIgnoreCase("Total")) {
					
					crearPromocionTotal(DatosDeObjeto,posicion);
					
				} else if (tipoDeObjeto.equalsIgnoreCase("UnoGratis")) {
					
					crearPromocionUnoGratis(DatosDeObjeto,posicion);
					
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
	
	private void crearPromocionPorcentual(String datos, int linea) {
		StringTokenizer st = new StringTokenizer(datos);
		
		String estaVigente = st.nextToken(",");
		String nombre = st.nextToken(",");
		String arg = st.nextToken(",");
		int posicion=0;
		
		try {
			ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>(); 
			
			// Guardo las atracciones en una lista
			while (st.hasMoreTokens()) {
				posicion++;
				Atraccion atraccion = this.mapaDeAtracciones.get(st.nextToken(","));
				atracciones.add(atraccion);
			}
			Comprable nuevoComprable = new PromocionPorcentual(nombre, Boolean.parseBoolean(estaVigente), 
					atracciones, Double.parseDouble(arg));
			this.comprables.add(nuevoComprable);
		} catch (NumberFormatException e) {
			System.err.println("Error en la atraccion "+nombre+". El 3er valor, debe ser un numero");
			e.printStackTrace();
		} catch (PromocionTieneUnSoloTipoDeAtraccion e) {
			System.err.println("Promocion mal configurada");
		} catch(IllegalArgumentException e){
			System.err.println(nombre+" tiene un Tipo de atraccion invalida");
		} catch(NullPointerException e) {
			String aviso="La promocion: "+nombre+" NO fue cargada";
			System.err.println("La "+posicion+"° Atraccion, de la linea "+linea+" es invalida. "+aviso);
			//"Atraccion/es invalida/s en la linea: "+posicion+"\n"+aviso
		}
		
	}
	
	private void crearPromocionTotal(String datos, int linea) {
		StringTokenizer st = new StringTokenizer(datos);
		
		String estaVigente = st.nextToken(",");
		String nombre = st.nextToken(",");
		String arg = st.nextToken(",");
		int posicion=0;
		
		try {
			ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>(); 
			
			// Guardo las atracciones en una lista
			while (st.hasMoreTokens()) {
				posicion++;
				Atraccion atraccion = this.mapaDeAtracciones.get(st.nextToken(","));
				atracciones.add(atraccion);
			}
			Comprable nuevoComprable = new PromocionAbsoluta(nombre, Boolean.parseBoolean(estaVigente), 
					atracciones, Integer.parseInt(arg));
			this.comprables.add(nuevoComprable);
		} catch (NumberFormatException e) {
			System.err.println("Error en la atraccion "+nombre+". El 3er valor, debe ser un numero");
			e.printStackTrace();
		} catch (PromocionTieneUnSoloTipoDeAtraccion e) {
			System.out.println("Promocion mal configurada");
		} catch(IllegalArgumentException e){
			System.err.println(nombre+" tiene un Tipo de atraccion invalida");
		} catch(NullPointerException e) {
			String aviso="La promocion: "+nombre+" NO fue cargada";
			System.err.println("La "+posicion+"° Atraccion, de la linea "+linea+" es invalida. "+aviso);
		}
		
	}
	
	private void crearPromocionUnoGratis(String datos,int linea) {
		StringTokenizer st = new StringTokenizer(datos);
		
		String estaVigente = st.nextToken(",");
		String nombre = st.nextToken(",");
		String arg = st.nextToken(",");
		int posicion=0;
		try {
			ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>(); 
			
			// Guardo las atracciones en una lista
			while (st.hasMoreTokens()) {
				posicion++;
				Atraccion atraccion = this.mapaDeAtracciones.get(st.nextToken(","));
				atracciones.add(atraccion);
			}
			Comprable nuevoComprable = new PromocionUnoGratuito(nombre, Boolean.parseBoolean(estaVigente), 
					atracciones, this.mapaDeAtracciones.get(arg));
			comprables.add(nuevoComprable);
		} catch (NumberFormatException e) {
			//es necesario?
			e.printStackTrace();
		} catch (PromocionTieneUnSoloTipoDeAtraccion e) {
			System.out.println("Promocion mal configurada");
		} catch(IllegalArgumentException e){
			System.err.println(nombre+" tiene un Tipo de atraccion invalida");
		} catch(NullPointerException e) {
			String aviso="La promocion: "+nombre+" NO fue cargada";
			System.err.println("La "+posicion+"° Atraccion, de la linea "+linea+" es invalida. "+aviso);
		}
		
	}

	private void crearUsuario(String datos) {
		StringTokenizer st = new StringTokenizer(datos);
		
		String nombre = st.nextToken(",");
		try{
			int presupuesto = Integer.parseInt(st.nextToken(","));
			double tiempoDisponible = Double.parseDouble(st.nextToken(","));
			TipoDeAtraccion tipoDeAtraccionPreferida = TipoDeAtraccion.valueOf(st.nextToken());		
			Usuario nuevoUsuario = new Usuario(nombre, presupuesto, tiempoDisponible, tipoDeAtraccionPreferida);
			this.usuarios.add(nuevoUsuario);	
		} catch(NumberFormatException e) {
			System.err.println("Error en el Usuario "+nombre+". En el 2do y 3er valor, se deben ingresar numeros");
		} catch(IllegalArgumentException e){
			System.err.println(nombre+" tiene un Tipo de atraccion invalida");
		}
		
	}
	
	private void crearAtraccion(String datos) {
		StringTokenizer st = new StringTokenizer(datos);
		
		
		String nombre = st.nextToken(",");
	try{	
		int costo = Integer.parseInt(st.nextToken(","));
		double tiempo = Double.parseDouble(st.nextToken(","));
		int cupo = Integer.parseInt(st.nextToken(","));
		TipoDeAtraccion tipo = TipoDeAtraccion.valueOf(st.nextToken(","));

		Atraccion nuevaAtraccion = new Atraccion(nombre, costo, tiempo, cupo, tipo);
		this.comprables.add(nuevaAtraccion);
		this.mapaDeAtracciones.put(nuevaAtraccion.getNombre(), nuevaAtraccion);
	} catch(NumberFormatException e) {
		System.err.println("Error en la atraccion "+nombre+". El 2do, 3er y 4to valor, deben ser numeros");
	} catch(IllegalArgumentException e){
		System.err.println(nombre+" tiene un Tipo de atraccion invalida");
	}
	}
	
	public void escribirArchivoDeSalida() {
		try {
			FileWriter escritor = new FileWriter("src//resumen");
			for (Usuario usuario: this.usuarios) {
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
	
	public ArrayList<Usuario> getUsuarios() {
		return this.usuarios;
	}
	
	public ArrayList<Comprable> getComprables() {
		return this.comprables;
	}

}
