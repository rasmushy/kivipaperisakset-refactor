package kivipaperisakset;

import java.util.HashMap;
import java.util.Map;

/**
 * Peli-luokan
 * tarkoituksena toimia kivipaperisakset-pelin ohjausluokkana.
 * Peli-luokka sisältää metodit pelin kulun ohjaamiseen.
 * Peli-luokka käyttää Pelaaja-luokkaa.
 *
 * @author Rasmus Hyyppä
 * @see Main
 * @see Pelaaja
 */
public class Peli {

    public static final int PELIN_VOITTAA = 3; // Peli loppuu kun jompikumpi pelaajista saa kolme voittoa
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
     * Tätä metodia kutsutaan kun halutaan pelata peliä.
     *
     * @see Pelaaja
     */
    public void pelaa() {
        if (peliLoppui) {
            System.out.println("Peli on jo päättynyt. Uusia pelejä ei voi aloittaa");
            return;
        }
        System.out.println("\nErä: " + pelatutPelit + " =====================\n");
        System.out.println("Tasapelien lukumäärä: " + tasapelit + "\n");
        System.out.println(p1 + "\n" + p2); // Tulostetaan pelaajien voitot
        valitseVoittaja(p1.pelaajanValinta(), p2.pelaajanValinta());
        pelatutPelit++;
        System.out.println();
    }

    /**
     * valitseVoittaja-metodi
     * tarkoituksena vertailla pelaajien valintoja ja määrittää kumpi voittaa.
     * Mikäli jompikumpi pelaajista voittaa päivitetään pelin loppumistieto
     * Metodi käyttää voittoKombinaatiot-hashMappia.
     */
    public void valitseVoittaja(String p1Valinta, String p2Valinta) {
        //Tarkistetaan onko tasapeli
        if (p1Valinta.equals(p2Valinta)) {
            tasapelit++;
            System.out.print("Tasapeli!");
            return;
        }
        //Kyseessä ei ole tasapeli
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
        if (p.getVoitot() >= PELIN_VOITTAA) {
            peliLoppui = true;
            System.out.print(", " + PELIN_VOITTAA + " VOITTOA - PELI PÄÄTTYY");
        }
    }

    public boolean getPeliLoppui() {
        return this.peliLoppui;
    }

    public int getTasapelit() {
        return this.tasapelit;
    }

    public Pelaaja getP1() {
        return this.p1;
    }

    public Pelaaja getP2() {
        return this.p2;
    }

    public int getPelatutPelit() {
        return this.pelatutPelit;
    }

}
