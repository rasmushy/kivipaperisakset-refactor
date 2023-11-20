package kivipaperisakset;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testaa Peli-luokkaa
 *
 * @author Rasmus Hyyppä
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PeliTest {

    Peli peli;

    @BeforeEach
    public void setUp() {
        peli = new Peli();
    }

    @Test
    @Order(1)
    @DisplayName("Testaa Peli-luokan konstruktori")
    public void pelinAlustusToimii() {
        assertEquals(0, peli.getPelatutPelit(), "Pelattujen pelien määrä on alussa yli 0");
        assertEquals(0, peli.getTasapelit(), "Tasapelien määrä on alussa yli 0");
        assertFalse(peli.getPeliLoppui(), "Peli on loppunut alussa");
    }

    @Test
    @Order(2)
    @DisplayName("Pelin logiikka toimii oikein")
    public void pelinLogiikkaToimii() {
        peli.valitseVoittaja("kivi", "sakset");
        assertEquals(1, peli.getP1().getVoitot(), "Kivi voittaa sakset, pelaaja 1:n voitot eivät kasvaneet");
        peli.valitseVoittaja("sakset", "paperi");
        assertEquals(2, peli.getP1().getVoitot(), "Sakset voittaa paperin, pelaaja 2:n voitot eivät kasvaneet");
        peli.valitseVoittaja("paperi", "kivi");
        assertEquals(3, peli.getP1().getVoitot(), "Paperi voittaa kiven, pelaaja 1:n voitot eivät kasvaneet toistamiseen");
        peli.valitseVoittaja("kivi", "kivi");
        assertEquals(3, peli.getP1().getVoitot(), "Kivi ei voita kiveä, pelaaja 1:n voitot kasvoivat");
        assertEquals(0, peli.getP2().getVoitot(), "Kivi ei voita kiveä, pelaaja 2:n voitot kasvoivat");
    }

    @ParameterizedTest(name = "{index} => p1Valinta={0}, p2Valinta={1}, onTasapeli={2}")
    @Order(3)
    @DisplayName("Tasapelit ja voitot kasvavat oikein")
    @CsvSource({
            "kivi, sakset, false",
            "paperi, kivi, false",
            "sakset, paperi, false",
            "kivi, kivi, true",
            "paperi, paperi, true",
            "sakset, sakset, true",
            "kivi, paperi, false",
            "paperi, sakset, false",
            "sakset, kivi, false",
    })
    public void testValitseVoittaja(String p1Valinta, String p2Valinta, boolean onTasapeli) {
        int alkuTasapelit = peli.getTasapelit();
        int alkuVoitotPelaaja1 = peli.getP1().getVoitot();
        int alkuVoitotPelaaja2 = peli.getP2().getVoitot();
        peli.valitseVoittaja(p1Valinta, p2Valinta);
        if (onTasapeli) {
            assertEquals(alkuTasapelit + 1, peli.getTasapelit(), "Tasapelin pitäisi lisätä tasapelien määrää.");
            assertEquals(alkuVoitotPelaaja1, peli.getP1().getVoitot(), "Tasapelin ei pitäisi lisätä voittoja pelaaja 1:lle.");
            assertEquals(alkuVoitotPelaaja2, peli.getP2().getVoitot(), "Tasapelin ei pitäisi lisätä voittoja pelaaja 2:lle.");
        } else {
            assertTrue(peli.getP1().getVoitot() > alkuVoitotPelaaja1 || peli.getP2().getVoitot() > alkuVoitotPelaaja2, "Jompikumman pelaajan voittojen määrän pitäisi kasvaa, jos ei ole tasapeli.");
        }
    }

    @Test
    @Order(4)
    @DisplayName("Peli päättyy kun pelaaja saavuttaa vaaditun määrän voittoja")
    public void peliPaattyyVoittoihin() {
        while (!peli.getPeliLoppui()) {
            peli.pelaa();
        }
        assertTrue(peli.getPeliLoppui(), "Pelin pitäisi loppua, kun pelaaja saavuttaa voittojen määrän.");
    }

    @Test
    @Order(5)
    @DisplayName("Pelin loppumisen jälkeen ei voi pelata")
    public void pelinLoppumisenJalkeenEiVoiPelata() {
        while (!peli.getPeliLoppui()) {
            peli.pelaa();
        }
        int pelatutPelit = peli.getPelatutPelit();
        peli.pelaa();
        assertEquals(pelatutPelit, peli.getPelatutPelit(), "Pelattujen pelien määrä kasvoi pelin loppumisen jälkeen");
    }

}
