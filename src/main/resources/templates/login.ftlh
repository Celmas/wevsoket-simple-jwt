<!doctype html>
<html lang="en">
<head>
    <title>SigIn</title>
</head>
<script
        src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
        crossorigin="anonymous"></script>
<script>
    function sendLoginForm() {
        var loginForm = {
            username: $('#login').val(),
            password: $('#password').val()
        };
        $.ajax({
            url: "api/login",
            method: "POST",
            data: JSON.stringify(loginForm),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                localStorage.setItem("AUTH", data.tokenValue.toString());
                document.cookie = "AUTH=" + localStorage.getItem("AUTH");
                $(location).attr('href', "/");
            }
        });
    }
</script>
<body>
<div class="form-style-3">

    <fieldset>
        <legend>Sign In</legend>
        <label for="login"><span>login</span><input id="login" type="email"
                                                    class="input-field"
                                                    name="login"
                                                    value=""/></label>
        <label for="password"><span>Password</span><input id="password" type="password"
                                                          class="input-field"
                                                          name="password"
                                                          value=""/></label>
    </fieldset>
    <fieldset>
        <label><span> </span>
            <button onclick="sendLoginForm()" type="submit" >Submit</button>
        </label>
    </fieldset>

</div>

</body>
</html>