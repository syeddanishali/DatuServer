package com.datu.test;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kie.internal.runtime.StatefulKnowledgeSession;

import com.datu.patient.CollectionResponse;
import com.datu.patient.Patient;
import com.datu.result.DisplayCollection;
import com.datu.result.RulesResult;

import static org.junit.Assert.assertEquals;

//Collect patient's initial information using MyInformation Survey
public class PI001_5Test extends RulesBaseTest {
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
	public void succesTest() {
	  System.out.println("Executing PI001_5Test.succesTest");
	  CollectionResponse c1 = new CollectionResponse();
      c1.setCollectionId(10);	  
	  
	  CollectionResponse c2 = new CollectionResponse();
      c2.setCollectionId(12);
      Calendar c = Calendar.getInstance();
      c.add(Calendar.DATE, -366);
      c2.setResponseDateTime(c.getTime());
      
      patient.getCollectionResponses().add(c1);
      patient.getCollectionResponses().add(c2);
            
      RulesResult rulesResult =  new RulesResult();
      patient.setRulesResult(rulesResult);
      
     
      ksession.insert(patient);
      ksession.fireAllRules();
      //List<DisplayCollection> dCollections = patient.getRulesResult().getDisplayCollections();
      boolean actualResult =  false;
      for(DisplayCollection displayCollection : patient.getRulesResult().getDisplayCollections()){
        if(displayCollection.getCollectionId() == 14){
            actualResult = true;
            break;
        }
      }
      assertEquals("Positive condition", actualResult, true);
	}
	
	@Test
    public void failureTest() {
	  System.out.println("Executing PI001_5Test.failureTest");
	  CollectionResponse c1 = new CollectionResponse();
      c1.setCollectionId(14);     
      
      CollectionResponse c2 = new CollectionResponse();
      c2.setCollectionId(14);
      Calendar c = Calendar.getInstance();
      c.add(Calendar.DATE, -370);
      c2.setResponseDateTime(c.getTime());
      
      patient.getCollectionResponses().add(c1);
      patient.getCollectionResponses().add(c2);
            
      RulesResult rulesResult =  new RulesResult();
      patient.setRulesResult(rulesResult);
      
     
      ksession.insert(patient);
      ksession.fireAllRules();
      //List<DisplayCollection> dCollections = patient.getRulesResult().getDisplayCollections();
      boolean actualResult =  false;
      for(DisplayCollection displayCollection : patient.getRulesResult().getDisplayCollections()){
        if(displayCollection.getCollectionId() == 14){
            actualResult = true;
            break;
        }
      }
      assertEquals("Positive condition", actualResult, false);
    }
}