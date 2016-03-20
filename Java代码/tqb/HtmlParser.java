package com.liuyong;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlParser {

	/**
	 * ����html�ĵ�
	 * @param htmlCont html�ĵ�����
	 * @return List<String> ���ĵ��в��ҵ���url�б�
	 */
	public List<String> parseNewUrls(String htmlCont){
		
		if(htmlCont==null)
			return null;
		
		List<String> newUrls = new ArrayList<String>();
		
		Document doc = Jsoup.parse(htmlCont);
		Elements links = doc.select("a[href]"); //����href���Ե�aԪ��
		//Elements pngs = doc.select("img[src$=.png]"); //��չ��Ϊ.png��ͼƬ
		
		// ������ʽ����
		String regEx = "/view/\\d+.htm";
		// ����������ʽ
	    Pattern pattern = Pattern.compile(regEx);
	    
	    for (Element link : links) {
			  String linkHref = link.attr("href");
			  Matcher matcher = pattern.matcher(linkHref);
			  // �����ַ������Ƿ���ƥ��������ʽ���ַ�/�ַ���
			  boolean rs = matcher.find();
			  //���ƥ�䣬ƴ�ӳ�URL����URL�Ĵ���ȡ�б�
			  if(rs){
				  if(!linkHref.startsWith("http://baike.baidu.com")){
					  linkHref = "http://baike.baidu.com"+linkHref;
				  }
				  
				  newUrls.add(linkHref);
			  }
		}
	    return newUrls;
	}

	/**
	 * ����html�ĵ��е�����
	 * @param htmlCont html�ĵ�����
	 * @return Data ���ҵ������������
	 */
	public Data parseData(String htmlCont){
		
		if(htmlCont==null)
			return null;
		
		Document doc = Jsoup.parse(htmlCont);
		
		//��ȡ����
		//<dd class="lemmaWgt-lemmaTitle-title"><h1>Python</h1>
		Element title_node = doc.select("dd[class=lemmaWgt-lemmaTitle-title]").select("h1").first();
		String title = title_node.text();
		
		//��ȡ��Ҫ
		//<div class="lemma-summary" label-module="lemmaSummary">
		Element summary_node = doc.select("div[class=lemma-summary]").first();
		String summary = summary_node.text();
		
		Data resData = new Data(title, summary);
		
		return resData;
	}

	

}
