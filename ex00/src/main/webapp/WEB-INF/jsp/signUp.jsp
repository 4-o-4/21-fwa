<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style>body{ background: antiquewhite; }</style>
<form action="signUp" method="post">
    <p style="margin-bottom: 5px;">Регистрация</p>
    <div>
        <label>
            <input type="text" name="firstname" placeholder="Имя" required>
        </label>
    </div>
    <div>
        <label>
            <input type="text" name="lastname" placeholder="Фамилия" required>
        </label>
    </div>
    <div>
        <label>
            <input type="text" name="phone" placeholder="Телефон" required>
        </label>
    </div>
    <div>
        <label>
            <input type="password" name="password" placeholder="Пароль" required>
        </label>
    </div>
    <div>
        <input type="submit" value="Зарегистрироваться">
    </div>
</form>

<a href="signIn">Вход</a>