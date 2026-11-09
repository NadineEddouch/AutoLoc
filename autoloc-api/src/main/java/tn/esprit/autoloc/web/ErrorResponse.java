package tn.esprit.autoloc.web;

import java.time.LocalDateTime;

// Chap 6 : question 18 - classe record pour structurer les réponses d'erreur
public record ErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        String path
) {}
