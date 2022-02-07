package org.corodiak.library.mapper;

import java.sql.Date;
import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.corodiak.library.model.Material;

@Mapper
public interface MaterialMapper {
	ArrayList<Material> selectMaterialList() throws Exception;
	ArrayList<Material> selectMaterialList(RowBounds rowBounds) throws Exception;
	ArrayList<Material> selectMaterialList(@Param("material")Material material) throws Exception;
	ArrayList<Material> selectMaterialList(@Param("material")Material material, RowBounds rowBounds) throws Exception;
	
	Material selectMaterialByOid(@Param("oid")String oid) throws Exception;
	
	int insertMaterial(@Param("material")Material material) throws Exception;
	
	int deleteMaterialByOid(@Param("oid")String oid) throws Exception;
	
	int updateMaterialLoanableByOid(@Param("oid")String oid, @Param("loanable")int loanable) throws Exception;
}
