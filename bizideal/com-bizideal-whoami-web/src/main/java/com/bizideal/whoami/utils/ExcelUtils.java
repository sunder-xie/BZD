package com.bizideal.whoami.utils;

import java.beans.PropertyDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.poi.hslf.model.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils<T> {

	List<T> list = new ArrayList<T>();

	/**
	 * 读取Excel2003-2007转换为实体Bean
	 * 
	 * @param inputStream
	 * @param tClass
	 *            实体类
	 * @param headerMapper
	 * @return
	 * @throws Exception
	 */
	public List<T> readBean(InputStream inputStream, Class<T> tClass, Map<String, String> headerMapper)
			throws Exception {

		ArrayList<ArrayList<String>> rows = ExcelIO.readExcel(inputStream, 0);

		for (int k = 1; k < rows.size(); k++) {
			T t = tClass.newInstance();
			for (int num = 0; num < rows.get(0).size(); num++) {
				for (String header : headerMapper.keySet()) {
					String name = rows.get(0).get(num);
					if (name.equals(header)) {
						PropertyDescriptor propertyDescriptor = BeanUtilsBean.getInstance().getPropertyUtils()
								.getPropertyDescriptor(t, headerMapper.get(header).toString());
						if (propertyDescriptor != null) {
							BeanUtils.setProperty(t, headerMapper.get(header).toString(),
									ExcelIO.changeType(rows.get(k).get(num), propertyDescriptor.getPropertyType()));
							break;
						}
					}
				}
			}
			list.add(t);
		}
		return list;
	}

	/**
	 * 根据properties文件名称读取文件
	 * 
	 * @param propertiesName
	 * @return
	 * @throws IOException
	 */
	public static Map<String, String> getModelMap(String propertiesName) throws IOException {
		Map<Object, Object> map = new HashMap<Object, Object>();
		InputStreamReader model = new InputStreamReader(
				ExcelUtils.class.getClassLoader().getResourceAsStream(propertiesName), "utf-8");
		Properties p = new Properties();
		p.load(model);
		map = p;
		Map<String, String> map2 = new HashMap<String, String>();
		for (Map.Entry<Object, Object> entry : map.entrySet()) {
			map2.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
		}
		model.close();
		return map2;
	}

	public static void main(String[] args) throws Exception {
		Map<String, String> fTM = getModelMap("personal.properties");
		System.out.println(fTM);
	}

}
