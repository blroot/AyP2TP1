package sugerencias;

public class VerificarDeEntradas {
	

	protected boolean comprobacionInicialDineroIngresado(int presupuesto) throws DineroIncorrectoException {
		boolean correcto=false;
		if(presupuesto>0){
			correcto=true;
			return correcto;
		} else {
			throw new DineroIncorrectoException();
		}
	}
	
	public boolean comprobacionFinalDineroIngresado(int presupuesto,String nombre) {
		boolean correcto=false;
		try {
			return comprobacionInicialDineroIngresado(presupuesto);
		} catch(DineroIncorrectoException e) {
			DineroIncorrectoException exception=new DineroIncorrectoException();
			System.err.println(exception.exponerError(nombre));
			return correcto;
		} 
		//VER ESTO
		catch(NumberFormatException e){
			System.err.println("Se debe introducir un numero");
			return correcto;
		}
	}

	protected boolean comprobacionInicialTiempoIngresado(double tiempo) throws TiempoIncorrectoException {
		boolean correcto=false;
		if(tiempo>0){
			correcto=true;
			return correcto;
		} else {
			throw new TiempoIncorrectoException();
		}
	}
	
	public boolean comprobacionFinalTiempoIngresado(double tiempo,String nombre) {
		boolean correcto=false;
		try {
			return comprobacionInicialTiempoIngresado(tiempo);
		} catch(TiempoIncorrectoException e) {
			TiempoIncorrectoException exception=new TiempoIncorrectoException();
			System.err.println(exception.exponerError(nombre));
			return correcto;
		} 
		//VER ESTO
		catch(NumberFormatException e){
			System.err.println("Se debe introducir un numero");
			return correcto;
		}
	}

	protected boolean comprobacionInicialCupo(int cupo) throws AtraccionCupoIncorrectoException {
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
		} catch(AtraccionCupoIncorrectoException exception) {
			System.err.println(exception.exponerError(nombre));
			return correcto;
		}
	}
}
