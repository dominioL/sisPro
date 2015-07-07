package br.dominioL.sisPro.ambiente;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import br.dominioL.sisPro.recursos.excecoes.RecursoExcecaoJava;
import br.dominioL.sisPro.recursos.excecoes.RecursoExcecaoWeb;

public final class AplicacaoSisPro extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> recursos = new HashSet<>();
		recursos.add(RecursoExcecaoWeb.class);
		recursos.add(RecursoExcecaoJava.class);
		return recursos;
	}

}