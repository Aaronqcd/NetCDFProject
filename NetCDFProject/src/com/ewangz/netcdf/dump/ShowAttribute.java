package com.ewangz.netcdf.dump;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ucar.nc2.NetcdfFile;

import com.ewangz.bean.Attribute;
import com.ewangz.bean.Variable;

/**
 * @date 2015-01-16
 * @author qincd
 * @功能   显示变量的属性类
 */
public class ShowAttribute {
	private static List<Variable> varList = null;
	private static List<Attribute> attList = new ArrayList<Attribute>(); 
	
	/**
	 * 
	 * @param path
	 * @return
	 * 
	 * @功能  显示变量的属性
	 */
	public static List<Attribute> show(String path) {
		NetcdfFile ncFile = null;
		try {
			//以只读的形式打开一个文件,第二个参数表示是否允许取消任务
			ncFile = NetcdfFile.open(path, null);
			varList = ShowAll.findVariable(path);
			//将attList清空
			attList.clear();
			for (Variable variable : varList) {
				Attribute att = new Attribute();
				ucar.nc2.Variable var = ncFile.findVariable(variable.toString());
				if(var == null) {
					System.out.println("can't find " + variable.toString());
					continue;
				}
				att.setVariable(variable.toString());
				att.setDataType(var.getDataType().toString());
				att.setDescription(var.getDescription());
				att.setDimensions(var.getDimensionsString());
				att.setGroup(var.getGroup().toString());
				att.setName(var.getShortName());
				//将shape组成String类型,以逗号分隔
				String shape = "";
				int[] num = var.getShape();
				for (int i=0; i<num.length; i++) {
					if(i == var.getShape().length-1) {
						shape = shape + "" + num[i];
					}
					else {
						shape = shape + "" + num[i] + ",";
					}
				}
				att.setShape(shape);
				att.setUnits(var.getUnitsString());
				attList.add(att);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return attList;
	}
}
