package com.datu.test;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kie.internal.runtime.StatefulKnowledgeSession;

import com.datu.patient.Demographics;
import com.datu.patient.Patient;
import com.datu.patient.TileResponse;
import com.datu.result.DisplayCollection;

//Collect patient's initial information using MyInformation Survey
public class PI001_17Test extends RulesBaseTest {
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
	  System.out.println("Executing PI001_17Test.succesTest");
	  Demographics demographics = new Demographics();
      demographics.setGenderCode("F");
      demographics.setAge(20);
      patient.setDemographics(demographics);
      
      TileResponse tileResponse2 = new TileResponse();
      tileResponse2.setTileResponseId(73);
      tileResponse2.setDismissFlag(true);
      Calendar c = Calendar.getInstance();
      c.add(Calendar.DATE, -290);
      tileResponse2.setResponseDateTime(c.getTime());
      
      TileResponse tileResponse3 = new TileResponse();
      tileResponse3.setTileResponseId(73);
      tileResponse3.setDismissFlag(true);
      c = Calendar.getInstance();
      c.add(Calendar.DATE, -290);
      tileResponse3.setResponseDateTime(c.getTime());
      
    
      patient.getTileResponses().add(tileResponse2); 
      patient.getTileResponses().add(tileResponse3);
      
      ksession.insert(patient);
      ksession.fireAllRules();
      
      boolean actualResult =  false;
      for(DisplayCollection displayCollection : patient.getRulesResult().getDisplayCollections()){
        if(displayCollection.getCollectionId() == 26){
            actualResult = true;
            break;
        }
      }
      
      System.out.println("succesTest actualResult="+actualResult);
      
      assertEquals("Positive condition", actualResult, true);
	}
	
	@Test
    public void failureTest() {
	  System.out.println("Executing PI001_17Test.failureTest");
      Demographics demographics = new Demographics();
      demographics.setGenderCode("M");
      demographics.setAge(20);
      patient.setDemographics(demographics);
      
      TileResponse tileResponse2 = new TileResponse();
      tileResponse2.setTileResponseId(73);
      tileResponse2.setDismissFlag(true);
      Calendar c = Calendar.getInstance();
      c.add(Calendar.DATE, -290);
      tileResponse2.setResponseDateTime(c.getTime());
      
      TileResponse tileResponse3 = new TileResponse();
      tileResponse3.setTileResponseId(73);
      tileResponse3.setDismissFlag(true);
      c = Calendar.getInstance();
      c.add(Calendar.DATE, -290);
      tileResponse3.setResponseDateTime(c.getTime());
      
    
      patient.getTileResponses().add(tileResponse2); 
      patient.getTileResponses().add(tileResponse3);
      
      ksession.insert(patient);
      ksession.fireAllRules();
      
      boolean actualResult =  false;
      for(DisplayCollection displayCollection : patient.getRulesResult().getDisplayCollections()){
        if(displayCollection.getCollectionId() == 26){
            actualResult = true;
            break;
        }
      }
      
      System.out.println("succesTest actualResult="+actualResult);
      
      assertEquals("Positive condition", actualResult, false);
    }
	
}