package kivipaperisakset;

/**
 * Peli-luokka
 * Tarkoituksena toimia kivipaperisakset-pelin ohjausluokkana.
 * Peli-luokka sisältää metodit pelin kulun ohjaamiseen.
 * Peli-luokka käyttää Pelaaja-luokkaa.
 *
 * @author Rasmus Hyyppä
 * @see Pelaaja
 */
public class Peli {

    public static void main(String args[]) {
        Pelaaja p1 = new Pelaaja();
        Pelaaja p2 = new Pelaaja();
        boolean peliLoppui = false;
        int pelatutPelit = 0;           // Pelattujen pelien lkm
        int p1Voitot = p1.voitot;       // Pelaaja 1:n voittojen lkm
        int p2Voitot = p2.voitot;       // Pelaaja 2:n voittojen lkm
        int tasapelit = 0;              // Tasapelien lkm
        String p1Valinta;
        String p2Valinta;
        do {
            System.out.println("Erä: "
                               + pelatutPelit + " =====================\n");
            System.out.println("Tasapelien lukumäärä: "
                               + tasapelit + "\n");
            p1Valinta = p1.pelaajanValinta();
            System.out.println("Pelaaja 1: " + p1Valinta
                               + "\n\t Pelaaja 1:llä koossa " + p1Voitot + " voittoa.");
            p2Valinta = p2.pelaajanValinta();
            System.out.println("Pelaaja 2: " + p2Valinta
                               + "\n\t Pelaaja 2:lla koossa " + p2Voitot + " voittoa.");
            // TODO: korjaa lukumäärän laskenta
            if ((p1Valinta.equals("kivi")) && (p2Valinta.equals("paperi"))) {
                System.out.println("Pelaaja 2 voittaa");
                p2Voitot++;  // Kokeile eri tapoja saada lukumäärän laskenta kuntoon
            } else if ((p1Valinta.equals("paperi")) && (p2Valinta.equals("kivi"))) {
                p1Voitot++;
                System.out.println("Pelaaja 1 voittaa");
            } else if ((p1Valinta.equals("kivi")) && (p2Valinta.equals("sakset"))) {
                p1Voitot = p1.setVoitot();
                System.out.println("Pelaaja 1 voittaa");
            } else if ((p1Valinta.equals("sakset")) && (p2Valinta.equals("kivi"))) {
                p2Voitot = p2.setVoitot();
                System.out.println("Pelaaja 2 voittaa");
            } else if ((p1Valinta.equals("sakset")) && (p2Valinta.equals("paperi"))) {
                p1Voitot = p1.setVoitot();
                System.out.println("Pelaaja 1 voittaa");
            } else if ((p1Valinta.equals("paperi")) && (p2Valinta.equals("sakset"))) {
                p2Voitot = p2.setVoitot();
                System.out.println("Pelaaja 2 voittaa");
            }
            if (p1Valinta == p2Valinta) {
                tasapelit++;
                System.out.println("\n\t\t\t Tasapeli \n");
            }
            pelatutPelit++;
            if ((p1.getVoitot() >= 3) || (p2.getVoitot() >= 3)) {
                peliLoppui = true;
                System.out.println("KOLME VOITTOA - PELI PÄÄTTYY");
            }
            System.out.println();
        } while (peliLoppui != true);
    }
}
