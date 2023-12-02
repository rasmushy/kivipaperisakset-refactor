package kivipaperisakset;

import java.util.Random;
/**
 * Pelaaja-luokan tarkoitus on toimia kivipaperisakset-pelin pelaajan ohjausluokkana.
 * Pelaaja-luokka sisältää metodit pelaajan pelivalinnan tekemiseen ja
 * voittojen laskemiseen sekä hakemiseen.
 * Pelaaja-luokkaa käytetään Peli-luokassa.
 *
 * @author Rasmus Hyyppä
 * @see Peli
 */
public final class Pelaaja {

    private Random random; // korvaa math.randomin

    private static int uId = 1; // Pelaaja-luokan uniikin id:n laskuri
    private int id; // Pelaajan id
    private int voitot; // Voittojen lukumäärä
    private String valinta; // Pelaajan valinta kivipaperisakset-pelissä

    private static final String[] VALINNAT = {"kivi", "paperi", "sakset"};

    public Pelaaja() {
        this(new Random());
        id = uId++;
        voitot = 0;
    }

    public Pelaaja(Random random) {
        this.random = random;
        id = uId++;
        voitot = 0;
    }

    /**
     * Pelaaja-luokan pelaajanValinta-metodi arpoo pelaajan valinnan
     * kivipaperisakset-pelissä.
     *
     * @return palauttaa pelaajan valinnan kivipaperisakset-pelissä.
     * @see Peli
     */
    public String pelaajanValinta() {
        valinta = VALINNAT[random.nextInt(VALINNAT.length)];
        System.out.println("Pelaaja " + this.getId() + " valinta: " + this.getValinta());
        return valinta;
    }

    public void addVoitto() {
        voitot += 1;
    }

    public int getVoitot() {
        return this.voitot;
    }

    public String getValinta() {
        return this.valinta;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Pelaaja " + this.getId() + ":llä koossa " + this.getVoitot() + " voittoa.";
    }
}
