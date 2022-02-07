package org.corodiak.library.model;

import java.sql.Date;

public interface Material {
	
	public String getMaterialOid();
	public String getMaterialName();
	public Date getMaterialRegisterDate();
	public int getMaterialPrice();
	public int getMaterialLoanable();
	
	public void setMaterialOid(String materialOid);
	public void setMaterialName(String materialName);
	public void setMaterialRegisterDate(Date materialRegisterDate);
	public void setMaterialPrice(int materialPrice);
	public void setMaterialLoanable(int materialLoanable);
}
