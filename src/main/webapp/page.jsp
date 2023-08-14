<%--
  Created by IntelliJ IDEA.
  User: Вячеслав
  Date: 08.08.2023
  Time: 1:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${book.name}</title>
    <meta charset="UTF-8">
    <link href="page.css" rel="stylesheet">
</head>
<body>
    <div class="center"><p class="zag">${currentPart.title}</p></div>
    <div class="center">
        <p class="text">${currentPart.text}</p>
        <div class="center">
                <c:forEach var="button" items="${currentPart.buttons}">
                    <c:if test="${button.text.equalsIgnoreCase('Конец')}">
                        <form class="main" action="/start" method="GET">
                            <label>
                                <button class="main3">${button.text}</button>
                            </label>
                        </form>
                    </c:if>

                    <c:if test="${!button.text.equalsIgnoreCase('Конец')}">
                        <form class="main" action="/page" method="GET">
                            <label>
                                <button class="main3" name="part" value="${button.nextPart}">${button.text}</button>
                            </label>
                        </form>
                    </c:if>
                </c:forEach>
        </div>
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
