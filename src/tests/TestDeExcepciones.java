package tests;
import sugerencias.*;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
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
	
	@Test(expected = CostoNegativoNoPermitido.class)
	public void probarExcepcionDeCostoNegativoEnPromocionAbsoluta() throws EntradaDeDatosException, CostoNegativoNoPermitido {
		ArrayList<Atraccion> comprables = new ArrayList<Atraccion>();
		comprables.add(new Atraccion("Moria", 10, 2.0, 6, TipoDeAtraccion.Aventura));
		comprables.add(new Atraccion("Mordor", 25, 3.0, 4, TipoDeAtraccion.Aventura));
		comprables.add(new Atraccion("Bosque Negro", 3, 4.0, 12, TipoDeAtraccion.Aventura));
		
		try {
			new PromocionAbsoluta("Pack Aventura", true, comprables, -5);
		} catch (PromocionTieneUnSoloTipoDeAtraccion e) {
			e.printStackTrace();
		}	
	}
	
	@Test(expected = PorcentajeFueraDeRango.class)
	public void probarExcepcionDePorcentajeNegativoEnPromocionPorcentual() throws EntradaDeDatosException, PorcentajeFueraDeRango {
		ArrayList<Atraccion> comprables = new ArrayList<Atraccion>();
		comprables.add(new Atraccion("Moria", 10, 2.0, 6, TipoDeAtraccion.Aventura));
		comprables.add(new Atraccion("Mordor", 25, 3.0, 4, TipoDeAtraccion.Aventura));
		comprables.add(new Atraccion("Bosque Negro", 3, 4.0, 12, TipoDeAtraccion.Aventura));
		
		try {
			new PromocionPorcentual("Pack Aventura", true, comprables, -5);
		} catch (PromocionTieneUnSoloTipoDeAtraccion e) {
			e.printStackTrace();
		}	
	}
	
	@Test(expected = AtraccionesNoContieneObtieneGratis.class)
	public void probarExcepcionDeListaDeAtraccionesNoContieneAtraccionGratuitaEnPromocionUnoGratis() throws EntradaDeDatosException, AtraccionesNoContieneObtieneGratis {
		Atraccion atraccion = new Atraccion("Estrella de la muerte", 3, 4.0, 12, TipoDeAtraccion.Aventura);
		
		ArrayList<Atraccion> comprables = new ArrayList<Atraccion>();
		comprables.add(new Atraccion("Moria", 10, 2.0, 6, TipoDeAtraccion.Aventura));
		comprables.add(new Atraccion("Mordor", 25, 3.0, 4, TipoDeAtraccion.Aventura));
		comprables.add(new Atraccion("Bosque Negro", 3, 4.0, 12, TipoDeAtraccion.Aventura));
		
		try {
			new PromocionUnoGratuito("Pack Aventura", true, comprables, atraccion);
		} catch (PromocionTieneUnSoloTipoDeAtraccion e) {
			e.printStackTrace();
		}	
	}
	
}
