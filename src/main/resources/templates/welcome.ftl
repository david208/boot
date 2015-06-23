<!DOCTYPE html>

<html lang="en">
<#assign ages = {"Joe":23, "Fred":25} + {"Joe":30, "Julia":18}>
<#assign atime = ["a","b","c","d"]>
<body>
    <#if message ="Hello, Andy1">
    one message
    <#else>
    no message
    </#if>
<br>

<#list atime as age>
${age}
</#list>
${ages.Joe}
<br>
	Date: ${time?date}
	<br>
	Time: ${time?time}
	<br>
	Message: ${message}
</body>
<@greet person="Fred"><@abc/></@greet>
<#macro greet person>
<#nested>
<font size="+2">Hello ${person}</font>
</#macro>
<#macro abc>
我插的
</#macro >
<#macro test foo>${foo} (<#nested>) ${foo}</#macro>
<@test foo="A"><@test foo="B"><@test foo="C"/></@test></@test>

<#macro do_thrice>
<#nested 1>
<#nested 2>
<#nested 3>
</#macro>
<@do_thrice ; x> <#-- 用户自定义指令 使用";"代替"as" -->
${x} Anything.
</@do_thrice>
</html>
