package br.protege.sisPro;

import org.junit.Assert;
import org.junit.Test;

import br.protege.sisPro.modelo.validacao.Validador;

public class TestesDoValidador {

	Validador validador;
	
	public TestesDoValidador() {
		
	}
	
	@Test
	public void testarValidaçãoDeNome() {
		validarNomeCorreto("lucas", "Lucas");
		validarNomeCorreto("lucas pereira", "Lucas Pereira");
		validarNomeCorreto("lucas pereira DA", "Lucas Pereira Da");
		validarNomeCorreto("LUCAS PEREIRA DA SILVA", "Lucas Pereira Da Silva");
		validarNomeCorreto("LUCAS PEREIRA DA SILVA ", "Lucas Pereira Da Silva");
		validarNomeCorreto("  LUCAS     PEREIRA DA SILVA    ", "Lucas Pereira Da Silva");
		validarNomeCorreto("lu", "Lu");
		validarNomeCorreto("l p", "L P");
		validarNomeCorreto("  l    p   ", "L P");
		validarNomeIncorreto("lucas p3reira", "Lucas P3reira");
		validarNomeIncorreto("lucas p 3 reira", "Lucas P 3 Reira");
		validarNomeIncorreto("LUPSI43", "Lupsi43");
		validarNomeIncorreto("LUP  SI @", "Lup Si @");
		validarNomeIncorreto("LUP_SI-@", "Lup_si-@");
		validarNomeIncorreto("  l  ", "L");
		validarNomeIncorreto("    ", null);
		validarNomeIncorreto("uuuuuuuuu uuuuuuuuu uuuuuuuuu uuuuuuuuu uuuuuuuuu uuuuuuuuu uuuuu", "Uuuuuuuuu Uuuuuuuuu Uuuuuuuuu Uuuuuuuuu Uuuuuuuuu Uuuuuuuuu Uuuuu");
	}
	
	@Test
	public void testarValidaçãoDeEndereçoEletrônico() {
		validarEndereçoEletrônicoCorreto("LUCAS@Gmail.com", "lucas@gmail.com");
		validarEndereçoEletrônicoCorreto("LUCAS@G.mail.com.BR", "lucas@g.mail.com.br");
		validarEndereçoEletrônicoCorreto("LU__CAS@Gmail.com", "lu__cas@gmail.com");
		validarEndereçoEletrônicoCorreto("LU__C-A--S@Gmail.com", "lu__c-a--s@gmail.com");
		validarEndereçoEletrônicoCorreto("LU__00@Gmail.c00om", "lu__00@gmail.c00om");
		validarEndereçoEletrônicoCorreto("www.lucas@Gmail.com", "www.lucas@gmail.com");
		validarEndereçoEletrônicoCorreto("l@g.c", "l@g.c");
		validarEndereçoEletrônicoCorreto("aaaaaaaaavaaaaaaaaav.aaaaaaaaav.aaaaaaaaav@aaaaaaaaav.aaaaaaaaav", "aaaaaaaaavaaaaaaaaav.aaaaaaaaav.aaaaaaaaav@aaaaaaaaav.aaaaaaaaav");
		validarEndereçoEletrônicoIncorreto("LUCAS,@G.mail.com", "lucas,@g.mail.com");
		validarEndereçoEletrônicoIncorreto("LUCAS@.Gmail.com", "lucas@.gmail.com");
		validarEndereçoEletrônicoIncorreto("LUCAS.@.Gmail.com", "lucas.@.gmail.com");
		validarEndereçoEletrônicoIncorreto("LUC~AS@Gmail.com", "luc~as@gmail.com");
		validarEndereçoEletrônicoIncorreto("@Gmail.com", "@gmail.com");
		validarEndereçoEletrônicoIncorreto("G@G", "g@g");
		validarEndereçoEletrônicoIncorreto(".g@Gmail.com", ".g@gmail.com");
		validarEndereçoEletrônicoIncorreto(" @ ", "@");
		validarEndereçoEletrônicoIncorreto("aaaaaaaaav.aaaaaaaaav.aaaaaaaaav.aaaaaaaaav@aaaaaaaaav.aaaaaaaaav", "aaaaaaaaav.aaaaaaaaav.aaaaaaaaav.aaaaaaaaav@aaaaaaaaav.aaaaaaaaav");
	}
	
	private void validarEndereçoEletrônicoCorreto(String endereçoEletrônico, String endereçoEletrônicoEsperado) {
		validador = new Validador();
		String endereçoEletrônicoValidado = validador.validarEndereçoEletrônico(endereçoEletrônico, true);
		Assert.assertEquals(endereçoEletrônicoEsperado, endereçoEletrônicoValidado);
		Assert.assertFalse(validador.possuiErro());
	}
	
	private void validarEndereçoEletrônicoIncorreto(String endereçoEletrônico, String endereçoEletrônicoEsperado) {
		validador = new Validador();
		String endereçoEletrônicoValidado = validador.validarEndereçoEletrônico(endereçoEletrônico, true);
		Assert.assertEquals(endereçoEletrônicoEsperado, endereçoEletrônicoValidado);
		Assert.assertTrue(validador.possuiErro());
	}
	
	private void validarNomeCorreto(String nome, String nomeEsperado) {
		validador = new Validador();
		String nomeValidado = validador.validarNome(nome, true);
		Assert.assertEquals(nomeEsperado, nomeValidado);
		Assert.assertFalse(validador.possuiErro());
	}
	
	private void validarNomeIncorreto(String nome, String nomeEsperado) {
		validador = new Validador();
		String nomeValidado = validador.validarNome(nome, true);
		Assert.assertEquals(nomeEsperado, nomeValidado);
		Assert.assertTrue(validador.possuiErro());
	}
}
