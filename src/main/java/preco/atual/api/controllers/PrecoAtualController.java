package preco.atual.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import preco.atual.api.dtos.PrecoAtualResponse;
import preco.atual.api.services.PrecoAtualService;

@RestController
@RequestMapping("/v1/precos/preco-atual")
public class PrecoAtualController {

	private final PrecoAtualService service;

	public PrecoAtualController(PrecoAtualService service) {
		this.service = service;
	}

	@GetMapping
	public PrecoAtualResponse buscarAtual(@RequestParam("codigo_ativo") String codigoAtivo) {
		return service.buscarAtual(codigoAtivo);
	}
}
