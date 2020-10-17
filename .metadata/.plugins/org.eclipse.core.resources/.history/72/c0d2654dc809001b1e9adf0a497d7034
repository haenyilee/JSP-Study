package com.sist.manager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*
 *  한비님 - 필라테스 요가 근력 
 */
public class ExManager {
   
   
   public static void main(String[] args) {
      
      try {
         
          Document doc=Jsoup.connect("https://www.msn.com/ko-kr/health/search/filter?cluster=fitness").get();
            Elements link=doc.select("section.srchres li a");
                  
            for(int j=0;j<link.size();j++)
           {
            try {
               String dLink="https://www.msn.com"+link.get(j).attr("href");
                 Document doc2=Jsoup.connect(dLink).get();
                 System.out.println(dLink);
                 
               Element title=doc2.selectFirst("div h1");
               Element level=doc2.select("div p").get(0);
               Element focus=doc2.select("div p").get(1);
               Element musle=doc2.select("div p").get(2);
               Element equipment=doc2.select("div p").get(3);
               Elements step_content=doc2.select("div.step-notes span");
               //Elements step_poster=doc2.select("div.exercise-step img");
               Elements tip=doc2.select("div.tips-section p");
               
               
                 //Elements video=doc2.
                 //select("div.exercise-section div.wcvideoplayer div.video_player_inner video"
                 //);
                
               
               System.out.println(title.text());
               System.out.println(level.text());
               System.out.println(focus.text());
               System.out.println(musle.text());
               System.out.println(equipment.text());
               System.out.println(step_content.html());
               //System.out.println(step_poster.html(""));
               System.out.println(tip.html());
               //System.out.println(video.html(""));
               System.out.println("================================================");
                
               
            }catch (Exception e) {}
            
           }
         
      }catch (Exception e) {}

   }

}