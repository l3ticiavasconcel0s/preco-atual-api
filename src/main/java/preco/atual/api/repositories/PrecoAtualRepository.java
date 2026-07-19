package preco.atual.api.repositories;

import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import preco.atual.api.models.PrecoAtual;

@Repository
public class PrecoAtualRepository {

	private static final String SELECT_ATUAL = """
			SELECT id, codigo_ativo, preco, data_hora_atualizacao, atualizado
			FROM precos.precificacao
			WHERE codigo_ativo = ?
			  AND atualizado = TRUE
			""";

	private final JdbcTemplate jdbcTemplate;

	public PrecoAtualRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Optional<PrecoAtual> buscarAtual(String codigoAtivo) {
		return jdbcTemplate.query(
				SELECT_ATUAL,
				(rs, rowNum) -> new PrecoAtual(
						rs.getLong("id"),
						rs.getString("codigo_ativo"),
						rs.getBigDecimal("preco"),
						rs.getTimestamp("data_hora_atualizacao").toLocalDateTime(),
						rs.getBoolean("atualizado")),
				codigoAtivo)
				.stream()
				.findFirst();
	}
}
