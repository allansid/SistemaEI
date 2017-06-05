package wrapper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	private static String doc;

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
		for(int i=1, n=1; i <= 20; i++, n++){
			doc = "C:\\Users\\Allan\\Downloads\\doc"+n+".txt";
			List<String> linhas = carregarArquivo(doc);
			Parser parser = new Parser(linhas);
			parser.parse();
			System.out.println(parser.toString() + "\n");
		}
		
	}

}