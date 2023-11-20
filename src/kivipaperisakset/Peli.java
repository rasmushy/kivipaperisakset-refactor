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

    public static final String PELAAJA_1_VOITTAA = "Pelaaja 1 voittaa";
    public static final String PELAAJA_2_VOITTAA = "Pelaaja 2 voittaa";
    public static void main(String args[]) {
        Pelaaja p1 = new Pelaaja();
        Pelaaja p2 = new Pelaaja();
        boolean peliLoppui = false;
        int pelatutPelit = 0;           // Pelattujen pelien lkm
        int tasapelit = 0;              // Tasapelien lkm
        String p1Valinta;
        String p2Valinta;
        do {
            System.out.println("\nErä: "
                               + pelatutPelit + " =====================\n");
            System.out.println("Tasapelien lukumäärä: "
                               + tasapelit + "\n");
            p1Valinta = p1.pelaajanValinta();
            System.out.println("Pelaaja 1: " + p1Valinta
                               + "\n\t Pelaaja 1:llä koossa " + p1.getVoitot() + " voittoa.");
            p2Valinta = p2.pelaajanValinta();
            System.out.println("Pelaaja 2: " + p2Valinta
                               + "\n\t Pelaaja 2:lla koossa " + p2.getVoitot() + " voittoa.\n");
            if ((p1Valinta.equals("kivi")) && (p2Valinta.equals("paperi"))) {
                p2.setVoitot();
                System.out.print(PELAAJA_2_VOITTAA);
            } else if ((p1Valinta.equals("paperi")) && (p2Valinta.equals("kivi"))) {
                p1.setVoitot();
                System.out.print(PELAAJA_1_VOITTAA);
            } else if ((p1Valinta.equals("kivi")) && (p2Valinta.equals("sakset"))) {
                p1.setVoitot();
                System.out.print(PELAAJA_1_VOITTAA);
            } else if ((p1Valinta.equals("sakset")) && (p2Valinta.equals("kivi"))) {
                p2.setVoitot();
                System.out.print(PELAAJA_2_VOITTAA);
            } else if ((p1Valinta.equals("sakset")) && (p2Valinta.equals("paperi"))) {
                p1.setVoitot();
                System.out.print(PELAAJA_1_VOITTAA);
            } else if ((p1Valinta.equals("paperi")) && (p2Valinta.equals("sakset"))) {
                p2.setVoitot();
                System.out.print(PELAAJA_2_VOITTAA);
            }
            if ((p1.getVoitot() >= 3) || (p2.getVoitot() >= 3)) {
                peliLoppui = true;
                System.out.print(", KOLME VOITTOA - PELI PÄÄTTYY");
            }
            if (p1Valinta == p2Valinta) {
                tasapelit++;
                System.out.print("Tasapeli");
            }
            pelatutPelit++;
            System.out.println();
        } while (peliLoppui != true);
    }
}
