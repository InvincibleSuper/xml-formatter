package com.gitee.jwds.xf;


import java.io.*;

public class Main {



    public static void main(String[] args) throws Exception{
        if (args.length == 0)return;
        FormatOption option = new FormatOption();
        if (args[0].length() == 1 && Character.isDigit(args[0].charAt(0))){
            int s = Integer.valueOf(args[0]);
            if (s == 1){
                option.setAttributeAppendStrategy(new SortAttributeAppendStrategy());
            }
            String[] copyArgs = new String[args.length-1];
            System.arraycopy(args, 1, copyArgs, 0, copyArgs.length);
            args = copyArgs;
        }
        String path = args[0];
        File file = new File(path);
        String outPath = path;
        if (args.length > 1){
            outPath = args[1];
        }
        try (FileInputStream fis = new FileInputStream(file)){
            new DefaultXMLFormatter().formattingWriteFile(outPath,fis,option);
        }

    }



}
