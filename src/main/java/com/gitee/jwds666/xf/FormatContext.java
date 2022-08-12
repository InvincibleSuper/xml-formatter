package com.gitee.jwds666.xf;

import java.io.OutputStream;

/**
 * 格式化上下文
 */
public class FormatContext {


    /**
     * xml的字符编码
     */
    private String encoding;

    /**
     * 要写入的流
     */
    private OutputStream writer;


    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public OutputStream getWriter() {
        return writer;
    }

    public void setWriter(OutputStream writer) {
        this.writer = writer;
    }
}
