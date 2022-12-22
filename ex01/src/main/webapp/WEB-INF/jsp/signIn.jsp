<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style>body{ background: antiquewhite; }</style>
<form action="signIn" method="post">
    <p style="margin-bottom: 5px;">Вход</p>
    <div>
        <label>
            <input type="text" name="firstname" placeholder="Имя" required>
        </label>
    </div>
    <div>
        <label>
            <input type="password" name="password" placeholder="Пароль" required>
        </label>
    </div>
    <div>
        <input type="submit" value="Войти">
    </div>
</form>

<a href="signUp">Зарегистрироваться</a>