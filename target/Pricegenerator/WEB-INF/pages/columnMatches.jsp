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
  <a title="Home" href="/admin/"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/home.png"></a>
</head>
<body>


<div class="container">

  <h3>Соответствие данных номерам колонок в прайсе ${columnMatches.name}</h3>




  <table class="table table-striped">

    <thead>
    <tr>
      <th align="center" style="width: 200px; "><b>Бренд</b></th>
      <th align="center" style="width: 200px; "><b>Наименование</b></th>
      <th align="center" style="width: 200px; "><b>Категория товара</b></th>
      <th align="center" style="width: 200px; "><b>Входящая цена</b></th>
      <th align="center" style="width: 200px; "><b>Оптовая цена</b></th>
      <th align="center" style="width: 200px; "><b>Розничная цена</b></th>
      <th align="center" style="width: 200px; "><b>Код товара</b></th>
      <th align="center" style="width: 200px; "><b>Наличие</b></th>
      <th align="center" style="width: 200px; "><b>Полка</b></th>
      <th align="center" style="width: 200px; "><b>Поставщик</b></th>
      <th align="center" style="width: 200px; "><b>Условия поставки</b></th>
      <th align="center" style="width: 200px; "><b>Картинка</b></th>
      <th align="center" style="width: 200px; "><b>Валюта</b></th>
      <th align="center" style="width: 200px; "><b>Сохранить</b></th>
    </tr>
    </thead>
    <thead>
    <tr>
      <form role="form" enctype="multipart/form-data" class="search" action="/admin/saveColumnMatch" method="post">
        <c:set var="name" value="${columnMatches.name}" />

        <input type="text" name="matchName" value="${name}" hidden="true"/>
        <td align="center" style="width: 200px; ">
          <select class="search"
                  <c:if test="${columnMatches.brandMatch==null}">
                    disabled="true"
                  </c:if>
                  name="brand">
            <c:set var="no" value="нет" />
            <c:if test="${columnMatches.brandMatch!=null}">
            <option>${columnMatches.brandMatch+1}</option>>
              <%--<option>нет</option>--%>
            </c:if>
            <c:if test="${columnMatches.brandMatch==null}">
              <option >${no}</option>
              <c:forEach var="i" begin="1" end="15">
                  <option>${i}</option>>
              </c:forEach>
            </c:if>
            <c:forEach var="i" begin="1" end="15">
            <c:if test="${columnMatches.brandMatch!=null && i!=columnMatches.brandMatch+1}">
              <option>${i}</option>>
            </c:if>
            </c:forEach>
          </select>
        </td>
        <td align="center" style="width: 200px; ">
          <select class="search"
                  <c:if test="${columnMatches.nameMatch==null}">
                    disabled="true"
                  </c:if>
                  name="name">
            <c:set var="no" value="нет" />
            <c:if test="${columnMatches.nameMatch!=null}">
              <option>${columnMatches.nameMatch+1}</option>>
              <%--<option>нет</option>--%>
            </c:if>
            <c:if test="${columnMatches.nameMatch==null}">
              <option >${no}</option>
              <c:forEach var="i" begin="1" end="15">
                <option>${i}</option>>
              </c:forEach>
            </c:if>
            <c:forEach var="i" begin="1" end="15">
              <c:if test="${columnMatches.nameMatch!=null && i!=columnMatches.nameMatch+1}">
                <option>${i}</option>>
              </c:if>
            </c:forEach>
          </select>
        </td>
        <td align="center" style="width: 200px; ">
          <select class="search"
                  <c:if test="${columnMatches.categoryMatch==null}">
                    disabled="true"
                  </c:if>
                  name="category">
            <c:set var="no" value="нет" />
            <c:if test="${not empty columnMatches.categoryMatch}">
              <option>${columnMatches.categoryMatch+1}</option>
              <%--<option>нет</option>--%>
            </c:if>
            <c:if test="${columnMatches.categoryMatch==null}">
              <option >${no}</option>
              <c:forEach var="i" begin="1" end="15">
                <option>${i}</option>>
              </c:forEach>
            </c:if>
            <c:forEach var="i" begin="1" end="15">
              <c:if test="${columnMatches.categoryMatch!=null && i!=columnMatches.categoryMatch+1}">
                <option>${i}</option>
              </c:if>
            </c:forEach>
          </select>
        </td>
        <td align="center" style="width: 200px; ">
          <select class="search"
                  <c:if test="${columnMatches.incomePriceMatch==null}">
                    disabled="true"
                  </c:if>
                  name="incomingprice">
            <c:set var="no" value="нет" />
            <c:if test="${columnMatches.incomePriceMatch!=null}">
              <option>${columnMatches.incomePriceMatch+1}</option>>
              <%--<option>нет</option>--%>
            </c:if>
            <c:if test="${columnMatches.incomePriceMatch==null}">
              <option >${no}</option>
              <c:forEach var="i" begin="1" end="15">
                <option>${i}</option>>
              </c:forEach>
            </c:if>
            <c:forEach var="i" begin="1" end="15">
              <c:if test="${columnMatches.incomePriceMatch!=null && i!=columnMatches.incomePriceMatch+1}">
                <option>${i}</option>
              </c:if>
            </c:forEach>
          </select>
        </td>
        <td align="center" style="width: 200px; ">
          <select class="search"
                  <c:if test="${columnMatches.wholesalePriceMatch==null}">
                    disabled="true"
                  </c:if>
                  name="wholesaleprice">
            <c:set var="no" value="нет" />
            <c:if test="${columnMatches.wholesalePriceMatch!=null}">
              <option>${columnMatches.wholesalePriceMatch+1}</option>
              <%--<option>нет</option>--%>
            </c:if>
            <c:if test="${columnMatches.wholesalePriceMatch==null}">
              <option >${no}</option>
              <c:forEach var="i" begin="1" end="15">
                <option>${i}</option>>
              </c:forEach>
            </c:if>
            <c:forEach var="i" begin="1" end="15">
              <c:if test="${columnMatches.wholesalePriceMatch!=null && i!=columnMatches.wholesalePriceMatch+1}">
                <option>${i}</option>
              </c:if>
            </c:forEach>
          </select>
        </td>
        <td align="center" style="width: 200px; ">
          <select class="search"
                  <c:if test="${columnMatches.retailPriceMatch==null}">
                    disabled="true"
                  </c:if>
                  name="retailprice">
            <c:set var="no" value="нет" />
            <c:if test="${columnMatches.retailPriceMatch!=null}">
              <option>${columnMatches.retailPriceMatch+1}</option>
              <%--<option>нет</option>--%>
            </c:if>
            <c:if test="${columnMatches.retailPriceMatch==null}">
              <option >${no}</option>
              <c:forEach var="i" begin="1" end="15">
                <option>${i}</option>
              </c:forEach>
            </c:if>
            <c:forEach var="i" begin="1" end="15">
              <c:if test="${columnMatches.retailPriceMatch!=null && i!=columnMatches.retailPriceMatch+1}">
                <option>${i}</option>
              </c:if>
            </c:forEach>
          </select>
        </td>
        <td align="center" style="width: 200px; ">
          <select class="search"
                  <c:if test="${columnMatches.codeMatch==null}">
                    disabled="true"
                  </c:if>
                  name="code">
            <c:set var="no" value="нет" />
            <c:if test="${columnMatches.codeMatch!=null}">
              <option>${columnMatches.codeMatch+1}</option>
              <%--<option>нет</option>--%>
            </c:if>
            <c:if test="${columnMatches.codeMatch==null}">
              <option >${no}</option>
              <c:forEach var="i" begin="1" end="15">
                <option>${i}</option>>
              </c:forEach>
            </c:if>
            <c:forEach var="i" begin="1" end="15">
              <c:if test="${columnMatches.codeMatch!=null && i!=columnMatches.codeMatch+1}">
                <option>${i}</option>
              </c:if>
            </c:forEach>
          </select>
        </td>
        <td align="center" style="width: 200px; ">
          <select class="search"
                  <c:if test="${columnMatches.availableMatch==null}">
                    disabled="true"
                  </c:if>
                  name="available">
            <c:set var="no" value="нет" />
            <c:if test="${columnMatches.availableMatch!=null}">
              <option>${columnMatches.availableMatch+1}</option>
              <%--<option>нет</option>--%>
            </c:if>
            <c:if test="${columnMatches.availableMatch==null}">
              <option >${no}</option>
              <c:forEach var="i" begin="1" end="15">
                <option>${i}</option>>
              </c:forEach>
            </c:if>
            <c:forEach var="i" begin="1" end="15">
              <c:if test="${columnMatches.availableMatch!=null && i!=columnMatches.availableMatch+1}">
                <option>${i}</option>
              </c:if>
            </c:forEach>
          </select>
        </td>
        <td align="center" style="width: 200px; ">
          <select class="search"
                  <c:if test="${columnMatches.shelfMatch==null}">
                    disabled="true"
                  </c:if>
                  name="shelf">
            <c:set var="no" value="нет" />
            <c:if test="${columnMatches.shelfMatch!=null}">
              <option>${columnMatches.shelfMatch+1}</option>>
              <%--<option>нет</option>--%>
            </c:if>
            <c:if test="${columnMatches.shelfMatch==null}">
              <option >${no}</option>
              <c:forEach var="i" begin="1" end="15">
                <option>${i}</option>>
              </c:forEach>
            </c:if>
            <c:forEach var="i" begin="1" end="15">
              <c:if test="${columnMatches.shelfMatch!=null && i!=columnMatches.shelfMatch+1}">
                <option>${i}</option>
              </c:if>
            </c:forEach>
          </select>
        </td>
        <td align="center" style="width: 200px; ">
          <select class="search"
<c:if test="${columnMatches.supplierMatch==null}">
  disabled="true"
</c:if>
                  name="supplier">
            <c:set var="no" value="нет" />
            <c:if test="${columnMatches.supplierMatch!=null}">
              <option>${columnMatches.supplierMatch+1}</option>>
              <%--<option>нет</option>--%>
            </c:if>
            <c:if test="${columnMatches.supplierMatch==null}">
              <option >${no}</option>
              <c:forEach var="i" begin="1" end="15">
                <option>${i}</option>>
              </c:forEach>
            </c:if>
            <c:forEach var="i" begin="1" end="15">
              <c:if test="${columnMatches.supplierMatch!=null && i!=columnMatches.supplierMatch+1}">
                <option>${i}</option>
              </c:if>
            </c:forEach>
          </select>
        </td>

        <td align="center" style="width: 200px; ">
          <select class="search"
                  <c:if test="${columnMatches.supplyCondition==null}">
                    disabled="true"
                  </c:if>
                  name="supplycondition">
            <c:set var="no" value="нет" />
            <c:if test="${columnMatches.supplyCondition!=null}">
              <option>${columnMatches.supplyCondition+1}</option>>
              <%--<option>нет</option>--%>
            </c:if>
            <c:if test="${columnMatches.supplyCondition==null}">
              <option >${no}</option>
              <c:forEach var="i" begin="1" end="15">
                <option>${i}</option>>
              </c:forEach>
            </c:if>
            <c:forEach var="i" begin="1" end="15">
              <c:if test="${columnMatches.supplyCondition!=null && i!=columnMatches.supplyCondition+1}">
                <option>${i}</option>
              </c:if>
            </c:forEach>
          </select>
        </td>

        <td align="center" style="width: 200px; ">
          <select class="search"
                  <c:if test="${columnMatches.pictureMatch==null}">
                    disabled="true"
                  </c:if>
                  name="picture">
            <c:set var="no" value="нет" />
            <c:if test="${columnMatches.pictureMatch!=null}">
              <option>${columnMatches.pictureMatch+1}</option>>
              <%--<option>нет</option>--%>
            </c:if>
            <c:if test="${columnMatches.pictureMatch==null}">
              <option >${no}</option>
              <c:forEach var="i" begin="1" end="15">
                <option>${i}</option>>
              </c:forEach>
            </c:if>
            <c:forEach var="i" begin="1" end="15">
              <c:if test="${columnMatches.pictureMatch!=null && i!=columnMatches.pictureMatch+1}">
                <option>${i}</option>
              </c:if>
            </c:forEach>
          </select>
        </td>


        <td align="center" style="width: 200px; ">
          <select class="search"
                  <c:if test="${columnMatches.currencyMatch==null}">
                    disabled="true"
                  </c:if>
                  name="currency">
            <c:set var="no" value="нет" />
            <c:if test="${columnMatches.currencyMatch!=null}">
              <option>${columnMatches.currencyMatch+1}</option>>
              <%--<option>нет</option>--%>
            </c:if>
            <c:if test="${columnMatches.currencyMatch==null}">
              <option >${no}</option>
              <c:forEach var="i" begin="1" end="15">
                <option>${i}</option>>
              </c:forEach>
            </c:if>
            <c:forEach var="i" begin="1" end="15">
              <c:if test="${columnMatches.currencyMatch!=null && i!=columnMatches.currencyMatch+1}">
                <option>${i}</option>
              </c:if>
            </c:forEach>
          </select>
        </td>


        <td align="center" style="width: 200px; "><button type="submit" class="search" title="Сохранить"><img class="image" src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/save.png"></button></td>




      </form>
    </tr>
    </thead>
  </table>
</div>
</body>
</html>
