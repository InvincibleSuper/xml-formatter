package com.github.xf;

import org.dom4j.Attribute;
import org.dom4j.Namespace;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface AttributeAppendStrategy {



    void append(FormatContext context, Map<String,Integer> myAttrMaxValueMap, List<Attribute> attributeList, List<Namespace> namespaceList) throws IOException;



}
