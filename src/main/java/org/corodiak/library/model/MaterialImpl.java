package org.corodiak.library.model;

import java.sql.Date;

public class MaterialImpl implements Material {

	protected String materialOid;
	protected String materialName;
	protected Date materialRegisterDate;
	protected int materialPrice;
	protected int materialLoanable;
	
	public MaterialImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String getMaterialOid() { return materialOid; }
	@Override
	public String getMaterialName() { return materialName; }
	@Override
	public Date getMaterialRegisterDate() { return materialRegisterDate; }
	@Override
	public int getMaterialPrice() { return materialPrice; }
	@Override
	public int getMaterialLoanable() { return materialLoanable; }
	
	@Override
	public void setMaterialOid(String materialOid) { this.materialOid = materialOid; }
	@Override
	public void setMaterialName(String materialName) { this.materialName = materialName; }
	@Override
	public void setMaterialRegisterDate(Date materialRegisterDate) { this.materialRegisterDate = materialRegisterDate; }
	@Override
	public void setMaterialPrice(int materialPrice) { this.materialPrice = materialPrice; }
	@Override
	public void setMaterialLoanable(int materialLoanable) { this.materialLoanable = materialLoanable; }
	
	
}
