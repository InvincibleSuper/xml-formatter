package com.gitee.jwds666.xf;

import org.dom4j.DocumentException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * XML格式化
 */
public interface XMLFormatter {


    /**
     * 格式化至输出流
     * @param os 输出流
     * @param xml xml源文件的输入流
     * @param formatOption 格式化选项
     * @return 格式化的上下文
     * @throws IOException IOException
     * @throws DocumentException DocumentException
     */
    FormatContext formattingWriteStream(OutputStream os,InputStream xml,FormatOption formatOption) throws IOException, DocumentException;



    /**
     * 格式化至输出流
     * @param os 输出流
     * @param xml xml源文件的输入流
     * @return 格式化的上下文
     * @throws IOException IOException
     * @throws DocumentException DocumentException
     */
    FormatContext formattingWriteStream(OutputStream os,InputStream xml) throws IOException, DocumentException;


    /**
     * 格式化至文件
     * @param path 输出文件的路径
     * @param xml xml源文件的输入流
     * @param formatOption 格式化选项
     * @return 格式化的上下文
     * @throws IOException IOException
     * @throws DocumentException DocumentException
     */
    FormatContext formattingWriteFile(String path,InputStream xml,FormatOption formatOption) throws IOException, DocumentException;

    /**
     * 格式化至文件
     * @param path 输出文件的路径
     * @param xml xml源文件的输入流
     * @return 格式化的上下文
     * @throws IOException IOException
     * @throws DocumentException DocumentException
     */
    FormatContext formattingWriteFile(String path,InputStream xml) throws IOException, DocumentException;
}
