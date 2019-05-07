package tests;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import sugerencias.Atraccion;
import sugerencias.Comprable;
import sugerencias.PromocionTieneUnSoloTipoDeAtraccion;
import sugerencias.PromocionUnoGratuito;
import sugerencias.TipoDeAtraccion;
import sugerencias.Usuario;
import sugerencias.UsuarioNoPuedeAdquirirComprable;

public class TestsUsuario {
	
	@Test
	public void puedoCrearUsuarioYSusDatosSonCorrectos() {
		Usuario usuario = new Usuario("Carlos", 10, 8.0, TipoDeAtraccion.Aventura);
		
		Assert.assertEquals("Carlos", usuario.getNombre());
		Assert.assertEquals(10, usuario.getPresupuesto());
		Assert.assertEquals(8.0, usuario.getTiempoDisponible(), 0.0001);
		Assert.assertEquals(TipoDeAtraccion.Aventura, usuario.getTipoDeAtraccion());
	}
	
	@Test
	public void siUsuarioTieneMonedasSuficientesPuedeAdquirirAtraccion() {
		Usuario usuario = new Usuario("Carlos", 10, 8.0, TipoDeAtraccion.Aventura);
		Comprable atraccion = new Atraccion("Minas Tirith", 5, 2.5, 25, TipoDeAtraccion.Paisaje);
		
		Assert.assertTrue(atraccion.puedeAdquirir(usuario));
	}
	
	@Test
	public void siAgregoAtraccionAUsuarioSeLeCobraElPrecioCorrespondiente() {
		Usuario usuario = new Usuario("Carlos", 10, 8.0, TipoDeAtraccion.Aventura);
		Comprable atraccion = new Atraccion("Minas Tirith", 5, 2.5, 25, TipoDeAtraccion.Paisaje);
		
		try {
			usuario.agregarComprable(atraccion);
		} catch (UsuarioNoPuedeAdquirirComprable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertEquals(5, usuario.getPresupuesto());
	}
	
	@Test
	public void siAgregoAtraccionAUsuarioSeLeAgregaAListaDeAtracciones() {
		Usuario usuario = new Usuario("Carlos", 10, 8.0, TipoDeAtraccion.Aventura);
		Comprable atraccion = new Atraccion("Minas Tirith", 5, 2.5, 25, TipoDeAtraccion.Paisaje);
		
		try {
			usuario.agregarComprable(atraccion);
		} catch (UsuarioNoPuedeAdquirirComprable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertEquals(atraccion, usuario.getComprados().get(0));
	}
	
	@Test
	public void siAgregoAtraccionAUsuarioSeLeDescuentaTiempoDisponibleCorrespondiente() {
		Usuario usuario = new Usuario("Carlos", 10, 8.0, TipoDeAtraccion.Aventura);
		Comprable atraccion = new Atraccion("Minas Tirith", 5, 2.5, 25, TipoDeAtraccion.Paisaje);
		
		try {
			usuario.agregarComprable(atraccion);
		} catch (UsuarioNoPuedeAdquirirComprable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertEquals(5.5, usuario.getTiempoDisponible(), 0.001);
	}
	
	@Test
	public void siAgregoAtraccionAlUsuarioNoPuedeVolverAAdquirirLaMismaAtraccion() {
		Usuario usuario = new Usuario("Carlos", 10, 8.0, TipoDeAtraccion.Aventura);
		Comprable atraccion = new Atraccion("Minas Tirith", 5, 2.5, 25, TipoDeAtraccion.Paisaje);
		
		try {
			usuario.agregarComprable(atraccion);
		} catch (UsuarioNoPuedeAdquirirComprable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertFalse(atraccion.puedeAdquirir(usuario));
	}
		
	@Test
	public void siAgregoAtraccionAlUsuarioNoPuedeAdquirirPromocionQueContieneLaMismaAtraccion() {
		Usuario usuario = new Usuario("Carlos", 10, 8.0, TipoDeAtraccion.Aventura);
		Atraccion atraccion = new Atraccion("Moria", 10, 2.0, 6, TipoDeAtraccion.Aventura);
					
		ArrayList<Atraccion> comprables = new ArrayList<Atraccion>();
		comprables.add(atraccion);
		comprables.add(new Atraccion("Mordor", 25, 3.0, 4, TipoDeAtraccion.Aventura));
		comprables.add(new Atraccion("Bosque Negro", 3, 4.0, 12, TipoDeAtraccion.Aventura));
				
		try {
			usuario.agregarComprable(atraccion);
		} catch (UsuarioNoPuedeAdquirirComprable e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Comprable comprable;
		try {
			comprable = new PromocionUnoGratuito("Pack Aventura", true, comprables, comprables.get(1));
			Assert.assertFalse(comprable.puedeAdquirir(usuario));
		} catch (PromocionTieneUnSoloTipoDeAtraccion e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void siAgregoPromocionAlUsuarioNoPuedeAdquirirAtraccionQueEstaEnLaPromocion() {
		Usuario usuario = new Usuario("Carlos", 1000, 100.0, TipoDeAtraccion.Aventura);
		Atraccion atraccion = new Atraccion("Moria", 10, 2.0, 6, TipoDeAtraccion.Aventura);
					
		ArrayList<Atraccion> comprables = new ArrayList<Atraccion>();
		comprables.add(atraccion);
		comprables.add(new Atraccion("Mordor", 25, 3.0, 4, TipoDeAtraccion.Aventura));
		comprables.add(new Atraccion("Bosque Negro", 3, 4.0, 12, TipoDeAtraccion.Aventura));
						
		Comprable comprable;
		try {
			comprable = new PromocionUnoGratuito("Pack Aventura", true, comprables, comprables.get(1));
			usuario.agregarComprable(comprable);
			Assert.assertFalse(atraccion.puedeAdquirir(usuario));
		} catch (PromocionTieneUnSoloTipoDeAtraccion e) {
			e.printStackTrace();
		} catch (UsuarioNoPuedeAdquirirComprable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void siAgregoPromocionAlUsuarioNoPuedeAdquirirOtraPromocionQueTieneAtraccionDeLaPrimera() {
		Usuario usuario = new Usuario("Carlos", 1000, 80.0, TipoDeAtraccion.Aventura);
		Atraccion atraccion = new Atraccion("Moria", 10, 2.0, 6, TipoDeAtraccion.Aventura);
					
		ArrayList<Atraccion> comprables = new ArrayList<Atraccion>();
		comprables.add(atraccion);
		comprables.add(new Atraccion("Mordor", 25, 3.0, 4, TipoDeAtraccion.Aventura));
		comprables.add(new Atraccion("Bosque Negro", 3, 4.0, 12, TipoDeAtraccion.Aventura));
		
		ArrayList<Atraccion> comprables2 = new ArrayList<Atraccion>();
		comprables2.add(atraccion);
		comprables2.add(new Atraccion("Mordorito", 25, 3.0, 4, TipoDeAtraccion.Aventura));
		comprables2.add(new Atraccion("Bosquecito", 3, 4.0, 12, TipoDeAtraccion.Aventura));
						
		Comprable comprable;
		Comprable comprable2;
		try {
			comprable = new PromocionUnoGratuito("Pack Aventura", true, comprables, comprables.get(1));
			comprable2 = new PromocionUnoGratuito("Pack Aventurita", true, comprables, comprables.get(1));
			usuario.agregarComprable(comprable);
			Assert.assertFalse(comprable2.puedeAdquirir(usuario));
		} catch (PromocionTieneUnSoloTipoDeAtraccion e) {
			e.printStackTrace();
		} catch (UsuarioNoPuedeAdquirirComprable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
