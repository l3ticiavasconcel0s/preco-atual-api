package preco.atual.api.exceptions;

public class PrecoAtualNotFoundException extends RuntimeException {

	public PrecoAtualNotFoundException(String codigoAtivo) {
		super("Nenhum preço atual encontrado para o ativo: " + codigoAtivo);
	}
}
