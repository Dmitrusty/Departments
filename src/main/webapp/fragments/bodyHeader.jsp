<%@page pageEncoding="UTF-8" %>
<div class="w3-container w3-light-blue w3-opacity w3-padding">
    <div>
        <button class="w3-btn w3-round-large w3-hover-blue w3-border w3-border-black w3-padding-small w3-right"
                onclick="location.href='/main/login'">Log out
        </button>
        <h1 class="w3-center w3-text-indigo">Departments</h1>
    </div>
</div>
<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <div>
        <div class="w3-container w3-card-4 w3-blue w3-padding">
            <div style="float: left">
                <%--                Выпадающие меню--%>
                <div class="w3-dropdown-hover">
                    <button class="w3-button w3-blue w3-hover-blue">Отделы</button>
                    <div class="w3-dropdown-content w3-bar-block w3-card-4 w3-blue">
                        <a href="/main/departments/list" class="w3-bar-item
                            w3-btn w3-hover-pale-yellow w3-padding-small"
                        >Список отделов</a>
                        <a href="/main/departments/add" class="w3-bar-item
                            w3-btn w3-hover-yellow w3-padding-small"
                        >Добавить новый отдел</a>
                    </div>
                </div>
                <div class="w3-dropdown-hover">
                    <button class="w3-button w3-blue w3-hover-blue">Сотрудники</button>
                    <div class="w3-dropdown-content w3-bar-block w3-card-4 w3-blue">
                        <c:if test="${departmentName != null}">
                            <a class="w3-bar-item w3-btn w3-hover-pale-green w3-padding-small"
                               href="/main/employees/list?departmentName=${departmentName}">Список сотрудников
                                отдела ${departmentName}</a>
                            <button class="w3-bar-item w3-btn w3-hover-green w3-padding-small"
                                    onclick="location.href='/main/employees/add?departmentName=${departmentName}'">
                                Добавить нового сотрудника в отдел ${departmentName}
                            </button>
                        </c:if>
                        <c:if test="${departmentName == null}">
                            <a class="w3-bar-item w3-btn w3-disabled w3-hover-pale-green w3-padding-small"
                               href="#">Список сотрудников отдела ...</a>
                            <a href="/main/employees/add" class="w3-bar-item w3-btn w3-hover-green w3-padding-small">
                                Добавить нового сотрудника</a>
                        </c:if>
                    </div>
                </div>
                <%--                Отдельные кнопки--%>
                <button class="w3-btn w3-round-large w3-hover-pale-yellow w3-border w3-border-black w3-padding-small"
                        style="width:20ch" onclick="location.href='/main/departments/list'">Список отделов
                </button>
                <button class="w3-btn w3-round-large w3-hover-yellow w3-border w3-border-black w3-padding-small"
                        style="width:25ch" onclick="location.href='/main/departments/add'">Добавить новый отдел
                </button>
                <c:if test="${departmentName != null}">
                    <a class="w3-btn w3-round-large w3-hover-pale-green w3-border w3-border-black w3-padding-small"
                       href="/main/employees/list?departmentName=${departmentName}">Список сотрудников
                        отдела ${departmentName}</a>
                    <button class="w3-btn w3-round-large w3-hover-green w3-border w3-border-black w3-padding-small"
                            onclick="location.href='/main/employees/add?departmentName=${departmentName}'">Добавить
                        нового сотрудника в отдел ${departmentName}
                    </button>
                </c:if>
                <c:if test="${departmentName == null}">
                    <a class="w3-btn w3-disabled w3-round-large w3-hover-pale-green w3-border w3-border-black w3-padding-small"
                       href="#">Список сотрудников отдела ...</a>
                    <button class="w3-btn w3-round-large w3-hover-green w3-border w3-border-black w3-padding-small"
                            style="width:25ch" onclick="location.href='/main/employees/add'">Добавить нового сотрудника
                    </button>
                </c:if>
            </div>
        </div>
    </div>
</div>