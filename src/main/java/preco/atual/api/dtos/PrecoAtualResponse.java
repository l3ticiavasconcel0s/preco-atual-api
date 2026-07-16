package preco.atual.api.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import preco.atual.api.models.PrecoAtual;

public record PrecoAtualResponse(
		Long id,
		@JsonProperty("codigo_ativo") String codigoAtivo,
		BigDecimal preco,
		@JsonProperty("data_hora_atualizacao")
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:SSS")
		LocalDateTime dataHoraAtualizacao) {

	public static PrecoAtualResponse from(PrecoAtual precoAtual) {
		return new PrecoAtualResponse(
				precoAtual.id(),
				precoAtual.codigoAtivo(),
				precoAtual.preco(),
				precoAtual.dataHoraAtualizacao());
	}
}
