package org.corodiak.library.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.corodiak.library.model.Member;

@Mapper
public interface MemberMapper {
	
	ArrayList<Member> selectMemberList() throws Exception;
	ArrayList<Member> selectMemberList(RowBounds rowBounds) throws Exception;
	
	Member selectMemberById(@Param("id")String id) throws Exception;
	
	int insertMember(@Param("member")Member member) throws Exception;
	
	int deleteMemberByOid(@Param("oid")int oid) throws Exception;

	int selectAdminByOid(@Param("oid")int oid) throws Exception;
	
	int updateMemberByOid(@Param("member")Member member) throws Exception;
}
