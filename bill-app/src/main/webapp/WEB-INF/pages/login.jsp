<div class="middle-box text-center loginscreen animated fadeInDown">
        <div>
            <div>
                <h1 class="logo-name">IN+</h1>
            </div>
            <h3>Welcome to IN+</h3>
            <p>Perfectly designed and precisely prepared admin theme with over 50 pages with extra new web app views.
                <!--Continually expanded and constantly improved Inspinia Admin Them (IN+)-->
        </p>
        <font color="red">
  				${SPRING_SECURITY_LAST_EXCEPTION.message}
     	</font>
        <p>Login in. To see it in action.</p>
        <form class="m-t" role="form" action="<%=request.getContextPath()%>/appLogin" method="POST">
            <div class="form-group">
                <input type="text" class="form-control" name="app_username" placeholder="Username" required="">
            </div>
            <div class="form-group">
                <input type="password" name="app_password" class="form-control" placeholder="Password" required="">
            </div>
            <button type="submit" class="btn btn-primary block full-width m-b">Login</button>

            <a href="#"><small>Forgot password?</small></a>
            <p class="text-muted text-center"><small>Do not have an account?</small></p>
            <a class="btn btn-sm btn-white btn-block" href="register.html">Create an account</a>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>	
        </form>
        <p class="m-t"> <small>Inspinia we app framework base on Bootstrap 3 &copy; 2014</small> </p>
    </div>
</div>

