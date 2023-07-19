<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <style>
        .sign figcaption {
            margin: 0 auto 5px; /* Отступы вокруг абзаца */
        }
        .button_blue {
            background: -moz-linear-gradient(#D0ECF4, #5BC9E1, #D0ECF4);
            background: -webkit-gradient(linear, 0 0, 0  100%, from(#D0ECF4), to(#D0ECF4), color-stop(0.5, #5BC9E1));
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#00BBD6', endColorstr='#EBFFFF');
            padding: 3px 7px;
            color: #333;
            -moz-border-radius: 5px;
            -webkit-border-radius: 5px;
            border-radius: 5px;
            border: 1px solid #666;
            height:50px;
            font-size:100%;
        }
        .button_green {
            background: -moz-linear-gradient(#beffbf, #2df32f, #beffbf);
            background: -webkit-gradient(linear, 0 0, 0  100%, from(#beffbf), to(#beffbf), color-stop(0.5, #2df32f));
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#beffbf', endColorstr='#2df32f');
            padding: 3px 7px;
            color: #333;
            -moz-border-radius: 5px;
            -webkit-border-radius: 5px;
            border-radius: 5px;
            border: 1px solid #666;
            font-size:100%;
        }
        .button_red {
            background: -moz-linear-gradient(#f6cbd4, #fa5b5b, #f6cbd4);
            background: -webkit-gradient(linear, 0 0, 0  100%, from(#f6cbd4), to(#f6cbd4), color-stop(0.5, #fa5b5b));
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#f6cbd4', endColorstr='#fa5b5b');
            padding: 3px 7px;
            color: #333;
            -moz-border-radius: 5px;
            -webkit-border-radius: 5px;
            border-radius: 5px;
            border: 1px solid #666;
            font-size:100%;
        }
        .table_blur {
            background: #f5ffff;
            border-collapse: collapse;
            text-align: left;
        }
        .table_blur th {
            border-top: 1px solid #777777;
            border-bottom: 1px solid #777777;
            box-shadow: inset 0 1px 0 #999999, inset 0 -1px 0 #999999;
            background: linear-gradient(#9595b6, #5a567f);
            color: white;
            padding: 10px 15px;
            position: relative;
        }
        .table_blur th:after {
            content: "";
            display: block;
            position: absolute;
            left: 0;
            top: 25%;
            height: 25%;
            width: 100%;
            background: linear-gradient(rgba(255, 255, 255, 0), rgba(255,255,255,.08));
        }
        .table_blur tr:nth-child(odd) {
            background: #ebf3f9;
        }
        .table_blur th:first-child {
            border-left: 1px solid #777777;
            border-bottom:  1px solid #777777;
            box-shadow: inset 1px 1px 0 #999999, inset 0 -1px 0 #999999;
        }
        .table_blur th:last-child {
            border-right: 1px solid #777777;
            border-bottom:  1px solid #777777;
            box-shadow: inset -1px 1px 0 #999999, inset 0 -1px 0 #999999;
        }
        .table_blur td {
            border: 1px solid #e3eef7;
            padding: 10px 15px;
            position: relative;
            transition: all 0.5s ease;
        }
        .table_blur tbody:hover td {
            color: transparent;
            text-shadow: 0 0 3px #a09f9d;
        }
        .table_blur tbody:hover tr:hover td {
            color: #444444;
            text-shadow: none;
        }
    </style>

</head>
<body style="font-family: Arial">

<h2>Все сохранённые варианты данных</h2>
<br>

<table class="table_blur">
    <tr>
        <th>&emsp;Название&emsp;</th>
        <th>&emsp;Частота, Гц&emsp;</th>
        <th>&emsp;Напряжение, В&emsp;</th>
        <th>&emsp;&emsp;&emsp;Действия&emsp;&emsp;&emsp;</th>
    </tr>

    <c:forEach var="data" items="${allInitialData}">

        <c:url var="useButton" value="/useOldData">
            <c:param name="dataId" value="${data.id}"/>
        </c:url>

        <c:url var="deleteButton" value="/deleteOldData">
            <c:param name="dataId" value="${data.id}"/>
        </c:url>

        <tr>
            <td align="center"> ${data.name}</td>
            <td align="center"> ${data.f}</td>
            <td align="center"> ${data.u1}</td>
            <td>

                <input type="button" value="Выбрать"
                       onclick="window.location.href = '${useButton}'"
                       class="button_green"/>

                <input type="button" value="Удалить"
                       onclick="window.location.href = '${deleteButton}'"
                       class="button_red"/>

            </td>
        </tr>
    </c:forEach>

</table>
<br><br>
<input type="button" value="Вернуться на главную страницу"
       onclick="window.location.href = 'goToMain'" class="button_blue"/>
</body>
</html>