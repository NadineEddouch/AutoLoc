package tn.esprit.autoloc.web.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.esprit.autoloc.domain.ModePaiement;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaiementDTO {
    private Long idPaiement;
    @NotNull @DecimalMin("0.01") private BigDecimal montant;
    @NotNull @PastOrPresent private LocalDate datePaiement;
    @NotNull private ModePaiement modePaiement;
}
