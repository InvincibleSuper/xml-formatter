package com.gitee.jwds.xf;

import org.dom4j.DocumentException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface XMLFormatter {



    FormatContext formattingWriteStream(OutputStream os,InputStream xml,FormatOption formatOption) throws IOException, DocumentException;


    FormatContext formattingWriteFile(String path,InputStream xml,FormatOption formatOption) throws IOException, DocumentException;
}
