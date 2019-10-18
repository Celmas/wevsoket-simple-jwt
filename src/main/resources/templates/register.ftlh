<!doctype html>
<html lang="en">
<head>
    <title>SigUp</title>
</head>
<script
        src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
        crossorigin="anonymous"></script>
<script>
    function sendRegistrationForm() {
        let registerForm = {
            username: $('#login').val(),
            password: $('#password').val()
        };
        $.ajax({
            url: "api/register",
            method: "POST",
            data: JSON.stringify(registerForm),
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
        <legend>Sign Up</legend>
        <label for="login"><span>Username</span><input id="login" type="text"
                                                       class="input-field"
                                                       name="login"
                                                       value=""/></label>
        <label for="password"><span>Password</span><input id="password" type="password"
                                                          class="input-field"
                                                          name="password"
                                                          value=""/></label>
    </fieldset>
    <fieldset>
        <label><span> </span><button onclick="sendRegistrationForm()">Submit</button></label>        </fieldset>
</div>

</body>
</html>