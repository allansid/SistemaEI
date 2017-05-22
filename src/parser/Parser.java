package parser;

import java.util.List;

public class Parser {
	private List<String> linhas;
	private String link;
	private String titulo;
	private String[] autores;
	private String[] assuntos;
	private String comentarios;

	public Parser(List<String> linhas) {
		this.linhas = linhas;
	}
	
	public void parse(){
		carregarLink();
		titulo = linhas.get(1);
		carregarAutores();
		carregarComentarios();
		carregarAssuntos();
	}
	
	public void carregarLink(){
		String linha = linhas.get(0);
		link = linha.split(" ")[0];
	}
	
	public void carregarAutores(){
		autores = linhas.get(2).split(", ");
	}
	
	public void carregarComentarios(){
		String linha = linhas.get(3);
		if(linha.startsWith("Comments")){
			comentarios = linha.substring(linha.indexOf(": "));
		} else {
			comentarios = null;
		}
	}
	
	public void carregarAssuntos(){
		String linha;
		if(comentarios == null) {
			linha = linhas.get(3);
		} else {
			linha = linhas.get(4); 
		}
		assuntos = linha.split("; ");
	}
	
	public String imprimirArray(String[] lista){
		StringBuilder string = new StringBuilder();
		string.append(lista[0]);
		if(lista.length > 1){
			for(int i=0; i < lista.length; i++) {
				string.append(", ");
				string.append(lista[i]);
			}
		}
		
		return string.toString();
	}
	
	@Override
	public String toString() {
		String autores = imprimirArray(this.autores);
		String assuntos = imprimirArray(this.assuntos);

		String resultado;
		resultado = "Authors: " + autores.toString() + "\n" +
		"Title: " + titulo + "\n" +
		"Subjects: " + assuntos.toString() + "\n" +
		"Comments: " + comentarios + "\n" +
		"Link: " + link;
		
		return resultado;
	}
}
