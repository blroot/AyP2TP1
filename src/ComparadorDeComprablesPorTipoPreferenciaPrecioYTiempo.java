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
		
		if (arg0.getTipoDeAtraccion() == this.tipoDeAtraccion && arg1.getTipoDeAtraccion() != this.tipoDeAtraccion) {
			puntaje = -1;
		} else if (arg1.getTipoDeAtraccion() == this.tipoDeAtraccion && arg0.getTipoDeAtraccion() != this.tipoDeAtraccion) {
			puntaje = 1;
		}
		
		if (puntaje == 0) {
			if (arg0.getTipoDeComprable() == TipoDeComprable.Promocion && arg1.getTipoDeComprable() != TipoDeComprable.Promocion) {
				puntaje = -1;
			} else if (arg1.getTipoDeComprable() == TipoDeComprable.Promocion && arg0.getTipoDeComprable() != TipoDeComprable.Promocion) {
				puntaje = 1;
			}
		}
		
		if (puntaje == 0) {
			if (arg0.getPrecio() > arg1.getPrecio()) {
				puntaje = -1;
			} else if (arg0.getPrecio() < arg1.getPrecio()) {
				puntaje = 1;
			}
		}
		if (puntaje == 0) {
			if (arg0.getTiempoTotal() > arg1.getTiempoTotal()) {
				puntaje = -1;
			} else {
				puntaje = 1;
			}
		}
				
		return puntaje;
	}
	
}
