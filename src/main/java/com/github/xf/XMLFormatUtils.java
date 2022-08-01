package com.github.xf;

import org.dom4j.Attribute;
import org.dom4j.Element;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class XMLFormatUtils {

    static String attrKey(Attribute attribute){
        if (attribute.getNamespacePrefix().isEmpty()){
            return attribute.getName();
        }
        return attribute.getNamespacePrefix() + ":" + attribute.getName();
    }



    static void retract(FormatContext context,int retract) throws IOException {

        for (int i = 0; i < retract; i++) {
            write(context," ");
        }
    }

    static int getValueWidth(String str){
        int res = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) <= 127){
                res +=1;
            }else{
                res +=2;
            }
        }
        return res;
    }



    static String eleKey(Element element){
        if (element.getNamespacePrefix().isEmpty()){
            return element.getName();
        }
        return element.getNamespacePrefix() + ":" + element.getName();
    }

    static void write(FormatContext context,String text) throws IOException {
        context.getWriter().write(text.getBytes(context.getEncoding()));
    }

    static Map<String,Integer> getAttributeWidth(Element element){
        LinkedHashMap<String,Integer> attributeWidthMap = new LinkedHashMap<>();
        for (Attribute attribute : element.attributes()) {
            attributeWidthMap.put(XMLFormatUtils.attrKey(attribute), XMLFormatUtils.getValueWidth(attribute.getValue()));
        }
        return attributeWidthMap;
    }

    static Map<String,Map<String,Integer>> getAttributeMaxWidth(List<Element> elements){
        Map<String,Map<String,Integer>> attributeMaxWidthMap = new HashMap<>();
        for (Element element : elements) {
            Map<String,Integer> attributeWidthMap = getAttributeWidth(element);
            String eleKey = eleKey(element);
            if (!attributeMaxWidthMap.containsKey(eleKey)){
                attributeMaxWidthMap.put(eleKey,attributeWidthMap);
            }
            Map<String,Integer> rootMap = attributeMaxWidthMap.get(eleKey);
            for (Map.Entry<String, Integer> entry : attributeWidthMap.entrySet()) {
                if (rootMap.containsKey(entry.getKey()) ){
                    int a = rootMap.get(entry.getKey()),b = entry.getValue();
                    if (b > a)rootMap.put(entry.getKey(),b);
                }else{
                    rootMap.put(entry.getKey(),entry.getValue());
                }
            }
        }
        return attributeMaxWidthMap;
    }
}
