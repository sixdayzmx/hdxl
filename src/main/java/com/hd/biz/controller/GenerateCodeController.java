package com.hd.biz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/code")
public class GenerateCodeController {


    @RequestMapping("/generate")
    public String generate(){

        return "generateCode";
    }
    @RequestMapping("/output")
    public String output(@RequestParam String type,@RequestParam long code1,@RequestParam long code2,@RequestParam String area){
        String fileName=null;
        String filePath="D:/CPLC导入";
        File filePath1 = new File(filePath);
        filePath1.mkdirs();
        long step = 10000;//定义一个变量step=10000
        long count = 6000;//定义一个变量count=6000
        String  areaCode ="3601"; //定义一个区域编码 areaCode=“3601”
        try {
            long fileCount = 0;
            if(count%step > 0) {//count%step >0   6000%10000=6000取余
                fileCount = count/step +1;//fileCount=1
                System.out.println(fileCount);
            }else {
                fileCount = count/step;
            }
            //long beginNum = 1;  //定义一个开始的数字beginNum为1
            //long endNum = 6001;  //定义一个结束的数字
            for(int i=1;i<=fileCount;i++) {
                if (type.equals("联通")){
                    fileName="D:/CPLC导入/联通cplc"+i+".txt";
                }
                else {
                    fileName="D:/CPLC导入/移动cplc"+i+".txt";
                }

                File file = new File(fileName);
                if(!file.exists()) {
                    file.createNewFile();
                }
                PrintWriter printWriter = new PrintWriter(new FileOutputStream(file));
                for(long p= code1;p <= code2; p++) {
                    StringBuilder cplc =new StringBuilder();
                    int zeroCount =  7 - String.valueOf(p).length();
                    if (type.equals("联通")){
                        cplc.append("9").append(getZeroString(zeroCount)).append(p).append(area).append("\n");
                        printWriter.write(cplc.toString());
                        printWriter.flush();
                    }
                    else if (type.equals("移动")){
                        cplc.append("0").append(getZeroString(zeroCount)).append(p).append(area).append("\n");
                        printWriter.write(cplc.toString());
                        printWriter.flush();
                    }


                }
                printWriter.flush();
                code1 =  code1 + step;
                code2 = code2 + step;
            }


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "redirect:/code/generate";
    }

    private static String getZeroString(int num) {
        StringBuilder zeroStr =new StringBuilder();
        int i = 0;
        while(i<num) {
            zeroStr.append("0");
            i++;
        }
        return zeroStr.toString();
    }

}
