package br.com.liraquality.sistemax;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class SistemaX {
	ArrayList<String> diretor = new ArrayList();
	ArrayList<String> filmes = new ArrayList();
	
	public void setDiretor(String Nome,String Nascimento) {
		diretor.add(Nome+";"+Nascimento);
		 
	}
	
	public void setFilmes(String Nome,String Lancamento) {
		filmes.add(Nome+";"+Lancamento);
		 
	}
	
	
	public ArrayList<String> getDiretor() {
		 
		 return diretor;
	}
	
	public ArrayList<String> getFilmes() {
		 
		 return filmes;
	}
	
	public void setCadastro() {
		setDiretor("Quentin Jerome Tarantino","27 de março de 1963");
		setDiretor("Martin Scorsese","17 de novembro de 1942");
		setDiretor("Steven Allan Spielberg","18 de dezembro de 1946");
		setFilmes("Pulp Fiction: Tempo de Violência","1994");
		setFilmes("Os Infiltrados","2006");
		setFilmes("Jogador Nº 1","2018");
		 
	}
 
 
}