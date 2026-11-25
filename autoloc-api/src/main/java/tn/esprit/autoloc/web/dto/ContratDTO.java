package tn.esprit.autoloc.web.dto;

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
    private LocalDate dateSignature;
    private BigDecimal montantTotal;
    private Boolean valide;
}
