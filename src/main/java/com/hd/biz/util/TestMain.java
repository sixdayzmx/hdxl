package com.hd.biz.util;

import cn.kingeid.guomi.SM2Utils;
import org.junit.Test;

import java.math.BigInteger;

public class TestMain {
    public static void main(String[] args) {
        String userId="31323334353637383132333435363738";//默认的
        String personPubKey ="0F10D241E1EF7FC9402CA07C64906D135F5DFC1C884B6DCB3599FE434B3AC66B6C7BB8D9890ACF31F455520644172123C92A15D6855EFF64234BDC9101E0C6FF";
//        String signData="1213y";
        String[] pubKeyArr = {personPubKey.substring(0,personPubKey.length()/2),personPubKey.substring(personPubKey.length()/2)};
        BigInteger pubKeyx = new BigInteger(pubKeyArr[0],16);
        BigInteger pubKeyy = new BigInteger(pubKeyArr[1],16);
        BigInteger c=new BigInteger(userId);
//        byte[] a=sourceData.getBytes();
//        byte[] b=userId.getBytes();
//        SM2Utils.getZaMH(a,b,c,c);
        String zaMH = SM2Utils.getZaMH(userId.getBytes(),"这是一个传说".getBytes(), pubKeyx, pubKeyy);
        System.out.println(zaMH);
    }

    @Test
    public void testAnd(){
        System.out.println(9&8);
    }

    @Test
    public void HashCode(){
        String Key="zmxisagoodmanhkhkkkdf";
        int index=0;
        char[] chars = Key.toCharArray();
        index=(int)chars[0];
        for(int k=0;k<Key.length();k++){
            index*=(1103515245+(int)chars[k]);
        }
        index>>=27;
        index&=16-1;
        System.out.println(index);
    }




}
