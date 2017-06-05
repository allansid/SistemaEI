package aberta;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ie.util.RelationTriple;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.naturalli.NaturalLogicAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import wrapper.Parser;

/**
 * A demo illustrating how to call the OpenIE system programmatically.
 */
public class CoreNlp {
	private static String doc;
	private static List<String> linhas = new ArrayList<>();
	
	public static List<String> carregarArquivo(String path) {
		//List<String> linhas = new ArrayList<>();
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

	public void extrai(String doc){
	

			this.linhas = carregarArquivo(doc);
			Parser parser = new Parser(linhas);
			parser.parse();
			System.out.println(parser.toString());
		
	}
		
  public void getRelacoes(String documentos) throws Exception {
	FileInputStream fisTargetFile = new FileInputStream(new File(documentos));
	
	extrai(documentos); 
	
	//String targetFileStr = IOUtils.toString(fisTargetFile, "UTF-8").substring(')');  	
	String targetFileStr = linhas.get(4);
    // Create the Stanford CoreNLP pipeline
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize,ssplit,pos,lemma,depparse,natlog,openie");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    
    // Annotate an example document.
    Annotation doc = new Annotation(targetFileStr);
    pipeline.annotate(doc);
    int cont=1;
    
    // Loop over sentences in the document
    for (CoreMap sentence : doc.get(CoreAnnotations.SentencesAnnotation.class)) {
      // Get the OpenIE triples for the sentence
      Collection<RelationTriple> triples = sentence.get(NaturalLogicAnnotations.RelationTriplesAnnotation.class);
      // Print the triples
      for (RelationTriple triple : triples) {
    	 
        System.out.print(
        	"Relation "+cont+": "+	
        	triple.relationGloss() + " " +"("+
        	triple.subjectGloss() +","+ " " +
            triple.objectGloss()+")"+"\n");
        cont++;
      }
    }
  }
}
