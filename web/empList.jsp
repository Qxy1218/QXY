<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 2018/3/2
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>EmpPerson</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.0.min.js"></script>
    <script>
       $(function () {
           $('.delete').click(function () {
                var name = $(this).next(":hidden").val();
                var label = confirm("确定要删除"+name+"的信息吗？")
               if (label){
                    return;
               }
                return false;
           });
       });
    </script>
</head>

<body>

    <c:if test="${page != null || page.umberOfElements > 0}">
        <center>
            <table border="1" cellspacing="1">
                <tr>
                    <th>ID</th>
                    <th>EmpName</th>

                    <th>Birth</th>
                    <th>WorkTime</th>

                    <th>Department</th>
                    <th>Edit</th>

                    <th>Del</th>
                </tr>
               <c:forEach items="${page.content}" var="emp">
                   <tr>
                       <td>${emp.id}</td>
                       <td>${emp.name}</td>
                       <td>${emp.birth}</td>
                       <td>${emp.workTime}</td>
                       <td>${emp.department.depName}</td>
                       <td><a href="${pageContext.request.contextPath}/employee/openUpdate?id=${emp.id}">修改</a></td>
                       <td>
                           <a href="${pageContext.request.contextPath}/employee/delete?id=${emp.id}" class="delete">删除</a>
                           <input type="hidden" value="${emp.name}">
                       </td>
                   </tr>
               </c:forEach>
                <tr>
                    <td colspan="7">
                        共 ${page.totalElements} 记录
                        共 ${page.totalPages} 页
                        当前第 ${page.number + 1} 页
                        <a href="?pageNo=${page.number + 1 - 1}">上一页</a>

                        <c:if test="${page.number + 1 == page.totalPages}">
                            下一页
                        </c:if>
                        <c:if test="${page.number + 1 != page.totalPages}">
                            <a href="?pageNo=${page.number + 1 + 1}">下一页</a>
                        </c:if>
                    </td>
                </tr>
            </table>
            <a href="/employee/insertEmp">新增</a>
        </center>
    </c:if>
</body>
</html>
