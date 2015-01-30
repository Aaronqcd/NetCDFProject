package com.ewangz.netcdf.dump;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ewangz.bean.Dimension;
import com.ewangz.bean.Variable;

import ucar.nc2.NetcdfFile;

/**
 * @date 2015-01-06
 * @author qincd
 * @功能   显示nc文件中的dimension和variable
 */
public class ShowAll {
	
	/**
	 * 
	 * @param path
	 * @return
	 * 
	 * @功能  查询nc文件里面的维度,并返回维度的信息
	 */
	public static List<Dimension> findDimension(String path) {
		NetcdfFile netcdfFile = null;
		List<Dimension> dimension = new ArrayList<Dimension>();
		try {
			netcdfFile = NetcdfFile.open(path, null);
			if(netcdfFile == null) {
				System.out.println("can't find file");
				return null;
			}
			//利用循环将维度信息添加到自定义的Dimension类中
			for (ucar.nc2.Dimension dimension2 : netcdfFile.getDimensions()) {
				Dimension dim = new Dimension();
				dim.setShortName(dimension2.getShortName());
				dimension.add(dim);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(netcdfFile != null) {
				try {
					netcdfFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return dimension;
	}
	
	/**
	 * 
	 * @param path
	 * @return
	 * 
	 * @功能  查询nc文件里面的变量,并返回变量的信息
	 */
	public static List<Variable> findVariable(String path) {
		NetcdfFile netcdfFile = null;
		List<Variable> variable = new ArrayList<Variable>();
		try {
			netcdfFile = NetcdfFile.open(path, null);
			if(netcdfFile == null) {
				System.out.println("can't find file");
				return null;
			}
			//利用循环将变量信息添加到自定义的Variable类中
			for (ucar.nc2.Variable variable2 : netcdfFile.getVariables()) {
				Variable vari = new Variable();
				vari.setShortName(variable2.getShortName());
				variable.add(vari);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(netcdfFile != null) {
				try {
					netcdfFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return variable;
	}
	
	public static void main(String[] args) {
		ShowAll.findDimension("nc/simple_xy.nc");
		ShowAll.findVariable("nc/simple_xy.nc");
	}
}
