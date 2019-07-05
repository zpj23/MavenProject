package com.zpj.materials.entity;

import javax.persistence.*;


/**
 * @Description: 采购单详细关联表
 * @ClassName: PurchaseDetail
 * @author zpj 
 * @date 2016-5-18 上午8:46:18
 *
 */
@Entity
@Table(name = "jl_material_purchase_detail_info")
public class PurchaseDetail implements java.io.Serializable {
	private int id;//主键
	private String goodsid;//物资id
	private String goodsName;//商品名称
	private double goodsprice;//物资单价
	private String supplierid;//供应商id;
	private double num=0;//采购数量
	private String purchaseid;//关联采购单主键id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name = "id",  nullable = false, precision = 22, scale = 0)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "goodsid",  nullable = false, precision = 22, scale = 0)
	public String getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}
	@Column(name = "goodsprice", precision=12 ,scale=2)
	public double getGoodsprice() {
		return goodsprice;
	}
	public void setGoodsprice(double goodsprice) {
		this.goodsprice = goodsprice;
	}
	
	@Column(name = "supplierid",   precision = 22, scale = 0)
	public String getSupplierid() {
		return supplierid;
	}
	public void setSupplierid(String supplierid) {
		this.supplierid = supplierid;
	}
	@Column(name = "num", precision=12 ,scale=2)
	public double getNum() {
		return num;
	}
	public void setNum(double num) {
		this.num = num;
	}
	
	@Column(name = "purchaseid", length = 50)
	public String getPurchaseid() {
		return purchaseid;
	}
	public void setPurchaseid(String purchaseid) {
		this.purchaseid = purchaseid;
	}

	@Transient
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder=new StringBuilder(200);
		stringBuilder.append("goodsid:"+this.getGoodsid())
				.append(",goodsprice:"+this.getGoodsprice())
				.append(",num:"+this.getNum()).append(",purchaseid:"+this.getPurchaseid());
		return stringBuilder.toString();
	}
}
