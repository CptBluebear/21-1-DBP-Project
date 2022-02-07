package org.corodiak.library.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.corodiak.library.model.MediaType;
import org.corodiak.library.model.MultiMedia;

@Mapper
public interface MultiMediaMapper {
	
	ArrayList<MultiMedia> selectMultiMediaList() throws Exception;
	ArrayList<MultiMedia> selectMultiMediaList(RowBounds rowBounds) throws Exception;
	ArrayList<MultiMedia> selectMultiMediaList(@Param("multiMedia")MultiMedia multiMedia) throws Exception;
	ArrayList<MultiMedia> selectMultiMediaList(@Param("multiMedia")MultiMedia multiMedia, RowBounds rowBounds) throws Exception;
	
	int insertMultiMedia(@Param("multiMedia")MultiMedia multiMedia, @Param("mediaType")MediaType mediaType) throws Exception;
	
	int deleteMultiMediaByMaterialId(@Param("materialOid")String oid) throws Exception;
}
