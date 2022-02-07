package org.corodiak.library.model;

import java.sql.Date;

public class Rental {
	private int rentalOid;
	private Material rentalMaterial;
	private Member rentalMember;
	private Date rentalDate;
	private Date returnDate;
	private int rentalIsReturn;
	
	public Rental() {
		// TODO Auto-generated constructor stub
	}
	
	public int getRentalOid() { return rentalOid; }
	public Material getRentalMaterial() { return rentalMaterial; }
	public Member getRentalMember() { return rentalMember; }
	public Date getRentalDate() { return rentalDate; }
	public Date getReturnDate() { return returnDate; }
	public int getRentalIsReturn() { return rentalIsReturn; }
	
	public void setRentalOid(int rentalOid) { this.rentalOid = rentalOid; }
	public void setRentalMaterial(Material rentalMaterial) { this.rentalMaterial = rentalMaterial; }
	public void setRentalMember(Member rentalMember) { this.rentalMember = rentalMember; }
	public void setRentalDate(Date rentalDate) { this.rentalDate = rentalDate; }
	public void setReturnDate(Date returnDate) { this.returnDate = returnDate; }
	public void setRentalIsReturn(int rentalIsReturn) { this.rentalIsReturn = rentalIsReturn; }
}
