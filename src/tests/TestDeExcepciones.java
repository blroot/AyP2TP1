package tests;
import sugerencias.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestDeExcepciones {

	//Atraccion(String nombre, int costo, double tiempo, int cupo, TipoDeAtraccion tipo)
	@Test(expected = AtraccionCupoIncorrectoException.class)
	public void probarExcepcionDeCupoNegativoEnAtraccion() throws EntradaDeDatosException {
		Atraccion atraccionDePrueba=new Atraccion("Moria", 10, 2, -5,TipoDeAtraccion.Aventura);
	}
	
	@Test(expected = TiempoIncorrectoException.class)
	public void probarExcepcionDeTiempoNegativoEnAtraccion() throws EntradaDeDatosException {
		Atraccion atraccionDePrueba=new Atraccion("Moria", 10, -1000, 5,TipoDeAtraccion.Aventura);
	}
	
	@Test(expected = DineroIncorrectoException	.class)
	public void probarExcepcionDeCostoNegativoEnAtraccion() throws EntradaDeDatosException {
		Atraccion atraccionDePrueba=new Atraccion("Moria", -800, 2, -5,TipoDeAtraccion.Aventura);
	}
	
	//Usuario(String nombre, int presupuesto, double tiempoDisponible, TipoDeAtraccion tipoDeAtraccionPreferida)
	@Test(expected = DineroIncorrectoException.class)
	public void probarExcepcionDePresupuestoNegativoEnUsuario() throws EntradaDeDatosException {
		Usuario usuarioDePrueba=new Usuario("Eclipse", -22, 50, TipoDeAtraccion.Aventura);
	}
	
	@Test(expected = TiempoIncorrectoException.class)
	public void probarExcepcionDeTiempoNegativoEnUsuario() throws EntradaDeDatosException {
		Usuario usuarioDePrueba=new Usuario("Eclipse", 22, -103, TipoDeAtraccion.Aventura);
	}
	
}
