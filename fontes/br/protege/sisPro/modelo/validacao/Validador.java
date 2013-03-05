package br.protege.sisPro.modelo.validacao;

import java.util.LinkedList;
import java.util.List;

public final class Validador {
	
	private MensagemDeErro mensagemDeErro;
	
	public Validador() {
		mensagemDeErro = new MensagemDeErro();
	}
	
	public String validarTipoDeCliente(String tipo) {
		String novoTipo = tipo;
		if (!tipo.equals("pessoaFisica") && !tipo.equals("pessoaJurídica")) {
			mensagemDeErro.adicionarCampoInválido("Tipo de cliente inválido. Tipo deve ser pessoaFisica ou pessoaJurídica.");
			novoTipo = "pessoaFisica";
		}
		
		return novoTipo;
	}
	
	public String validarNome(String nome, boolean obrigatório) {
		return validarTextoCurto(nome, "nome", "Nome inválido. Formato do nome: somente letras, com no mínimo 2 e no máximo 64 letras.", obrigatório);
	}
	
	public String validarBloco(String bloco, boolean obrigatório) {
		return validarTextoCurto(bloco, "bloco", "Bloco inválido. Formato do bloco: somente letras, com no mínimo 2 e no máximo 64 letras.", obrigatório);
	}
	
	public String validarEdifícil(String edifícil, boolean obrigatório) {
		return validarTextoCurto(edifícil, "edifícil", "Edifícil inválido. Formato do edifícil: somente letras, com no mínimo 2 e no máximo 64 letras.", obrigatório);
	}
	
	public String validarCondomínio(String condomínio, boolean obrigatório) {
		return validarTextoCurto(condomínio, "condomínio", "Condomínio inválido. Formato do condomínio: somente letras, com no mínimo 2 e no máximo 64 letras.", obrigatório);
	}
	
	public String validarLogradouro(String logradouro, boolean obrigatório) {
		String novoLogradouro = capitalizarTexto(logradouro);
		boolean estáVazio = novoLogradouro.isEmpty();
		if (!estáVazio && !novoLogradouro.matches("^[\\p{L}\\s][0-9\\p{L}\\s]{1,63}$")) {
				mensagemDeErro.adicionarCampoInválido("Logradouro inválido. Formato do logradouro: somente letras e números, com no mínimo 2 e no máximo 64 letras, começando por uma letra.");
		}
		novoLogradouro = validarSeCampoÉObrigatório(estáVazio, obrigatório, "logradouro", novoLogradouro);
		
		return novoLogradouro;
	}
	
	public String validarBairro(String bairro, boolean obrigatório) {
		return validarTextoCurto(bairro, "bairro", "Bairro inválido. Formato do bairro: somente letras, com no mínimo 2 e no máximo 64 letras.", obrigatório);
	}
	
	public String validarCidade(String cidade, boolean obrigatório) {
		return validarTextoCurto(cidade, "cidade", "Cidade inválida. Formato da cidade: somente letras, com no mínimo 2 e no máximo 64 letras.", obrigatório);
	}
	
	public String validarEstado(String estado, boolean obrigatório) {
		String novoEstado = removerEspaçosExtrasDoTexto(estado);
		novoEstado = novoEstado.toUpperCase();
		boolean estáVazio = novoEstado.isEmpty();
		if (!estáVazio && !novoEstado.matches("^[A-Z]{2}$")) {
			mensagemDeErro.adicionarCampoInválido("Estado inválido. Formato do estado: somente letras, com extamente 2 letras.");
		}
		novoEstado = validarSeCampoÉObrigatório(estáVazio, obrigatório, "estado", novoEstado);
		
		return novoEstado;
	}
	
	public String validarEndereçoEletrônico(String endereçoEletrônico, boolean obrigatório) {
		String novoEndereçoEletrônico = (endereçoEletrônico != null) ? endereçoEletrônico.replaceAll(" ", "") : "";
		novoEndereçoEletrônico = novoEndereçoEletrônico.toLowerCase();
		boolean estáVazio = novoEndereçoEletrônico.isEmpty();
		boolean possuiErro = (!novoEndereçoEletrônico.matches("^(([a-z0-9]+[-_]*[.]?)*[a-z0-9])[@](([a-z0-9]+[.])+[a-z0-9]+)$") || novoEndereçoEletrônico.length() > 64); 
		if (!estáVazio && possuiErro) {
			mensagemDeErro.adicionarCampoInválido("Endereço eletrônico "+endereçoEletrônico+" inválido. Formato do endereço eletrônico: somente letras, números, traço (-), sublinhado (_), ponto (.) e arroba (@), com no mínimo 5 e no máximo 64 caracteres.");
		}
		novoEndereçoEletrônico = validarSeCampoÉObrigatório(estáVazio, obrigatório, "endereço eletrônico", novoEndereçoEletrônico);
		
		return novoEndereçoEletrônico;
	}
	
	public String validarSite(String site, boolean obrigatório) {
		String novoSite = (site != null) ? site.replaceAll(" ", "") : "";
		novoSite = novoSite.toLowerCase();
		boolean estáVazio = novoSite.isEmpty();
		boolean possuiErro = (!novoSite.matches("^([a-z]+([a-z0-9]+[.])+[a-z0-9]+)$") || novoSite.length() > 64);
		if (!estáVazio && possuiErro) {
			mensagemDeErro.adicionarCampoInválido("Site "+site+" inválido. Formato do site: somente letras, números e ponto (.), com no mínimo 4 e no máximo 64 caracteres.");
		}
		novoSite = validarSeCampoÉObrigatório(estáVazio, obrigatório, "site", novoSite);
		
		return novoSite;
	}
	
	public String validarInformaçõesExtras(String informaçõesExtras, boolean obrigatório) {
		String novasInformaçõesExtras = removerEspaçosExtrasDoTexto(informaçõesExtras);
		boolean estáVazio = novasInformaçõesExtras.isEmpty();
		novasInformaçõesExtras = validarSeCampoÉObrigatório(estáVazio, obrigatório, "informações extras", informaçõesExtras);
		
		return novasInformaçõesExtras;
	}
	
	public String validarIdentificador(String chave,  boolean obrigatório) {
		return validarChaveDoCouchDb(chave, "identificador", "O indentificador é inválido. Formato do identificador: somente letras e números com exatamente 32 caracteres.", obrigatório);
	}
	
	public String validarRevisão(String chave, boolean obrigatório) {
		return validarChaveDoCouchDb(chave, "revisão", "A revisão é inválida. Formato da revisão: somente letras, números e traço (-), com  uma quantidade arbitrária de números, seguido por um traço e seguido por 32 caracteres.", obrigatório);
	}
	
	public String validarIdentificadorEstrangeiro(String chave, String nomeDoEstrangeiro,  boolean obrigatório) {
		return validarChaveDoCouchDb(chave, "identificador", "O "+nomeDoEstrangeiro+" é inválido. Formato do identificador: somente letras e números com exatamente 32 caracteres.", obrigatório);
	}
	
	public String validarCpf(String cpf, boolean obrigatório) {
		return validarNúmero(cpf, 11, "CPF inválido. Formato do CPF: somente números, com exatamente 11 números.", obrigatório, "cpf");
	}
	
	public String validarCnpj(String cnpj, boolean obrigatório) {
		return validarNúmero(cnpj, 12, "CNPJ inválido. Formato do CNPJ: somente números, com exatamente 12 números.", obrigatório, "cnpj");
	}
	
	public String validarInscriçãoEstadual(String inscriçãoEstadual, boolean obrigatório) {
		return validarNúmero(inscriçãoEstadual, 12, "Inscrição estadual inválida. Formato da inscrição estadual: somente números, com exatamente 12 números.", obrigatório, "inscrição estadual");
	}
	
	public String validarTelefone(String telefone, boolean obrigatório) {
		return validarNúmero(telefone, 10, "Telefone "+telefone+" inválido. Formato do telefone: somente números, com exatamente 10 números, sendo os dois primeiros números o código de área.", obrigatório, "telefone");
	}
	
	public String validarApartamento(String apartamento, boolean obrigatório) {
		return validarNúmero(apartamento, 1, 10, "Apartamento "+apartamento+" inválido. Formato do apartamento: somente números, com no mínimo 1 e no máximo 10 dígitos", obrigatório, "apartamento");
	}

	public String validarNúmeroDeLogradouro(String número, boolean obrigatório) {
		return validarNúmero(número, 1, 10, "Número "+número+" inválido. Formato do número: somente números, com no mínimo 1 e no máximo 10 dígitos", obrigatório, "número");
	}
	
	public String validarCep(String cep, boolean obrigatório) {
		return validarNúmero(cep, 8, "CEP "+cep+" inválido. Formato do cep: somente números, com no mínimo 1 e no máximo 10 dígitos", obrigatório, "número");
	}
	
	public List<String> validarEndereçosEletrônicos(List<String> endereçosEletrônicos, boolean obrigatório) {
		List<String> novaListaDeEndereçosEletrônicos = new LinkedList<String>();
		if (endereçosEletrônicos != null) {
			for (String endereçoEletrônico : endereçosEletrônicos) {
				String endereçoEletrônicoValidado = validarEndereçoEletrônico(endereçoEletrônico, false);
				if (endereçoEletrônicoValidado != null && !endereçoEletrônicoValidado.isEmpty() && !novaListaDeEndereçosEletrônicos.contains(endereçoEletrônicoValidado)) {
					novaListaDeEndereçosEletrônicos.add(endereçoEletrônicoValidado);
				}
			}
		}
		if (novaListaDeEndereçosEletrônicos.size() == 0) {
			if (obrigatório) {
				mensagemDeErro.adicionarCampoInválido("É necessário informar pelo menos um endereço eletrônico.");
			}
			novaListaDeEndereçosEletrônicos = null;
		}
		
		return novaListaDeEndereçosEletrônicos;
	}
	
	public List<String> validarTelefones(List<String> telefones, boolean obrigatório) {
		List<String> novaListaDeTelefones = new LinkedList<String>();
		if (telefones != null) {
			for (String telefone : telefones) {
				String telefoneValidado = validarTelefone(telefone, false);
				if (telefoneValidado != null && !telefoneValidado.isEmpty() && !novaListaDeTelefones.contains(telefoneValidado)) {
					novaListaDeTelefones.add(telefoneValidado);
				}
			}
		}
		if (novaListaDeTelefones.size() == 0) {
			if (obrigatório) {
				mensagemDeErro.adicionarCampoInválido("É necessário informar pelo menos um telefone.");
			}
			novaListaDeTelefones = null;
		}
		
		return novaListaDeTelefones;
	}
	
	public List<String> validarIdentificadoresChavesDoCouchDb(List<String> chaves, String nomeDoCampo, boolean obrigatório) {
		List<String> novaListaDeChaves = new LinkedList<String>();
		if (chaves != null) {
			for (String chave : chaves) {
				String chaveValidada = validarIdentificadorEstrangeiro(chave, nomeDoCampo, false);
				if (chaveValidada != null && !chaveValidada.isEmpty() && !novaListaDeChaves.contains(chaveValidada)) {
					novaListaDeChaves.add(chaveValidada);
				}
			}
		}
		if (novaListaDeChaves.size() == 0) {
			if (obrigatório) {
				mensagemDeErro.adicionarCampoInválido("É necessário informar pelo menos um "+nomeDoCampo);
			}
			novaListaDeChaves = null;
		}
		
		return novaListaDeChaves;
	}
	
	public MensagemDeErro fornecerMensagemDeErro() {
		return mensagemDeErro;
	}
	
	public boolean possuiErro() {
		return mensagemDeErro.possuiErro();
	}
	
	private String validarTextoCurto(String campo, String nomeDoCampo, String mensagemDeErroDoCampo, boolean obrigatório) {
		String novoCampo = capitalizarTexto(campo);
		boolean estáVazio = novoCampo.isEmpty();
		if (!estáVazio && !novoCampo.matches("^[\\p{L}\\s]{2,64}$")) {
				mensagemDeErro.adicionarCampoInválido(mensagemDeErroDoCampo);
		}
		novoCampo = validarSeCampoÉObrigatório(estáVazio, obrigatório, nomeDoCampo, novoCampo);
		
		return novoCampo;
	}
	
	private String validarNúmero(String número, int quantidadeDeDígitos, String mensagemDeUmPossívelErro, boolean obrigatório, String nomeDoCampo) {
		return validarNúmero(número, quantidadeDeDígitos, quantidadeDeDígitos, mensagemDeUmPossívelErro, obrigatório, nomeDoCampo);
	}
	
	private String validarNúmero(String número, int quantidadeMínimaDeDígitos, int quantidadeMáximaDeDígitos, String mensagemDeUmPossívelErro, boolean obrigatório, String nomeDoCampo) {
		String novoNúmero = removerEspaçosExtrasDoTexto(número);
		boolean estáVazio = novoNúmero.isEmpty();
		boolean possuiErro = !novoNúmero.matches("^[0-9]{"+quantidadeMínimaDeDígitos+","+quantidadeMáximaDeDígitos+"}$");
		if (!estáVazio && possuiErro) {
			mensagemDeErro.adicionarCampoInválido(mensagemDeUmPossívelErro);
		}
		novoNúmero = validarSeCampoÉObrigatório(estáVazio, obrigatório, nomeDoCampo, novoNúmero);
		
		return novoNúmero;
	}
	
	private String validarChaveDoCouchDb(String chave, String nomeDoCampo, String mensagemDeErroDoCampo,  boolean obrigatório) {
		String novaChave = (chave != null) ? chave.replaceAll(" ", "") : "";
		novaChave = novaChave.toLowerCase();
		boolean estáVazio = novaChave.isEmpty();
		boolean possuiErro = (!novaChave.matches("^([0-9]+-)?[0-9a-z]{32}$"));
		if (!estáVazio && possuiErro) {
			mensagemDeErro.adicionarCampoInválido(mensagemDeErroDoCampo);
		}
		novaChave = validarSeCampoÉObrigatório(estáVazio, obrigatório, nomeDoCampo, novaChave);
		
		return novaChave;
	}
	
	private String capitalizarTexto(String texto) {
		texto = removerEspaçosExtrasDoTexto(texto);
		texto = texto.toLowerCase();
		String[] palavrasDoTexto = texto.split(" ");
		String textoCapitalizado = "";
		for (String parteDoNome : palavrasDoTexto) {
			if(parteDoNome.length() > 0) {
				String primeiraLetra = parteDoNome.charAt(0)+"";
				textoCapitalizado += primeiraLetra.toUpperCase()+parteDoNome.substring(1, parteDoNome.length())+" ";
			}
		}
		
		return (textoCapitalizado.isEmpty()) ? textoCapitalizado : textoCapitalizado.substring(0, textoCapitalizado.length()-1);
	}
	
	private String removerEspaçosExtrasDoTexto(String texto) {
		texto = (texto != null) ? texto.trim() : "";
		texto = texto.replaceAll("[\\s]+", " ");
		
		return texto;
	}
	
	private String validarSeCampoÉObrigatório(boolean estáVazio, boolean obrigatório, String nomeDoCampo, String campo) {
		if (estáVazio) {
			if (obrigatório) {
				mensagemDeErro.adicionarCampoInválido("O preenchimendo do campo "+nomeDoCampo+" é obrigatório");
			}
			return null;
		}
		
		return campo;
	}
}
