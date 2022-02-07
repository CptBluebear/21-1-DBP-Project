package org.corodiak.library.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.corodiak.library.model.MediaType;

@Mapper
public interface MediaTypeMapper {
	
	ArrayList<MediaType> selectMediaTypeList() throws Exception;
	int insertMediaType(@Param("mediaType")MediaType mediaType) throws Exception;
	
}
