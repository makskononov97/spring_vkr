<%@ page language="java" pageEncoding="UTF-8"%>

<html>
<head>

    <title>Title</title>
    <style>
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
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        google.charts.load('current', {'packages':['corechart']});
        google.charts.setOnLoadCallback(drawChart);

        function drawChart() {
            var data = google.visualization.arrayToDataTable([
                ['P2, Вт',            'I1, А'],
                [${result.r_PnK0},    ${result.r_Ioo90}],
                [${result.r_PnK1},    ${result.r_Ioo91}],
                [${result.r_PnK2},    ${result.r_Ioo92}],
                [${result.r_PnK3},    ${result.r_Ioo93}],
                [${result.r_PnK4},    ${result.r_Ioo94}]

        ]);

            var options = {
                title: 'Рабочие характеристики I1 = f (P2)',
                curveType: 'function',
                legend: { position: 'bottom' }
            };

            var chart = new google.visualization.LineChart(document.getElementById('curve_chart1'));

            chart.draw(data, options);
        }
    </script>
    <script type="text/javascript">
        google.charts.load('current', {'packages':['corechart']});
        google.charts.setOnLoadCallback(drawChart);

        function drawChart() {
            var data = google.visualization.arrayToDataTable([
                ['P2, Вт',            'η'],
                [${result.r_PnK0},    ${result.r_KPDp90}],
                [${result.r_PnK1},    ${result.r_KPDp91}],
                [${result.r_PnK2},    ${result.r_KPDp92}],
                [${result.r_PnK3},    ${result.r_KPDp93}],
                [${result.r_PnK4},    ${result.r_KPDp94}]

            ]);

            var options = {
                title: 'Рабочие характеристики η = f (P2)',
                curveType: 'function',
                legend: { position: 'bottom' }
            };

            var chart = new google.visualization.LineChart(document.getElementById('curve_chart2'));

            chart.draw(data, options);
        }
    </script>
    <script type="text/javascript">
        google.charts.load('current', {'packages':['corechart']});
        google.charts.setOnLoadCallback(drawChart);

        function drawChart() {
            var data = google.visualization.arrayToDataTable([
                ['P2, Вт',            'cosφ'],
                [${result.r_PnK0},    ${result.r_cos090}],
                [${result.r_PnK1},    ${result.r_cos091}],
                [${result.r_PnK2},    ${result.r_cos092}],
                [${result.r_PnK3},    ${result.r_cos093}],
                [${result.r_PnK4},    ${result.r_cos094}]

            ]);

            var options = {
                title: 'Рабочие характеристики cosφ = f (P2)',
                curveType: 'function',
                legend: { position: 'bottom' }
            };

            var chart = new google.visualization.LineChart(document.getElementById('curve_chart3'));

            chart.draw(data, options);
        }
    </script>
    <script type="text/javascript">
        google.charts.load('current', {'packages':['corechart']});
        google.charts.setOnLoadCallback(drawChart);

        function drawChart() {
            var data = google.visualization.arrayToDataTable([
                ['P2, Вт',            's'],
                [${result.r_PnK0},    ${result.r_Sn90}],
                [${result.r_PnK1},    ${result.r_Sn91}],
                [${result.r_PnK2},    ${result.r_Sn92}],
                [${result.r_PnK3},    ${result.r_Sn93}],
                [${result.r_PnK4},    ${result.r_Sn94}]

            ]);

            var options = {
                title: 'Рабочие характеристики s = f (P2)',
                curveType: 'function',
                legend: { position: 'bottom' }
            };

            var chart = new google.visualization.LineChart(document.getElementById('curve_chart4'));

            chart.draw(data, options);
        }
    </script>
    <script type="text/javascript">
        google.charts.load('current', {'packages':['corechart']});
        google.charts.setOnLoadCallback(drawChart);

        function drawChart() {
            var data = google.visualization.arrayToDataTable([
                ['P2, Вт',            'M, Нм'],
                [${result.r_PnK0},    ${result.r_M90}],
                [${result.r_PnK1},    ${result.r_M91}],
                [${result.r_PnK2},    ${result.r_M92}],
                [${result.r_PnK3},    ${result.r_M93}],
                [${result.r_PnK4},    ${result.r_M94}]

            ]);

            var options = {
                title: 'Рабочие характеристики M = f (P2)',
                curveType: 'function',
                legend: { position: 'bottom' }
            };

            var chart = new google.visualization.LineChart(document.getElementById('curve_chart5'));

            chart.draw(data, options);
        }
    </script>
</head>
<body style="font-family: Arial">
<br>
Произведен расчет  ЭМ - ${result.r_name};&emsp;Мощность - ${result.r_Pn} Вт;&emsp;Напряжение - ${result.r_U1} В;
&emsp;Частота сети - ${result.r_f} Гц
<br><br>

<table class="table_blur">

    <tr>
    <td align="center">&emsp;Ток намагничивания&emsp;</td>
    <td align="center">&emsp;${result.r_Im} А&emsp;</td>
    <td align="center">&emsp;${result.r_Imo} о.е.&emsp;</td>
    </tr>

    <tr>
        <td align="center">&emsp;Активное сопротивление фазы обмотки статора&emsp;</td>
        <td align="center">&emsp;${result.r_r1} Ом&emsp;</td>
        <td align="center">&emsp;${result.r_r1o} о.е.&emsp;</td>
    </tr>

    <tr>
        <td align="center">&emsp;Реактивное сопротивление фазы обмотки статора&emsp;</td>
        <td align="center">&emsp;${result.r_x1} Ом&emsp;</td>
        <td align="center">&emsp;${result.r_x1o} о.е.&emsp;</td>
    </tr>

    <tr>
        <td align="center">&emsp;Главное индуктивное сопротивление&emsp;</td>
        <td align="center">&emsp;${result.r_xm} Ом&emsp;</td>
        <td align="center">&emsp;${result.r_xmo} о.е.&emsp;</td>
    </tr>

    <tr>
        <td align="center">&emsp;Активное сопротивление ротора, приведенное к статору&emsp;</td>
        <td align="center">&emsp;${result.r_r2I} Ом&emsp;</td>
        <td align="center">&emsp;${result.r_r2Io} о.е.&emsp;</td>
    </tr>

    <tr>
        <td align="center">&emsp;Реактивное сопротивление ротора, приведенное к статору&emsp;</td>
        <td align="center">&emsp;${result.r_x2I} Ом&emsp;</td>
        <td align="center">&emsp;${result.r_x2Io} о.е.&emsp;</td>
    </tr>
</table>
<br><br>
Рабочие характеристики (I<sub>1</sub>, η, cosφ, s, M) = f (P2)
<br><br>
<table class="table_blur">
    <tr>
        <th align="center">k</th>
        <th align="center">k*Pn, Вт</th>
        <th align="center">I<sub>1</sub>,А</th>
        <th align="center">η, %</th>
        <th align="center">cosφ</th>
        <th align="center">s</th>
        <th align="center">M, Нм</th>
    </tr>
    <tr>
        <td align="center">&emsp;0,25&emsp;</td>
        <td align="center">&emsp;${result.r_PnK0}&emsp;</td>
        <td align="center">&emsp;${result.r_Ioo90}&emsp;</td>
        <td align="center">&emsp;${result.r_KPDp90}&emsp;</td>
        <td align="center">&emsp;${result.r_cos090}&emsp;</td>
        <td align="center">&emsp;${result.r_Sn90}&emsp;</td>
        <td align="center">&emsp;${result.r_M90}&emsp;</td>
    </tr>
    <tr>
        <td align="center">&emsp;0,5&emsp;</td>
        <td align="center">&emsp;${result.r_PnK1}&emsp;</td>
        <td align="center">&emsp;${result.r_Ioo91}&emsp;</td>
        <td align="center">&emsp;${result.r_KPDp91}&emsp;</td>
        <td align="center">&emsp;${result.r_cos091}&emsp;</td>
        <td align="center">&emsp;${result.r_Sn91}&emsp;</td>
        <td align="center">&emsp;${result.r_M91}&emsp;</td>
    </tr>
    <tr>
        <td align="center">&emsp;0,75&emsp;</td>
        <td align="center">&emsp;${result.r_PnK2}&emsp;</td>
        <td align="center">&emsp;${result.r_Ioo92}&emsp;</td>
        <td align="center">&emsp;${result.r_KPDp92}&emsp;</td>
        <td align="center">&emsp;${result.r_cos092}&emsp;</td>
        <td align="center">&emsp;${result.r_Sn92}&emsp;</td>
        <td align="center">&emsp;${result.r_M92}&emsp;</td>
    </tr>
    <tr>
        <td align="center">&emsp;1&emsp;</td>
        <td align="center">&emsp;${result.r_PnK3}&emsp;</td>
        <td align="center">&emsp;${result.r_Ioo93}&emsp;</td>
        <td align="center">&emsp;${result.r_KPDp93}&emsp;</td>
        <td align="center">&emsp;${result.r_cos093}&emsp;</td>
        <td align="center">&emsp;${result.r_Sn93}&emsp;</td>
        <td align="center">&emsp;${result.r_M93}&emsp;</td>
    </tr>
    <tr>
        <td align="center">&emsp;1.25&emsp;</td>
        <td align="center">&emsp;${result.r_PnK4}&emsp;</td>
        <td align="center">&emsp;${result.r_Ioo94}&emsp;</td>
        <td align="center">&emsp;${result.r_KPDp94}&emsp;</td>
        <td align="center">&emsp;${result.r_cos094}&emsp;</td>
        <td align="center">&emsp;${result.r_Sn94}&emsp;</td>
        <td align="center">&emsp;${result.r_M94}&emsp;</td>
    </tr>
</table>
<br>
<div id="curve_chart1" style="width: 600px; height: 400px"; align="left"></div>
<div id="curve_chart2" style="width: 600px; height: 400px"; align="right"></div>
<div id="curve_chart3" style="width: 600px; height: 400px"></div>
<div id="curve_chart4" style="width: 600px; height: 400px"></div>
<div id="curve_chart5" style="width: 600px; height: 400px"></div>
<br>
Коэффициент заполнения паза -&emsp;${result.r_kpZ}
<br><br>
Кратность максимальнного момента -&emsp;${result.r_KMM}
<br><br>
Кратность пускового момента -&emsp;${result.r_Mp}
<br><br>
Кратность пускового тока -&emsp;${result.r_KpyckI}
<br><br>
Среднее превышение t обмотки статора над t окр. среды -&emsp;${result.r_O1}&emsp; <sup>o</sup>C
<br><br>

<br>
<input type="button" value="Вернуться на главную страницу (другого не дано)"
       onclick="window.location.href = 'goToMain'" class="button_blue"/>

</body>
</html>
