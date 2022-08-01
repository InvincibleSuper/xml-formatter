package com.gitee.jwds.xf;

import org.dom4j.DocumentException;
import org.junit.Test;

import java.io.*;


public class XMLFormattingTest {

    @Test
    public void formatDefault() throws DocumentException, IOException {
        FormatOption option = new FormatOption();
        InputStream is = this.getClass().getResourceAsStream("/test.xml");
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        FormatContext context = new DefaultXMLFormatter().formattingWriteStream(os,is,option);
        System.out.println(os.toString(context.getEncoding()));
    }


    @Test
    public void formatSort() throws DocumentException, IOException {
        FormatOption option = new FormatOption();
        option.setAttributeAppendStrategy(new SortAttributeAppendStrategy());
        InputStream is = this.getClass().getResourceAsStream("/test.xml");
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        FormatContext context = new DefaultXMLFormatter().formattingWriteStream(os,is,option);
        System.out.println(os.toString(context.getEncoding()));
    }
}
