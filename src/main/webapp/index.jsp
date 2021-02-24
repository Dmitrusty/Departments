<%--@elvariable id="user" type="myapp.model.Account"--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@ include file="/fragments/pageHead.jsp" %>
<body class="w3-light-grey">
<div class="w3-container w3-light-blue w3-opacity">
    <div>
        <h1 class="w3-center w3-text-indigo">Departments</h1>
    </div>
</div>

<div class="mycard-cc w3-card-4 w3-round padding-bottom">
    <%@ include file="/fragments/infoPanel.jsp" %>

    <div class="w3-container w3-blue">
        <h2>Введите логин и пароль:</h2>
    </div>

    <div>
        <form action="<c:url value="/main/login"/>" method="post" class="w3-container">
            <br/>
            <div>
                <table class="big w3-right-align">
                    <tr>
                        <td><label for="login">Логин: *</label></td>
                        <td><input type="text" name="login" id="login" size="35" maxlength="35"
                                   autofocus value="${user.login}" required/></td>
                    </tr>
                    <tr>
                        <td><label for="password">Пароль: *</label></td>
                        <td><input type="password" name="password" id="password" size="35" maxlength="35"
                                   value="${user.password}" required=/></td>
                    </tr>
                </table>
            </div>
            <p class="w3-left-align small"><sup>*</sup>Поля, отмеченные звездочкой, обязательны к заполнению</p>
            <input type="submit" class="w3-left w3-hover-blue" value="Авторизация">
        </form>
    </div>
</div>
<%@ include file="/fragments/bodyFooter.jsp" %>
</body>
</html>

