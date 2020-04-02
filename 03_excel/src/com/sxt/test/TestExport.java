package com.sxt.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sxt.domain.User;
import com.sxt.utils.ExprotExcelUtils;

/**
 * 导出用户数据
 * @author LJH
 *
 */
public class TestExport {

	public static void main(String[] args) {
		List<User> users=new ArrayList<>();
		for (int i = 1; i <=10; i++) {
			users.add(new User(i, "小明"+i, "武汉"+i, new Date()));
		}
		ExprotExcelUtils.exportObject(users, "用户数据", "用户数据", "所有用户数据.xls");
		
	}	
	
}
