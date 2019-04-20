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

}
