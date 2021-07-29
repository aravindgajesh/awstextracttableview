package com.example.demo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Root{
    @JsonProperty("DocumentMetadata") 
    public DocumentMetadata documentMetadata;
    @JsonProperty("JobStatus") 
    public String jobStatus;
    @JsonProperty("Blocks") 
    public List<Block> blocks;
    @JsonProperty("AnalyzeDocumentModelVersion") 
    public String analyzeDocumentModelVersion;
	public DocumentMetadata getDocumentMetadata() {
		return documentMetadata;
	}
	public void setDocumentMetadata(DocumentMetadata documentMetadata) {
		this.documentMetadata = documentMetadata;
	}
	public String getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}
	public List<Block> getBlocks() {
		return blocks;
	}
	public void setBlocks(List<Block> blocks) {
		this.blocks = blocks;
	}
	public String getAnalyzeDocumentModelVersion() {
		return analyzeDocumentModelVersion;
	}
	public void setAnalyzeDocumentModelVersion(String analyzeDocumentModelVersion) {
		this.analyzeDocumentModelVersion = analyzeDocumentModelVersion;
	}
    
    
}
