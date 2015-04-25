package com.datu.patient;

import java.util.ArrayList;
import java.util.List;

import com.datu.result.RulesResult;

/**
 * This class was automatically generated by the data modeler tool.
 */

public class Patient implements java.io.Serializable {

  static final long serialVersionUID = 1L;

  @org.kie.api.definition.type.Label("patientId")
  private int patientId;
  @org.kie.api.definition.type.Label("demographics")
  private Demographics demographics;
  @org.kie.api.definition.type.Label("tileResponses")
  private List<TileResponse> tileResponses = new ArrayList<TileResponse>();
  @org.kie.api.definition.type.Label("collectionResponses")
  private List<CollectionResponse> collectionResponses = new ArrayList<CollectionResponse>();

  private RulesResult rulesResult = new RulesResult(); // C

  private List<ProgramEnrollment> programEnrollments = new ArrayList<ProgramEnrollment>();

  public Patient() {
  }

  public int getPatientId() {
    return this.patientId;
  }

  public void setPatientId(int patientId) {
    this.patientId = patientId;
  }

  public Demographics getDemographics() {
    return this.demographics;
  }

  public void setDemographics(Demographics demographics) {
    this.demographics = demographics;
  }

  public List<TileResponse> getTileResponses() {
    return this.tileResponses;
  }

  public void setTileResponses(List<TileResponse> tileResponses) {
    this.tileResponses = tileResponses;
  }

  public List<CollectionResponse> getCollectionResponses() {
    return this.collectionResponses;
  }

  public void setCollectionResponses(List<CollectionResponse> collectionResponses) {
    this.collectionResponses = collectionResponses;
  }

  public com.datu.result.RulesResult getRulesResult() {
    return this.rulesResult;
  }

  public void setRulesResult(com.datu.result.RulesResult rulesResult) {
    this.rulesResult = rulesResult;
  }

  public List<ProgramEnrollment> getProgramEnrollments() {
    return this.programEnrollments;
  }

  public void setProgramEnrollments(List<ProgramEnrollment> programEnrollments) {
    this.programEnrollments = programEnrollments;
  }

  public Patient(int patientId, Demographics demographics,
      List<TileResponse> tileResponses, List<CollectionResponse> collectionResponses,
      com.datu.result.RulesResult rulesResult, List<ProgramEnrollment> programEnrollments) {
    this.patientId = patientId;
    this.demographics = demographics;
    this.tileResponses = tileResponses;
    this.collectionResponses = collectionResponses;
    this.rulesResult = rulesResult;
    this.programEnrollments = programEnrollments;
  }
}