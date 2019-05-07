package sugerencias;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

public abstract class Sistema {
	
	public static void main(String[] args) throws IOException {
		
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		ArrayList<Comprable> comprables = new ArrayList<Comprable>();
		HashMap<String, Atraccion> mapaDeAtracciones = new HashMap<String, Atraccion>();
		
		GestorDeArchivos.leerArchivoDeConfiguracion(usuarios, comprables, mapaDeAtracciones);
		
		////////////////////////////////////
		////////////////////////////////////
		//creo scanner para aceptar/rechazar
		Scanner entrada=new Scanner(System.in);
		//se recorre usuario por usuario
		for(Usuario usuario: usuarios) {
			System.out.println("\nHola " + usuario.getNombre());
			//se ordenan las atracciones cargadas
			Comparator<Comprable> comparador = new ComparadorDeComprablesPorTipoPreferenciaPrecioYTiempo(usuario.getTipoDeAtraccion());
			Collections.sort(comprables,comparador);
			//se iteran las atracciones
			for (Comprable comprable : comprables) {
				//si no tiene atraccion repetida y puede adquirir, pasa
				if(comprable.puedeAdquirir(usuario)) {
					//ofrece atraccion
					System.out.println("Tenemos la siguiente oferta para vos: \n" + comprable.toString());
					System.out.println("¿Aceptas la oferta?: <aceptar/rechazar>");
					if(entrada.hasNext()) {
						//si pongo el "Aceptar" funciona mal
						if(entrada.next().equals("aceptar")) {
							try {
								comprable.vender(usuario);
							} catch (UsuarioNoPuedeAdquirirComprable e) {
								System.out.println(e);
							}						
							System.out.println("\nGracias por tu compra!");
						if(entrada.nextLine().equals("rechazar")) {
								break;
							}
						}
					}
				}
			}
			//se imprime el itinerario y aviso que paso al otro usuario
			System.out.println("\nItinerario del usuario: "+usuario.getNombre()+"\nDinero gastado: "+usuario.getDineroGastado()
								+"\nTiempo necesario: "+usuario.getTiempoGastado()+"\nCompró: ");
			usuario.getNombresDeComprados();
			System.out.println("\nPasa al otro usuario");
		}
		GestorDeArchivos.escribirArchivoDeSalida(usuarios);
		entrada.close();
	}
	
}
