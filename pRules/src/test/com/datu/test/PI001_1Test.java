package com.datu.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kie.internal.runtime.StatefulKnowledgeSession;

import com.datu.patient.CollectionResponse;
import com.datu.patient.Patient;
import com.datu.result.DisplayCollection;

import static org.junit.Assert.assertEquals;

//Collect patient's initial information using MyInformation Survey
public class PI001_1Test extends RulesBaseTest {
  private StatefulKnowledgeSession ksession = null;
  private Patient patient;
  
	@Before
    public void setUp() {
        ksession = initializeKnowledgeSession();
        patient = new Patient();
    }
	
	@After
	public void tearDown() {
        if(ksession != null){
          ksession.dispose();
        }
    }
	
	@Test
	public void successCollectionResponseWithout11() {
	  System.out.println("Executing PI001_1Test.successCollectionResponseWithout11");
	  CollectionResponse c1 = new CollectionResponse();
      c1.setCollectionId(12);
      patient.getCollectionResponses().add(c1);
      ksession.insert(patient);
      ksession.fireAllRules();
      //List<DisplayCollection> dCollections = patient.getRulesResult().getDisplayCollections();
      boolean actualResult =  false;
      for(DisplayCollection displayCollection : patient.getRulesResult().getDisplayCollections()){
        if(displayCollection.getCollectionId() == 11){
            actualResult = true;
            break;
        }
      }
     
      
      assertEquals("Positive condition", actualResult, true);
	}
	
	@Test
    public void successCollectionResponsesWithout11() {
	  System.out.println("Executing PI001_1Test.successCollectionResponsesWithout11");
      CollectionResponse c1 = new CollectionResponse();
      c1.setCollectionId(12);
      
      CollectionResponse c2 = new CollectionResponse();
      c2.setCollectionId(13);
      
      patient.getCollectionResponses().add(c1);
      patient.getCollectionResponses().add(c2);
      ksession.insert(patient);
      ksession.fireAllRules();
      //List<DisplayCollection> dCollections = patient.getRulesResult().getDisplayCollections();
      boolean actualResult =  false;
      for(DisplayCollection displayCollection : patient.getRulesResult().getDisplayCollections()){
        if(displayCollection.getCollectionId() == 11){
            actualResult = true;
            break;
        }
      }
      System.out.println("actualResult="+actualResult);
      
      assertEquals("Positive condition", actualResult, true);
    }
	
	@Test
    public void failureCtionResponseWith11() {
	  System.out.println("Executing PI001_1Test.failureCtionResponseWith11");
      CollectionResponse c1 = new CollectionResponse();
      c1.setCollectionId(11);
      patient.getCollectionResponses().add(c1);
      ksession.insert(patient);
      ksession.fireAllRules();
      boolean actualResult =  false;
      for(DisplayCollection displayCollection : patient.getRulesResult().getDisplayCollections()){
        if(displayCollection.getCollectionId() == 11){
            actualResult = true;
            break;
        }
      }
      System.out.println("actualResult="+actualResult);
      
      assertEquals("Positive condition", actualResult, false);
    }
	
	@Test
    public void failureCollectionResponsesWith11() {
	  System.out.println("Executing PI001_1Test.failureCollectionResponsesWith11");
      CollectionResponse c1 = new CollectionResponse();
      c1.setCollectionId(11);
      CollectionResponse c2 = new CollectionResponse();
      c2.setCollectionId(12);
      patient.getCollectionResponses().add(c1);
      patient.getCollectionResponses().add(c2);
      ksession.insert(patient);
      ksession.fireAllRules();
      boolean actualResult =  false;
      for(DisplayCollection displayCollection : patient.getRulesResult().getDisplayCollections()){
        if(displayCollection.getCollectionId() == 11){
            actualResult = true;
            break;
        }
      }
      assertEquals("Positive condition", actualResult, false);
    }
}