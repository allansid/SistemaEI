package wrapper;

import edu.stanford.nlp.ie.util.RelationTriple;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.naturalli.NaturalLogicAnnotations;
import edu.stanford.nlp.util.CoreMap;

import java.io.File;
import java.io.FileInputStream;
import java.util.Collection;
import java.util.Properties;

import org.apache.commons.io.IOUtils;

/**
 * A demo illustrating how to call the OpenIE system programmatically.
 */
public class CoreNlp {

  public static void main(String[] args) throws Exception {
	FileInputStream fisTargetFile = new FileInputStream(new File("A:\\UFPE\\Mineração da Web\\Projeto2\\doc1.txt"));
	String targetFileStr = IOUtils.toString(fisTargetFile, "UTF-8");  
	
	  
    // Create the Stanford CoreNLP pipeline
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize,ssplit,pos,lemma,depparse,natlog,openie");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    
    // Annotate an example document.
    Annotation doc = new Annotation(targetFileStr);
    pipeline.annotate(doc);

    // Loop over sentences in the document
    for (CoreMap sentence : doc.get(CoreAnnotations.SentencesAnnotation.class)) {
      // Get the OpenIE triples for the sentence
      Collection<RelationTriple> triples = sentence.get(NaturalLogicAnnotations.RelationTriplesAnnotation.class);
      // Print the triples
      for (RelationTriple triple : triples) {
        System.out.println(
        	triple.relationGloss() + " " +"("+
        	triple.subjectGloss() +","+ " " +
            triple.objectGloss()+")");
      }
    }
  }
}
