package com.gitee.jwds666.xf;

import org.dom4j.Attribute;
import org.dom4j.Namespace;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SortAttributeAppendStrategy implements AttributeAppendStrategy{


    
    @Override
    public void append(FormatContext context, Map<String, Integer> myAttrMaxValueMap, List<Attribute> attributeList, List<Namespace> namespaceList) throws IOException {
        if (myAttrMaxValueMap == null)return;
        Map<String,Integer> sortMap = new TreeMap<>();
        sortMap.putAll(myAttrMaxValueMap);


        int size = 0;
        for (String s : sortMap.keySet()) {
            Attribute attribute = getAttribute(attributeList,s);
            int maxWidth = sortMap.get(s);
            if (attribute == null){
                XMLFormatUtils.retract(context,maxWidth);
            }else{
                size++;
                String attrKey = XMLFormatUtils.attrKey(attribute);
                XMLFormatUtils.write(context," ");
                XMLFormatUtils.write(context,attrKey);
                XMLFormatUtils.write(context,"=\"");
                XMLFormatUtils.write(context,attribute.getValue());
                XMLFormatUtils.write(context,"\"");
                if (myAttrMaxValueMap != null && (size != attributeList.size() || namespaceList != null && !namespaceList.isEmpty())){ ;
                    int width = XMLFormatUtils.getValueWidth(attribute.getValue());
                    XMLFormatUtils.retract(context,maxWidth - width);
                }
            }

        }

    }


    protected Attribute getAttribute(List<Attribute> attributes,String attrKey){
        for (Attribute attribute : attributes) {
            if (XMLFormatUtils.attrKey(attribute).equals(attrKey)){
                return attribute;
            }
        }
        return null;
    }
}
