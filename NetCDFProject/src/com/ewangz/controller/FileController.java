package com.ewangz.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ewangz.bean.Attribute;
import com.ewangz.bean.Dimension;
import com.ewangz.bean.Variable;
import com.ewangz.fileoperation.FileShow;
import com.ewangz.netcdf.dump.ShowAll;
import com.ewangz.netcdf.dump.ShowAttribute;
import com.ewangz.netcdf.dump.ShowVariable;

/**
 * @date 2015-01-04
 * @author qincd
 * @功能      处理文件的controller类
 */

@Controller
@RequestMapping("/")
public class FileController {
	
	@RequestMapping("show.do")
	@ResponseBody
	public void showAll(HttpServletRequest request, HttpServletResponse response, 
			ModelMap model) {
		/*request.getSession().getServletContext().getRealPath("/")等于
		D:\apache-tomcat-7.0.52\webapps\NetCDFProject\*/
		String fileName = request.getSession().getServletContext().getRealPath("/")+"nc";
		List<String> fileList = FileShow.showAll(fileName);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("fileList", fileList);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(jsonObject);
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.write(jsonArray.toString());
		//model.addAttribute("fileList", fileList);
		//return "total";
	}

	/**
	 * 
	 * @param fileName
	 * @param request
	 * @param response
	 * @param model
	 * 
	 * @功能  将list以json格式传到jsp页面
	 */
	@RequestMapping("showAll.do")
	@ResponseBody 				//返回文本到jsp页面,而非返回某个页面
	public void showAll(@RequestParam String fileName, HttpServletRequest request, 
			HttpServletResponse response, ModelMap model) {
		String path = request.getSession().getServletContext().getRealPath("/")+"nc"+File.separator+fileName;
		List<Dimension> dimList = ShowAll.findDimension(path);
		List<Variable> varList = ShowAll.findVariable(path);
		List<Attribute> attList = ShowAttribute.show(path);
		for (Attribute attribute : attList) {
			System.out.println(attribute.getDataType()+","+attribute.getDimensions()+","+attribute.getName()
					+","+attribute.getShape());
		}
		//JSONObject是一个无序的键值对集合
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("dimList", dimList);
		jsonObject.put("varList", varList);
		jsonObject.put("attList", attList);
		//JSONArray是一个按顺序排列好的数组集合
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(jsonObject);
		//PrintWriter向文本输出流打印格式化的对象
		PrintWriter out = null;
		try {
			//response.getWriter()返回一个能发送字符信息的PrintWriter对象
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(jsonArray.toString());
		//向PrintWriter对象中写入字符串
		out.write(jsonArray.toString());
	}
	
	public String showVariable(@RequestParam String fileName, HttpServletRequest request, 
			HttpServletResponse response) {
		String path = request.getSession().getServletContext().getRealPath("/")+"nc"+File.separator+fileName;
		StringBuffer strBuff = ShowVariable.show(path);			//返回nc文件格式化后的信息
		return strBuff.toString();
	}
	
	@RequestMapping("showAttribute.do")
	@ResponseBody 				//返回文本到jsp页面,而非返回某个页面
	public void showAttribute(@RequestParam String fileName, HttpServletRequest request, 
			HttpServletResponse response) {
		System.out.println("showAttribute");
		String path = request.getSession().getServletContext().getRealPath("/")+"nc"+File.separator+fileName;
		
	}
}
