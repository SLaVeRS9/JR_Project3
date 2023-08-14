<%--
  Created by IntelliJ IDEA.
  User: Вячеслав
  Date: 03.08.2023
  Time: 1:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>${book.name}</title>
    <meta charset="UTF-8">
    <link href="styles.css" rel="stylesheet">
</head>
<body>
    <div class="center"><h1>${book.name}</h1></div>
    <div class="center"><p class="zag">Добро пожаловать, ${user.name}!</p></div>
    <div>
        <p class="text">${book.parts[initPart].text}</p>
    </div>

    <div class="center">
        <c:if test="${isRegistered == true}">
            <form class="main" action="/page" method="GET">
                <label class="main2">
                    <button class="main3" name="part" value="${book.firstPart}">Продолжить</button>
                </label>
            </form>
            <form class="main" action="/restart" method="GET">
                <label>
                    <button class="main3">Начать с нуля</button>
                </label>
            </form>
        </c:if>
        <c:if test="${isRegistered == false}">
            <form class="main" action="/start" method="POST">
                <label>
                    <span class="welcome">Представься:</span>
                    <input class="main3" type="text" name="userName"/>
                </label><br><br>
                <label>
                    <button class="main3">Отправиться в путешествие</button>
                </label>
            </form>
        </c:if>
    </div>
</body>
<footer>
    <div class="statistics">
        <p class="statistics"><strong>Статистика</strong></p>
        Имя пользователя: ${user.name}<br>
        IP: ${infoFormDTO.userIP}<br>
        ID сессии: ${infoFormDTO.JSessionID}<br>
        Количество прожитых часов: ${infoFormDTO.stepsInGame}<br>
        Кол-во игр на игрока: ${infoFormDTO.countPlayedGame}<br>
    </div>
</footer>
</html>

