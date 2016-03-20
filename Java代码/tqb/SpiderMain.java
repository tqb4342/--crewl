package com.liuyong;

import java.util.List;
import java.util.Map;
//������ȳ���
public class SpiderMain {
	
	UrlManager urls;//URL������
	HtmlDownloader downloader;//HTML������
	HtmlParser parser;//HTML������
	HtmlOutputer outputer;//HTML�����
	
	
	//���ȳ����ʼ��
	public SpiderMain() {
		this.urls = new UrlManager();
		this.downloader = new HtmlDownloader();
		this.parser = new HtmlParser();
		this.outputer = new HtmlOutputer();
	}

	/**
	 * ������
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		String root_url = "http://baike.baidu.com/subview/29/12654100.htm";//������ȡ�ĸ�URL
		SpiderMain spider = new SpiderMain();//��ʼ�����ȳ���
		spider.craw(root_url);//ִ������
	}

	private void craw(String root_url) throws Exception {
		
		//��¼��ǰ��ȡ�ĵڼ���URL
		int count = 1;
		
		
		urls.addNewUrl(root_url);//��Ӹ�URL
		while(urls.hasNewUrl()){
			try{
				//���µ�URL��õ�һ���µ�URL
				String newUrl = urls.getNewUrl();
				System.out.println("craw "+count+" : "+newUrl);
				
				//��ȡ���URL��Ӧҳ���html
				String htmlCont = downloader.download(newUrl);
				
				//��ȡ��ҳ�������з���Ҫ���URL
				List<String> newUrls = parser.parseNewUrls(htmlCont);
				
				//��ȡָ�����������������
				Data newData  = parser.parseData(htmlCont);
				
				//���õ�����URL��ӵ�URL��������
				urls.addNewUrls(newUrls);
				
				//�������������ӵ��ռ�����
				outputer.collectData(newData);
					
				//������ȡ����	
				if(count == 100){
					break;
				}
				count++;				
			}catch(Exception e){
				System.out.println("craw filed");
			}

			
		}
		
		//��html��ʽ�����ȡ��������
		outputer.outputHtml();
	
	}

}
