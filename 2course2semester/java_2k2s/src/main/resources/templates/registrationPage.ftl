<#--<html lang="en">-->
<#--<head>-->
<#--    <meta charset="UTF-8">-->
<#--    <meta name="viewport" content="width=device-width, initial-scale=1.0">-->
<#--    <meta http-equiv="X-UA-Compatible" content="ie=edge">-->
<#--    <link rel="stylesheet" type="text/css" href="/css/registration.css">-->
<#--    <title>Login And Registration page</title>-->
<#--</head>-->
<#--<body>-->
<#--<h2>HomeWork Website</h2>-->
<#--<div class="container" id="container">-->
<#--    <div class="form-container sign-up-container">-->
<#--        <form action="/auth/signup" method="post">-->
<#--            <h1>Create Account</h1>-->
<#--            <input type="text" placeholder="Name" name="name" />-->
<#--            <input type="email" placeholder="Email" name="email" />-->
<#--            <input type="password" placeholder="Password" name="password" />-->
<#--            <button>Sign Up</button>-->
<#--        </form>-->
<#--    </div>-->
<#--    <div class="form-container sign-in-container">-->
<#--        <form action="/auth/signin" method="post">-->
<#--            <h1>Sign in</h1>-->
<#--            <input type="email" placeholder="Email" name="email" />-->
<#--            <input type="password" placeholder="Password" name="password" />-->
<#--            <button>Sign In</button>-->
<#--        </form>-->
<#--    </div>-->
<#--    <div class="overlay-container">-->
<#--        <div class="overlay">-->
<#--            <div class="overlay-panel overlay-left">-->
<#--                <h1>Welcome Back!</h1>-->
<#--                <p>To keep connected with us please login with your personal info</p>-->
<#--                <button class="ghost" id="signIn">Sign In</button>-->
<#--            </div>-->
<#--            <div class="overlay-panel overlay-right">-->
<#--                <h1>Hello, Friend!</h1>-->
<#--                <p>Enter your personal details and start journey with us</p>-->
<#--                <button class="ghost" id="signUp">Sign Up</button>-->
<#--            </div>-->
<#--        </div>-->
<#--    </div>-->
<#--</div>-->
<#--</body>-->
<#--<script src="/js/registration.js"></script>-->
<#--</html>-->


<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/registration.css">
</head>
<body>
<div class="container">
    <div class="header">
        <h2>Registration</h2>
    </div>
    <form id="formID" class="form" action="/registration" method="post" name="vform" onsubmit="return Validate()">
        <div id="username_div" class="form-control">
            <label>Username:</label> <br>
            <input type="text" name="username" class="textInput" id="usernameID" placeholder="Enter a username">
            <div id="username_error"></div>
        </div>
        <div id="password_div" class="form-control">
            <label>Password:</label> <br>
            <input type="password" name="password" class="textInput" placeholder="Enter a password">
            <div id="password_error"></div>
        </div>
        <div id="email_div" class="form-control">
            <label>Email:</label> <br>
            <input type="email" name="email" class="textInput" placeholder="Enter your email">
            <div id="email_error"></div>
        </div>
        <div>
            <input type="submit" value="Sign Up" class="btn" name="register" id="submitbtn">
        </div>
    </form>
</div>
<span id="error" class=""></span>
<script src = "/js/validation.js"></script>
</body>
</html>
