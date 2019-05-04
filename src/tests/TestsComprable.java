package tests;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import org.junit.Assert;
import org.junit.Test;

import sugerencias.Atraccion;
import sugerencias.ComparadorDeComprablesPorTipoPreferenciaPrecioYTiempo;
import sugerencias.Comprable;
import sugerencias.Prioridad;
import sugerencias.Promocion;
import sugerencias.PromocionAbsoluta;
import sugerencias.PromocionPorcentual;
import sugerencias.PromocionTieneUnSoloTipoDeAtraccion;
import sugerencias.PromocionUnoGratuito;
import sugerencias.TipoDeAtraccion;

public class TestsComprable {
	
	@Test
	public void puedoCrearAtraccionMoriaYTieneCostoTiempoYPrioridadCorrecto() {
		Comprable atraccion = new Atraccion("Moria", 10, 2.0, 6, TipoDeAtraccion.Aventura);
		Assert.assertEquals(10, atraccion.getPrecio());
		Assert.assertEquals(2.0, atraccion.getTiempoTotal(), 0.1);
		Assert.assertEquals(Prioridad.Baja, atraccion.getPrioridad());
		Assert.assertEquals(TipoDeAtraccion.Aventura, atraccion.getTipoDeAtraccion());
	}
	
	@Test
	public void puedoCrearPromocionYTieneCostoTiempoYPrioridadCorrecto() {
		ArrayList<Atraccion> comprables = new ArrayList<Atraccion>();
		comprables.add(new Atraccion("Moria", 10, 2.0, 6, TipoDeAtraccion.Aventura));
		comprables.add(new Atraccion("Mordor", 25, 3.0, 4, TipoDeAtraccion.Aventura));
		comprables.add(new Atraccion("Bosque Negro", 3, 4.0, 12, TipoDeAtraccion.Aventura));
		
		Comprable comprable;
		try {
			comprable = new Promocion("Pack Aventura", true, comprables);
			Assert.assertEquals(38, comprable.getPrecio());
			Assert.assertEquals(9.0, comprable.getTiempoTotal(), 0.1);
			Assert.assertEquals(Prioridad.Alta, comprable.getPrioridad());
			Assert.assertEquals(TipoDeAtraccion.Aventura, comprable.getTipoDeAtraccion());
		} catch (PromocionTieneUnSoloTipoDeAtraccion e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void promocionAbsolutaTieneCostoCorrecto() {
		ArrayList<Atraccion> comprables = new ArrayList<Atraccion>();
		comprables.add(new Atraccion("Moria", 10, 2.0, 6, TipoDeAtraccion.Aventura));
		comprables.add(new Atraccion("Mordor", 25, 3.0, 4, TipoDeAtraccion.Aventura));
		comprables.add(new Atraccion("Bosque Negro", 3, 4.0, 12, TipoDeAtraccion.Aventura));
		
		Comprable comprable;
		try {
			comprable = new PromocionAbsoluta("Pack Aventura", true, comprables, 30);
			Assert.assertEquals(30, comprable.getPrecio());
		} catch (PromocionTieneUnSoloTipoDeAtraccion e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void promocionPorcentualTieneCostoCorrecto() {
		ArrayList<Atraccion> comprables = new ArrayList<Atraccion>();
		comprables.add(new Atraccion("Moria", 10, 2.0, 6, TipoDeAtraccion.Aventura));
		comprables.add(new Atraccion("Mordor", 20, 3.0, 4, TipoDeAtraccion.Aventura));
		comprables.add(new Atraccion("Bosque Negro", 30, 4.0, 12, TipoDeAtraccion.Aventura));
		
		Comprable comprable;
		try {
			comprable = new PromocionPorcentual("Pack Aventura", true, comprables, 10);
			Assert.assertEquals(54, comprable.getPrecio());
		} catch (PromocionTieneUnSoloTipoDeAtraccion e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void promocionMordorGratuitoTieneCostoCorrecto() {
		ArrayList<Atraccion> comprables = new ArrayList<Atraccion>();
		comprables.add(new Atraccion("Moria", 10, 2.0, 6, TipoDeAtraccion.Aventura));
		comprables.add(new Atraccion("Mordor", 25, 3.0, 4, TipoDeAtraccion.Aventura));
		comprables.add(new Atraccion("Bosque Negro", 3, 4.0, 12, TipoDeAtraccion.Aventura));
		
		Comprable comprable;
		try {
			comprable = new PromocionUnoGratuito("Pack Aventura", true, comprables, comprables.get(1));
			Assert.assertEquals(13, comprable.getPrecio());
		} catch (PromocionTieneUnSoloTipoDeAtraccion e) {
			e.printStackTrace();
		}	
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
		
		ArrayList<Comprable> comprables = new ArrayList<Comprable>();
		comprables.add(moria);
		comprables.add(minas);
		comprables.add(comarca);
		comprables.add(mordor);
		comprables.add(abismo);
		comprables.add(lothlorien);
		comprables.add(erebor);
		comprables.add(bosqueNegro);
		
		ArrayList<Atraccion> aventura = new ArrayList<Atraccion>();
		aventura.add(moria);
		aventura.add(mordor);
		
		ArrayList<Atraccion> paisaje = new ArrayList<Atraccion>();
		paisaje.add(minas);
		paisaje.add(abismo);
		
		PromocionAbsoluta packAventura = null;
		try {
			packAventura = new PromocionAbsoluta("Pack Aventura", true, aventura, 25);
		} catch (PromocionTieneUnSoloTipoDeAtraccion e) {
			e.printStackTrace();
		}
		PromocionAbsoluta packPaisaje = null;
		try {
			packPaisaje = new PromocionAbsoluta("Pack Paisaje", true, paisaje, 8);
		} catch (PromocionTieneUnSoloTipoDeAtraccion e) {
			e.printStackTrace();
		}
		
		comprables.add(packAventura);
		comprables.add(packPaisaje);
		
		Comparator<Comprable> comparador = new ComparadorDeComprablesPorTipoPreferenciaPrecioYTiempo(TipoDeAtraccion.Aventura);
		
		Collections.sort(comprables, comparador);
				
		Assert.assertEquals(packAventura, comprables.get(0));
		Assert.assertEquals(mordor, comprables.get(1));
		Assert.assertEquals(moria, comprables.get(2));
		Assert.assertEquals(bosqueNegro, comprables.get(3));
		Assert.assertEquals(packPaisaje, comprables.get(4));
		Assert.assertEquals(lothlorien, comprables.get(5));
		Assert.assertEquals(erebor, comprables.get(6));
		Assert.assertEquals(minas, comprables.get(7));
		Assert.assertEquals(abismo, comprables.get(8));
		Assert.assertEquals(comarca, comprables.get(9));
		
	}

}
