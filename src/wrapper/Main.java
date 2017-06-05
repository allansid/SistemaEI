package wrapper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	

	public static void main(String[] args) {
		CoreNlp core=new CoreNlp();
		try {
			core.getRelacoes("C:\\Users\\Allan\\Downloads\\doc1.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}