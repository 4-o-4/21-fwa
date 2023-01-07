<%@ page pageEncoding="UTF-8" %>
<jsp:include page="includes/header.jsp"/>

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
            <input type="tel" name="phone" placeholder="+7 (900) 123-45-67" pattern="\+7\s?[\(]{0,1}9[0-9]{2}[\)]{0,1}\s?\d{3}[-]{0,1}\d{2}[-]{0,1}\d{2}" required>
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

<jsp:include page="includes/footer.jsp"/>