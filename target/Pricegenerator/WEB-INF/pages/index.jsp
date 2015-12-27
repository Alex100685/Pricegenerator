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
</head>
<body>


<div class="container">

  <form action="/admin/marginAdministration" method="post">
    <button title="Установить наценку" type="submit" class="search"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/price_tag.png"></button>
  </form>

  <h3>Прайсы</h3>

  <form action="/admin/productSearch" method="post">
    <button title="Найти товар" type="submit" class="search"><img src="https://cdn4.iconfinder.com/data/icons/miu/22/common_search_lookup_-24.png"></button>
  </form>

  <form action="/admin/brandMatches" method="post">
    <button title="Таблица соответствий брендов" type="submit" class="search"><img src="https://cdn2.iconfinder.com/data/icons/rental-icon/240/airbnb-24.png"></button>
  </form>

  <table class="table table-striped">

    <thead>
    <tr>
      <th align="center" style="width: 200px; "><b>Название поставщика</b></th>
      <th align="center" style="width: 200px; "><b>Дата последней загрузки</b></th>
      <th align="center" style="width: 200px; "><b>Выбрать прайс</b></th>
      <th align="center" style="width: 200px; "><b>Загрузить</b></th>
      <th align="center" style="width: 200px; "><b>Удалить</b></th>
    </tr>
    </thead>

      <tr>
        <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/updatePriceAutotechnix" method="post">
        <td align="center" style="width: 200px; ">Автотоехникс</td>
        <td align="center" style="width: 200px; ">${updates[0].dateOfUpdate}</td>
        <td align="center" style="width: 200px; ">
          <input type="file" name="file">
        </td>
        <td align="center" style="width: 200px; ">
          <button type="submit" class="search"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/save.png"></button>
        </td>
        </form>
        <td align="center" style="width: 200px; "><a class="search" title="Очистить" href="/admin/deletePrice?name=Автотехникс"><img class="image" src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/trash.png"></a></td>
      </tr>

      <tr>
        <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/updatePriceIntercars" method="post">
          <td align="center" style="width: 200px; ">Интеркарс</td>
          <td align="center" style="width: 200px; ">${updates[1].dateOfUpdate}</td>
          <td align="center" style="width: 200px; ">
            <input type="file" name="file">
          </td>
          <td align="center" style="width: 200px; ">
            <button type="submit" class="search"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/save.png"></button>
          </td>
        </form>
        <td align="center" style="width: 200px; "><a class="search" title="Очистить" href="/admin/deletePrice?name=Интеркарс"><img class="image" src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/trash.png"></a></td>
      </tr>

      <tr>
        <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/updatePriceVlad" method="post">
          <td align="center" style="width: 200px; ">Влад</td>
          <td align="center" style="width: 200px; ">${updates[2].dateOfUpdate}</td>
          <td align="center" style="width: 200px; ">
            <input type="file" name="file">
          </td>
          <td align="center" style="width: 200px; ">
            <button type="submit" class="search"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/save.png"></button>
          </td>
        </form>
        <td align="center" style="width: 200px; "><a class="search" title="Очистить" href="/admin/deletePrice?name=Влад"><img class="image" src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/trash.png"></a></td>
      </tr>

      <tr>
        <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/updatePriceElit" method="post">
          <td align="center" style="width: 200px; ">Элит</td>
          <td align="center" style="width: 200px; ">${updates[3].dateOfUpdate}</td>
          <td align="center" style="width: 200px; ">
            <input type="file" name="file">
          </td>
          <td align="center" style="width: 200px; ">
            <button type="submit" class="search"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/save.png"></button>
          </td>
        </form>
        <td align="center" style="width: 200px; "><a class="search" title="Очистить" href="/admin/deletePrice?name=Элит"><img class="image" src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/trash.png"></a></td>
      </tr>

    <tr>
      <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/updatePriceGenstar" method="post">
        <td align="center" style="width: 200px; ">Генстар</td>
        <td align="center" style="width: 200px; ">${updates[4].dateOfUpdate}</td>
        <td align="center" style="width: 200px; ">
          <input type="file" name="file">
        </td>
        <td align="center" style="width: 200px; ">
          <button type="submit" class="search"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/save.png"></button>
        </td>
      </form>
      <td align="center" style="width: 200px; "><a class="search" title="Очистить" href="/admin/deletePrice?name=Генстар"><img class="image" src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/trash.png"></a></td>
    </tr>

    <tr>
      <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/updatePriceAmperis" method="post">
        <td align="center" style="width: 200px; ">Амперис</td>
        <td align="center" style="width: 200px; ">${updates[5].dateOfUpdate}</td>
        <td align="center" style="width: 200px; ">
          <input type="file" name="file">
        </td>
        <td align="center" style="width: 200px; ">
          <button type="submit" class="search"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/save.png"></button>
        </td>
      </form>
      <td align="center" style="width: 200px; "><a class="search" title="Очистить" href="/admin/deletePrice?name=Амперис"><img class="image" src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/trash.png"></a></td>
    </tr>

    <tr>
      <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/updatePriceTomarket" method="post">
        <td align="center" style="width: 200px; ">ТОМАРКЕТ</td>
        <td align="center" style="width: 200px; ">${updates[6].dateOfUpdate}</td>
        <td align="center" style="width: 200px; ">
          <input type="file" name="file">
        </td>
        <td align="center" style="width: 200px; ">
          <button type="submit" class="search"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/save.png"></button>
        </td>
      </form>
      <td align="center" style="width: 200px; "><a class="search" title="Очистить" href="/admin/deletePrice?name=Томаркет"><img class="image" src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/trash.png"></a></td>
    </tr>

    <tr>
      <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/updatePriceUnicTrade" method="post">
        <td align="center" style="width: 200px; ">Юник Трейд</td>
        <td align="center" style="width: 200px; ">${updates[7].dateOfUpdate}</td>
        <td align="center" style="width: 200px; ">
          <input type="file" name="file">
        </td>
        <td align="center" style="width: 200px; ">
          <button type="submit" class="search"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/save.png"></button>
        </td>
      </form>
      <td align="center" style="width: 200px; "><a class="search" title="Очистить" href="/admin/deletePrice?name=Юниктрейд"><img class="image" src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/trash.png"></a></td>
    </tr>

    <tr>
      <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/updatePriceElitOrignal" method="post">
        <td align="center" style="width: 200px; ">Элит Оригинал</td>
        <td align="center" style="width: 200px; ">${updates[8].dateOfUpdate}</td>
        <td align="center" style="width: 200px; ">
          <input type="file" name="file">
        </td>
        <td align="center" style="width: 200px; ">
          <button type="submit" class="search"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/save.png"></button>
        </td>
      </form>
      <td align="center" style="width: 200px; "><a class="search" title="Очистить" href="/admin/deletePrice?name=Элиторигинал"><img class="image" src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/trash.png"></a></td>
    </tr>

  </table>

  <h3></h3>

  <table class="table table-strip">
    <thead>

    <tr>
      <th align="center" style="width: 397px; ">Формат файла</th>
      <th align="center" style="width: 150px; ">Обновить</th>
      <th align="center" style="width: 150px; ">Cводный прайс</th>
      <th align="center" style="width: 150px; ">AvtoXCatalog ТОМаркета</th>
      <th align="center" style="width: 150px; ">AvtoXCatalog avtostop.net</th>
    </tr>

    </thead>

    <tr>
      <td align="center" style="width: 438px; ">XLSX</td>
      <td align="center" style="width: 150px; ">
        <a class="search" title="Обновить сводный прайс xlsx" href="/admin/refreshPrice"><img class="image" src="https://cdn3.iconfinder.com/data/icons/faticons/32/sync-01-24.png"></a>
      </td>
      <td align="center" style="width: 150px; ">
        <a class="search" title="Скачать сводный прайс xlsx" href="/admin/downloadPrice"><img class="image" src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/download.png"></a>
      </td>
      <td align="center" style="width: 150px; ">
      <a class="search" title="Скачать AvtoXCatalog для ТОМаркета xlsx" href="/admin/downloadPriceAvtoXCatalogTomarket"><img class="image" src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/download.png"></a>
    </td>
      <td align="center" style="width: 150px; ">
        <a class="search" title="Скачать AvtoXCatalog для shop.avtostop.net xlsx" href="/admin/downloadPriceAvtoXCatalogAuto"><img class="image" src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/download.png"></a>
      </td>
    </tr>

    <tr>
      <td align="center" style="width: 438px; ">CSV</td>
      <td align="center" style="width: 150px; ">
        <a class="search" title="Обновить сводный прайс csv" href="/admin/refreshPriceCsv"><img class="image" src="https://cdn3.iconfinder.com/data/icons/faticons/32/sync-01-24.png"></a>
      </td>
      <td align="center" style="width: 150px; ">
        <a class="search" title="Скачать сводный прайс csv" href="/admin/downloadPriceCsv"><img class="image" src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/download.png"></a>
      </td>
      <td align="center" style="width: 150px; ">
        <a class="search" title="Скачать AvtoXCatalog для ТОМаркета csv" href="/admin/downloadPriceAvtoXCatalogTomarketCsv"><img class="image" src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/download.png"></a>
      </td>
      <td align="center" style="width: 150px; ">
        <a class="search" title="Скачать AvtoXCatalog для shop.avtostop.net csv" href="/admin/downloadPriceAvtoXCatalogAutoCsv"><img class="image" src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/download.png"></a>
      </td>
    </tr>

  </table>

  <table>
  <thead>
  <tr>
    <th align="center" style="width: 964px; "><b>Дополнительный комментарий</b></th>
    <th align="center" style="width: 200px; "><b>Сохранить</b></th>
  </tr>
  </thead>

  <thead>
  <tr>
    <form role="form" enctype="multipart/form-data" class="search" action="/admin/saveComment" method="post">
      <td align="center" style="width: 964px; "><input type="text" class="search" name="comment" placeholder="Комментрий" style="width: 500px; "></td>
      <td align="center" style="width: 200px; "><button type="submit" class="search" title="Добавить соответствие"><img class="image" src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/save.png"></button></td>
    </form>
  </tr>
  </thead>
  </table>




</div>
</body>
</html>