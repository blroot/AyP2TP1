package sugerencias;

public class UsuarioYAtraccionTiempoIncorrectoException extends EntradaDeDatosException {
	
//	public static final String positivo="El valor referido a Tiempo Disponible, es incorrecto. Este debe ser mayor a 0";
	@Override
	public String exponerError(String nombre) {
		return "El valor referido a Tiempo en: "+nombre+", es incorrecto. Este debe ser mayor a 0";
	}
}
