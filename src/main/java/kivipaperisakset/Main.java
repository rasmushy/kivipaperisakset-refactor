package kivipaperisakset;

/**
 * Main-luokka
 * Tarkoituksena toimia kivipaperisakset-pelin käynnistysluokkana.
 * Main-luokka sisältää vain main-metodin.
 *
 * @author Rasmus Hyyppä
 * @see Peli
 * @see Pelaaja
 * @see Main
 */
public class Main {

    public static void main(String[] args) {
        Peli peli = new Peli();
        while (!peli.getPeliLoppui()) {
            peli.pelaa();
        }
    }
}
