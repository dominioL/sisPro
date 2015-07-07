package br.dominioL.sisPro.ambiente;

import javax.ws.rs.core.Application;

import br.dominioL.sisPro.infraestrutura.dados.BancoDeDados;
import br.dominioL.sisPro.infraestrutura.http.ConstrutorDeUri;

public interface Ambiente<T extends BancoDeDados<?, ?>> {

	public ConstrutorDeUri construtorDeUri();

	public ConstrutorDeUri construtorDeUriParaBancoDeDados();

	public Application aplicacao();

	public T bancoDeDados();

}