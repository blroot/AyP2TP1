package sugerencias;

public class Atraccion extends Comprable {
	
	private int costo;
	private double promedioDeTiempo;
	private int cupoDiario;
	private TipoDeAtraccion tipoDeAtraccion;
	private VerificarDeEntradas verificador;

	public Atraccion(String nombre, int costo, double tiempo, int cupo, TipoDeAtraccion tipo) {
		super(nombre, Prioridad.Baja);
		verificador=new VerificarDeEntradas();
		setCosto(costo);
		setPromedioDeTiempo(tiempo);
		setCupoDiario(cupo);
		try {
			setTipoDeAtraccion(tipo);	
		}catch(IllegalArgumentException e) {
			System.err.println("Tipo de atraccion no incorrecta");
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setCosto(int costo) {
		if(verificador.comprobacionFinalDineroIngresado(costo,getNombre())){
			this.costo = costo;
		}
	}
	//LISTO
	public void setPromedioDeTiempo(double tiempo) {
		if(verificador.comprobacionFinalTiempoIngresado(tiempo,getNombre())) {
			this.promedioDeTiempo = tiempo;	
		}
	}
	//LISTO
	public void setCupoDiario(int cupo) {
		if(verificador.comprobacionFinalCupo(cupo,getNombre())){
			this.cupoDiario = cupo;
			}
	}
	//LISTO
	public void setTipoDeAtraccion(TipoDeAtraccion tipo) {	
		this.tipoDeAtraccion = tipo;
	}
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
	public void disminuirCupoDiario() {
		this.cupoDiario--;
	}

	@Override
	public int getPrecio() {
		return this.costo;
	}

	@Override
	public boolean puedeAdquirir(Usuario usuario) {
		if (usuario.getPresupuesto() < this.getPrecio() 
				|| usuario.getTiempoDisponible() < this.getTiempoTotal()
				|| usuario.getComprados().contains(this)) {
			return false;
		}
				
		// Si ya adquirió la atracción retorna false
		for (Comprable comprable: usuario.getComprados()) {
			if (comprable.tieneOEsAtraccion(this)) {
				return false;
			}
		}
		
		return true;
	}

	@Override
	public void vender(Usuario usuario) throws UsuarioNoPuedeAdquirirComprable {
		if(puedeAdquirir(usuario)) {
			usuario.agregarComprable(this);
			this.cupoDiario--;
		} else {
			throw new UsuarioNoPuedeAdquirirComprable("El usuario no tiene recursos suficientes para comprar el comprable o comprable no tiene cupo disponible");
		}
	}

	@Override
	public double getTiempoTotal() {
		return this.promedioDeTiempo;
	}

	@Override
	public TipoDeAtraccion getTipoDeAtraccion() {
		return this.tipoDeAtraccion;
	}

	@Override
	public String toString() {
		return "Nombre: " + super.getNombre() 
			+ "\nTipo: " + this.tipoDeAtraccion 
			+ "\nPrecio: " + this.getPrecio() 
			+ "\nPromedio de Tiempo: " + this.promedioDeTiempo 
			+ "\nCupo: " + this.cupoDiario;
	}

	@Override
	public boolean tieneOEsAtraccion(Atraccion atraccion) {
		return this == atraccion;
	}

}
