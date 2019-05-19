package sugerencias;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

public abstract class Sistema {
	
	public static void main(String[] args) throws IOException {
		
		GestorDeArchivos gestorDeArchivos = new GestorDeArchivos();
		gestorDeArchivos.leerArchivosDeConfiguracion();
		
//		//creo scanner para aceptar/rechazar
//		Scanner entrada=new Scanner(System.in);
		
		ArrayList<Usuario> usuarios = gestorDeArchivos.getUsuarios();
		ArrayList<Comprable> comprables = gestorDeArchivos.getComprables();
		
		Sugerencia sugerencia=new Sugerencia(usuarios, comprables);
		
		sugerencia.sugerirServicios();
		
		
		gestorDeArchivos.escribirArchivoDeSalida();
	}
	
}

////se recorre usuario por usuario
//for(Usuario usuario: usuarios) {
//	System.out.println("\nHola " + usuario.getNombre());
//	//se ordenan las atracciones cargadas
//	Comparator<Comprable> comparador = new ComparadorDeComprablesPorTipoPreferenciaPrecioYTiempo(usuario.getTipoDeAtraccion());
//	Collections.sort(comprables,comparador);
//	//se iteran las atracciones
//	for (Comprable comprable : comprables) {
//		//System.out.println(comprables);
//		//si no tiene atraccion repetida y puede adquirir, pasa
//		if(comprable.puedeAdquirir(usuario)) {
//			boolean respuestaIncorrecta = false;
//			do {
//				respuestaIncorrecta = false;
//				//ofrece atraccion
//				System.out.println("Tenemos la siguiente oferta para vos: \n" + comprable.toString());
//				System.out.println("¿Aceptas la oferta?: <si/no>");
//				if(entrada.hasNext()) {
//					String siguiente = entrada.next();
//					//si pongo el "Aceptar" funciona mal
//					if(siguiente.equals("si")) {
//						try {
//							comprable.vender(usuario);
//						} catch (UsuarioNoPuedeAdquirirComprable e) {
//							System.out.println(e);
//						}						
//						System.out.println("\nGracias por tu compra!");
//					} else if(siguiente.equals("no")) {
//						break;
//					} else {
//						System.err.println("\nDebe ingresar si o no");
//						respuestaIncorrecta = true;
//					}
//				}
//			} while (respuestaIncorrecta); 
//		}
//	}
//	//se imprime el itinerario y aviso que paso al otro usuario
//	System.out.println("\nItinerario del usuario: "+usuario.getNombre()+"\nDinero gastado: "+usuario.getDineroGastado()
//	+"\nTiempo necesario: "+usuario.getTiempoGastado()+"\nCompró: ");
//	usuario.getNombresDeComprados();
//	System.out.println("\nPasa al otro usuario");
//}
//gestorDeArchivos.escribirArchivoDeSalida();
//entrada.close();