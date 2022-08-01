package com.gitee.jwds666.xf;

import java.io.OutputStream;

public class FormatContext {

    private String encoding;

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
