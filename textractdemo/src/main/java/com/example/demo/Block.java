package com.example.demo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
public class Block{
    @JsonProperty("BlockType") 
    public String blockType;
    @JsonProperty("Geometry") 
    public Geometry geometry;
    @JsonProperty("Id") 
    public String id;
    @JsonProperty("Relationships") 
    public List<Relationship> relationships;
    @JsonProperty("Page") 
    public int page;
    @JsonProperty("Confidence") 
    public double confidence;
    @JsonProperty("Text") 
    public String text;
    @JsonProperty("TextType") 
    public String textType;

    @JsonProperty("RowIndex") 
    public long rowIndex;

    @JsonProperty("ColumnIndex") 
    public long columnIndex;
    

    @JsonProperty("RowSpan") 
    public long rowSpan;

    @JsonProperty("ColumnSpan") 
    public long columnSpan;
    
    @JsonProperty("EntityTypes") 
    public List<String> entityTypes;
    
    
	public String getBlockType() {
		return blockType;
	}
	public void setBlockType(String blockType) {
		this.blockType = blockType;
	}
	public Geometry getGeometry() {
		return geometry;
	}
	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<Relationship> getRelationships() {
		return relationships;
	}
	public void setRelationships(List<Relationship> relationships) {
		this.relationships = relationships;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public double getConfidence() {
		return confidence;
	}
	public void setConfidence(double confidence) {
		this.confidence = confidence;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTextType() {
		return textType;
	}
	public void setTextType(String textType) {
		this.textType = textType;
	}
	public long getRowIndex() {
		return rowIndex;
	}
	public void setRowIndex(long rowIndex) {
		this.rowIndex = rowIndex;
	}
	public long getColumnIndex() {
		return columnIndex;
	}
	public void setColumnIndex(long columnIndex) {
		this.columnIndex = columnIndex;
	}
	public long getRowSpan() {
		return rowSpan;
	}
	public void setRowSpan(long rowSpan) {
		this.rowSpan = rowSpan;
	}
	public long getColumnSpan() {
		return columnSpan;
	}
	public void setColumnSpan(long columnSpan) {
		this.columnSpan = columnSpan;
	}
	public List<String> getEntityTypes() {
		return entityTypes;
	}
	public void setEntityTypes(List<String> entityTypes) {
		this.entityTypes = entityTypes;
	}
    
    
}
