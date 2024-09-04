<html>
<head>
    <title>Verify OTP</title>
</head>
<body>
    <h2>Verify OTP</h2>
    <form action="/verify" method="post">
        <input type="hidden" name="email" value="${email}"/>
        OTP: <input type="text" name="otp"/><br/>
        <input type="submit" value="Verify"/>
    </form>
    <div style="color:red">${error}</div>
</body>
</html>
