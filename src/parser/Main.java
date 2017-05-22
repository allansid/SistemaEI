package parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	private static String[] docs = {"C:\\Users\\mvgn\\Downloads\\doc1.txt", "C:\\Users\\mvgn\\Downloads\\doc2.txt", "C:\\Users\\mvgn\\Downloads\\doc3.txt"};


	public static List<String> carregarArquivo(String path) {
		List<String> linhas = new ArrayList<>();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(path));
			while(br.ready()){
				linhas.add(br.readLine());
			}
			br.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return linhas;
	}

	public static void main(String[] args) {
		for(int i=0; i < docs.length; i++){
			List<String> linhas = carregarArquivo(docs[i]);
			Parser parser = new Parser(linhas);
			parser.parse();
			System.out.println(parser.toString());
			System.out.println();
		}
		
	}

}
