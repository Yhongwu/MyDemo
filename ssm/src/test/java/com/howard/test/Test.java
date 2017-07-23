package com.howard.test;

import java.util.ArrayList;
import java.util.List;

public class Test {
	@org.junit.Test
	public void testContains() {
		List<String> urls = new ArrayList<>();
		urls.add("/ecms/scan.do");
		urls.add("/ecms/scan_result.do");
		System.out.println(urls.contains("/dlpecms/ecms/scan.do"));
		System.out.println(urls.get(0).indexOf("/dlpecms/ecms/scan.do"));
		System.out.println("/ecms/scan.do".indexOf(urls.get(0)));
	}
}
