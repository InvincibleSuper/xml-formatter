package com.gitee.jwds666.xf;

import org.dom4j.Attribute;
import org.dom4j.Namespace;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 参数追加策略
 */
public interface AttributeAppendStrategy {


    /**
     * 追加
     * @param context 格式化上下文
     * @param myAttrMaxValueMap 当前的参数最大长度表
     * @param attributeList 参数列表
     * @param namespaceList 命名空间列表
     * @throws IOException IOException
     */
    void append(FormatContext context, Map<String,Integer> myAttrMaxValueMap, List<Attribute> attributeList, List<Namespace> namespaceList) throws IOException;



}
