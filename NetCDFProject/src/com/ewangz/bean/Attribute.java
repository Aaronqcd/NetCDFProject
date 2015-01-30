package com.ewangz.bean;

/**
 * @date 2015-01-16
 * @author qincd
 * @功能   变量的属性类
 * 
 */
public class Attribute {
	private String variable;					//变量
	private String dataType;					//数据类型
	private String description;					//描述
	private String dimensions;					//维度
	private String group;						//变量逻辑上的集合
	private String name;						//名称
	private String shape;						//种类
	private String units;						//单位
	public String getVariable() {
		return variable;
	}
	public void setVariable(String variable) {
		this.variable = variable;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDimensions() {
		return dimensions;
	}
	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShape() {
		return shape;
	}
	public void setShape(String shape) {
		this.shape = shape;
	}
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}
	
	
}
