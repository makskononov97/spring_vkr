<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <style>
        .sign {
            float: right; /* Выравнивание по правому краю */
            border: 1px solid #333; /* Параметры рамки */
            padding: 7px; /* Поля внутри блока */
            margin: 10px 0 5px 5px; /* Отступы вокруг */
            background: #f0f0f0; /* Цвет фона */
            text-align: center; /* Выравнивание по центру */

        }
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
            height:50px;
            font-size:100%;
        }
    </style>
</head>
<body style="font-family: Arial">

<h2>Поверочный расчёт асинхронного двигателя</h2>
<br>
<h3>Введите данные или выбрите предыдущие</h3>
<br>
<figure class="sign">
    <img src="https://thumb.cloud.mail.ru/weblink/thumb/xw1/b3JK/VfSTEyNGF" alt="Асинхронный двигатель">
    <figcaption>Асинхронный двигатель</figcaption>
</figure>
    <input type="button" value="Выбрать из базы"
       onclick="window.location.href = 'showOldInitialData'" class="button_blue"/>
<br>
<form:form action="${pageContext.request.contextPath}/a" modelAttribute="data" method="post">

    <form:hidden path="id"/>
    <h4>Номинальные данные</h4>
    <br>
    Название <form:input path="name"/>
    <br><br>
    Мощность, Вт  <form:input path="pn"/>
    <br><br>
    Частота вращения, об/мин  <form:input path="nc"/>
    <br><br>
    Напряжение, В  <form:input path="u1"/>
    <br><br>
    Частота сети, Гц  <form:input path="f"/>
    <br><br>
    КПД, в о.е  <form:input path="KPD"/>
    <br><br>
    Коэффициент мощности, в о.е.  <form:input path="cos"/>
    <br><br>
    Высота оси вращения, м  <form:input path="h"/>
    <br><br>
    Диаметр вала м  <form:input path="DB"/>
    <br><br>
    <h4>Основные размеры сердечника статора</h4>
    <br>
    Марка стали <form:input path="st1"/>
    <br><br>
    Диаметр статора внешний, м  <form:input path="da"/>
    <br><br>
    Диаметр статора внутренний, м  <form:input path="d1"/>
    <br><br>
    Длина магниторповода статора, м  <form:input path="l1"/>
    <br><br>
    <figure class="sign">
        <img src="https://thumb.cloud.mail.ru/weblink/thumb/xw1/Bxf4/GnA7AZk6W" alt="Эскиз пазов статора">
        <figcaption>Эскиз пазов статора</figcaption>
    </figure>
    Число пазов статора  <form:input path="z1"/>
    <br><br>
    <h4>Размеры паза статора в штампе</h4>
    <br>
    b11 <form:input path="b11"/>
    <br><br>
    b12 <form:input path="b12"/>
    <br><br>
    h11 <form:input path="h11"/>
    <br><br>
    e11 <form:input path="e11"/>
    <br><br>
    m11 <form:input path="m11"/>
    <br><br>
    <h4>Основные размеры сердечника ротора</h4>
    <br>
    Марка стали <form:input path="st2"/>
    <br><br>
    Длина магниторповода ротора, м   <form:input path="l2"/>
    <br><br>
    Воздушный зазор, м  <form:input path="o"/>
    <br><br>
    Число пазов ротора <form:input path="z2"/>
    <br><br>
    <figure class="sign">
        <img src="https://thumb.cloud.mail.ru/weblink/thumb/xw1/TAdG/7FepKJ8je" alt="Эскиз пазов ротора">
        <figcaption>Эскиз пазов ротора</figcaption>
    </figure>
    Скоз пазов, м  <form:input path="bck"/>
    <br><br>
    <h4>Размеры паза ротора в штампе</h4>
    <br>
    b21 <form:input path="b21"/>
    <br><br>
    b22 <form:input path="b22"/>
    <br><br>
    h21 <form:input path="h21"/>
    <br><br>
    e21 <form:input path="e21"/>
    <br><br>
    m21 <form:input path="m21"/>
    <br><br>
    <h4>Параметры обмотки статора</h4>
    <br>
    Число эффективных проводников в пазу <form:input path="sp1"/>
    <br><br>
    Число параллельных ветвей обмотки <form:input path="a1"/>
    <br><br>
    Площадь сечения эффективного проводника, м2  <form:input path="qef"/>
    <br><br>
    Сечение изолированного проводника, м  <form:input path="dI"/>
    <br><br>
    Число элементарных проводников <form:input path="nef"/>
    <br><br>
    Шаг  <form:input path="yp1"/>
    <br><br>
    <input type="submit" name="createNewData" value="Сохранить в базу" class="button_blue">

    <input type="submit" name="sendDataToCalculation" value="Рассчитать" class="button_green"/>

    <br><br>

</form:form>

</body>
</html>

