<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<table border="1" cellspacing="0">
    <tr>
        <td colspan="2">freemarker模版引擎：${name}</td>
    </tr>
    <tr>
        <td rowspan="2">输出对象属性值：</td>
        <td>name：${people.getName()}</td>
    </tr>
    <tr>
        <td>petName：${people.getPetName()}</td>
    </tr>
    <tr>
        <td>返回字符串长度</td>
        <td>name长度：${people.getName()?length}</td>
    </tr>
    <tr>
        <td>输出集合遍历：</td>
        <td>
            <#list people.getType() as type>${type}<br></#list>
        </td>
    </tr>
    <tr>
        <td>输出map属性：</td>
        <td>
            <span>${people.getUser().username}</span><br>
            <span>${people.getUser()['password']?c}</span>
        </td>
    </tr>
    <tr>
        <td>算术运算符：</td>
        <td>
            直接运算：<span>${1+2}</span><br>
            获取数据后再进行运算：<span >${people.getAge() + 10}</span>
        </td>
    </tr>
    <tr>
        <td>比较运算符：</td>
        <td>
            直接运算：<span><#if 1 gt 2>true<#else>false</#if></span><br>
        </td>
    </tr>
</table>
</body>
</html>