package com.capgemini.nodes;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ldrygala on 2015-02-09.
 * <p/>
 * Write validate for
 * <ul>
 *     <li>node id should have 4 characters</li>
 *     <li>node description can have maximal 128 characters</li>
 *     <li>no cycle</li>
 *     <li>only penultimate can have two subsequent</li>
 * </ul>
 */
public class NodeValidators {
    public static void validateMethod(List<Node> nodes) {
    	boolean firstElementFound = false;
    	List<Node> lastTwoNodes = new ArrayList<Node>();
    	for(int i = 0; i < nodes.size(); i++){
    		ValidateChars(nodes.get(i));
    		if(nodes.get(i).getPredecessorId() == null){
    			if(firstElementFound == true){
    				System.out.println("More than one initial node.");
    			}
    			else{
    				firstElementFound = true;
    			}
    		}
    		
    		for(int j = 0; j < nodes.size(); j++){
    			if(j != i){
    				if(nodes.get(i).getPredecessorId() == nodes.get(j).getPredecessorId()){
    					if(lastTwoNodes != null){
	    					if(!lastTwoNodes.contains(nodes.get(i))){
	    						lastTwoNodes.add(nodes.get(i));
	    					}
	    					if(!lastTwoNodes.contains(nodes.get(j))){
	    						lastTwoNodes.add(nodes.get(j));
	    					}
    					}
    				}
    			}
    		}
    	}
    	if (firstElementFound == false){
    		System.out.println("No initial node found.");
    	}
    	
    	if(lastTwoNodes.size() > 2){
    		System.out.println("Cycle found.");
    	}
    	
    	if(lastTwoNodes != null){
	    	for(int i = 0; i < lastTwoNodes.size(); i++){
	    		for(int j = 0; j < nodes.size(); j++){
	    			if(nodes.get(j).getPredecessorId() == lastTwoNodes.get(i).getId()){
	    				System.out.println("Node with two subsequent nodes is not penultimate.");
	    			}
	    		}
	    	}
    	}
    }
    private static void ValidateChars (Node node){
    	if (node.getId().length() != 4){
    		System.out.println("Node numer " + node.getId() + " has invalid id length error.");
    	}
    	if (node.getDescription().length() > 128){
    		System.out.println("Node numer " + node.getId() + " has invalid description length error.");
    	}
    }
}
