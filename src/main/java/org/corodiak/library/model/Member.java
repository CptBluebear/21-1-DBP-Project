package org.corodiak.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Member {
	private int memberOid;
	private String memberId;
	@JsonIgnore
	private String memberPassword;
	private String memberName;
	private String memberPhoneNumber;
	private String memberAddress;
	
	public Member() {
		// TODO Auto-generated constructor stub
	}
	
	public int getMemberOid() { return memberOid; }
	public String getMemberId() { return memberId; }
	public String getMemberPassword() { return memberPassword; }
	public String getMemberName() { return memberName; }
	public String getMemberPhoneNumber() { return memberPhoneNumber; }
	public String getMemberAddress() { return memberAddress; }
	
	public void setMemberOid(int memberOid) { this.memberOid = memberOid; }
	public void setMemberId(String memberId) { this.memberId = memberId; }
	public void setMemberPassword(String memberPassword) { this.memberPassword = memberPassword; }
	public void setMemberName(String memberName) { this.memberName = memberName; }
	public void setMemberPhoneNumber(String memberPhoneNumber) { this.memberPhoneNumber = memberPhoneNumber; }
	public void setMemberAddress(String memberAddress) { this.memberAddress = memberAddress; }
	
	
}
