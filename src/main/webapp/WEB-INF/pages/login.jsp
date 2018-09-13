<div class="middle-box text-center loginscreen animated fadeInDown">
        <div>
            <h3>Welcome to Invoice+</h3>
            <p>Perfectly designed for Store Management
                <!--Continually expanded and constantly improved Inspinia Admin Them (IN+)-->
        </p>
        <font color="red"> ${SPRING_SECURITY_LAST_EXCEPTION.message} </font>
        <p>Login in. To see it in action.</p>
        <form class="m-t" role="form" action="<%=request.getContextPath()%>/login" method="POST">
            <div class="form-group">
                <input type="text" class="form-control" name="email" placeholder="Username" required="">
            </div>
            <div class="form-group">
                <input type="password" name="password" class="form-control" placeholder="Password" required="">
            </div>
            <button type="submit" class="btn btn-primary block full-width m-b">Login</button>

           <!--  <a href="#"><small>Forgot password?</small></a> -->
            <p class="text-muted text-center"><small>Do not have an account?</small></p>
             <p class="text-muted text-center"><small>Ask admin to create a new account for you</small></p>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>	
        </form>
    </div>
</div>

