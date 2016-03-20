package com.liuyong;

import java.util.ArrayList;
import java.util.List;
//URL������
public class UrlManager {
	
	List<String> newUrls = new ArrayList();//��������ʵ�URL
	List<String> oldUrls = new ArrayList();//�����ѷ��ʵ�URL
	
	/**
	 * ����µ�URL��������URL�б���
	 * @param url
	 */
	public void addNewUrl(String url) {
		if(url==null)
			return;
		if((!newUrls.contains(url))&&(!oldUrls.contains(url))){//���ԭ���ǣ���url�Ȳ��ڴ������б��У�Ҳ�����ѷ����б���
			newUrls.add(url);
		}
		
	}

	/**
	 * ����µ�URL���ϵ�������URL�б���
	 * @param urls
	 */
	public void addNewUrls(List<String> urls) {
		
		if(urls==null||urls.isEmpty()){
			return;
		}
		for(String url:urls){
			addNewUrl(url);
		}
		
	}
	
	/**
	 * �Ƿ��д����ʵ�URL����
	 * @return ���д����ʵ�URL������true
	 */
	public boolean hasNewUrl() {
		return !newUrls.isEmpty();
	}

	
	/**
	 * �Ӵ�����URL�б���ȡ��һ��URL
	 * @return ȡ����URL����
	 */
	public String getNewUrl() {
		if(newUrls.size()==0){
			return null;
		}
		
		//�������б�ȡ������뵽�ѷ����б���
		String newUrl = newUrls.get(newUrls.size()-1);
		newUrls.remove(newUrls.size()-1);
		oldUrls.add(newUrl);
		return newUrl;
	}



}
