package tests;
import org.junit.Assert;
import org.junit.Test;

import sugerencias.Atraccion;
import sugerencias.Comprable;
import sugerencias.TipoDeAtraccion;
import sugerencias.Usuario;

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
		
		usuario.agregarComprable(atraccion);
		Assert.assertEquals(5, usuario.getPresupuesto());
	}
	
	@Test
	public void siAgregoAtraccionAUsuarioSeLeAgregaAListaDeAtracciones() {
		Usuario usuario = new Usuario("Carlos", 10, 8.0, TipoDeAtraccion.Aventura);
		Comprable atraccion = new Atraccion("Minas Tirith", 5, 2.5, 25, TipoDeAtraccion.Paisaje);
		
		usuario.agregarComprable(atraccion);
		Assert.assertEquals(atraccion, usuario.getComprados().get(0));
	}
	
	@Test
	public void siAgregoAtraccionAUsuarioSeLeDescuentaTiempoDisponibleCorrespondiente() {
		Usuario usuario = new Usuario("Carlos", 10, 8.0, TipoDeAtraccion.Aventura);
		Comprable atraccion = new Atraccion("Minas Tirith", 5, 2.5, 25, TipoDeAtraccion.Paisaje);
		
		usuario.agregarComprable(atraccion);
		Assert.assertEquals(5.5, usuario.getTiempoDisponible(), 0.001);
	}

}
