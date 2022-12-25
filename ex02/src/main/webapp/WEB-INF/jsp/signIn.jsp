<%@ page pageEncoding="UTF-8" %>
<jsp:include page="includes/header.jsp"/>

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

<jsp:include page="includes/footer.jsp"/>