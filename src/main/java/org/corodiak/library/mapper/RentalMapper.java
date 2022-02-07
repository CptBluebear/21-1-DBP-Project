package org.corodiak.library.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.corodiak.library.model.Material;
import org.corodiak.library.model.Member;
import org.corodiak.library.model.Rental;

@Mapper
public interface RentalMapper {
	
	ArrayList<Rental> selectRentalList() throws Exception;
	ArrayList<Rental> selectRentalList(RowBounds rowBounds) throws Exception;
	
	ArrayList<Rental> selectRentalListByMemberId(@Param("memberOid")int memberOid) throws Exception;
	
	int insertRental(@Param("rental")Rental rental, @Param("material")Material material, @Param("member")Member member) throws Exception;
	int updateRentalIsReturnByMaterialOid(@Param("material")Material material, @Param("isReturn")int isReturn) throws Exception;

}
