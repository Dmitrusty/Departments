<%@ page import="myapp.model.Account" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@ include file="/fragments/pageHead.jsp" %>
<body>
<div  class="w3-container w3-light-blue w3-opacity">
    <div>
        <h1 class="w3-center">WebApp_010_Departments</h1>
    </div>
</div>
<h2>Стартовая страница - логин</h2>
<form action="/main/login" method="post">
    <fieldset>
        <%--        todo модальный месседж--%>
        <c:if test="${message != null}">
            <div><p>${message}</p></div>
        </c:if>
        <legend>Введите логин и пароль:</legend>
        <p>Проверка: user.login = ${user.login}</p>
        <div class="big">
            <label>Логин: *<input type="text" name="login" size="35" maxlength="35"
                                  value="${user.login}" required/></label><br/>

            <label>Пароль: *<input type="password" name="password" min="100" max="625"
                                   value="${user.password}" required=/></label><br/>
        </div>
        <p class="small"><sup>*</sup>Поля, отмеченные звездочкой, обязательны к заполнению</p>
        <input type="submit" value="Отправить">
    </fieldset>
</form>
<%@ include file="/fragments/bodyFooter.jsp" %>
</body>
</html>

