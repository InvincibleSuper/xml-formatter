package com.gitee.jwds666.xf;

/**
 * 格式化选项
 */
public class FormatOption {

    /**
     * 参数追加策略
     */
    private AttributeAppendStrategy attributeAppendStrategy = new DefaultAttributeAppendStrategy();

    /**
     * 缩进大小，1代表一个空格
     */
    private int retractSize = 4;

    public AttributeAppendStrategy getAttributeAppendStrategy() {
        return attributeAppendStrategy;
    }

    public void setAttributeAppendStrategy(AttributeAppendStrategy attributeAppendStrategy) {
        this.attributeAppendStrategy = attributeAppendStrategy;
    }


    public int getRetractSize() {
        return retractSize;
    }

    public void setRetractSize(int retractSize) {
        this.retractSize = retractSize;
    }
}
