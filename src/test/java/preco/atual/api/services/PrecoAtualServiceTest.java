package preco.atual.api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import preco.atual.api.dtos.PrecoAtualResponse;
import preco.atual.api.exceptions.PrecoAtualNotFoundException;
import preco.atual.api.models.PrecoAtual;
import preco.atual.api.repositories.PrecoAtualRepository;

@ExtendWith(MockitoExtension.class)
class PrecoAtualServiceTest {

	@Mock
	private PrecoAtualRepository repository;

	private PrecoAtualService service;

	@BeforeEach
	void setUp() {
		service = new PrecoAtualService(repository);
	}

	@Test
	void deveRetornarPrecoMaisRecente() {
		PrecoAtual preco = new PrecoAtual(
				1L,
				"ITAU3",
				new BigDecimal("100.01"),
				LocalDateTime.of(2026, 5, 5, 0, 0));
		when(repository.buscarMaisRecente("ITAU3")).thenReturn(Optional.of(preco));

		PrecoAtualResponse response = service.buscarAtual("ITAU3");

		assertEquals(preco.id(), response.id());
		assertEquals(preco.codigoAtivo(), response.codigoAtivo());
		assertEquals(preco.preco(), response.preco());
		assertEquals(preco.dataHoraAtualizacao(), response.dataHoraAtualizacao());
	}

	@Test
	void deveRetornarNotFoundQuandoNaoExistirPreco() {
		when(repository.buscarMaisRecente("ITAU3")).thenReturn(Optional.empty());

		assertThrows(PrecoAtualNotFoundException.class, () -> service.buscarAtual("ITAU3"));
	}
}
