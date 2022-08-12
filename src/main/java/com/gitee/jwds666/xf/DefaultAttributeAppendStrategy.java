package com.gitee.jwds666.xf;

import org.dom4j.Attribute;
import org.dom4j.Namespace;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 默认的参数追加策略
 * 不改变参数位置
 */
public class DefaultAttributeAppendStrategy implements AttributeAppendStrategy{


    /**
     * 追加
     * @param context 格式化上下文
     * @param myAttrMaxValueMap 当前的参数最大长度表
     * @param attributeList 参数列表
     * @param namespaceList 命名空间列表
     * @throws IOException IOException
     */
    @Override
    public void append(FormatContext context, Map<String, Integer> myAttrMaxValueMap
            , List<Attribute> attributeList, List<Namespace> namespaceList) throws IOException {
        for (int i = 0; i < attributeList.size(); i++) {
            Attribute attribute = attributeList.get(i);
            String attrKey = XMLFormatUtils.attrKey(attribute);
            XMLFormatUtils.write(context," ");
            XMLFormatUtils.write(context,attrKey);
            XMLFormatUtils.write(context,"=\"");
            XMLFormatUtils.write(context,attribute.getValue());
            XMLFormatUtils.write(context,"\"");
            if (myAttrMaxValueMap != null && (i+1 != attributeList.size() || namespaceList != null && !namespaceList.isEmpty())){
                int maxWidth = myAttrMaxValueMap.get(attrKey);
                int width = XMLFormatUtils.getValueWidth(attribute.getValue());
                XMLFormatUtils.retract(context,maxWidth - width);
            }
        }
    }
}
