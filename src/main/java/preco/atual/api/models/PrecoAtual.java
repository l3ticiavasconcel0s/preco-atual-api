package preco.atual.api.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PrecoAtual(
		Long id,
		String codigoAtivo,
		BigDecimal preco,
		LocalDateTime dataHoraAtualizacao) {
}
