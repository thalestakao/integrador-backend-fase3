package br.com.icarros.icontas.dto.request;

import java.math.BigDecimal;

import javax.validation.constraints.Positive;

import br.com.icarros.icontas.entity.Transacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepositaRequest {
	
	@Positive(message = "valor é um campo positivo e obrigatório")
	private BigDecimal vlrDeposita;

}
