package com.sist.controller;

import java.util.*;

import java.lang.reflect.*;

import java.io.File;
class A{
   @RequestMapping("aaa.do")
   public void aaa()
   {
      System.out.println("A:aaa() Cal...");
   }
   @RequestMapping("bbb.do")
   public void bbb()
   {
      System.out.println("B:bbb() Cal...");
   }
   @RequestMapping("ccc.do")
   public void ccc()
   {
      System.out.println("C:ccc() Cal...");
   }
}
//aaa.do ===> aaa() 호출
//bbb.do ===> bbb() 호출
//ccc.do ===> ccc() 호출
public class MainClass {
   public static void main(String[] args) {
      Scanner s = new Scanner(System.in);
      System.out.println("사이트주소 입력 : ");
      String cmd=s.next();
      
//      A a = new A();
//      if(cmd.equals("aaa.do"))
//      {
//         a.aaa();
//      }
//      else if(cmd.equals("bbb.do"))
//      {
//         a.bbb();
//      }
//      else if (cmd.equals("ccc.do"))
//      {
//         a.ccc();
//      }
      try {
         // 클래스의 모든 정보를 읽어온다.
         Class clsName=Class.forName("com.sist.controller.A");
         // 메모리 할당
         Object obj=clsName.newInstance();
         // A클래스가 가지고 있는 모든 메소드를 읽어 온다.
         Method[] methods=clsName.getDeclaredMethods();
         for(Method m:methods)
            
         {
            RequestMapping rm=m.getAnnotation(RequestMapping.class);
            
            if(cmd.equals(rm.value()))
            {
               m.invoke(obj, null);
               // 메소드 호출 (obj(A클래스) 가 가지고 있는 메소드를 호출한다)
               // null ==> 매개변수
            }
         }
      }catch (Exception e) {
      }
   }
}
