package com.github.xf;

public class FormatOption {


    private AttributeAppendStrategy attributeAppendStrategy = new DefaultAttributeAppendStrategy();
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
