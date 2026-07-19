package preco.atual.api.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import preco.atual.api.dtos.PrecoAtualResponse;
import preco.atual.api.exceptions.PrecoAtualNotFoundException;
import preco.atual.api.repositories.PrecoAtualRepository;

@Service
public class PrecoAtualService {

	private final PrecoAtualRepository repository;

	public PrecoAtualService(PrecoAtualRepository repository) {
		this.repository = repository;
	}

	@Transactional(readOnly = true)
	public PrecoAtualResponse buscarAtual(String codigoAtivo) {
		return repository.buscarAtual(codigoAtivo)
				.map(PrecoAtualResponse::from)
				.orElseThrow(() -> new PrecoAtualNotFoundException(codigoAtivo));
	}
}
