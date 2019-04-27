import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class Sistema {
	
	public static void main(String[] args) throws IOException {
		
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		ArrayList<Comprable> comprables = new ArrayList<Comprable>();
		
		GestorDeArchivos.leerArchivoDeConfiguracion(usuarios, comprables);
		
		Iterator<Usuario> iteradorDeUsuarios = usuarios.iterator();
		System.out.println("Los siguientes Usuarios fueron cargados...");
		while (iteradorDeUsuarios.hasNext()) {
			System.out.println(iteradorDeUsuarios.next().toString());
		}
		
		Iterator<Comprable> iteradorDeComprables = comprables.iterator();
		System.out.println("Los/as siguientes Atracciones/Promociones fueron cargadas/os...");
		while (iteradorDeComprables.hasNext()) {
			System.out.println(iteradorDeComprables.next().toString());
		}
		
	}
	
}
