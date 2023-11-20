package kivipaperisakset;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * Testaa Pelaaja-luokan toiminnot
 *
 * @author Rasmus Hyyppä
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PelaajaTest {

    @Mock
    private Random mockRandom;
    private Pelaaja pelaaja;

    @BeforeEach
    public void setUp() {
        AutoCloseable autoCloseable = MockitoAnnotations.openMocks(this);
        pelaaja = new Pelaaja(mockRandom);
    }

    @Test
    @Order(1)
    @DisplayName("Pelaajan alustaminen asettaa oikean id:N ja nollaa voitot")
    public void pelaajaLuokanKonstruktoriToimii() {
        assertEquals(0, pelaaja.getVoitot(), "Pelaajan voittojen määrä ei ole alussa 0");
        assertEquals(1, pelaaja.getId(), "Pelaajan id ei ole alussa 1");
    }

    @Test
    @Order(2)
    @DisplayName("pelaajanValinta palauttaa jokaisen valinnan")
    public void pelaajanValintaPalauttaaJokaisenValinnan() {
        when(mockRandom.nextInt(anyInt())).thenReturn(0, 1, 2); // Mockataan palautusarvot
        assertEquals("kivi", pelaaja.pelaajanValinta(), "Ensimmäisen valinnan pitäisi olla kivi.");
        assertEquals("paperi", pelaaja.pelaajanValinta(), "Toisen valinnan pitäisi olla paperi.");
        assertEquals("sakset", pelaaja.pelaajanValinta(), "Kolmannen valinnan pitäisi olla sakset.");
    }

    @Test
    @Order(3)
    @DisplayName("toString palauttaa oikean merkkijonon")
    public void toStringPalauttaaOikeanMerkkijonon() {
        String odotettu = "Pelaaja " + pelaaja.getId() + ":llä koossa 0 voittoa.";
        assertEquals(odotettu, pelaaja.toString(), "toString-metodin tulisi palauttaa oikea merkkijono pelaajan tiedoista.");
    }

    @Test
    @Order(4)
    @DisplayName("addVoitto metodi lisää pelaajan voittoja oikein")
    public void addVoittoLisaaVoittojaOikein() {
        int alkuperainenVoittojenMaara = pelaaja.getVoitot();
        pelaaja.addVoitto();
        assertEquals(alkuperainenVoittojenMaara + 1, pelaaja.getVoitot(), "Yhden voiton lisääminen pitäisi kasvattaa voittojen määrää yhdellä.");
        pelaaja.addVoitto();
        assertEquals(alkuperainenVoittojenMaara + 2, pelaaja.getVoitot(), "Toisen voiton lisääminen pitäisi kasvattaa voittojen määrää kahdella.");
    }

    @Test
    @Order(5)
    @DisplayName("Jokaisella pelaajalla on ainutlaatuinen ID")
    public void jokaisellaPelaajallaOnAinutlaatuinenID() {
        Pelaaja toinenPelaaja = new Pelaaja(mockRandom);
        assertNotEquals(pelaaja.getId(), toinenPelaaja.getId(), "Jokaisen pelaajan ID:n tulee olla yksilöllinen.");
    }

}
