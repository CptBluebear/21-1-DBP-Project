package org.corodiak.library.model;

public class Digital extends MaterialImpl {
	private int digitalOid;
	private String digitalFilePath;
	
	public Digital() {
	}
	
	public int getDigitalOid() { return digitalOid; }
	public String getDigitalFilePath() { return digitalFilePath; }
	
	public void setDigitalOid(int digitalOid) { this.digitalOid = digitalOid; }
	public void setDigitalFilePath(String digitalFilePath) { this.digitalFilePath = digitalFilePath; }
}
