package br.com.icarros.icontas.dto.response;

import java.math.BigDecimal;

import br.com.icarros.icontas.dto.request.DepositaRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepositaResponse {
	
	private BigDecimal valor;
	
}
