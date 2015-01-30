package com.ewangz.fileoperation;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * @date 2014-01-04
 * @author qincd
 * @功能   显示文件类
 */
public class FileShow {
	
	/**
	 * 
	 * @param fileName
	 * @return
	 * 
	 * @功能  显示fileName下的所有文件
	 */
	public static List<String> showAll(String fileName) {
		File file = new File(fileName);
		return Arrays.asList(file.list());			//list()就是实现显示fileName下所有文件的方法
	}
}
