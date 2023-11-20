
package kivipaperisakset;

/**
 * Pelaaja-luokka
 * Tarkoituksena toimia kivipaperisakse!-pelin pelaajan ohjausluokkana.
 * Pelaaja-luokka sisältää metodit pelaajan pelivalinnan tekemiseen ja
 * voittojen laskemiseen.
 * Pelaaja-luokkaa käytetään Peli-luokassa.
 *
 * @author Rasmus Hyyppä
 * @see Peli
 */
public class Pelaaja {

    int voitot; // Voittojen lukumäärä
    int voitotYhteensä;

    /**
     * Pelaaja-luokan pelaajanValinta-metodi arpoo pelaajan valinnan
     * kivipaperisakset-pelissä.
     * @return palauttaa pelaajan valinnan kivipaperisakset-pelissä.
     * @see Peli
     */
    public String pelaajanValinta() {
        String valinta = "";
        int c = (int) (Math.random() * 3);
        switch (c) {
            case 0:
                valinta = ("kivi");
                break;
            case 1:
                valinta = ("paperi");
                break;
            case 2:
                valinta = ("sakset");
                break;
        }
        return valinta;
    }

    public int setVoitot() {
        int voitotYhteensä = voitot++;
        return voitotYhteensä;
    }

    public int getVoitot() {
        return (voitot);
    }
}
