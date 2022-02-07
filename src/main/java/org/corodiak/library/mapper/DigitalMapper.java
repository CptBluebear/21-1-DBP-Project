package org.corodiak.library.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.corodiak.library.model.Digital;

@Mapper
public interface DigitalMapper {
	
	ArrayList<Digital> selectDigitalList() throws Exception;
	ArrayList<Digital> selectDigitalList(RowBounds rowBounds) throws Exception;
	ArrayList<Digital> selectDigitalList(@Param("digital")Digital digital) throws Exception;
	ArrayList<Digital> selectDigitalList(@Param("digital")Digital digital, RowBounds rowBounds) throws Exception;
	
	int insertDigital(@Param("digital")Digital digital) throws Exception;
	
	int deleteDigitalByMaterialId(@Param("materialOid")String oid) throws Exception;
	
}
