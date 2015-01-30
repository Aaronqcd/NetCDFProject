package com.ewangz.netcdf.dump;

import java.io.IOException;

import ucar.ma2.ArrayInt;
import ucar.ma2.InvalidRangeException;
import ucar.nc2.NetcdfFile;
import ucar.nc2.Variable;

/**
 * @date 2015-01-05
 * @author qincd
 * @功能     显示nc文件的数据
 */
public class ShowVariable {
	
	/**
	 * 
	 * @param fileName
	 * @return
	 * 
	 * @功能  显示nc文件的数据,并返回一个StringBuffer对象  
	 */
	public static StringBuffer show(String fileName) {
		//以只读的形式打开nc类型的文件
		NetcdfFile dataFile = null;
		StringBuffer strBuff = new StringBuffer();
		try {
			//打开文件
			dataFile = NetcdfFile.open(fileName, null);
			//以data指代取得的变量
			Variable dataVar = dataFile.findVariable("data");
			if (dataVar == null) {
				System.out.println("can't find variable data");
				return null;
			}
			//得到该nc文件的维度,例：二维时shape.length=2,shape[0]=6(x的长度)
			int[] shape = dataVar.getShape();
			//dim=shape
			int[] dim = new int[shape.length];
			for(int i=0; i<shape.length; i++) {
				dim[i] = shape[i];
			}
			int[] dataOut1;
			int[][] dataOut2;
			int[][][] dataOut3;
			ArrayInt.D2 dataArray;
			if(shape.length == 1) {
				dataOut1 = new int[dim[0]];
			}
			//判断该文件数据是否为二维
			else if(shape.length == 2) {
				dataOut2 = new int[dim[0]][dim[1]];
				int[] origin = new int[2];
				dataArray = (ArrayInt.D2) dataVar.read(origin, dim);
				strBuff.append("int data(");
				//dataVar.getDimensionsString()输出的数据为x y,所以需要用" "来提取出x和y
				String[] dimStr = dataVar.getDimensionsString().split(" ");
				for(int i=0; i<dimStr.length; i++) {
					if(i==dimStr.length-1) {
						strBuff.append(dimStr[i]+"="+dim[1]);
					}
					else {
						strBuff.append(dimStr[i]+"="+dim[0]+",&nbsp;");
					}
				}
				strBuff.append(");<br>data:<br>{<br>");
				for (int i = 0; i < dim[0]; i++) {
					for (int j = 0; j < dim[1]; j++) {
						dataOut2[i][j] = dataArray.get(i, j);
						if(j == dim[1]-1) {
							strBuff.append(dataOut2[i][j]+"}");
						}
						else if(j == 0) {
							strBuff.append("&nbsp;&nbsp;{"+dataOut2[i][j]+",&nbsp;");
						}
						else {
							strBuff.append(dataOut2[i][j]+",&nbsp;");
						}
					}
					if(i==dim[0]-1) {
						strBuff.append("<br>");
					}
					else {
						strBuff.append(",<br>");
					}
				}
				strBuff.append("}");
			}
			else if(shape.length == 3) {
				dataOut3 = new int[dim[0]][dim[1]][dim[2]];
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidRangeException e) {
			e.printStackTrace();
		} finally {
			if(dataFile != null) {
				try {
					dataFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return strBuff;
	}
	
	public static void main(String[] args) {
		StringBuffer strBuff = ShowVariable.show("nc/simple_xy.nc");
		System.out.println(strBuff.toString());
	}
}
