package tn.esprit.autoloc;
// Chap 5: Question 1+2
// Atelier 10 - Question 11 : Ajouter un constructeur privé pour empêcher l'instanciation
public class Utils {
    // Constructeur privé pour empêcher l'instanciation de cette classe utilitaire
    private Utils() {
        throw new UnsupportedOperationException("Classe utilitaire - ne pas instancier");
    }

    public static boolean isNotEmpty(String str){
        return str != null && !str.trim().isEmpty();
    }
}
