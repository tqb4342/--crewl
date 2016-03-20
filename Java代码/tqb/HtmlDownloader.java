package com.liuyong;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
//HTML������
public class HtmlDownloader {

	/**
	 * ��ȡָ��URLҳ���HTML����
	 * @param downloadUrl
	 * @return htmlҳ���ַ���
	 */
	public String download(String downloadUrl) {	
		Document doc = null;
		try {
			doc = Jsoup.connect(downloadUrl).get();
		} catch (IOException e) {
			System.out.println("������������������");
		}
		
		return  doc.html();
		
	}

}
