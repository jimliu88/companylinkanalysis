package model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Company {

	private String name;
	private String id;
	private int linkToCompanyCount;
	private int linkFromCompanyCount;
	private HashMap<String, Integer> linkToCompanySet;
	private HashMap<String, Integer> linkFromCompanySet;

	
	// other properities 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getLinkToCompanyCount() {
		return linkToCompanyCount;
	}
	public void setLinkToCompanyCount() {
		int count=0;
		if(linkToCompanySet!=null && !linkToCompanySet.isEmpty()){
			Iterator iterator=linkToCompanySet.entrySet().iterator();			
			while(iterator.hasNext()){
				Entry entry=(Entry)iterator.next();
				count+=(int)entry.getValue();
			}
		}		
		this.linkToCompanyCount = count;
	}
	public int getLinkFromCompanyCount() {
		return linkFromCompanyCount;
	}
	public void setLinkFromCompanyCount() {
		int count=0;
		if(linkFromCompanySet!=null && !linkFromCompanySet.isEmpty()){
			Iterator iterator=linkFromCompanySet.entrySet().iterator();			
			while(iterator.hasNext()){
				Entry entry=(Entry)iterator.next();
				count+=(int)entry.getValue();
			}
		}		
		this.linkFromCompanyCount = count;
	}
	public HashMap<String, Integer> getLinkToCompanySet() {
		return linkToCompanySet;
	}
	public void setLinkToCompanySet(HashMap<String, Integer> linkToCompanySet) {
		this.linkToCompanySet = linkToCompanySet;
	}
	public HashMap<String, Integer> getLinkFromCompanySet() {
		return linkFromCompanySet;
	}
	public void setLinkFromCompanySet(HashMap<String, Integer> linkFromCompanySet) {
		this.linkFromCompanySet = linkFromCompanySet;
	}
	
	
}
