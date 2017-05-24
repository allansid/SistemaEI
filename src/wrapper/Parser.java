package wrapper;

import java.util.List;

public class Parser {
	private List<String> linhas;
	private String link;
	private String titulo;
	private String autores;
	private String assuntos;
	private String comentarios;
	private String journalRef;
	
	public Parser(List<String> linhas) {
		this.linhas = linhas;
	}
	
	public void parse(){
		this.link = linhas.get(0);
		this.titulo = linhas.get(1);
		this.autores = linhas.get(2);
		loadTags();
	}
	
	public void loadTags(){
		String linha = linhas.get(3);
		if(linha.startsWith("Comments:")){
			comentarios  = linha.split("Comments:")[1];
			linha = linhas.get(4);
		} else {
			comentarios = null;
		}
		
		if(linha.startsWith("Journal-ref:")){
			journalRef = linha.split("Journal-ref")[1];
			linha = linhas.get(5);
		}
		
		if(linha.startsWith("Subjects:")){
			assuntos = linha.split("Subjects:")[1];
		}
	}
	
//	public String imprimirArray(String[] lista){
//		StringBuilder string = new StringBuilder();
//		string.append(lista[0]);
//		if(lista.length > 1){
//			for(int i=0; i < lista.length; i++) {
//				string.append(", ");
//				string.append(lista[i]);
//			}
//		}
//		
//		return string.toString();
//	}
	
	@Override
	public String toString() {

		String resultado;
		resultado = 
		"Authors: "	+ autores		+ "\n" +
		"Title: "	+ titulo		+ "\n" +
		"Subjects:"	+ assuntos		+ "\n" +
		"Comments:"	+ comentarios 	+ "\n" +
		"Link:"		+ link;
		
		return resultado;
	}
}
