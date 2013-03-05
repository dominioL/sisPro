package br.protege.sisPro.modelo.json;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;

public final class ManipuladorJson {
	
	private JSONObject representaçãoJson;
	
	public ManipuladorJson(JSONObject representaçãoJson) {
		this.representaçãoJson = representaçãoJson;
	}
	
	public void adicionarObjetoJson(JSONObject objetoJson, String nomeDoCampo) {
		try {
			representaçãoJson.put(nomeDoCampo, objetoJson);
		} catch (JSONException erro) {
			erro.printStackTrace();
		}
	}
	
	public void adicionarCampo(boolean campo, String nomeDoCampo) {
		try {
			representaçãoJson.put(nomeDoCampo, campo);
		} catch (JSONException erro) {
			erro.printStackTrace();
		}
	}
	
	public void adicionarCampo(String campo, String nomeDoCampo) {
		if (campo != null && !campo.isEmpty()) {
			try {
				representaçãoJson.put(nomeDoCampo, campo);
			} catch (JSONException erro) {
				erro.printStackTrace();
			}
		}
	}
	
	public void adicionarColeçãoDeCampos(List<String> campos, String nomeDoCampo) {
		if (campos != null && campos.size() > 0) {
			try {
				representaçãoJson.put(nomeDoCampo, campos);
			} catch (JSONException erro) {
				erro.printStackTrace();
			}
		}
	}
	
	public String obterCampoJsonComoTexto(String nomeDoCampo) {
		String campo = null;
		try {
			campo = representaçãoJson.getString(nomeDoCampo);
		} catch (ClientHandlerException erro) {
			erro.printStackTrace();
		} catch (UniformInterfaceException erro) {
			erro.printStackTrace();
		} catch (JSONException erro) {
			erro.printStackTrace();
		}
		
		return campo;
	}
	
//	private JSONArray obterColeçãoComoJson(List<String> coleção) {
//		JSONArray coleçãoComoJson = new JSONArray();
//		for (String elemento : coleção) {
//			coleçãoComoJson.put(elemento);
//		}
//		
//		return coleçãoComoJson;
//	}
	
	public static JSONObject obterRespostaComoJson(ClientResponse respsota) {
		JSONObject respostaComoJson = null;
		try {
			 respostaComoJson = new JSONObject(respsota.getEntity(String.class));
		} catch (ClientHandlerException erro) {
			erro.printStackTrace();
		} catch (UniformInterfaceException erro) {
			erro.printStackTrace();
		} catch (JSONException erro) {
			erro.printStackTrace();
		}
		
		return respostaComoJson;
	}
}
