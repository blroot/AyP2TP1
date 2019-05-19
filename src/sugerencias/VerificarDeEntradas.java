package sugerencias;

public class VerificarDeEntradas {
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	private boolean comprobacionInicialDineroIngresado(int presupuesto) throws UsuarioYAtraccionDineroIncorrectoException {
		boolean correcto=false;
		if(presupuesto>0){
			correcto=true;
			return correcto;
		} else {
			throw new UsuarioYAtraccionDineroIncorrectoException();
		}
	}
	
	public boolean comprobacionFinalDineroIngresado(int presupuesto,String nombre) {
		boolean correcto=false;
		try {
			return comprobacionInicialDineroIngresado(presupuesto);
		} catch(UsuarioYAtraccionDineroIncorrectoException e) {
			UsuarioYAtraccionDineroIncorrectoException exception=new UsuarioYAtraccionDineroIncorrectoException();
			System.err.println(exception.exponerError(nombre));
			return correcto;
		} 
		//VER ESTO
		catch(NumberFormatException e){
			System.err.println("Se debe introducir un numero");
			return correcto;
		}
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	private boolean comprobacionInicialTiempoIngresado(double tiempo) throws UsuarioYAtraccionTiempoIncorrectoException {
		boolean correcto=false;
		if(tiempo>0){
			correcto=true;
			return correcto;
		} else {
			throw new UsuarioYAtraccionTiempoIncorrectoException();
		}
	}
	
	public boolean comprobacionFinalTiempoIngresado(double tiempo,String nombre) {
		boolean correcto=false;
		try {
			return comprobacionInicialTiempoIngresado(tiempo);
		} catch(UsuarioYAtraccionTiempoIncorrectoException e) {
			UsuarioYAtraccionTiempoIncorrectoException exception=new UsuarioYAtraccionTiempoIncorrectoException();
			System.err.println(exception.exponerError(nombre));
			return correcto;
		} 
		//VER ESTO
		catch(NumberFormatException e){
			System.err.println("Se debe introducir un numero");
			return correcto;
		}
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	private boolean comprobacionInicialCupo(int cupo) throws AtraccionCupoIncorrectoException {
		boolean correcto=false;
		if(cupo>0){
			correcto=true;
			return correcto;
		} else {
			throw new AtraccionCupoIncorrectoException();
		}
	}
	
	public boolean comprobacionFinalCupo(int cupo,String nombre) {
		boolean correcto=false;
		try {
			return comprobacionInicialCupo(cupo);
		} catch(AtraccionCupoIncorrectoException e) {
			AtraccionCupoIncorrectoException exception=new AtraccionCupoIncorrectoException();
			System.err.println(exception.exponerError(nombre));
			return correcto;
		}
	}
}
