package com.zpj.materials.entity;

import com.zpj.common.UUIDGenerator;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * @Description: 采购登记表
 * @ClassName: Purchase
 * @author zpj 
 * @date 2016-4-9 下午3:27:59
 *
 */
@Entity
@Table(name = "jl_material_purchase_info")
public class Purchase implements java.io.Serializable {
	private String id= UUIDGenerator.generatePk("CGD");//主键  生成规则CGD+时间戳
	private int departmentid;//采购部门id
	private double totalprice;//合计采购金额
	private String chargename;//采购人
	private int loginid;//登记人id
	private String state;//0进货，1出货
	private Date createtime;//创建时间采购时间
	private String name;//采购单名
	private List<PurchaseDetail> details;//详细信息
	@Id
	@Column(name = "id")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "departmentid",   precision = 22, scale = 0)
	public int getDepartmentid() {
		return departmentid;
	}
	public void setDepartmentid(int departmentid) {
		this.departmentid = departmentid;
	}
	
	
	@Column(name = "totalprice", precision=12 ,scale=2)
	public double getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}
	@Column(name = "chargename", length = 100)
	public String getChargename() {
		return chargename;
	}
	public void setChargename(String chargename) {
		this.chargename = chargename;
	}
	@Column(name = "loginid",   precision = 22, scale = 0)
	public int getLoginid() {
		return loginid;
	}
	public void setLoginid(int loginid) {
		this.loginid = loginid;
	}

	@Column(name = "state", length = 100)
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createtime", length=7)
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Transient
	public List<PurchaseDetail> getDetails() {
		return details;
	}

	public void setDetails(List<PurchaseDetail> details) {
		this.details = details;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder=new StringBuilder(200);
		stringBuilder.append("name:"+this.getName())
				.append(",state:"+this.getState())
				.append(",totalprice:"+this.getTotalprice());
		return stringBuilder.toString();
	}
}
