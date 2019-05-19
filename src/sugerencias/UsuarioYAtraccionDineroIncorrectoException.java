package sugerencias;

public class UsuarioYAtraccionDineroIncorrectoException extends EntradaDeDatosException {
	
	public static final String positivo="El valor referido a Presupuesto, es incorrecto. Este debe ser mayor a 0";
	@Override
	public String exponerError(String nombre) {
		return "El valor referido a dinero en: "+nombre+", es incorrecto. Este debe ser mayor a 0";
	}
	
}
