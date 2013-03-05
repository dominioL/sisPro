package br.protege.sisPro.bancoDeDados;

import java.util.List;

public interface TipoBancoDeDados<T> {
	
	public boolean adicionar(T dado);
	
	public boolean remover(T dado);
	
	public T fornecer(T dado);
	
	public List<T> fornecerLista(T dado);
	
	public boolean salvar(T dado);
}
