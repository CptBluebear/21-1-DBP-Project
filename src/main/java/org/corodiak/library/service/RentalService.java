package org.corodiak.library.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.corodiak.library.mapper.MaterialMapper;
import org.corodiak.library.mapper.RentalMapper;
import org.corodiak.library.model.Material;
import org.corodiak.library.model.MaterialImpl;
import org.corodiak.library.model.Member;
import org.corodiak.library.model.Rental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;

@Service
public class RentalService {
	
	@Autowired
	RentalMapper rentalMapper;
	
	@Autowired
	MaterialMapper materialMapper;
	
	@Transactional
	public boolean rentalMaterial(String materialOid, int memberOid) throws Exception
	{
		Material material = materialMapper.selectMaterialByOid(materialOid);
		if(material == null) return false;
		if(material.getMaterialLoanable() != 2) return false;
		
		Rental rental = new Rental();
		rental.setRentalMaterial(material);
		Member member = new Member();
		member.setMemberOid(memberOid);
		rental.setRentalMember(member);
		rental.setRentalDate(Date.valueOf(LocalDate.now()));
		rental.setReturnDate(Date.valueOf(LocalDate.now().plusDays(14)));
		rental.setRentalIsReturn(0);
		int result = rentalMapper.insertRental(rental, rental.getRentalMaterial(), rental.getRentalMember());
		if(result <= 0) return false;
		materialMapper.updateMaterialLoanableByOid(materialOid, 1);
			
		return true;
	}
	
	@Transactional
	public boolean returnMaterial(String materialOid) throws Exception
	{
		Material material = materialMapper.selectMaterialByOid(materialOid);
		if(material == null) return false;
		if(material.getMaterialLoanable() != 1) return false;
		
		rentalMapper.updateRentalIsReturnByMaterialOid(material, 1);
		materialMapper.updateMaterialLoanableByOid(materialOid, 2);
		
		return true;
	}
	
	public ArrayList<Rental> rentalListByMemberOid(int memberOid) throws Exception
	{
		return rentalMapper.selectRentalListByMemberId(memberOid);
	}
	
}
