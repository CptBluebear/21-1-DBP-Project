package org.corodiak.library.model;

public class MultiMedia extends MaterialImpl {
	private int multiMediaOid;
	private MediaType multiMediaType;
	
	public MultiMedia() {
		// TODO Auto-generated constructor stub
	}
	
	public int getMultiMediaOid() { return multiMediaOid; }
	public MediaType getMultiMediaType() { return multiMediaType; }
	
	public void setMultiMediaOid(int multiMediaOid) { this.multiMediaOid = multiMediaOid; }
	public void setMultiMediaType(MediaType multiMediaType) { this.multiMediaType = multiMediaType; }
}
