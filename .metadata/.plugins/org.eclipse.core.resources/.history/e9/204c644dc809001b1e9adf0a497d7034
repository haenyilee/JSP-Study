package com.sist.manager;

import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class equipMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		   {
			int k=1;
			 for(int i=1;i<=2;i++)
	            {
	               Document doc=Jsoup.connect("http://cnk.co.kr/goods/goods_list.php?page="+i+"&cateCd=014").get();
	                Elements link=doc.select("div.item_tit_box a");

			     
			     for(int j=0;j<link.size();j++)
			     {
			    	try
			    	{
			    	
			    		String dlink=link.get(j).attr("href");
	                      dlink="http://cnk.co.kr"+dlink.substring(2,dlink.length());
			    	 Document doc2=Jsoup.connect(dlink).get();
			//    	 System.out.println(dlink);
			    	
			    	 Element title=doc2.selectFirst("div.item_detail_tit h3");
			    	 Element poster=doc2.selectFirst("div span.img_photo_big img");
			    	 Element price=doc2.selectFirst("dl.item_price dd");
			    	 Element delivery=doc2.select("dl.item_delivery dd strong").get(0);
//			    	 Element made=doc2.select("div.item_detail_list dl dd").get(6);
			    	 Elements link2=doc2.select("div.txt-manual img");
	    	
			    	 
			    	 System.out.println("상품명:"+title.text());
			    	 System.out.println("상품이미지:"+"http://cnk.co.kr"+poster.attr("src"));
			    	 System.out.println("상품가격:"+price.text());
			    	 System.out.println("배송비:"+delivery.text());
//			    	 System.out.println("제조사:"+made.text());
			    	 System.out.println("상품상세:");
			    	 for(int a=0;a<link2.size();a++)
			    	 {
			    		 try
			    		 {
			    			 System.out.println("http://cnk.co.kr"+link2.attr("src"));
			    		 }catch(Exception ex) {}
			    	 }
			    	 System.out.println("================================================");
//			    	
//			    	 MovieVO vo=new MovieVO();
//			    	 vo.setCateno(5);
//			    	 //vo.setNo(k);
//			    	 vo.setTitle(title.text());
//			    	 vo.setPoster(poster.attr("src"));
//			    	 vo.setRegdate(regdate.text());
//			    	 vo.setGenre(genre.text());
//			    	 vo.setGrade(grade.text());
//			    	 vo.setScore(score.text());
//			    	 vo.setActor(actor.text());
//			    	 vo.setDirector(director.text());
//			    	 vo.setStory(story.text());
//			    	 vo.setKey(youtubeGetKey(title.text()));
//			    	 
//			    	 dao.movieInsert(vo);
			    	 //k++;
			    	}catch(Exception ex){ex.printStackTrace();}
			     }
			    
			 }
			
		   }catch(Exception ex)
		   {
			   ex.printStackTrace();
		   }
	}

}
