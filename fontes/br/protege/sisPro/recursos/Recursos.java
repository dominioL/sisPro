package br.protege.sisPro.recursos;

public final class Recursos {

	private static Recursos INSTÂNCIA;
	
	private static final String CLIENTES = "/clientes";
	private static final String CLIENTES_COM_BARRA = CLIENTES+"/";
	private static final String CADASTRO_DE_ENDEREÇO = "/cadastroDeEndereco";
	private static final String CADASTRO_DE_ENDEREÇO_COM_INTERROGAÇÂO = CADASTRO_DE_ENDEREÇO+"?identificadorDoCliente=";
	private static final String ENDEREÇOS = "/enderecos";
	private static final String ENDEREÇOS_COM_BARRA = ENDEREÇOS+"/";
	
	private Recursos() {
		
	}
	
	public static Recursos fornecerInstância() {
		return (INSTÂNCIA == null) ? (INSTÂNCIA = new Recursos()) : INSTÂNCIA;
	}
	
	public String fornecerUriDoCliente(String identificadorDoCliente) {
		return CLIENTES_COM_BARRA+identificadorDoCliente;
	}
	
	public String fornecerUriDoCadastroDeEndereço(String identificadorDoCliente) {
		return CADASTRO_DE_ENDEREÇO_COM_INTERROGAÇÂO+identificadorDoCliente;
	}
	
	public String fornecerUriDoEndereço(String identificadorDoEndereço) {
		return ENDEREÇOS_COM_BARRA+identificadorDoEndereço;
	}
}
