package br.dominioL.sisPro.testes;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import br.dominioL.sisPro.testes.infraestrutura.http.TesteConstrutorDeUri;
import br.dominioL.sisPro.testes.infraestrutura.mapeadores.json.TesteMapeadorJson;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	TesteConstrutorDeUri.class,
	TesteMapeadorJson.class,
})

public class SuiteDeTestes {

}