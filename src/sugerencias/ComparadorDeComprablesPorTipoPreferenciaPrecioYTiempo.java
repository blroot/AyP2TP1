package sugerencias;
import java.util.Comparator;

public class ComparadorDeComprablesPorTipoPreferenciaPrecioYTiempo implements Comparator<Comprable> {
	
	private TipoDeAtraccion tipoDeAtraccion;
	
	public ComparadorDeComprablesPorTipoPreferenciaPrecioYTiempo(TipoDeAtraccion tipoDeAtraccion) {
		this.tipoDeAtraccion = tipoDeAtraccion;
	}

	@Override
	public int compare(Comprable arg0, Comprable arg1) {
		// TODO: seguramente se puede mejorar
		
		int puntaje = 0;
		
		// Primero se compara por tipo de atracci�n
		if (arg0.getTipoDeAtraccion() == this.tipoDeAtraccion && arg1.getTipoDeAtraccion() != this.tipoDeAtraccion) {
			puntaje = -1;
		} else if (arg1.getTipoDeAtraccion() == this.tipoDeAtraccion && arg0.getTipoDeAtraccion() != this.tipoDeAtraccion) {
			puntaje = 1;
		}
		
		// Si hasta ahora son iguales se compara por prioridad
		if (puntaje == 0) {
			puntaje += arg0.getPrioridad().compareTo(arg1.getPrioridad());
		}
		
		// Si hasta ahora son iguales se compara por precio
		if (puntaje == 0) {
			puntaje -= Integer.compare(arg0.getPrecio(), arg1.getPrecio());
		}
		
		// Si hasta ahora son iguales se compara por tiempo
		if (puntaje == 0) {
			puntaje -= Double.compare(arg0.getTiempoTotal(), arg1.getTiempoTotal());
		}
				
		return puntaje;
	}
	
}
