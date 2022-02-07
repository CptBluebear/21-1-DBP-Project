package org.corodiak.library.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.corodiak.library.mapper.MemberMapper;
import org.corodiak.library.model.Member;
import org.corodiak.library.util.JWTUtil;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {
	
	@Autowired
	MemberMapper memberMapper;
	
	@Transactional
	public boolean register(String id, String password, String name, String phoneNumber, String address)
	{
		try
		{
			Member member = new Member();
			member.setMemberId(id);
			member.setMemberPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
			member.setMemberName(name);
			member.setMemberPhoneNumber(phoneNumber);
			member.setMemberAddress(address);
			
			memberMapper.insertMember(member);
			
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	@Transactional
	public String login(String id, String password)
	{
		try
		{
			Member member = memberMapper.selectMemberById(id);
			if(member == null) return null;
			if(!BCrypt.checkpw(password, member.getMemberPassword())) return null;
			Map<String, Object> payload = new HashMap<String, Object>();
			payload.put("memberOid", member.getMemberOid());
			
			return JWTUtil.createToken(payload);
		}
		catch(Exception e)
		{
			return  null;
		}
	}
	
	@Transactional
	public boolean checkAdmin(int memberOid) throws Exception
	{
		if(memberMapper.selectAdminByOid(memberOid) < 1) return false;
		else return true;
			
	}
	
	public ArrayList<Member> readMemberList() throws Exception
	{
		return memberMapper.selectMemberList();
	}
	
	@Transactional
	public boolean removeMember(int memberOid) throws Exception
	{
		int result = memberMapper.deleteMemberByOid(memberOid);
		if(result > 0) return true;
		return false;
	}
	
	public boolean modifyMember(int memberOid, String memberPassword, String memberPhoneNumber, String memberAddress) throws Exception
	{
		Member member = new Member();
		member.setMemberOid(memberOid);
		member.setMemberPassword(BCrypt.hashpw(memberPassword, BCrypt.gensalt()));
		member.setMemberPhoneNumber(memberPhoneNumber);
		member.setMemberAddress(memberAddress);
		
		int result = memberMapper.updateMemberByOid(member);
		if(result > 0) return true;
		else return false;
	}
	
	public Map<String, Object> loginCheck(String token)
	{
		return JWTUtil.validateToken(token);
	}
	
	
}
