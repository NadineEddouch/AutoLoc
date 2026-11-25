package tn.esprit.autoloc.web.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContratDTO {
    private Long idContrat;
    @NotNull @PastOrPresent private LocalDate dateSignature;
    @NotNull @DecimalMin("0.01") private BigDecimal montantTotal;
    @NotNull private Boolean valide;
}
