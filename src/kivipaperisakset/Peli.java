package kivipaperisakset;

import java.util.HashMap;
import java.util.Map;

import static kivipaperisakset.Main.PELIN_VOITTAA;
/**
 * Peli-luokka
 * Tarkoituksena toimia kivipaperisakset-pelin ohjausluokkana.
 * Peli-luokka sisältää metodit pelin kulun ohjaamiseen.
 * Peli-luokka käyttää Pelaaja-luokkaa.
 *
 * @author Rasmus Hyyppä
 * @see Main
 * @see Pelaaja
 */
public class Peli {


    private final Map<String, String> voittoKombinaatiot = new HashMap<>(); // Voittavat kombinaatiot
    private final Pelaaja p1;
    private final Pelaaja p2;
    private boolean peliLoppui;         // Pelin status
    private int pelatutPelit;           // Pelattujen pelien lkm
    private int tasapelit;              // Tasapelien lkm

    public Peli() {
        voittoKombinaatiot.put("kivi", "sakset");
        voittoKombinaatiot.put("sakset", "paperi");
        voittoKombinaatiot.put("paperi", "kivi");
        p1 = new Pelaaja();
        p2 = new Pelaaja();
        peliLoppui = false;
        pelatutPelit = 0;
        tasapelit = 0;
    }

    /**
     * pelaa-metodi
     * Tarkoituksena ohjata pelin kulkua.
     *
     * @see Pelaaja
     */
    public void pelaa() {
        System.out.println("\nErä: " + pelatutPelit + " =====================\n");
        System.out.println("Tasapelien lukumäärä: " + tasapelit + "\n");
        System.out.println(p1 + "\n" + p2); // Tulostetaan pelaajien voitot
        if (valitseVoittaja(p1.pelaajanValinta(), p2.pelaajanValinta()) >= PELIN_VOITTAA) {
            peliLoppui = true;
            System.out.print(", " + PELIN_VOITTAA + " VOITTOA - PELI PÄÄTTYY");
        }
        pelatutPelit++;
        System.out.println();
    }

    public boolean getPeliLoppui() {
        return this.peliLoppui;
    }

    /**
     * tarkistaVoittaja-metodi
     * Tarkoituksena vertailla pelaajien valintoja ja määrittää kumpi voittaa.
     * Metodi käyttää voittoKombinaatiot-hashMappia.
     */
    private int valitseVoittaja(String p1Valinta, String p2Valinta) {
        if (p1Valinta.equals(p2Valinta)) {
            tasapelit++;
            System.out.print("Tasapeli!");
            return 0;
        }

        Pelaaja p;
        if (voittoKombinaatiot.get(p1Valinta).equals(p2Valinta)) {
            p1.addVoitto();
            System.out.print("Pelaaja 1 voittaa");
            p = p1;
        } else {
            p2.addVoitto();
            System.out.print("Pelaaja 2 voittaa");
            p = p2;
        }

        return p.getVoitot();
    }
}
