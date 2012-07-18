<html>
<head>
</head>
<body>
    <h1>${message}</h1>
    Now is ${now} - which is nice.
    <br>What is especially nice is that we can change template file without restarting the server
    <ul>
        <#list list as item>
            <li>${item}</li>
        </#list>
    </ul>
</body>
</html>