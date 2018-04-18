package com.qianjun.script;

import com.qianjun.script.utils.ScriptUtils;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.lang.reflect.Array;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ScriptException, NoSuchMethodException {
        Thread [] threads = new Thread[10];
        for ( int i = 0; i < 10 ; i++){
            threads[i] = new Thread(new DoAction(i + ""), ""+i);
        }

        for ( int i = 0; i < 10 ; i++){
            threads[i].start();
        }
    }
    static  class  DoAction implements Runnable{

        private  String name;
        DoAction(String name){
            this.name = name ;
        }

        @Override
        public void run() {
            Map<String, Object> map = new HashMap<String, Object>(){
                {
                    put("name", Thread.currentThread() +"---"+name);
                }
            };

            Date olddate = new Date("05/11/2017");
            Date newdate = new Date("04/14/2018");

            String scrpit = "def getName(startDate, endDate){ " +
                    "Date latestdate = new Date(); " +
                    "return startDate.compareTo(latestdate)<=0 && endDate.compareTo(latestdate)>=0;}";
            Object result = null;
            try {
                result =  ScriptUtils.invokeScriptFunction("groovy",
                        scrpit,
                        "getName",
                        olddate,newdate
                );
            }catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (ScriptException e) {
                e.printStackTrace();
            }


            System.out.println( "Hello "+ result + "!" );
        }
    }
}
