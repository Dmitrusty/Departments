package myapp.controllers;

import myapp.model.Account;
import myapp.service.implementations.AccountService;
import myapp.utils.MyUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginController implements InterfaceController {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch (request.getMethod()) {
            case "POST":
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                String accessLevel = "USER";

                if (login != null && password != null && accessLevel != null) {
                    // если и логин и пароль получены
                    Account check = new Account(login, password, accessLevel);
                    Account validAccount = AccountService.findAccount(check);
                    if (validAccount != null) {
                        // В случае, если аккаунт найден
                        // Сохранить информацию пользователя в Session
                        MyUtils.storeLoginedUser(request.getSession(), validAccount);
                        // И Redirect на страницу /main/departments/list.
                        response.sendRedirect(request.getContextPath() + "/main/departments/list");
                        return;
                    } else {
                        // В случае, если аккаунт не найден
                        // удалить инфо о пользователе в Session,
                        MyUtils.deleteLoginedUser(request.getSession());
                        request.setAttribute("user", check);
                    }
                }
                // если логин или пароль не получены:
                // выдать сообщение "неверный логин или пароль"
                request.setAttribute("Message", "Неверный логин или пароль. Повторите ввод.");
                // forward (перенаправить) к /main/login.jsp
//                request.getRequestDispatcher("/index.jsp").forward(request, response);
//                break;z
            case "GET":
            default:
                request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}
