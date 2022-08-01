# xml-formatter

#### 介绍
使用更符合人类阅读方式来格式化XML


```
<ref name="message" type="message.sender">
    <p id="1" name="BeginString" val="${BeginString}"/>
    <p id="36" name="Message" val="${Message}"/>
    <p id="42" name="Sender" val="${Sender}"/>
    <p id="73" name="SendingTime" val="${SendingTime}"/>
    <p id="1134" name="Target" val="${Target}"/>
</ref>
```
使上方格式转换为下方格式
```
<ref name="message" type="message.sender">
    <p id="1"    name="BeginString" val="${BeginString}"/>
    <p id="36"   name="Message"     val="${Message}"/>
    <p id="42"   name="Sender"      val="${Sender}"/>
    <p id="73"   name="SendingTime" val="${SendingTime}"/>
    <p id="1134" name="Target"      val="${Target}"/>
</ref>

```

具体是让XML对齐参数间距。支持默认和排序两种对其方式。