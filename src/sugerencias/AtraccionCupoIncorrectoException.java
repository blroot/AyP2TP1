package sugerencias;

public class AtraccionCupoIncorrectoException extends EntradaDeDatosException{

	public String exponerError(String nombre) {
		return "El valor referido a Cupo en: "+nombre+", es incorrecto. Este debe ser mayor a 0";
	}
}
