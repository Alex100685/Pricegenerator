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

  <h3>Бренды</h3>




  <table class="table table-striped">


    <thead>
    <tr>
      <th align="center" style="width: 200px; "><b>Id</b></th>
      <th align="center" style="width: 200px; "><b>Заменить с</b></th>
      <th align="center" style="width: 200px; "><b>Заменить на</b></th>
      <th align="center" style="width: 200px; "><b>Отрезать от артикула</b></th>
      <th align="center" style="width: 200px; "><b>Действие</b></th>
    </tr>
    </thead>
    <thead>
    <tr>
      <td align="center" style="width: 200px; "></td>
          <form role="form" enctype="multipart/form-data" class="search" action="/admin/saveMatch" method="post">
      <td align="center" style="width: 200px; "><input type="text" class="search" required="required" pattern=".{1,}" title="Не должна быть пустой" name="from" placeholder="Заменить с" style="width: 300px; "></td>
      <td align="center" style="width: 200px; "><input type="text" class="search" required="required" pattern=".{1,}" title="Не должна быть пустой" name="to" placeholder="Заменить на" style="width: 300px; "></td>
            <td align="center" style="width: 200px; "><input type="text" class="search" title="Не должна быть пустой" name="cut" placeholder="Отрезать текст" style="width: 300px; "></td>
      <td align="center" style="width: 200px; "><button type="submit" class="search" title="Добавить соответствие"><img class="image" src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/save.png"></button></td>
        </form>
    </tr>
    </thead>

    <c:forEach items="${barndmatchesList}" var="match">
      <tr>
        <td align="center" style="width: 200px; ">${match.id}</td>
        <td align="center" style="width: 200px; ">${match.priceBrand}</td>
        <td align="center" style="width: 200px; ">${match.priceBrandMatch}</td>
        <td align="center" style="width: 200px; ">${match.cutFromArticule}</td>
        <td align="center" style="width: 200px; "><a class="search" title="Удалить соответствие" href="/admin/deleteMatch?brand=${match.priceBrand}&trueBrand=${match.priceBrandMatch}&cut=${match.cutFromArticule}"><img class="image" src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/trash.png"></a></td>
      </tr>
    </c:forEach>
  </table>
</div>
</body>
</html>