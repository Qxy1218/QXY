<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 2018/3/4
  Time: 9:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.0.min.js"></script>
<html>
<head>
    <title>insert</title>
    <script>
        $(document).ready(function () {
            $('#names').blur(function () {
                var names= $('#names').val();
                //var name=$.trim(names);
                //判断当修改的用户名和之前的用户名一样时该用户名可用
                var lastName=$('#hidName').val();
                if(names==lastName){
                    alert("用户名可用");
                    return;
                }
                $.ajax({
                    url:"${pageContext.request.contextPath}/employee/ajaxName?name="+names,
                    type:"post",
                    success:function (data) {
                        if (data=="0"){
                            alert("该用户名正确");
                        }else if(data=="1"){
                            alert("该用户名已经使用了");
                            $('#names').val("")
                        }else{
                            alert("程序或者网络出现问题！")
                        }
                    }
                })
            });

        });
    </script>
</head>
<body>
<
<form action="${pageContext.request.contextPath}/employee/save" method="post">
    <center>



        <table border="1" cellspacing="1">
            <c:if test="${employee.id!=null}">
                <input type="hidden" value="${employee.id}" name="id">
                <input type="hidden" value="${employee.name}" id="hidName">
            </c:if>
            <tr>
                <td>姓名</td>
                <td>
                    <input type="text" name="name" id="names" value="${employee.name}">
                </td>
            </tr>
            <tr>
                <td>生日</td>
                <td>
                    <input type="text" name="birth" id="birth" value="${employee.birth}">
                </td>
            </tr>
            <tr>
                <td>部门</td>
                <td>
                    <select name="department.id">
                        <option value="${employee.department.id}" selected="selected">${employee.department.depName}</option>
                        <c:forEach items="${departmentList}" var="dep">
                            <c:if test="${employee.department.id == dep.id}">
                                <option value="${dep.id}" hidden="hidden"></option>
                            </c:if>
                            <c:if test="${employee.department.id != dep.id}">
                                <option value="${dep.id}" datatype="Department">${dep.depName}</option>
                            </c:if>

                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td align="center" colspan="2">
                    <input type="submit" value="SUBMIT">
                </td>
            </tr>
        </table>
    </center>
</form>
</body>
</html>
