package br.dominioL.sisPro.testes;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import br.dominioL.sisPro.testes.http.TesteConstrutorDeUri;
import br.dominioL.sisPro.testes.mapeadores.json.TesteMapeadorJson;
import br.dominioL.sisPro.testes.validadores.TesteValidador;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	TesteConstrutorDeUri.class,
	TesteMapeadorJson.class,
	TesteValidador.class
})

public class SuiteDeTestes {

}
