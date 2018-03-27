package com.totoro.data.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.totoro.core.model.UserInfo;
import com.totoro.core.model.UserInfoExample;
import com.totoro.core.utils.Fish;
import com.totoro.core.utils.MyPage;
import com.totoro.data.dao.LhbDetailMapper;
import com.totoro.data.dao.LhbInfoMapper;
import com.totoro.data.dao.TestaddrMapper;
import com.totoro.data.model.LhbDetail;
import com.totoro.data.model.LhbInfo;
import com.totoro.data.model.LhbInfoExample;
import com.totoro.data.model.Node;
import com.totoro.data.model.TestaddrExample;
import com.totoro.data.service.DataService;

@Service("dataService")
public class DataServiceImpl implements DataService{

	@Autowired
	private LhbInfoMapper lhbInfoMapper;
	@Autowired
	private LhbDetailMapper lhbDetailMapper;
	@Autowired
	private TestaddrMapper testaddrMapper;
	
	/**
	 * 获取东方财富龙虎榜的部分数据
	 */
	@SuppressWarnings("unchecked")
	public void gainLhbData() {
		Map<String, Object> map = this.getLhb();		
		List<LhbInfo> infolist = (List<LhbInfo>) map.get("info");		
		lhbInfoMapper.saveInfoList(infolist);
		
		List<LhbDetail> detaillist = (List<LhbDetail>) map.get("detail");
		lhbDetailMapper.saveInfoList(detaillist);
	}
	
	
	private  Map<String, Object> getLhb(){
		  Map<String, Object> map = new HashMap<String, Object>();
		  List<LhbInfo> infolist = new ArrayList<LhbInfo>();
		  List<LhbDetail> detaillist = new ArrayList<LhbDetail>();
		  LhbInfo info = null;
		  LhbDetail detail = null;
		  try{
			  CloseableHttpClient client = HttpClients.createDefault();
			  
		      // 使用 GET 方法 ，如果服务器需要通过 HTTPS 连接，那只需要将下面 URL 中的 http 换成 https 
			  HttpGet get=new HttpGet("http://data.eastmoney.com/stock/lhb.html");
		      //使用POST方法
			  HttpPost post = new HttpPost("http://seputu.com/biji1/18.html");
			  //post提交设置参数
			  post.setEntity(null); 
			  HttpResponse res = client.execute(get);
	
		      //打印服务器返回的状态
		      //System.out.println(res.getStatusLine().getStatusCode());
		      //返回的信息/
		      String content = EntityUtils.toString(res.getEntity(),"gbk");
		      
		      Document doc = Jsoup.parse(content); 
		      Elements links = doc.getElementsByClass("lhbtable");
		      
		      String firstname = "http://data.eastmoney.com";
		      String name ="";String fd="";String code="";String url="";
		      int num = 0;
		      for (Element e : links) {
		    	  Elements aa =e.getElementsByTag("td");		    	  
		    	  for (Element z : aa) {
		    		  name = z.getElementsByClass("wname").text();
		    		  if(!"".equals(name)){
		    			 info = new LhbInfo();
		    			 info.setId(Fish.getUUID());		    			 
			    	     info.setName(name);			    	    
			    	     code = z.getElementsByTag("a").eq(0).attr("data_code");
			    	     info.setCode(code);			    	     
			    	     fd = z.getElementsByClass("wpercent").text().replace("%","");			    	     
			    	     info.setRanges(new BigDecimal(fd));			    	     
			    	     info.setCreatetime(new Date());
			    	     url = firstname+z.getElementsByTag("a").eq(1).attr("href");
			    	     //System.out.println(name+"...."+url); 
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
				   	    		detail = new LhbDetail();
				   	    		detail.setId(Fish.getUUID());
				   	    		detail.setCreatetime(new Date());
				   	    		detail.setCode(code);
				   	    		detail.setType(1);
				   	    		detail.setYyt(x.getElementsByClass("sc-name").text());
				   	    		detail.setTimes(Integer.parseInt(x.getElementsByClass("times").text().replace("次","")));
				   	    		detail.setPercent(x.getElementsByClass("percent").text());
				   	    		detail.setPercentcss(x.getElementsByClass("percent").attr("style"));
				   	    		detail.setBuymoney(new BigDecimal(el.eq(2).text()));
				   	    		detail.setBuyproportion(new BigDecimal(el.eq(3).text().replace("%","")));
				   	    		if("-".equals(el.eq(4).text())){
				   	    			detail.setSellmoney(new BigDecimal("0"));
				   	    			detail.setSellproportion(new BigDecimal("0"));
				   	    		}else{
				   	    			detail.setSellmoney(new BigDecimal(el.eq(4).text()));
				   	    			detail.setSellproportion(new BigDecimal(el.eq(5).text().replace("%","")));
				   	    		}
				   	    		detail.setTotal(new BigDecimal(el.eq(6).text()));
				   	    		detaillist.add(detail);
				   	    	}
				   	      }
				   	      //卖出前5			   	      
				   	      Elements links2 = doc1.getElementById("tab-4").getElementsByTag("tr");
				   	      for (Element x : links2) {
				   	    	  if(!x.hasClass("total-tr")){
						   	    	Elements el = x.getElementsByTag("td");
						   	    	if(!"".equals(el.text().trim())){
						   	    		detail = new LhbDetail();
						   	    		detail.setId(Fish.getUUID());
						   	    		detail.setCreatetime(new Date());
						   	    		detail.setCode(code);
						   	    		detail.setType(0);
						   	    		detail.setYyt(x.getElementsByClass("sc-name").text());
						   	    		detail.setTimes(Integer.parseInt(x.getElementsByClass("times").text().replace("次","")));
						   	    		detail.setPercent(x.getElementsByClass("percent").text());
						   	    		detail.setPercentcss(x.getElementsByClass("percent").attr("style"));
						   	    		if("-".equals(el.eq(2).text())){
						   	    			detail.setSellmoney(new BigDecimal("0"));
						   	    			detail.setSellproportion(new BigDecimal("0"));
						   	    		}else{
						   	    			detail.setBuymoney(new BigDecimal(el.eq(2).text()));
							   	    		detail.setBuyproportion(new BigDecimal(el.eq(3).text().replace("%","")));
						   	    		}
						   	    		
						   	    		detail.setSellmoney(new BigDecimal(el.eq(4).text()));
						   	    		detail.setSellproportion(new BigDecimal(el.eq(5).text().replace("%","")));
						   	    		detail.setTotal(new BigDecimal(el.eq(6).text()));						   	    		
						   	    		detaillist.add(detail);
						   	    	}
				   	    	  }
				   	      }
				   	num++;
				   	/*if(num>5){
				   		break;
				   	}*/
				   	   infolist.add(info);
		    		  }
		    		  
		    	  }
		      }
		      map.put("info", infolist);
		      map.put("detail", detaillist);
		      //释放连接
		      client.close();
		  }catch (Exception e) {
			e.printStackTrace();
		}		
		return map;
	}


	
	public MyPage findDataPage(String selectname, Integer page, Integer pagesize) {
		page = (page-1)*pagesize;
		List<LhbInfo> list = lhbInfoMapper.selectDataPage(selectname,page,pagesize);
		
		LhbInfoExample example = new LhbInfoExample();
		LhbInfoExample.Criteria c = example.createCriteria();
		c.andNameLike("%"+selectname+"%");
		
		int total = lhbInfoMapper.countByExample(example);
		
		return new MyPage(list, total);
	}


	@Override
	public List<Node> getAllDirList() {
		return testaddrMapper.selectAddr();
	}
	
	
}
