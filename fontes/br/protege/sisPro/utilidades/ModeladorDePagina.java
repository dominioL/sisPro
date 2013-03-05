package br.protege.sisPro.utilidades;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;

import org.json.JSONObject;

import br.protege.sisPro.estruturaInterna.ArquivoHtml;
import br.protege.sisPro.estruturaInterna.Pasta;

public final class ModeladorDePagina {
	
	private static String PÁGINA_INICIAL;
	
	private String modelo;
	private String caminhoBase;
	
	private static final String MARCA_DE_TÍTULO = "<!--título-->";
	private static final String MARCA_DE_CONTEÚDO = "<!--conteúdo-->";
	private static final String MARCA_DO_MENU_TEMÁTICO = "<!--menuTemático-->";
	private static final String MARCA_DE_INFORMAÇÕES_JSON = "\"informaçõesJson\"";
	private static final String NOME_PADRÃO_DO_ARQUIVO_MOLDE = ArquivoHtml.INDEX;
	private static final String CRLF = "\r\n";
	private static final int TAMANHO_DO_BUFFER = 2048;
	
	public ModeladorDePagina(String caminhoBase, String nomeDoArquivoMolde) {
		this.caminhoBase = caminhoBase;
		modelo = lerArquivo(nomeDoArquivoMolde);
	}
	
	private ModeladorDePagina() {
		this.caminhoBase = Pasta.HTML;
		modelo = PÁGINA_INICIAL;
	}
	
	public static ModeladorDePagina fornecerMoldeDePáginaInicial() {
		if (PÁGINA_INICIAL == null) { 
			PÁGINA_INICIAL = new ModeladorDePagina(Pasta.HTML, NOME_PADRÃO_DO_ARQUIVO_MOLDE).fornecerPáginaMoldada();
		}
		
		return new ModeladorDePagina();
	}
	
	public void fixarModelo(String nomeDaMarcação, String nomeDoArquivoModelo) {
		String textoDoArquivoModelo = lerArquivo(nomeDoArquivoModelo);
		modelo = modelo.replaceFirst(nomeDaMarcação, textoDoArquivoModelo);
	}
	
	public void fixarConteúdo(String nomeDoArquivoComConteúdo) {
		fixarModelo(MARCA_DE_CONTEÚDO, nomeDoArquivoComConteúdo);
	}
	
	public void fixarMenuTemático(String nomeDoArquivoComMenuTemático) {
		fixarModelo(MARCA_DO_MENU_TEMÁTICO, nomeDoArquivoComMenuTemático);
	}
	
	public void fixarTítulo(String título) {
		modelo = modelo.replaceFirst(MARCA_DE_TÍTULO, título);
	}
	
	public void fixarInformaçõesJson(JSONObject informaçõesJson) {
		modelo = modelo.replaceFirst(MARCA_DE_INFORMAÇÕES_JSON, informaçõesJson.toString());
	}
	
	public String fornecerPáginaMoldada() {
		return modelo;
	}
	
	private String lerArquivo(String nomeDoArquivo) {
		String textoLido = "";
		try {
			BufferedReader leitor = new BufferedReader(new FileReader(caminhoBase+nomeDoArquivo), TAMANHO_DO_BUFFER);
			StringWriter escritor = new StringWriter(TAMANHO_DO_BUFFER);
			while (leitor.ready()) {
				escritor.write(leitor.readLine()+CRLF);
				escritor.flush();
			}
			textoLido = escritor.toString();
			leitor.close();
			escritor.close();
		} catch (FileNotFoundException erro) {
			erro.printStackTrace();
		} catch (IOException erro) {
			erro.printStackTrace();
		}
		
		return textoLido;
	}
}
