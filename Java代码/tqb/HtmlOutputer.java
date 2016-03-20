package com.liuyong;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
//�����
public class HtmlOutputer {
	
	//ά��һ���洢���ݵ�����
	List<Data> datas = new ArrayList<Data>();

	/**
	 * �ռ�����
	 * @param data �ռ���������
	 */
	public void collectData(Data data) {
		if(data==null)
			return;
		datas.add(data);
		
	}

	
	/**
	 * ��������html��ʽ���
	 */
	public void outputHtml(){
		File file = new File("output.html");
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(file);
			//�������
			os.write("<html><link href='output.css' rel='stylesheet'><body><h1 align='center'>����liuyong�ĳ�����ȡ���İٿƹؼ�����Ϣ</h1>".getBytes());
			os.write("<table border='1' width='70%' align='center'>".getBytes());
			for(Data data : datas){
				os.write("<tr>".getBytes());
				os.write("<td width='15%'>".getBytes());
				os.write(data.getTitle().getBytes());
				os.write("</td>".getBytes());
				
				os.write("<td width='85%'>".getBytes());
				os.write(data.getSummary().getBytes());
				os.write("</td>".getBytes());
				os.write("</tr>".getBytes());
			}
			os.write("</table>".getBytes());
			os.write("</body></html>".getBytes());
			
			
		}catch (Exception e) {
			System.out.println("�������������");
			e.printStackTrace();
		}finally{
			if(os!=null){
				try {
					os.flush();
				} catch (IOException e) { 
					e.printStackTrace();
				}
				try {
					os.close();
				} catch (IOException e) { 
					e.printStackTrace();
				}
			}
		}
		
	}

}
