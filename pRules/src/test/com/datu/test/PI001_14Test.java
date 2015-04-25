package com.datu.test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kie.internal.runtime.StatefulKnowledgeSession;

import com.datu.patient.Patient;
import com.datu.patient.TileResponse;
import com.datu.patient.TileResponseEngagement;
import com.datu.result.DisplayTile;

public class PI001_14Test extends RulesBaseTest {
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
	  System.out.println("Executing PI001_14Test.succesTest");
      TileResponseEngagement te1 = new TileResponseEngagement();
      te1.setActionName("AppointmentDate");
      te1.setActionValue("2015/04/10");
      
      TileResponse tr1 = new TileResponse();
      tr1.setTileResponseId(27);
      tr1.getTileResponseEngagements().add(te1);
      
      patient.getTileResponses().add(tr1);
      ksession.insert(patient);
      ksession.fireAllRules();
      
      boolean actualResult =  false;
      for(DisplayTile displayTile : patient.getRulesResult().getDisplayTiles()){
        if(displayTile.getTileRange().equals("81-86")){
            actualResult = true;
            break;
        }
      }
      assertEquals("Positive condition", actualResult, true);
	}
}