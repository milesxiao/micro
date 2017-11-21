package me.tdcarefor.util;

import java.util.Date;

/**
 * Created by xxg on 16/12/7.
 */
public class Equal {
    public static boolean eq(String str1,String str2){
        boolean result=null==str1&&null==str2;
        if(!result){
            if(null!=str1 && null!=str2){
                result=str1.equals(str2);
            }
        }
        return result;
    }

    public static boolean eq(Integer i1,Integer i2){
        boolean result=null==i1&&null==i2;
        if(!result){
            if(null!=i1 && null!=i2){
                result=i1.equals(i2);
            }
        }
        return result;
    }


    public static boolean eq(Float f1,Float f2){
        boolean result=null==f1&&null==f2;
        if(!result){
            if(null!=f1 && null!=f2){
                result=f1==f2;
            }
        }
        return result;
    }

    public static boolean eq(Date dt1,Date dt2){
        boolean result=null==dt1&&null==dt2;
        if(!result){
            if(null!=dt1 && null!=dt2){
                result = dt1.getTime()==dt2.getTime();
            }
        }
        return result;
    }
}
