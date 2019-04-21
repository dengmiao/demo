package com.corgi.test.map;

import java.io.*;

public class Test {

	public static void main(String[] args) throws IOException {
		HashMap<String, String> map = new HashMap<>();
		map.put("a", "11");
		map.put("b", "22");
		map.put("c", "33");
		map.put("d", "44");
		map.put("e", "55");
		map.put("f", "66");
		map.put("g", "77");
		map.put("h", "88");
		map.put("i", "99");
		map.put("j", "111");
		map.put("k", "122");
		map.put("l", "133");
		map.put("m", "144");
		map.put("n", "155");
		map.put("o", "166");
		map.put("p", "177");
		System.out.println(map);
		System.out.println(map.get("f"));
		System.out.println(map.size());

		BufferedInputStream in = new BufferedInputStream(new FileInputStream("1.jpg"));
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("2.jpg"));
		int i;
		while((i = in.read()) !=-1){
			//加密，再亦或解密
			out.write(i ^ 9527);
		}
	}

}
