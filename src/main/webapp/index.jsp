<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@ include file="/fragments/pageHead.jsp" %>
<body class="w3-light-grey">
<div class="w3-container w3-light-blue w3-opacity">
    <div>
        <h1 class="w3-center w3-text-indigo">WebApp_010_Departments</h1>
    </div>
</div>

<div class="mycard-cc w3-card-4 w3-round padding-bottom">
    <div class="w3-container w3-blue">
        <h2>Введите логин и пароль:</h2>
    </div>

    <div>
        <form action="/main/login" method="post" class="w3-container">
            <br/>
            <div>
                <table class="big w3-right-align">
                    <tr>
                        <td>Логин: *</td>
                        <td><input type="text" name="login" size="35" maxlength="35"
                                   autofocus value="${user.login}" required/></td>
                    </tr>
                    <tr>
                        <td>Пароль: *</td>
                        <td><input type="password" name="password" size="35" maxlength="35"
                                   value="${user.password}" required=/></td>
                    </tr>
                </table>
            </div>
            <p class="w3-left-align small"><sup>*</sup>Поля, отмеченные звездочкой, обязательны к заполнению</p>
            <input type="submit" class="w3-left" value="Авторизация">
        </form>
    </div>
</div>
<%@ include file="/fragments/bodyFooter.jsp" %>
</body>
</html>
<%--todo общие стили для: кнопки --%>

