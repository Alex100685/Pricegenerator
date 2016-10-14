<%@ page import="ua.autoshop.utils.currencyrate.Currency" %>
<%@ page import="ua.autoshop.utils.currencyrate.CurrencyRateReader" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="ua.autoshop.utils.currencyrate.CurrencyList" %>
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

  <table class="table table-striped">

    <%
      CurrencyList currencyList = null;
      String date = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
      String usdRate = "N/A";
      String eurRate = "N/A";
      try{
        currencyList = CurrencyRateReader.getCurrencies();
      }catch (Exception e){

      }
      if(currencyList!=null){
        for(Currency currency: currencyList.getCurrencies()){
          if(currency.getDigital_code().equalsIgnoreCase("840")){
            usdRate = currency.getExchange_rate();
          }
          if(currency.getDigital_code().equalsIgnoreCase("978")){
            eurRate = currency.getExchange_rate();
          }
        }
      }
    %>

    <h3>Курсы валют установленные НБУ</h3>

    <thead>
    <tr>
      <th align="center" style="width: 200px; "><b>Дата</b></th>
      <th align="center" style="width: 200px; "><b>100 USD</b></th>
      <th align="center" style="width: 200px; "><b>100 EUR</b></th>
    </tr>
    </thead>
    <thead>
    <tr>


      <td align="center" style="width: 200px; "><% out.println(date); %></td>
      <td align="center" style="width: 200px; "><% out.println(usdRate);%></td>
      <td align="center" style="width: 200px; "><% out.println(eurRate); %></td>
    </tr>
    </thead>

  </table>

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
        <td align="center" style="width: 200px; ">Автотоехникс РОЗНИЦА</td>
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
      <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/setMarginAutotechixWholesale" method="post">
        <td align="center" style="width: 200px; ">Автотоехникс ОПТ</td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[8].lessThanHundred}" name="lessThanHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[8].lessThanHundredFixed}" name="lessThanHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[8].fromHundredToTreeHundred}" name="fromHundredToTreeHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[8].fromHundredToTreeHundredFixed}" name="fromHundredToTreeHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[8].fromTreeHundredToFiveHundred}" name="fromTreeHundredToFiveHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[8].fromTreeHundredToFiveHundredFixed}" name="fromTreeHundredToFiveHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[8].fromFiveHundredToThousand}" name="fromFiveHundredToThousand"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[8].fromFiveHundredToThousandFixed}" name="fromFiveHundredToThousandFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[8].fromThousandToFiveThousands}" name="fromThousandToFiveThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[8].fromThousandToFiveThousandsFixed}" name="fromThousandToFiveThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[8].fromFiveToTenThousands}" name="fromFiveToTenThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[8].fromFiveToTenThousandsFixed}" name="fromFiveToTenThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[8].fromTenToTwentyThousands}" name="fromTenToTwentyThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[8].fromTenToTwentyThousandsFixed}" name="fromTenToTwentyThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[8].fromTwentyToFifty}" name="fromTwentyToFifty"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[8].fromTwentyToFiftyFixed}" name="fromTwentyToFiftyFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[8].fromFiftyToHundred}" name="fromFiftyToHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[8].fromFiftyToHundredFixed}" name="fromFiftyToHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[8].aboveHundredThousands}" name="aboveHundredThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[8].aboveHundredThousandsFixed}" name="aboveHundredThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[8].usdCurrencyRate}" name="usdCurrencyRate"></td>
        <td align="center" style="width: 200px; ">
          <button type="submit" class="search"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/save.png"></button>
        </td>
      </form>
    </tr>

    <tr>
      <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/setMarginIntercars" method="post">
        <td align="center" style="width: 200px; ">Интеркарс РОЗНИЦА</td>
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
      <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/setMarginIntercarsWholesale" method="post">
        <td align="center" style="width: 200px; ">Интеркарс ОПТ</td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[9].lessThanHundred}" name="lessThanHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[9].lessThanHundredFixed}" name="lessThanHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[9].fromHundredToTreeHundred}" name="fromHundredToTreeHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[9].fromHundredToTreeHundredFixed}" name="fromHundredToTreeHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[9].fromTreeHundredToFiveHundred}" name="fromTreeHundredToFiveHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[9].fromTreeHundredToFiveHundredFixed}" name="fromTreeHundredToFiveHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[9].fromFiveHundredToThousand}" name="fromFiveHundredToThousand"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[9].fromFiveHundredToThousandFixed}" name="fromFiveHundredToThousandFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[9].fromThousandToFiveThousands}" name="fromThousandToFiveThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[9].fromThousandToFiveThousandsFixed}" name="fromThousandToFiveThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[9].fromFiveToTenThousands}" name="fromFiveToTenThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[9].fromFiveToTenThousandsFixed}" name="fromFiveToTenThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[9].fromTenToTwentyThousands}" name="fromTenToTwentyThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[9].fromTenToTwentyThousandsFixed}" name="fromTenToTwentyThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[9].fromTwentyToFifty}" name="fromTwentyToFifty"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[9].fromTwentyToFiftyFixed}" name="fromTwentyToFiftyFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[9].fromFiftyToHundred}" name="fromFiftyToHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[9].fromFiftyToHundredFixed}" name="fromFiftyToHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[9].aboveHundredThousands}" name="aboveHundredThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[9].aboveHundredThousandsFixed}" name="aboveHundredThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[9].usdCurrencyRate}" name="usdCurrencyRate"></td>
        <td align="center" style="width: 200px; ">
          <button type="submit" class="search"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/save.png"></button>
        </td>
      </form>
    </tr>

    <tr>
      <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/setMarginVlad" method="post">
        <td align="center" style="width: 200px; ">Влад РОЗНИЦА</td>
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
      <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/setMarginVladWholesale" method="post">
        <td align="center" style="width: 200px; ">Влад ОПТ</td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[10].lessThanHundred}" name="lessThanHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[10].lessThanHundredFixed}" name="lessThanHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[10].fromHundredToTreeHundred}" name="fromHundredToTreeHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[10].fromHundredToTreeHundredFixed}" name="fromHundredToTreeHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[10].fromTreeHundredToFiveHundred}" name="fromTreeHundredToFiveHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[10].fromTreeHundredToFiveHundredFixed}" name="fromTreeHundredToFiveHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[10].fromFiveHundredToThousand}" name="fromFiveHundredToThousand"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[10].fromFiveHundredToThousandFixed}" name="fromFiveHundredToThousandFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[10].fromThousandToFiveThousands}" name="fromThousandToFiveThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[10].fromThousandToFiveThousandsFixed}" name="fromThousandToFiveThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[10].fromFiveToTenThousands}" name="fromFiveToTenThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[10].fromFiveToTenThousandsFixed}" name="fromFiveToTenThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[10].fromTenToTwentyThousands}" name="fromTenToTwentyThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[10].fromTenToTwentyThousandsFixed}" name="fromTenToTwentyThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[10].fromTwentyToFifty}" name="fromTwentyToFifty"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[10].fromTwentyToFiftyFixed}" name="fromTwentyToFiftyFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[10].fromFiftyToHundred}" name="fromFiftyToHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[10].fromFiftyToHundredFixed}" name="fromFiftyToHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[10].aboveHundredThousands}" name="aboveHundredThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[10].aboveHundredThousandsFixed}" name="aboveHundredThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[10].usdCurrencyRate}" name="usdCurrencyRate"></td>
        <td align="center" style="width: 200px; ">
          <button type="submit" class="search"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/save.png"></button>
        </td>
      </form>
    </tr>

    <tr>
      <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/setMarginElit" method="post">
        <td align="center" style="width: 200px; ">Элит РОЗНИЦА</td>
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
      <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/setMarginElitWholesale" method="post">
        <td align="center" style="width: 200px; ">Элит ОПТ</td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[11].lessThanHundred}" name="lessThanHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[11].lessThanHundredFixed}" name="lessThanHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[11].fromHundredToTreeHundred}" name="fromHundredToTreeHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[11].fromHundredToTreeHundredFixed}" name="fromHundredToTreeHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[11].fromTreeHundredToFiveHundred}" name="fromTreeHundredToFiveHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[11].fromTreeHundredToFiveHundredFixed}" name="fromTreeHundredToFiveHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[11].fromFiveHundredToThousand}" name="fromFiveHundredToThousand"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[11].fromFiveHundredToThousandFixed}" name="fromFiveHundredToThousandFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[11].fromThousandToFiveThousands}" name="fromThousandToFiveThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[11].fromThousandToFiveThousandsFixed}" name="fromThousandToFiveThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[11].fromFiveToTenThousands}" name="fromFiveToTenThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[11].fromFiveToTenThousandsFixed}" name="fromFiveToTenThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[11].fromTenToTwentyThousands}" name="fromTenToTwentyThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[11].fromTenToTwentyThousandsFixed}" name="fromTenToTwentyThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[11].fromTwentyToFifty}" name="fromTwentyToFifty"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[11].fromTwentyToFiftyFixed}" name="fromTwentyToFiftyFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[11].fromFiftyToHundred}" name="fromFiftyToHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[11].fromFiftyToHundredFixed}" name="fromFiftyToHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[11].aboveHundredThousands}" name="aboveHundredThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[11].aboveHundredThousandsFixed}" name="aboveHundredThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[11].usdCurrencyRate}" name="usdCurrencyRate"></td>
        <td align="center" style="width: 200px; ">
          <button type="submit" class="search"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/save.png"></button>
        </td>
      </form>
    </tr>

    <tr>
    <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/setMarginGenstar" method="post">
      <td align="center" style="width: 200px; ">Генстар РОЗНИЦА</td>
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
      <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/setMarginGenstarWholesale" method="post">
        <td align="center" style="width: 200px; ">Генстар ОПТ</td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[12].lessThanHundred}" name="lessThanHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[12].lessThanHundredFixed}" name="lessThanHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[12].fromHundredToTreeHundred}" name="fromHundredToTreeHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[12].fromHundredToTreeHundredFixed}" name="fromHundredToTreeHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[12].fromTreeHundredToFiveHundred}" name="fromTreeHundredToFiveHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[12].fromTreeHundredToFiveHundredFixed}" name="fromTreeHundredToFiveHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[12].fromFiveHundredToThousand}" name="fromFiveHundredToThousand"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[12].fromFiveHundredToThousandFixed}" name="fromFiveHundredToThousandFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[12].fromThousandToFiveThousands}" name="fromThousandToFiveThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[12].fromThousandToFiveThousandsFixed}" name="fromThousandToFiveThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[12].fromFiveToTenThousands}" name="fromFiveToTenThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[12].fromFiveToTenThousandsFixed}" name="fromFiveToTenThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[12].fromTenToTwentyThousands}" name="fromTenToTwentyThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[12].fromTenToTwentyThousandsFixed}" name="fromTenToTwentyThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[12].fromTwentyToFifty}" name="fromTwentyToFifty"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[12].fromTwentyToFiftyFixed}" name="fromTwentyToFiftyFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[12].fromFiftyToHundred}" name="fromFiftyToHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[12].fromFiftyToHundredFixed}" name="fromFiftyToHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[12].aboveHundredThousands}" name="aboveHundredThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[12].aboveHundredThousandsFixed}" name="aboveHundredThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[12].usdCurrencyRate}" name="usdCurrencyRate"></td>
        <td align="center" style="width: 200px; ">
          <button type="submit" class="search"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/save.png"></button>
        </td>
      </form>
    </tr>

    <tr>
      <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/setMarginAmperis" method="post">
        <td align="center" style="width: 200px; ">Амперис РОЗНИЦА</td>
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

    <tr>
      <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/setMarginAmperisWholesale" method="post">
        <td align="center" style="width: 200px; ">Амперис ОПТ</td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[13].lessThanHundred}" name="lessThanHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[13].lessThanHundredFixed}" name="lessThanHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[13].fromHundredToTreeHundred}" name="fromHundredToTreeHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[13].fromHundredToTreeHundredFixed}" name="fromHundredToTreeHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[13].fromTreeHundredToFiveHundred}" name="fromTreeHundredToFiveHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[13].fromTreeHundredToFiveHundredFixed}" name="fromTreeHundredToFiveHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[13].fromFiveHundredToThousand}" name="fromFiveHundredToThousand"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[13].fromFiveHundredToThousandFixed}" name="fromFiveHundredToThousandFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[13].fromThousandToFiveThousands}" name="fromThousandToFiveThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[13].fromThousandToFiveThousandsFixed}" name="fromThousandToFiveThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[13].fromFiveToTenThousands}" name="fromFiveToTenThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[13].fromFiveToTenThousandsFixed}" name="fromFiveToTenThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[13].fromTenToTwentyThousands}" name="fromTenToTwentyThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[13].fromTenToTwentyThousandsFixed}" name="fromTenToTwentyThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[13].fromTwentyToFifty}" name="fromTwentyToFifty"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[13].fromTwentyToFiftyFixed}" name="fromTwentyToFiftyFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[13].fromFiftyToHundred}" name="fromFiftyToHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[13].fromFiftyToHundredFixed}" name="fromFiftyToHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[13].aboveHundredThousands}" name="aboveHundredThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[13].aboveHundredThousandsFixed}" name="aboveHundredThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[13].usdCurrencyRate}" name="usdCurrencyRate"></td>
        <td align="center" style="width: 200px; ">
          <button type="submit" class="search"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/save.png"></button>
        </td>
      </form>
    </tr>

    <tr>
      <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/setMarginTomarket" method="post">
        <td align="center" style="width: 200px; ">ТО Маркет РОЗНИЦА</td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[6].lessThanHundred}" name="lessThanHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[6].lessThanHundredFixed}" name="lessThanHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[6].fromHundredToTreeHundred}" name="fromHundredToTreeHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[6].fromHundredToTreeHundredFixed}" name="fromHundredToTreeHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[6].fromTreeHundredToFiveHundred}" name="fromTreeHundredToFiveHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[6].fromTreeHundredToFiveHundredFixed}" name="fromTreeHundredToFiveHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[6].fromFiveHundredToThousand}" name="fromFiveHundredToThousand"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[6].fromFiveHundredToThousandFixed}" name="fromFiveHundredToThousandFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[6].fromThousandToFiveThousands}" name="fromThousandToFiveThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[6].fromThousandToFiveThousandsFixed}" name="fromThousandToFiveThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[6].fromFiveToTenThousands}" name="fromFiveToTenThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[6].fromFiveToTenThousandsFixed}" name="fromFiveToTenThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[6].fromTenToTwentyThousands}" name="fromTenToTwentyThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[6].fromTenToTwentyThousandsFixed}" name="fromTenToTwentyThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[6].fromTwentyToFifty}" name="fromTwentyToFifty"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[6].fromTwentyToFiftyFixed}" name="fromTwentyToFiftyFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[6].fromFiftyToHundred}" name="fromFiftyToHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[6].fromFiftyToHundredFixed}" name="fromFiftyToHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[6].aboveHundredThousands}" name="aboveHundredThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[6].aboveHundredThousandsFixed}" name="aboveHundredThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[6].usdCurrencyRate}" name="usdCurrencyRate"></td>
        <td align="center" style="width: 200px; ">
          <button type="submit" class="search"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/save.png"></button>
        </td>
      </form>
    </tr>
    <tr>
      <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/setMarginTomarketWholesale" method="post">
        <td align="center" style="width: 200px; ">ТО Маркет ОПТ</td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[7].lessThanHundred}" name="lessThanHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[7].lessThanHundredFixed}" name="lessThanHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[7].fromHundredToTreeHundred}" name="fromHundredToTreeHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[7].fromHundredToTreeHundredFixed}" name="fromHundredToTreeHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[7].fromTreeHundredToFiveHundred}" name="fromTreeHundredToFiveHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[7].fromTreeHundredToFiveHundredFixed}" name="fromTreeHundredToFiveHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[7].fromFiveHundredToThousand}" name="fromFiveHundredToThousand"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[7].fromFiveHundredToThousandFixed}" name="fromFiveHundredToThousandFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[7].fromThousandToFiveThousands}" name="fromThousandToFiveThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[7].fromThousandToFiveThousandsFixed}" name="fromThousandToFiveThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[7].fromFiveToTenThousands}" name="fromFiveToTenThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[7].fromFiveToTenThousandsFixed}" name="fromFiveToTenThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[7].fromTenToTwentyThousands}" name="fromTenToTwentyThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[7].fromTenToTwentyThousandsFixed}" name="fromTenToTwentyThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[7].fromTwentyToFifty}" name="fromTwentyToFifty"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[7].fromTwentyToFiftyFixed}" name="fromTwentyToFiftyFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[7].fromFiftyToHundred}" name="fromFiftyToHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[7].fromFiftyToHundredFixed}" name="fromFiftyToHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[7].aboveHundredThousands}" name="aboveHundredThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[7].aboveHundredThousandsFixed}" name="aboveHundredThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[7].usdCurrencyRate}" name="usdCurrencyRate"></td>
        <td align="center" style="width: 200px; ">
          <button type="submit" class="search"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/save.png"></button>
        </td>
      </form>
    </tr>

    <tr>
      <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/setMarginUnicTrade" method="post">
        <td align="center" style="width: 200px; ">Юник трейд РОЗНИЦА</td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[14].lessThanHundred}" name="lessThanHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[14].lessThanHundredFixed}" name="lessThanHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[14].fromHundredToTreeHundred}" name="fromHundredToTreeHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[14].fromHundredToTreeHundredFixed}" name="fromHundredToTreeHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[14].fromTreeHundredToFiveHundred}" name="fromTreeHundredToFiveHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[14].fromTreeHundredToFiveHundredFixed}" name="fromTreeHundredToFiveHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[14].fromFiveHundredToThousand}" name="fromFiveHundredToThousand"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[14].fromFiveHundredToThousandFixed}" name="fromFiveHundredToThousandFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[14].fromThousandToFiveThousands}" name="fromThousandToFiveThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[14].fromThousandToFiveThousandsFixed}" name="fromThousandToFiveThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[14].fromFiveToTenThousands}" name="fromFiveToTenThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[14].fromFiveToTenThousandsFixed}" name="fromFiveToTenThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[14].fromTenToTwentyThousands}" name="fromTenToTwentyThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[14].fromTenToTwentyThousandsFixed}" name="fromTenToTwentyThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[14].fromTwentyToFifty}" name="fromTwentyToFifty"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[14].fromTwentyToFiftyFixed}" name="fromTwentyToFiftyFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[14].fromFiftyToHundred}" name="fromFiftyToHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[14].fromFiftyToHundredFixed}" name="fromFiftyToHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[14].aboveHundredThousands}" name="aboveHundredThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[14].aboveHundredThousandsFixed}" name="aboveHundredThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[14].usdCurrencyRate}" name="usdCurrencyRate"></td>
        <td align="center" style="width: 200px; ">
          <button type="submit" class="search"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/save.png"></button>
        </td>
      </form>
    </tr>
    <tr>
      <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/setMarginUnicTradeWholesale" method="post">
        <td align="center" style="width: 200px; ">Юник трейд ОПТ</td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[15].lessThanHundred}" name="lessThanHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[15].lessThanHundredFixed}" name="lessThanHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[15].fromHundredToTreeHundred}" name="fromHundredToTreeHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[15].fromHundredToTreeHundredFixed}" name="fromHundredToTreeHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[15].fromTreeHundredToFiveHundred}" name="fromTreeHundredToFiveHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[15].fromTreeHundredToFiveHundredFixed}" name="fromTreeHundredToFiveHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[15].fromFiveHundredToThousand}" name="fromFiveHundredToThousand"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[15].fromFiveHundredToThousandFixed}" name="fromFiveHundredToThousandFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[15].fromThousandToFiveThousands}" name="fromThousandToFiveThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[15].fromThousandToFiveThousandsFixed}" name="fromThousandToFiveThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[15].fromFiveToTenThousands}" name="fromFiveToTenThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[15].fromFiveToTenThousandsFixed}" name="fromFiveToTenThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[15].fromTenToTwentyThousands}" name="fromTenToTwentyThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[15].fromTenToTwentyThousandsFixed}" name="fromTenToTwentyThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[15].fromTwentyToFifty}" name="fromTwentyToFifty"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[15].fromTwentyToFiftyFixed}" name="fromTwentyToFiftyFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[15].fromFiftyToHundred}" name="fromFiftyToHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[15].fromFiftyToHundredFixed}" name="fromFiftyToHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[15].aboveHundredThousands}" name="aboveHundredThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[15].aboveHundredThousandsFixed}" name="aboveHundredThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[15].usdCurrencyRate}" name="usdCurrencyRate"></td>
        <td align="center" style="width: 200px; ">
          <button type="submit" class="search"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/save.png"></button>
        </td>
      </form>
    </tr>

    <tr>
      <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/setMarginElitOriginal" method="post">
        <td align="center" style="width: 200px; ">Элит Оригинал РОЗНИЦА</td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[16].lessThanHundred}" name="lessThanHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[16].lessThanHundredFixed}" name="lessThanHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[16].fromHundredToTreeHundred}" name="fromHundredToTreeHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[16].fromHundredToTreeHundredFixed}" name="fromHundredToTreeHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[16].fromTreeHundredToFiveHundred}" name="fromTreeHundredToFiveHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[16].fromTreeHundredToFiveHundredFixed}" name="fromTreeHundredToFiveHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[16].fromFiveHundredToThousand}" name="fromFiveHundredToThousand"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[16].fromFiveHundredToThousandFixed}" name="fromFiveHundredToThousandFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[16].fromThousandToFiveThousands}" name="fromThousandToFiveThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[16].fromThousandToFiveThousandsFixed}" name="fromThousandToFiveThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[16].fromFiveToTenThousands}" name="fromFiveToTenThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[16].fromFiveToTenThousandsFixed}" name="fromFiveToTenThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[16].fromTenToTwentyThousands}" name="fromTenToTwentyThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[16].fromTenToTwentyThousandsFixed}" name="fromTenToTwentyThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[16].fromTwentyToFifty}" name="fromTwentyToFifty"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[16].fromTwentyToFiftyFixed}" name="fromTwentyToFiftyFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[16].fromFiftyToHundred}" name="fromFiftyToHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[16].fromFiftyToHundredFixed}" name="fromFiftyToHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[16].aboveHundredThousands}" name="aboveHundredThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[16].aboveHundredThousandsFixed}" name="aboveHundredThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[16].usdCurrencyRate}" name="usdCurrencyRate"></td>
        <td align="center" style="width: 200px; ">
          <button type="submit" class="search"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/save.png"></button>
        </td>
      </form>
    </tr>
    <tr>
      <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/setMarginElitOriginalWholesale" method="post">
        <td align="center" style="width: 200px; ">Элит Оригинал ОПТ</td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[17].lessThanHundred}" name="lessThanHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[17].lessThanHundredFixed}" name="lessThanHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[17].fromHundredToTreeHundred}" name="fromHundredToTreeHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[17].fromHundredToTreeHundredFixed}" name="fromHundredToTreeHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[17].fromTreeHundredToFiveHundred}" name="fromTreeHundredToFiveHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[17].fromTreeHundredToFiveHundredFixed}" name="fromTreeHundredToFiveHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[17].fromFiveHundredToThousand}" name="fromFiveHundredToThousand"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[17].fromFiveHundredToThousandFixed}" name="fromFiveHundredToThousandFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[17].fromThousandToFiveThousands}" name="fromThousandToFiveThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[17].fromThousandToFiveThousandsFixed}" name="fromThousandToFiveThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[17].fromFiveToTenThousands}" name="fromFiveToTenThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[17].fromFiveToTenThousandsFixed}" name="fromFiveToTenThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[17].fromTenToTwentyThousands}" name="fromTenToTwentyThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[17].fromTenToTwentyThousandsFixed}" name="fromTenToTwentyThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[17].fromTwentyToFifty}" name="fromTwentyToFifty"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[17].fromTwentyToFiftyFixed}" name="fromTwentyToFiftyFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[17].fromFiftyToHundred}" name="fromFiftyToHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[17].fromFiftyToHundredFixed}" name="fromFiftyToHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[17].aboveHundredThousands}" name="aboveHundredThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[17].aboveHundredThousandsFixed}" name="aboveHundredThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[17].usdCurrencyRate}" name="usdCurrencyRate"></td>
        <td align="center" style="width: 200px; ">
          <button type="submit" class="search"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/save.png"></button>
        </td>
      </form>
    </tr>

    <tr>
      <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/setMarginAsg" method="post">
        <td align="center" style="width: 200px; ">ASG РОЗНИЦА</td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[18].lessThanHundred}" name="lessThanHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[18].lessThanHundredFixed}" name="lessThanHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[18].fromHundredToTreeHundred}" name="fromHundredToTreeHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[18].fromHundredToTreeHundredFixed}" name="fromHundredToTreeHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[18].fromTreeHundredToFiveHundred}" name="fromTreeHundredToFiveHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[18].fromTreeHundredToFiveHundredFixed}" name="fromTreeHundredToFiveHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[18].fromFiveHundredToThousand}" name="fromFiveHundredToThousand"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[18].fromFiveHundredToThousandFixed}" name="fromFiveHundredToThousandFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[18].fromThousandToFiveThousands}" name="fromThousandToFiveThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[18].fromThousandToFiveThousandsFixed}" name="fromThousandToFiveThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[18].fromFiveToTenThousands}" name="fromFiveToTenThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[18].fromFiveToTenThousandsFixed}" name="fromFiveToTenThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[18].fromTenToTwentyThousands}" name="fromTenToTwentyThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[18].fromTenToTwentyThousandsFixed}" name="fromTenToTwentyThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[18].fromTwentyToFifty}" name="fromTwentyToFifty"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[18].fromTwentyToFiftyFixed}" name="fromTwentyToFiftyFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[18].fromFiftyToHundred}" name="fromFiftyToHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[18].fromFiftyToHundredFixed}" name="fromFiftyToHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[18].aboveHundredThousands}" name="aboveHundredThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[18].aboveHundredThousandsFixed}" name="aboveHundredThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[18].usdCurrencyRate}" name="usdCurrencyRate"></td>
        <td align="center" style="width: 200px; ">
          <button type="submit" class="search"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/save.png"></button>
        </td>
      </form>
    </tr>
    <tr>
      <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/setMarginAsg" method="post">
        <td align="center" style="width: 200px; ">ASG ОПТ</td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[19].lessThanHundred}" name="lessThanHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[19].lessThanHundredFixed}" name="lessThanHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[19].fromHundredToTreeHundred}" name="fromHundredToTreeHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[19].fromHundredToTreeHundredFixed}" name="fromHundredToTreeHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[19].fromTreeHundredToFiveHundred}" name="fromTreeHundredToFiveHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[19].fromTreeHundredToFiveHundredFixed}" name="fromTreeHundredToFiveHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[19].fromFiveHundredToThousand}" name="fromFiveHundredToThousand"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[19].fromFiveHundredToThousandFixed}" name="fromFiveHundredToThousandFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[19].fromThousandToFiveThousands}" name="fromThousandToFiveThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[19].fromThousandToFiveThousandsFixed}" name="fromThousandToFiveThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[19].fromFiveToTenThousands}" name="fromFiveToTenThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[19].fromFiveToTenThousandsFixed}" name="fromFiveToTenThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[19].fromTenToTwentyThousands}" name="fromTenToTwentyThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[19].fromTenToTwentyThousandsFixed}" name="fromTenToTwentyThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[19].fromTwentyToFifty}" name="fromTwentyToFifty"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[19].fromTwentyToFiftyFixed}" name="fromTwentyToFiftyFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[19].fromFiftyToHundred}" name="fromFiftyToHundred"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[19].fromFiftyToHundredFixed}" name="fromFiftyToHundredFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[19].aboveHundredThousands}" name="aboveHundredThousands"><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[19].aboveHundredThousandsFixed}" name="aboveHundredThousandsFixed"></td>
        <td align="center" style="width: 200px; "><input type="text" pattern = "^[0-9]+(\.[0-9]{1,3})?$" required class="form-control" value="${margin[19].usdCurrencyRate}" name="usdCurrencyRate"></td>
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