<%@page pageEncoding="UTF-8" %>
<div class="w3-container w3-light-blue w3-opacity w3-padding">
    <div>
        <button class="w3-btn w3-round-large w3-hover-blue w3-border w3-border-black w3-padding-small w3-right"
                onclick="location.href='/main/login'">Log out
        </button>
        <h1 class="w3-center w3-text-indigo">WebApp_010_Departments</h1>
    </div>
</div>
<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <div>
        <div class="w3-container w3-card-4 w3-blue w3-padding">
            <div style="float: left">
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
                        <a href="/main/employees/list" class="w3-bar-item
                            w3-btn w3-hover-pale-green w3-padding-small"
                        >Список сотрудников</a>
                        <a href="/main/employees/add" class="w3-bar-item
                            w3-btn w3-hover-green w3-padding-small"
                        >Добавить нового сотрудника</a>
                    </div>
                </div>

                <button class="w3-btn w3-round-large w3-hover-pale-yellow w3-border w3-border-black w3-padding-small"
                        style="width:20ch" onclick="location.href='/main/departments/list'">Список отделов
                </button>
                <button class="w3-btn w3-round-large w3-hover-yellow w3-border w3-border-black w3-padding-small"
                        style="width:25ch" onclick="location.href='/main/departments/add'">Добавить новый отдел
                </button>
                <button class="w3-btn w3-round-large w3-hover-pale-green w3-border w3-border-black w3-padding-small"
                        style="width:20ch" onclick="location.href='/main/employees/list'">Список сотрудников
                </button>
                <button class="w3-btn w3-round-large w3-hover-green w3-border w3-border-black w3-padding-small"
                        style="width:25ch" onclick="location.href='/main/employees/add'">Добавить нового сотрудника
                </button>
            </div>
        </div>
    </div>
</div>