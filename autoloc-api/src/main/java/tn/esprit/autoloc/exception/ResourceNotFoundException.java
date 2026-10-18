package tn.esprit.autoloc.exception;
// Chap 5: Question 3+4
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String ressource, String id) {
        super(ressource + " introuvable avec l'identifiant " + id);

    }
}