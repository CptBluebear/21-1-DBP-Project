package org.corodiak.library.model;

import java.sql.Date;

public class Reservation {
	private int reservationOid;
	private Material reservationMaterial;
	private Member reservationMember;
	private Date reservationDate;
	
	public Reservation() {
		// TODO Auto-generated constructor stub
	}
	
	public int getReservationOid() { return reservationOid; }
	public Material getReservationMaterial() { return reservationMaterial; }
	public Member getReservationMember() { return reservationMember; }
	public Date getReservationDate() { return reservationDate; }
	
	public void setReservationOid(int reservationOid) { this.reservationOid = reservationOid; }
	public void setReservationMaterial(Material reservationMaterial) { this.reservationMaterial = reservationMaterial; }
	public void setReservationMember(Member reservationMember) { this.reservationMember = reservationMember; }
	public void setReservationDate(Date reservationDate) { this.reservationDate = reservationDate; }
}
