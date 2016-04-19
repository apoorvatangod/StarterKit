package com.capgemini.nodes;

/**
 * Created by ldrygala on 2015-02-09.
 */
public class Node {
    private String id;
    private String description;
    private String predecessorId;
    
	public Node(String id, String description, String predecessorId) {
		this.id = id;
		this.description = description;
		this.predecessorId = predecessorId;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPredecessorId() {
		return predecessorId;
	}
	public void setPredecessorId(String predecessorId) {
		this.predecessorId = predecessorId;
	}
    
}
