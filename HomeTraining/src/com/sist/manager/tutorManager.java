package com.sist.manager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*
 * 해니님 - 튜터
 */
public class tutorManager {

	
	
	public void tutorAllData()
	{
		int no=1;
		int cno=1; // 1=PT , 2=요가 , 3=필라테스 , 4=팀운동(GX)
		
		for(int i=1;i<=7;i++)
		{
			try {
				// PT
				Document doc=Jsoup.connect("https://taling.me/Home/Search/?page="+i+"&cateMain=&cateSub=27&region=&orderIdx=&query=&code=&org=&day=&time=&tType=&region=&regionMain=").get();
				Elements link=doc.select("div.cont2_class a");
				
				// 요가
				// https://taling.me/Home/Search/?page="+i+"&cateMain=&cateSub=29&region=&orderIdx=&query=&code=&org=&day=&time=&tType=&region=&regionMain=
				
				
				// 필라테스
				// https://taling.me/Home/Search/?page="+i+"&cateMain=&cateSub=114&region=&orderIdx=&query=&code=&org=&day=&time=&tType=&region=&regionMain=
				
				// 팀운동(GX)
				// https://taling.me/Home/Search/?page="+i+"&cateMain=&cateSub=228&region=&orderIdx=&query=&code=&org=&day=&time=&tType=&region=&regionMain=
				
				
				for(int j=0;j<link.size();j++)
				{
					try {
						
						String url="https://taling.me/"+link.get(j).attr("href");
						
						Document doc2=Jsoup.connect(url).get();
						
						System.out.println("-------"+no+"---------");
						no++;
						Elements coach_name=doc2.select("div.name");
						System.out.println("코치이름:"+coach_name.text());
						
						Element coach_detail=doc2.select("div.d_info04").get(0);
						System.out.println("코치정보:"+coach_detail.html());
						
						Elements coach_image=doc2.select("input#tutorImage");
						System.out.println("코치사진:"+coach_image.attr("value"));
						
						Element poster=doc2.select("link").get(0);
						System.out.println("포스터:"+poster.attr("href"));
						
						Elements title=doc2.select("div.class_title div.title");
						System.out.println("글제목:"+title.text());
						
						Element content=doc2.select("div.d_info04").get(1);
						System.out.println("수업정보:"+content.html());
						
						//파싱된 데이터 예시 : ￦40,000/시간
						Elements price=doc2.select("li.ohu");
						System.out.println("가격:"+price.text());
						
						Element city=doc2.selectFirst("div.region_name");
						System.out.println("지역:"+city.text());
						
						// 협의 후 결정이 많아서 데이터 긁어와도 소용이 없을 듯!!
						Elements indate=doc2.select("div.indate");
						System.out.println("강습일:"+indate.text());
						
						
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				
				
			} catch (Exception e) {
				e.getMessage();
			}
		}
		
		
	}
	
	public static void main(String[] args) {
		
		tutorManager tm= new tutorManager();
		tm.tutorAllData();
		

	}

}
