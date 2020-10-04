package com.sist.manager;
import java.io.FileWriter;
import java.util.*;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sist.dao.HomeTrainingVO;
public class HomeTrainingManager {
	public void homeTrainingAllData() {
		try {
			ArrayList<HomeTrainingVO> list=new ArrayList<HomeTrainingVO>();
			String[] site= {
					"https://dshop.dietshin.com/goods/goods_list.asp?sort=BEST&c_idx=0&pc_idx=52&gotopage=", // 식단
					"https://dshop.dietshin.com/goods/goods_list.asp?sort=BEST&c_idx=60&pc_idx=56&gotopage=" // 헬스케어
			};
			
			int no=1;
			int cno=1;
			int subCno=1;
			
			// for(int i=1;i<=5;i++) 
			{
			
				Document doc=Jsoup.connect("https://dshop.dietshin.com/goods/goods_list.asp?sort=BEST&c_idx=0&pc_idx=52&gotopage=1").get();
				Elements link = doc.select("ul.bestsn_nlist a");
				for(int i=0;i<link.size();i++) {
					try {
						String url="https://dshop.dietshin.com"+link.get(i*3).attr("href");
						Document doc2=Jsoup.connect(url).get();
						System.out.println("https://dshop.dietshin.com"+link.get(i*3).attr("href")); 
						
						Element poster=doc2.selectFirst("div.v_topimg img#goodsimgm0");
		    	    	System.out.println("상품사진:"+poster.attr("src"));
		    	    	
		    	    	Element title=doc2.selectFirst("h1.v_top_name");
		    	    	System.out.println("상품이름:"+title.text());
		    	    	
		    	    	Element subTitle=doc2.selectFirst("p.v_top_txt");
		    	    	System.out.println("상품설명:"+subTitle.text());
		    	    	
		    	    	Element price=doc2.selectFirst("em.v_n_top_sale");
		    	    	System.out.println("상품가격:"+price.text());
		    	    	
		    	    	Element delivery=doc2.select("dd.mt3").get(1);
		    	    	System.out.println("배송비:"+delivery.text());
		    	    	
						Elements link2 = doc2.select("div.pdt_txt img");
						System.out.println("상세정보:");
						for(int j=0;j<link2.size();j++) {
							try {
								System.out.println(link2.get(j).attr("src"));
							}catch (Exception ex) {}
						}
		    	    	System.out.println("============================================");
						
					}catch (Exception ex) {}
				}				
			}

		}catch (Exception ex) {
			System.out.println(ex.getMessage());
		}		
	}
	public static void main(String[] args) {
		HomeTrainingManager m=new HomeTrainingManager();
		m.homeTrainingAllData();
	}
}