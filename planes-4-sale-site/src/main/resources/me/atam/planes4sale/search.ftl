<#-- @ftlvariable name="" type=" me.atam.planes4sale.SearchResultsView" -->
<html lang="en">

<head>
    <meta charset="utf-8"/>
    <title>Planes 4 Sale - Search Results</title>


    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="canonical"  href="assets/bootstrap-3.4.1-dist/css/bootstrap.css"
          integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">

    <title>Starter Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="assets/bootstrap-3.4.1-dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="assets/bootstrap-3.4.1-dist/css/ie10-viewport-bug-workaround.css" rel="stylesheet">


</head>

<body>

<div class="container">

    <div class="starter-template">
        <h1>Planes 4 Sale!!!!</h1>
        <p class="lead">Search Resuls below</p>



        <#list getPlanes as item>
            <div id="plane-">
                <!--<p>${item.manufacturer}</p>-->
                <!--<p>${item.reg}</p>-->
            </div>

        </#list>

    </div>

</div>

</body>
</html>


