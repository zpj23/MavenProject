package com.totoro.es.controller;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.totoro.core.model.UserInfo;
import com.totoro.es.utils.EsUtils;

@Controller
@RequestMapping("/es")
public class EsController {

	
	@RequestMapping("/toSearch")
	public String toSearch(ModelMap modelMap){
		return "/es/index";
	}
	
	
	@RequestMapping("/SearchNews")
	public String SearchNews(HttpServletRequest req,ModelMap modelMap,String page,String word){
		try{ 
	        int pageNum=1;
	        if(page!=null&&!"".equals(page)){
	        	pageNum = Integer.parseInt(page);
	        }
	        if(word==null){
	        	word="";
	        }
	        searchSpnews(word, pageNum,req);	
	        req.setAttribute("word", word);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "/es/result";
	}
	
	
	
	@RequestMapping("/SearchNews2")
	public void SearchNews2(ModelMap modelMap, HttpServletRequest req, HttpServletResponse resp){
		try{
			req.setCharacterEncoding("UTF-8");
	        String query = req.getParameter("query");
	        System.out.println(query);
	        String pageNumStr=req.getParameter("pageNum");
	        int pageNum=1;
	
	        if (pageNumStr!=null&&Integer.parseInt(pageNumStr)>1){
	            pageNum=Integer.parseInt(pageNumStr);
	        }
	        searchSpnews(query, pageNum,req);
	
	        req.setAttribute("queryBack", query);
	        req.getRequestDispatcher("result.jsp").forward(req, resp);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	private void searchSpnews(String query, int pageNum,HttpServletRequest req) {

        long start = System.currentTimeMillis();
        TransportClient client = EsUtils.getSingleClient();
        MultiMatchQueryBuilder multiMatchQuery = QueryBuilders
                .multiMatchQuery(query, "title", "content");
        HighlightBuilder highlightBuilder = new HighlightBuilder()
                .preTags("<span style=\"color:red\">")
                .postTags("</span>")
                .field("title")
                .field("content");

        SearchResponse searchResponse = client.prepareSearch("spnews")
                .setTypes("news")
                .setQuery(multiMatchQuery)
                .highlighter(highlightBuilder)
                .setFrom((pageNum-1)*10)
                .setSize(10)
                .execute()
                .actionGet();

        SearchHits hits = searchResponse.getHits();
        ArrayList<Map<String, Object>> newslist = new ArrayList<Map<String, Object>>();
        for (SearchHit hit : hits) {
            Map<String, Object> news = hit.getSourceAsMap();

            HighlightField hTitle = hit.getHighlightFields().get("title");
            if (hTitle != null) {
                Text[] fragments = hTitle.fragments();
                String hTitleStr = "";
                for (Text text : fragments) {
                    hTitleStr += text;
                }
                news.put("title", hTitleStr);
            }

            HighlightField hContent = hit.getHighlightFields().get("content");
            if (hContent != null) {
                Text[] fragments = hContent.fragments();
                String hContentStr = "";
                for (Text text : fragments) {
                    hContentStr += text;
                }
                news.put("content", hContentStr);
            }
            
            HighlightField hUrl = hit.getHighlightFields().get("url");
            if (hUrl != null) {
                Text[] fragments = hUrl.fragments();
                String hUrlStr = "";
                for (Text text : fragments) {
                	hUrlStr += text;
                }
                news.put("url", hUrlStr);
            }
            newslist.add(news);
        }
        long end = System.currentTimeMillis();
        req.setAttribute("newslist", newslist);
        req.setAttribute("totalHits", hits.getTotalHits() + "");
        req.setAttribute("totalTime", (end - start) + "");
                
        //分页的参数回传
        req.setAttribute("page", pageNum);
        //总的页数
        int totalpage = (int) Math.ceil((float)hits.getTotalHits()/10);       
        req.setAttribute("totalpage", totalpage);
        
        if(totalpage<=10||pageNum<6){
        	req.setAttribute("endpage", 10);
        	req.setAttribute("beginpage", 1);
        }else{
        	if(pageNum+4<totalpage){
            	req.setAttribute("endpage", pageNum+4);
            	req.setAttribute("beginpage", pageNum-5);
            }else{
            	req.setAttribute("endpage", totalpage);
            	req.setAttribute("beginpage", totalpage-9);
            }
        }        
    }
	
	
	
	
	public static void main(String[] args) {
		int a  = (int) Math.ceil((float)91/10)  ;
		System.out.println(a);
		System.out.println(Math.ceil(9.00));
	}
	
	
}
