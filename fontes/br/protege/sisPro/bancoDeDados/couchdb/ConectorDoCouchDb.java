package br.protege.sisPro.bancoDeDados.couchdb;

import java.net.MalformedURLException;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;

public final class ConectorDoCouchDb {

	private static CouchDbConnector INSTÂNCIA;

	public static CouchDbConnector fornecerConector() {
		if (INSTÂNCIA == null) {
			HttpClient clienteHttp = null;
			try {
				clienteHttp = new StdHttpClient.Builder().url("http://localhost:5984").build();
			} catch (MalformedURLException erro) {
				erro.printStackTrace();
			}
			CouchDbInstance instânciaDoCouchDb = new StdCouchDbInstance(clienteHttp);
			INSTÂNCIA = new StdCouchDbConnector("sispro", instânciaDoCouchDb);
		}
		
		return INSTÂNCIA;
	}
}
