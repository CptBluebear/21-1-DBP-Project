package org.corodiak.library.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.corodiak.library.model.Material;
import org.corodiak.library.model.Member;
import org.corodiak.library.model.Reservation;

@Mapper
public interface ReservationMapper {
	
	ArrayList<Reservation> selectReservationList() throws Exception;
	ArrayList<Reservation> selectReservationList(RowBounds rowBounds) throws Exception;
	
	ArrayList<Reservation> selectReservationByMaterialOid(@Param("materialOid")String oid) throws Exception;
	
	int insertReservation(@Param("reservation")Reservation reservation, @Param("material")Material material, @Param("member")Member member) throws Exception;
	int deleteReservationByOid(@Param("oid")int oid) throws Exception;
}
