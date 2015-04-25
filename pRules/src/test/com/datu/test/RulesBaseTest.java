package com.datu.test;

import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;

import com.datu.patient.Patient;
import com.datu.patient.TileResponse;
import com.datu.patient.TileResponseEngagement;

public abstract class RulesBaseTest {

  protected StatefulKnowledgeSession initializeKnowledgeSession() {
    KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
    kbuilder.add(ResourceFactory.newClassPathResource("rules/PI01.drl", PI001_1Test.class), ResourceType.DRL);
    KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
    kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
    StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
    return ksession;
  }
  
  protected void printMe(Patient patient){
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
