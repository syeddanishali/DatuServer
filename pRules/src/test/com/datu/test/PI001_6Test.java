package com.datu.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kie.internal.runtime.StatefulKnowledgeSession;

import com.datu.patient.Demographics;
import com.datu.patient.Patient;
import com.datu.patient.TileResponse;
import com.datu.patient.TileResponseEngagement;
import com.datu.result.DisplayCollection;
import com.datu.result.RulesResult;

import static org.junit.Assert.assertEquals;

//Collect patient's initial information using MyInformation Survey
public class PI001_6Test extends RulesBaseTest {
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
	  System.out.println("Executing PI001_6Test.succesTest");
	  Demographics demographics = new Demographics();
      demographics.setGenderCode("F");
      demographics.setAge(20);
      patient.setDemographics(demographics);
     
      TileResponseEngagement tileResponseEngagement = new TileResponseEngagement();
      tileResponseEngagement.setActionValue("Pregnancy/Postpartum");
      
      TileResponse tileResponse = new TileResponse();
      tileResponse.setTileResponseId(12);
      tileResponse.getTileResponseEngagements().add(tileResponseEngagement);
    
      RulesResult rulesResult =  new RulesResult();
      patient.setRulesResult(rulesResult);
      patient.getTileResponses().add(tileResponse);
     
      ksession.insert(patient);
      ksession.fireAllRules();
      //List<DisplayCollection> dCollections = patient.getRulesResult().getDisplayCollections();
      boolean actualResult =  true;
      for(DisplayCollection displayCollection : patient.getRulesResult().getDisplayCollections()){
        if(displayCollection.getCollectionId() == 19){
            actualResult = true;
            break;
        }
      }
      assertEquals("Positive condition", actualResult, true);
	}
	
	@Test
    public void failureTestNoResponseEngagements() {
	  
	  Demographics demographics = new Demographics();
      demographics.setGenderCode("F");
      demographics.setAge(20);
      patient.setDemographics(demographics);
     
      TileResponse tileResponse = new TileResponse();
      tileResponse.setTileResponseId(12);
      //tileResponse.getTileResponseEngagements().add(tileResponseEngagement);
    
      RulesResult rulesResult =  new RulesResult();
      patient.setRulesResult(rulesResult);
      patient.getTileResponses().add(tileResponse);
     
      ksession.insert(patient);
      ksession.fireAllRules();
      //List<DisplayCollection> dCollections = patient.getRulesResult().getDisplayCollections();
      boolean actualResult =  false;
      for(DisplayCollection displayCollection : patient.getRulesResult().getDisplayCollections()){
        if(displayCollection.getCollectionId() == 19){
            actualResult = true;
            break;
        }
      }
      System.out.println("failureTestNoResponseEngagements actualResult="+actualResult);
      
      assertEquals("Positive condition", actualResult, false);
    }
	
	@Test
    public void failureTestMale() {
	  System.out.println("Executing PI001_6Test.failureTestMale");
      Demographics demographics = new Demographics();
      demographics.setGenderCode("M");
      demographics.setAge(20);
      patient.setDemographics(demographics);
     
      TileResponse tileResponse = new TileResponse();
      tileResponse.setTileResponseId(12);
      

      TileResponseEngagement tileResponseEngagement = new TileResponseEngagement();
      tileResponseEngagement.setActionValue("Pregnancy/Postpartum");
      tileResponse.getTileResponseEngagements().add(tileResponseEngagement);
    
      RulesResult rulesResult =  new RulesResult();
      patient.setRulesResult(rulesResult);
      patient.getTileResponses().add(tileResponse);
     
      ksession.insert(patient);
      ksession.fireAllRules();
      //List<DisplayCollection> dCollections = patient.getRulesResult().getDisplayCollections();
      boolean actualResult =  false;
      for(DisplayCollection displayCollection : patient.getRulesResult().getDisplayCollections()){
        if(displayCollection.getCollectionId() == 19){
            actualResult = true;
            break;
        }
      }
      assertEquals("Positive condition", actualResult, false);
    }
	
	@Test
    public void failureTestAgeBellow18() {
	  System.out.println("Executing PI001_6Test.failureTestAgeBellow18");
      Demographics demographics = new Demographics();
      demographics.setGenderCode("M");
      demographics.setAge(16);
      patient.setDemographics(demographics);
     
      TileResponse tileResponse = new TileResponse();
      tileResponse.setTileResponseId(13);
      

      /*TileResponseEngagement tileResponseEngagement = new TileResponseEngagement();
      tileResponseEngagement.setActionValue("Pregnancy/Portpartum");
      tileResponse.getTileResponseEngagements().add(tileResponseEngagement);*/
    
      RulesResult rulesResult =  new RulesResult();
      patient.setRulesResult(rulesResult);
      patient.getTileResponses().add(tileResponse);
     
      ksession.insert(patient);
      ksession.fireAllRules();
      //List<DisplayCollection> dCollections = patient.getRulesResult().getDisplayCollections();
      boolean actualResult =  false;
      for(DisplayCollection displayCollection : patient.getRulesResult().getDisplayCollections()){
        if(displayCollection.getCollectionId() == 19){
            actualResult = true;
            break;
        }
      }
      assertEquals("Positive condition", actualResult, false);
    }
	
	@Test
    public void failureTestAgeGreaterThan50() {
	  System.out.println("Executing PI001_6Test.failureTestAgeGreaterThan50");
      Demographics demographics = new Demographics();
      demographics.setGenderCode("M");
      demographics.setAge(51);
      patient.setDemographics(demographics);
     
      TileResponse tileResponse = new TileResponse();
      tileResponse.setTileResponseId(12);
      

      TileResponseEngagement tileResponseEngagement = new TileResponseEngagement();
      tileResponseEngagement.setActionValue("Pregnancy/Postpartum");
      tileResponse.getTileResponseEngagements().add(tileResponseEngagement);
    
      RulesResult rulesResult =  new RulesResult();
      patient.setRulesResult(rulesResult);
      
      patient.getTileResponses().add(tileResponse);
     
      ksession.insert(patient);
      ksession.fireAllRules();
      //List<DisplayCollection> dCollections = patient.getRulesResult().getDisplayCollections();
      boolean actualResult =  false;
      
      for(DisplayCollection displayCollection : patient.getRulesResult().getDisplayCollections()){
        if(displayCollection.getCollectionId() == 19){
            actualResult = true;
            break;
        }
      }
      assertEquals("Positive condition", actualResult, false);
    }
	
	public void printMe(Patient patient){
	  if(patient != null){
	      if(patient.getDemographics() != null){
	        System.out.println(patient.getDemographics().getGenderCode()+":"+patient.getDemographics().getAge());
	      }
	      System.out.println("TileResponses...");  
	      for(int i=0;i<patient.getTileResponses().size();i++){
	        TileResponse tileResponse = (TileResponse) patient.getTileResponses().get(i);
	        System.out.println(tileResponse.getTileResponseId());
	        if(tileResponse.getTileResponseEngagements() != null && tileResponse.getTileResponseEngagements().size() > 0){
	          System.out.println("TileResponseEngagements");
	           for(int j=0; j < tileResponse.getTileResponseEngagements().size(); j++){
	             TileResponseEngagement engg = (TileResponseEngagement) tileResponse.getTileResponseEngagements().get(i);
	             System.out.println(engg.getActionName()+":"+engg.getActionValue());
	           }
	        }
	      } 
	  }
	}
}