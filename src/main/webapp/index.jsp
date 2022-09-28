<!DOCTYPE html>
<html>
   <head>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <link rel="stylesheet" href="css/login.css">
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.2/css/all.min.css"/>
   </head>

   <body>
      <script src="js/login.js"></script>
      <div class="error">
        ${incorrectUser}
      </div>
      <div class="container">
         <div class="wrapper">
            <div class="title"><span>Login Form</span></div>
            <form action="controller" method="post">
               <input name="command" value="authorization" hidden="hidden">
               <div class="row">
                  <i class="fas fa-user"></i>
                  <input name="login" placeholder="Login">
               </div>
               <div class="row">
                  <i class="fas fa-lock"></i>
                  <input type="password" name="password" placeholder="Password" id="pass" required>
                  <i class="fas fa-eye-slash" id="eye" onclick="changingVisiabilityOfPassword()"></i>
               </div>
               <div class="row button">
                  <input type="submit" value="Login">
               </div>
            </form>
         </div>
      </div>
   </body>
</html>