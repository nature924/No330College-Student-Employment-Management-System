package com.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * 日期转换器,接收字符串,近回日期对象
 * @author Administrator
 *
 */
public class CustomDateConverter implements Converter<String,Date>{

	public Date convert(String source) {
		
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			//返回转换成的日期
			return sdf.parse(source);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//失败返回null
		return null;
	}

}
