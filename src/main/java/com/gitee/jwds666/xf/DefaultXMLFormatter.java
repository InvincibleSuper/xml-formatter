package com.gitee.jwds666.xf;

import org.dom4j.*;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.util.*;

public class DefaultXMLFormatter implements XMLFormatter{

    /**
     * 格式化至输出流
     * @param os 输出流
     * @param xml xml源文件的输入流
     * @param formatOption 格式化选项
     * @return 格式化的上下文
     * @throws IOException IOException
     * @throws DocumentException DocumentException
     */
    @Override
    public FormatContext formattingWriteStream(OutputStream os, InputStream xml, FormatOption formatOption) throws IOException, DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(xml);
        FormatContext context = new FormatContext();
        context.setEncoding(document.getXMLEncoding());
        context.setWriter(os);
        XMLFormatUtils.write(context,"<?xml version=\"1.0\" encoding=\""+context.getEncoding()+"\"?>\n");
        Map<String,Integer> myAttrMaxValueMap = XMLFormatUtils.getAttributeWidth(document.getRootElement());
        processEle(document.getRootElement(),0,myAttrMaxValueMap,context,formatOption);
        return context;
    }


    /**
     * 格式化至输出流
     *
     * @param os  输出流
     * @param xml xml源文件的输入流
     * @return 格式化的上下文
     * @throws IOException IOException
     * @throws DocumentException DocumentException
     */
    @Override
    public FormatContext formattingWriteStream(OutputStream os, InputStream xml) throws IOException, DocumentException {
        return formattingWriteStream(os,xml,new FormatOption());
    }

    /**
     * 格式化至文件
     *
     * @param path 输出文件的路径
     * @param xml  xml源文件的输入流
     * @return 格式化的上下文
     * @throws IOException IOException
     * @throws DocumentException DocumentException
     */
    @Override
    public FormatContext formattingWriteFile(String path, InputStream xml) throws IOException, DocumentException {
        return formattingWriteFile(path,xml,new FormatOption());
    }

    /**
     * 格式化至文件
     * @param path 输出文件的路径
     * @param xml xml源文件的输入流
     * @param formatOption 格式化选项
     * @return 格式化的上下文
     * @throws IOException IOException
     * @throws DocumentException DocumentException
     */
    @Override
    public FormatContext formattingWriteFile(String path, InputStream xml, FormatOption formatOption) throws IOException, DocumentException {

        File outFile = new File(path);
        if (!outFile.exists()){
            if (outFile.getParentFile() != null && !outFile.getParentFile().exists()){
                outFile.getParentFile().mkdirs();
            }
            outFile.createNewFile();
        }
        SAXReader reader = new SAXReader();
        Document document = reader.read(xml);
        FormatContext context = new FormatContext();
        context.setEncoding(document.getXMLEncoding());
        context.setWriter(new FileOutputStream(outFile));
        XMLFormatUtils.write(context,"<?xml version=\"1.0\" encoding=\""+context.getEncoding()+"\"?>\n");
        Map<String,Integer> myAttrMaxValueMap = XMLFormatUtils.getAttributeWidth(document.getRootElement());
        
        processEle(document.getRootElement(),0,myAttrMaxValueMap,context,formatOption);

        context.getWriter().flush();
        return context;
    }

    protected void processEle(Element element,
                              int retract,
                              Map<String,Integer> myAttrMaxValueMap,
                              FormatContext context,
                              FormatOption option) throws IOException {
        //获取子节点属性值中最大长度
        List<Element> elements = element.elements();
        Map<String,Map<String,Integer>> attrMaxValueEleMap = XMLFormatUtils.getAttributeMaxWidth(elements);
       

        //获取所有的节点。并根据Class分类
        List<Node> nodes = element.content();
        Map<Class,List> classNodeMap = new HashMap<>();
        for (Node node : nodes) {
            classNodeMap.putIfAbsent(node.getClass(),new ArrayList<>());
            classNodeMap.get(node.getClass()).add(node);
        }

        //构建输出的内容
        boolean isSingle = element.getText().isEmpty();

        XMLFormatUtils.retract(context,retract);
        XMLFormatUtils.write(context,"<");
        XMLFormatUtils.write(context,XMLFormatUtils.eleKey(element));



        List<Namespace> namespaceList = classNodeMap.get(Namespace.class);
        //追加属性
        List<Attribute> attributeList = element.attributes();
        option.getAttributeAppendStrategy().append(context,myAttrMaxValueMap,attributeList,namespaceList);


        //追加命名空间

        if (namespaceList != null){
            for (Namespace namespace : namespaceList) {
                XMLFormatUtils.write(context," xmlns");
                if (!namespace.getPrefix().isEmpty()){
                    XMLFormatUtils.write(context,":");
                }
                XMLFormatUtils.write(context,namespace.getPrefix());
                XMLFormatUtils.write(context,"=\"");
                XMLFormatUtils.write(context,namespace.getURI());
                XMLFormatUtils.write(context,"\"");
            }

        }
        if (isSingle){
            XMLFormatUtils.write(context,"/");
        }
        XMLFormatUtils.write(context,">");



        //判断子级是节点还是Text,节点或自闭合换行
        if (!isSingle && elements.isEmpty()){
            XMLFormatUtils.write(context,element.getText());
        }else{
            XMLFormatUtils.write(context,"\n");
        }
        //将子节点进行写入
        for (Element element1 : elements) {
            Map<String,Integer> attrMaxValueMap = attrMaxValueEleMap.get(element1.getName());
            processEle(element1,retract+option.getRetractSize(),attrMaxValueMap,context,option);
        }

        if (!isSingle){
            if (!elements.isEmpty()){
                XMLFormatUtils.retract(context,retract);
            }
            XMLFormatUtils.write(context,"</"+element.getName()+">\n");
        }
    }
}
