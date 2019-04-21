import java.util.Arrays;
import java.util.Comparator;

import org.junit.Assert;
import org.junit.Test;

public class TestsComprable {
	
	@Test
	public void puedoCrearAtraccionMoriaYTieneCostoTiempoYTipoCorrecto() {
		Comprable atraccion = new Atraccion("Moria", 10, 2.0, 6, TipoDeAtraccion.Aventura);
		Assert.assertEquals(10, atraccion.getPrecio());
		Assert.assertEquals(2.0, atraccion.getTiempoTotal(), 0.1);
		Assert.assertEquals(TipoDeComprable.Atraccion, atraccion.getTipoDeComprable());
		Assert.assertEquals(TipoDeAtraccion.Aventura, atraccion.getTipoDeAtraccion());
	}
	
	@Test
	public void puedoCrearPromocionYTieneCostoTiempoYTipoCorrecto() {
		Atraccion[] comprables = new Atraccion[3];
		comprables[0] = new Atraccion("Moria", 10, 2.0, 6, TipoDeAtraccion.Aventura);
		comprables[1] = new Atraccion("Mordor", 25, 3.0, 4, TipoDeAtraccion.Aventura);
		comprables[2] = new Atraccion("Bosque Negro", 3, 4.0, 12, TipoDeAtraccion.Aventura);
		
		Comprable comprable = new Promocion("Pack Aventura", true, comprables);
		
		Assert.assertEquals(38, comprable.getPrecio());
		Assert.assertEquals(9.0, comprable.getTiempoTotal(), 0.1);
		Assert.assertEquals(TipoDeComprable.Promocion, comprable.getTipoDeComprable());
		Assert.assertEquals(TipoDeAtraccion.Aventura, comprable.getTipoDeAtraccion());
	}
	
	@Test
	public void promocionAbsolutaTieneCostoCorrecto() {
		Atraccion[] atracciones = new Atraccion[3];
		atracciones[0] = new Atraccion("Moria", 10, 2.0, 6, TipoDeAtraccion.Aventura);
		atracciones[1] = new Atraccion("Mordor", 25, 3.0, 4, TipoDeAtraccion.Aventura);
		atracciones[2] = new Atraccion("Bosque Negro", 3, 4.0, 12, TipoDeAtraccion.Aventura);
		
		Comprable comprable = new PromocionAbsoluta("Pack Aventura", true, atracciones, 30);
		
		Assert.assertEquals(30, comprable.getPrecio());
	}
	
	@Test
	public void promocionPorcentualTieneCostoCorrecto() {
		Atraccion[] atracciones = new Atraccion[3];
		atracciones[0] = new Atraccion("Moria", 10, 2.0, 6, TipoDeAtraccion.Aventura);
		atracciones[1] = new Atraccion("Mordor", 20, 3.0, 4, TipoDeAtraccion.Aventura);
		atracciones[2] = new Atraccion("Bosque Negro", 30, 4.0, 12, TipoDeAtraccion.Aventura);
		
		Comprable comprable = new PromocionPorcentual("Pack Aventura", true, atracciones, 10);
		
		Assert.assertEquals(54, comprable.getPrecio());
	}
	
	@Test
	public void promocionMordorGratuitoTieneCostoCorrecto() {
		Atraccion[] atracciones = new Atraccion[3];
		atracciones[0] = new Atraccion("Moria", 10, 2.0, 6, TipoDeAtraccion.Aventura);
		atracciones[1] = new Atraccion("Mordor", 20, 3.0, 4, TipoDeAtraccion.Aventura);
		atracciones[2] = new Atraccion("Bosque Negro", 30, 4.0, 12, TipoDeAtraccion.Aventura);
		
		Comprable comprable = new PromocionUnoGratuito("Pack Aventura", true, atracciones, atracciones[1]);
		
		Assert.assertEquals(40, comprable.getPrecio());
	}
	
	@Test
	public void puedoOrdenarPorTipoDeComprableAventuraPrecioYTiempo() {
		Atraccion moria = new Atraccion("Moria", 10, 2.0, 6, TipoDeAtraccion.Aventura);
		Atraccion minas = new Atraccion("Minas Tirith", 5, 2.5, 25, TipoDeAtraccion.Paisaje);
		Atraccion comarca = new Atraccion("La Comarca", 3, 6.5, 150, TipoDeAtraccion.Degustacion);
		Atraccion mordor = new Atraccion("Mordor", 25, 3.0, 4, TipoDeAtraccion.Aventura);
		Atraccion abismo = new Atraccion("Abismo de Helm", 5, 2.0, 15, TipoDeAtraccion.Paisaje);
		Atraccion lothlorien = new Atraccion("Lothlorien", 35, 1.0, 30, TipoDeAtraccion.Degustacion);
		Atraccion erebor = new Atraccion("Erebor", 12, 3.0, 32, TipoDeAtraccion.Paisaje);
		Atraccion bosqueNegro = new Atraccion("Bosque Negro", 3, 4.0, 12, TipoDeAtraccion.Aventura);
		
		Comprable[] comprables = new Comprable[10];
		comprables[0] = moria;
		comprables[1] = minas;
		comprables[2] = comarca;
		comprables[3] = mordor;
		comprables[4] = abismo;
		comprables[5] = lothlorien;
		comprables[6] = erebor;
		comprables[7] = bosqueNegro;
		
		Atraccion[] aventura = new Atraccion[2];
		aventura[0] = moria;
		aventura[1] = mordor;
		
		Atraccion[] paisaje = new Atraccion[2];
		paisaje[0] = minas;
		paisaje[1] = abismo;
		
		PromocionAbsoluta packAventura = new PromocionAbsoluta("Pack Aventura", true, aventura, 25);
		PromocionAbsoluta packPaisaje = new PromocionAbsoluta("Pack Paisaje", true, paisaje, 8);
		
		comprables[8] = packAventura;
		comprables[9] = packPaisaje;
		
		Comparator<Comprable> comparador = new ComparadorDeComprablesPorTipoPreferenciaPrecioYTiempo(TipoDeAtraccion.Aventura);
		
		Arrays.sort(comprables, comparador);
				
		Assert.assertEquals(packAventura, comprables[0]);
		Assert.assertEquals(mordor, comprables[1]);
		Assert.assertEquals(moria, comprables[2]);
		Assert.assertEquals(bosqueNegro, comprables[3]);
		Assert.assertEquals(packPaisaje, comprables[4]);
		Assert.assertEquals(lothlorien, comprables[5]);
		Assert.assertEquals(erebor, comprables[6]);
		Assert.assertEquals(minas, comprables[7]);
		Assert.assertEquals(abismo, comprables[8]);
		Assert.assertEquals(comarca, comprables[9]);
		
	}

}
