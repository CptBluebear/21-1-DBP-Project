package org.corodiak.library.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import org.corodiak.library.mapper.ReservationMapper;
import org.corodiak.library.model.Material;
import org.corodiak.library.model.MaterialImpl;
import org.corodiak.library.model.Member;
import org.corodiak.library.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
	
	@Autowired
	ReservationMapper reservationMapper;
	
	public ArrayList<Reservation> readReservationList(String materialOid) throws Exception
	{
		return reservationMapper.selectReservationByMaterialOid(materialOid);
	}
	
	public boolean removeReservation(int oid) throws Exception
	{
		int result = reservationMapper.deleteReservationByOid(oid);
		if(result > 0) return true;
		else return false;
	}
	
	public boolean addReservation(String materialOid, int memberOid) throws Exception
	{
		Reservation reservation = new Reservation();
		Material material = new MaterialImpl();
		Member member = new Member();
		material.setMaterialOid(materialOid);
		member.setMemberOid(memberOid);
		reservation.setReservationMaterial(material);
		reservation.setReservationMember(member);
		reservation.setReservationDate(Date.valueOf(LocalDate.now()));
		
		int result = reservationMapper.insertReservation(reservation, reservation.getReservationMaterial(), reservation.getReservationMember());
		if(result > 0) return true;
		else return false;
	}
}
