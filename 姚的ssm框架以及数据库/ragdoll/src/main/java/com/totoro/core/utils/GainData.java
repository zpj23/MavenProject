package com.totoro.core.utils;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class GainData {

	
	public static void main(String[] args) throws ClientProtocolException, IOException {
				
		  CloseableHttpClient client = HttpClients.createDefault();
		  
	      // 使用 GET 方法 ，如果服务器需要通过 HTTPS 连接，那只需要将下面 URL 中的 http 换成 https 
		  HttpGet get=new HttpGet("http://data.eastmoney.com/stock/lhb.html");
	      //使用POST方法
		  HttpPost post = new HttpPost("http://seputu.com/biji1/18.html");
		  //post提交设置参数
		  post.setEntity(null); 
		  HttpResponse res = client.execute(get);

	      //打印服务器返回的状态
	      System.out.println(res.getStatusLine().getStatusCode());
	      //打印返回的信息
	       
	      
	      String content = EntityUtils.toString(res.getEntity(),"gbk");
	      //System.out.println(content);
	      Document doc = Jsoup.parse(content); 
	      Elements links = doc.getElementsByClass("lhbtable");
	      
	      String firstname = "http://data.eastmoney.com";
	      String name ="";String fd="";
	      for (Element e : links) {
	    	  Elements aa =e.getElementsByTag("td");
	    	  //System.out.println(aa.text());
	    	  for (Element z : aa) {
	    		  name = z.getElementsByClass("wname").text();
	    		  if(!"".equals(name)){	    		  
		    	     System.out.print(name);
		    	     System.out.print("   |    ");
		    	     String code = z.getElementsByTag("a").eq(0).attr("data_code");
		    	     System.out.print(code);
		    	     System.out.print("   |    ");
		    	     fd = z.getElementsByClass("wpercent").text().replace("%","");
		    	     System.out.print(fd);
		    	     System.out.print("   |    ");
		    	     String url = firstname+z.getElementsByTag("a").eq(1).attr("href");
		    	     System.out.println(url);		    	     
		    	     
		    		  
		    	     //详情页面的数据
		    	     HttpGet get1=new HttpGet(url);
		    	     HttpResponse res1 = client.execute(get1);
		    	     String content1 = EntityUtils.toString(res1.getEntity(),"gbk");
		    	     Document doc1 = Jsoup.parse(content1);
		    	     //买入前5
		   	         Elements links1 = doc1.getElementById("tab-2").getElementsByTag("tr");
			   	      for (Element x : links1) {
			   	    	Elements el = x.getElementsByTag("td");
			   	    	if(!"".equals(el.text().trim())){
				   	    	System.out.println(x.getElementsByClass("sc-name").text());
				   	    	System.out.println(x.getElementsByClass("times").text());
				   	    	System.out.println(x.getElementsByClass("percent").text());
				   	    	System.out.println(x.getElementsByClass("percent").attr("style"));				   	    	
				   	    	System.out.println(el.eq(2).text());
				   	    	System.out.println(el.eq(3).text());
				   	    	System.out.println(el.eq(4).text());
				   	    	System.out.println(el.eq(5).text());
				   	    	System.out.println(el.eq(6).text());
			   	    	}
			   	      }
			   	      //卖出前5			   	      
			   	      Elements links2 = doc1.getElementById("tab-4").getElementsByTag("tr");
			   	      for (Element x : links2) {
			   	    	  if(!x.hasClass("total-tr")){
					   	    	Elements el = x.getElementsByTag("td");
					   	    	if(!"".equals(el.text().trim())){
					   	    		System.out.println(x.getElementsByClass("sc-name").text());
						   	    	System.out.println(x.getElementsByClass("times").text());
						   	    	System.out.println(x.getElementsByClass("percent").text());
						   	    	System.out.println(x.getElementsByClass("percent").attr("style"));
						   	    	System.out.println(el.eq(2).text());
						   	    	System.out.println(el.eq(3).text());
						   	    	System.out.println(el.eq(4).text());
						   	    	System.out.println(el.eq(5).text());
						   	    	System.out.println(el.eq(6).text());
					   	    	}
			   	    	  }
			   	      }
	    	     
	    	         
	    		  }
	    		  
	    	  }
	      }
	     	      
	      
	      
	      //System.out.println(EntityUtils.toString(res.getEntity(),"utf-8"));
	      //释放连接
	      client.close();
	      
	      
	      
	}
		
		
	
}
