package sugerencias;

public class DineroIncorrectoException extends EntradaDeDatosException {
	
	@Override
	public String exponerError(String nombre) {
		return "El valor referido a dinero en: "+nombre+", es incorrecto. Este debe ser mayor a 0";
	}
	
}
