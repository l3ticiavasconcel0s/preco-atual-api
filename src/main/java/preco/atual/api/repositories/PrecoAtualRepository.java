package preco.atual.api.repositories;

import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import preco.atual.api.models.PrecoAtual;

@Repository
public class PrecoAtualRepository {

	private static final String SELECT_MAIS_RECENTE = """
			SELECT id, codigo_ativo, preco, data_hora_atualizacao
			FROM precos.precificacao
			WHERE codigo_ativo = ?
			ORDER BY data_hora_atualizacao DESC, id DESC
			LIMIT 1
			""";

	private final JdbcTemplate jdbcTemplate;

	public PrecoAtualRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Optional<PrecoAtual> buscarMaisRecente(String codigoAtivo) {
		return jdbcTemplate.query(
				SELECT_MAIS_RECENTE,
				(rs, rowNum) -> new PrecoAtual(
						rs.getLong("id"),
						rs.getString("codigo_ativo"),
						rs.getBigDecimal("preco"),
						rs.getTimestamp("data_hora_atualizacao").toLocalDateTime()),
				codigoAtivo)
				.stream()
				.findFirst();
	}
}
