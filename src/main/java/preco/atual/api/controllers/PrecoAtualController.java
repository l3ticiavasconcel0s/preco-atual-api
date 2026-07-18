package preco.atual.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import preco.atual.api.dtos.PrecoAtualResponse;
import preco.atual.api.services.PrecoAtualService;

@RestController
@RequestMapping("/preco-atual/v1/precos")
public class PrecoAtualController {

	private final PrecoAtualService service;

	public PrecoAtualController(PrecoAtualService service) {
		this.service = service;
	}

	@GetMapping("/{codigoAtivo}")
	public PrecoAtualResponse buscarAtual(@PathVariable String codigoAtivo) {
		return service.buscarAtual(codigoAtivo);
	}
}
