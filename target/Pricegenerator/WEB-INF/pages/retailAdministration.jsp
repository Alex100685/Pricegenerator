<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
  <style>
    .image {
      ​width: 70%;
      height: 70%;
    }
    a:hover {
      font-weight:bold;
    }

    .search {
      display: inline-block;
      font-family: arial,sans-serif;
      font-size: 12px;
      font-weight: bold;
      color: rgb(68,68,68);
      text-decoration: none;
      user-select: none;
      padding: .2em 1.2em;
      outline: none;
      border: 1px solid rgba(0,0,0,.1);
      border-radius: 2px;
      background: rgb(245,245,245) linear-gradient(#f4f4f4, #f1f1f1);
      transition: all .218s ease 0s;
      cursor: pointer;
    }
    .search:hover {
      color: rgb(24,24,24);
      border: 1px solid rgb(198,198,198);
      background: #f7f7f7 linear-gradient(#f7f7f7, #f1f1f1);
      box-shadow: 0 1px 2px rgba(0,0,0,.1);
    }
    .search:active {
      color: rgb(51,51,51);
      border: 1px solid rgb(204,204,204);
      background: rgb(238,238,238) linear-gradient(rgb(238,238,238), rgb(224,224,224));
      box-shadow: 0 1px 2px rgba(0,0,0,.1) inset;
    }

    .DeleteButton {
      display: inline-block;
      font-family: arial,sans-serif;
      font-size: 12px;
      font-weight: bold;
      color: rgb(68,68,68);
      text-decoration: none;
      user-select: none;
      padding: .2em 1.2em;
      outline: none;
      border: 1px solid rgba(0,0,0,.1);
      border-radius: 2px;
      background: #f7f7f7 linear-gradient(#faa3a3, #ff7070);
      transition: all .218s ease 0s;
      cursor: pointer;
    }
    .DeleteButton:hover {
      color: rgb(24,24,24);
      border: 1px solid rgb(198,198,198);
      background: #f7f7f7 linear-gradient(#faa3a3, #ff7070);
      box-shadow: 0 1px 2px rgba(0,0,0,.1);
    }
    .ViewButton {

      display: inline-block;
      font-family: arial,sans-serif;
      font-size: 12px;
      font-weight: bold;
      color: rgb(68,68,68);
      text-decoration: none;
      user-select: none;
      padding: .2em 1.2em;
      outline: none;
      border: 1px solid rgba(0,0,0,.1);
      border-radius: 2px;
      background: rgb(245,245,245) linear-gradient(#f4f4f4, #f1f1f1);
      transition: all .218s ease 0s;
      cursor: pointer;
    }
    .ViewButton:hover {
      color: rgb(24,24,24);
      border: 1px solid rgb(198,198,198);
      background: #f7f7f7 linear-gradient(#f7f7f7, #f1f1f1);
      box-shadow: 0 1px 2px rgba(0,0,0,.1);

    }
    .flat-table {
      display: block;
      font-family: arial;
      -webkit-font-smoothing: antialiased;
      font-size: 115%;
      overflow: auto;
      width: auto;
    }
    th {
      background-color: rgb(112,196,105);
      color: white;
      font-weight: normal;
      padding: 5px 20px;
      text-align: center;
    }
    td {
      background-color: rgb(238, 238, 238);
      color: rgb(111, 111, 111);
      padding: 5px 20px;
    }
    .options{
      display: block;
    }

    div#formline*{
      display: inline-block;
    }
  </style>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>autoshop</title>
  <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script> -->
  <a title="Exit" href="<c:url value="/j_spring_security_logout"/>"><h4><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/logout.png"></h4></a>
  <a href="/admin/"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/home.png"></a>
</head>
<body>


<div class="container">

  <h3>Установить наценку в ценовых диапазонах</h3>
  <table class="table table-striped">

    <thead>
    <tr>
      <th align="center" style="width: 200px; "><b>Название поставщика</b></th>
      <th align="center" style="width: 200px; "><b>Меньше 100</b></th>
      <th align="center" style="width: 200px; "><b>От 100 до 300</b></th>
      <th align="center" style="width: 200px; "><b>От 300 до 500</b></th>
      <th align="center" style="width: 200px; "><b>От 500 до 1000</b></th>
      <th align="center" style="width: 200px; "><b>От 1000 до 5000</b></th>
      <th align="center" style="width: 200px; "><b>От 5000 до 10000</b></th>
      <th align="center" style="width: 200px; "><b>От 10000 до 20000</b></th>
      <th align="center" style="width: 200px; "><b>От 20000 до 50000</b></th>
      <th align="center" style="width: 200px; "><b>От 50000 до 100000</b></th>
      <th align="center" style="width: 200px; "><b>Больше 100000</b></th>
      <th align="center" style="width: 200px; "><b>Курс валюты</b></th>

      <th align="center" style="width: 200px; "><b>Сохранить</b></th>
    </tr>
    </thead>

    <tr>
      <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/setMarginAutotechix" method="post">
        <td align="center" style="width: 200px; ">Автотоехникс</td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[0].lessThanHundred}" name="lessThanHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[0].lessThanHundredFixed}" name="lessThanHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[0].fromHundredToTreeHundred}" name="fromHundredToTreeHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[0].fromHundredToTreeHundredFixed}" name="fromHundredToTreeHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[0].fromTreeHundredToFiveHundred}" name="fromTreeHundredToFiveHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[0].fromTreeHundredToFiveHundredFixed}" name="fromTreeHundredToFiveHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[0].fromFiveHundredToThousand}" name="fromFiveHundredToThousand"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[0].fromFiveHundredToThousandFixed}" name="fromFiveHundredToThousandFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[0].fromThousandToFiveThousands}" name="fromThousandToFiveThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[0].fromThousandToFiveThousandsFixed}" name="fromThousandToFiveThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[0].fromFiveToTenThousands}" name="fromFiveToTenThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[0].fromFiveToTenThousandsFixed}" name="fromFiveToTenThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[0].fromTenToTwentyThousands}" name="fromTenToTwentyThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[0].fromTenToTwentyThousandsFixed}" name="fromTenToTwentyThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[0].fromTwentyToFifty}" name="fromTwentyToFifty"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[0].fromTwentyToFiftyFixed}" name="fromTwentyToFiftyFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[0].fromFiftyToHundred}" name="fromFiftyToHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[0].fromFiftyToHundredFixed}" name="fromFiftyToHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[0].aboveHundredThousands}" name="aboveHundredThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[0].aboveHundredThousandsFixed}" name="aboveHundredThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[0].usdCurrencyRate}" name="usdCurrencyRate"></td>
        <td align="center" style="width: 200px; ">
          <button type="submit" class="search"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/save.png"></button>
        </td>
      </form>
    </tr>

    <tr>
      <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/setMarginIntercars" method="post">
        <td align="center" style="width: 200px; ">Интеркарс</td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[1].lessThanHundred}" name="lessThanHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[1].lessThanHundredFixed}" name="lessThanHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[1].fromHundredToTreeHundred}" name="fromHundredToTreeHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[1].fromHundredToTreeHundredFixed}" name="fromHundredToTreeHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[1].fromTreeHundredToFiveHundred}" name="fromTreeHundredToFiveHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[1].fromTreeHundredToFiveHundredFixed}" name="fromTreeHundredToFiveHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[1].fromFiveHundredToThousand}" name="fromFiveHundredToThousand"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[1].fromFiveHundredToThousandFixed}" name="fromFiveHundredToThousandFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[1].fromThousandToFiveThousands}" name="fromThousandToFiveThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[1].fromThousandToFiveThousandsFixed}" name="fromThousandToFiveThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[1].fromFiveToTenThousands}" name="fromFiveToTenThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[1].fromFiveToTenThousandsFixed}" name="fromFiveToTenThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[1].fromTenToTwentyThousands}" name="fromTenToTwentyThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[1].fromTenToTwentyThousandsFixed}" name="fromTenToTwentyThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[1].fromTwentyToFifty}" name="fromTwentyToFifty"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[1].fromTwentyToFiftyFixed}" name="fromTwentyToFiftyFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[1].fromFiftyToHundred}" name="fromFiftyToHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[1].fromFiftyToHundredFixed}" name="fromFiftyToHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[1].aboveHundredThousands}" name="aboveHundredThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[1].aboveHundredThousandsFixed}" name="aboveHundredThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[1].usdCurrencyRate}" name="usdCurrencyRate"></td>
        <td align="center" style="width: 200px; ">
          <button type="submit" class="search"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/save.png"></button>
        </td>
      </form>
    </tr>

    <tr>
      <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/setMarginVlad" method="post">
        <td align="center" style="width: 200px; ">Влад</td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[2].lessThanHundred}" name="lessThanHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[2].lessThanHundredFixed}" name="lessThanHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[2].fromHundredToTreeHundred}" name="fromHundredToTreeHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[2].fromHundredToTreeHundredFixed}" name="fromHundredToTreeHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[2].fromTreeHundredToFiveHundred}" name="fromTreeHundredToFiveHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[2].fromTreeHundredToFiveHundredFixed}" name="fromTreeHundredToFiveHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[2].fromFiveHundredToThousand}" name="fromFiveHundredToThousand"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[2].fromFiveHundredToThousandFixed}" name="fromFiveHundredToThousandFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[2].fromThousandToFiveThousands}" name="fromThousandToFiveThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[2].fromThousandToFiveThousandsFixed}" name="fromThousandToFiveThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[2].fromFiveToTenThousands}" name="fromFiveToTenThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[2].fromFiveToTenThousandsFixed}" name="fromFiveToTenThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[2].fromTenToTwentyThousands}" name="fromTenToTwentyThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[2].fromTenToTwentyThousandsFixed}" name="fromTenToTwentyThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[2].fromTwentyToFifty}" name="fromTwentyToFifty"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[2].fromTwentyToFiftyFixed}" name="fromTwentyToFiftyFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[2].fromFiftyToHundred}" name="fromFiftyToHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[2].fromFiftyToHundredFixed}" name="fromFiftyToHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[2].aboveHundredThousands}" name="aboveHundredThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[2].aboveHundredThousandsFixed}" name="aboveHundredThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[2].usdCurrencyRate}" name="usdCurrencyRate"></td>
        <td align="center" style="width: 200px; ">
          <button type="submit" class="search"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/save.png"></button>
        </td>
      </form>
    </tr>

    <tr>
      <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/setMarginElit" method="post">
        <td align="center" style="width: 200px; ">Элит</td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[3].lessThanHundred}" name="lessThanHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[3].lessThanHundredFixed}" name="lessThanHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[3].fromHundredToTreeHundred}" name="fromHundredToTreeHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[3].fromHundredToTreeHundredFixed}" name="fromHundredToTreeHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[3].fromTreeHundredToFiveHundred}" name="fromTreeHundredToFiveHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[3].fromTreeHundredToFiveHundredFixed}" name="fromTreeHundredToFiveHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[3].fromFiveHundredToThousand}" name="fromFiveHundredToThousand"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[3].fromFiveHundredToThousandFixed}" name="fromFiveHundredToThousandFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[3].fromThousandToFiveThousands}" name="fromThousandToFiveThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[3].fromThousandToFiveThousandsFixed}" name="fromThousandToFiveThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[3].fromFiveToTenThousands}" name="fromFiveToTenThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[3].fromFiveToTenThousandsFixed}" name="fromFiveToTenThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[3].fromTenToTwentyThousands}" name="fromTenToTwentyThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[3].fromTenToTwentyThousandsFixed}" name="fromTenToTwentyThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[3].fromTwentyToFifty}" name="fromTwentyToFifty"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[3].fromTwentyToFiftyFixed}" name="fromTwentyToFiftyFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[3].fromFiftyToHundred}" name="fromFiftyToHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[3].fromFiftyToHundredFixed}" name="fromFiftyToHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[3].aboveHundredThousands}" name="aboveHundredThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[3].aboveHundredThousandsFixed}" name="aboveHundredThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[3].usdCurrencyRate}" name="usdCurrencyRate"></td>
        <td align="center" style="width: 200px; ">
          <button type="submit" class="search"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/save.png"></button>
        </td>
      </form>
    </tr>

    <tr>
      <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/setMarginGenstar" method="post">
        <td align="center" style="width: 200px; ">Генстар</td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[4].lessThanHundred}" name="lessThanHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[4].lessThanHundredFixed}" name="lessThanHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[4].fromHundredToTreeHundred}" name="fromHundredToTreeHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[4].fromHundredToTreeHundredFixed}" name="fromHundredToTreeHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[4].fromTreeHundredToFiveHundred}" name="fromTreeHundredToFiveHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[4].fromTreeHundredToFiveHundredFixed}" name="fromTreeHundredToFiveHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[4].fromFiveHundredToThousand}" name="fromFiveHundredToThousand"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[4].fromFiveHundredToThousandFixed}" name="fromFiveHundredToThousandFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[4].fromThousandToFiveThousands}" name="fromThousandToFiveThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[4].fromThousandToFiveThousandsFixed}" name="fromThousandToFiveThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[4].fromFiveToTenThousands}" name="fromFiveToTenThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[4].fromFiveToTenThousandsFixed}" name="fromFiveToTenThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[4].fromTenToTwentyThousands}" name="fromTenToTwentyThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[4].fromTenToTwentyThousandsFixed}" name="fromTenToTwentyThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[4].fromTwentyToFifty}" name="fromTwentyToFifty"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[4].fromTwentyToFiftyFixed}" name="fromTwentyToFiftyFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[4].fromFiftyToHundred}" name="fromFiftyToHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[4].fromFiftyToHundredFixed}" name="fromFiftyToHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[4].aboveHundredThousands}" name="aboveHundredThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[4].aboveHundredThousandsFixed}" name="aboveHundredThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[4].usdCurrencyRate}" name="usdCurrencyRate"></td>
        <td align="center" style="width: 200px; ">
          <button type="submit" class="search"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/save.png"></button>
        </td>
      </form>
    </tr>

    <tr>
      <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/setMarginAmperis" method="post">
        <td align="center" style="width: 200px; ">Амперис</td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[5].lessThanHundred}" name="lessThanHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[5].lessThanHundredFixed}" name="lessThanHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[5].fromHundredToTreeHundred}" name="fromHundredToTreeHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[5].fromHundredToTreeHundredFixed}" name="fromHundredToTreeHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[5].fromTreeHundredToFiveHundred}" name="fromTreeHundredToFiveHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[5].fromTreeHundredToFiveHundredFixed}" name="fromTreeHundredToFiveHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[5].fromFiveHundredToThousand}" name="fromFiveHundredToThousand"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[5].fromFiveHundredToThousandFixed}" name="fromFiveHundredToThousandFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[5].fromThousandToFiveThousands}" name="fromThousandToFiveThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[5].fromThousandToFiveThousandsFixed}" name="fromThousandToFiveThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[5].fromFiveToTenThousands}" name="fromFiveToTenThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[5].fromFiveToTenThousandsFixed}" name="fromFiveToTenThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[5].fromTenToTwentyThousands}" name="fromTenToTwentyThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[5].fromTenToTwentyThousandsFixed}" name="fromTenToTwentyThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[5].fromTwentyToFifty}" name="fromTwentyToFifty"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[5].fromTwentyToFiftyFixed}" name="fromTwentyToFiftyFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[5].fromFiftyToHundred}" name="fromFiftyToHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[5].fromFiftyToHundredFixed}" name="fromFiftyToHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[5].aboveHundredThousands}" name="aboveHundredThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[5].aboveHundredThousandsFixed}" name="aboveHundredThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[5].usdCurrencyRate}" name="usdCurrencyRate"></td>
        <td align="center" style="width: 200px; ">
          <button type="submit" class="search"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/save.png"></button>
        </td>
      </form>
    </tr>


  </table>

  <h3></h3>





</div>
</body>

</html>