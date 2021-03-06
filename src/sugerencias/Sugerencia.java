package sugerencias;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Sugerencia {
	
	public Sugerencia(ArrayList<Usuario> usuarios,ArrayList<Comprable> comprables) {
		this.comprables=comprables;
		this.usuarios=usuarios;
	}
	
	public void sugerirServicios() {	
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
					boolean respuestaIncorrecta = false;
					//ofrece atraccion, repite si la respuesta no es "si" o "no"
					do {
						respuestaIncorrecta = false;
						System.out.println("Tenemos la siguiente oferta para vos: \n" + comprable.toString());
						System.out.println("�Aceptas la oferta?: <si/no>");
						if(entrada.hasNext()) {
							String siguiente = entrada.next();
							if(siguiente.equals("si")) {
								try {
									comprable.vender(usuario);
								} catch (UsuarioNoPuedeAdquirirComprable e) {
									System.out.println(e);
								}						
								System.out.println("\nGracias por tu compra!");
							} else if(siguiente.equals("no")) {
									break;
							} else {
								System.err.println("\nDebe ingresar si o no");
								respuestaIncorrecta = true;
							}
						}
					} while (respuestaIncorrecta); 
				}
			}
			//se imprime el itinerario y aviso que paso al otro usuario
			System.out.println("\nItinerario del usuario: "+usuario.getNombre()+"\nDinero gastado: "+usuario.getDineroGastado()
								+"\nTiempo necesario: "+usuario.getTiempoGastado()+"\nCompr�: ");
			usuario.getNombresDeComprados();
			System.out.println("\nPasa al otro usuario");
		}
		entrada.close();
	}
	
	ArrayList<Usuario> usuarios;
	ArrayList<Comprable> comprables;
	Scanner entrada=new Scanner(System.in);
}
