<%--
  Created by IntelliJ IDEA.
  User: Вячеслав
  Date: 03.08.2023
  Time: 1:39
  To change this template use File | Settings | File Templates.
--%>

<%--<jsp:useBean id="statisticsBean" scope="session" class="jR_Project3.models.SessionContextForForm"/>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ночь в лесу оборотней</title>
    <meta charset="UTF-8">
</head>
<body style="background-image: image('background_werwolf_start.jpg')">
    <div>Добро пожаловать, {путник}!</div>
    <div>
        <p>Берегись, читатель!<br>
            Сам выбрал наш ужастик &mdash; никто тебе не поможет!<br>
            Доставай плавки!<br>
            Ты с семьей едешь на все лето в местечко под названием &laquo;Лесной мир&raquo;.<br>
            Ты как на иголках: не терпится поскорее очутиться на берегу озера.<br>
            Потом у костра мальчишки рассказывают всякие страсти о Лесном мире.<br>
            По слухам, в этих лесах по ночам бродят оборотни.<br>
            Но тебя не так-то легко напугать. Ты готов к приключениям.<br>
            Решишься обследовать самые глухие и мрачные уголки леса?<br>
            Хватит тебе храбрости сунуться в тоннель Волн, обитель жуткого озерного чудовища?<br>
            Ты готов биться с огненно-рыжими муравьями?<br>
        </p>
    </div>

    <div>
        <form action="/start" method="POST">
            <label>Представься:
                <input type="text" name="userName" />
            </label><br><br>
            <label>
                <input type="submit" value="Отправиться в путешествие" />
                    <%--<button type="submit">Отправиться в путешествие</button>--%>
            </label>
        </form>
    </div>

    <div>
        <p>Статистика<br>
        Имя пользователя: ${statisticsBean.userName}<br>
        IP: ${statisticsBean.userIP}<br>
        ID сессии: ${statisticsBean.JSessionID}<br>
        Количество прожитых часов: ${statisticsBean.stepsInGame}<br>
        Кол-во игр на игрока: ${statisticsBean.countPlayedGame}<br>
    </div>
    <p></p>
</body>
</html>