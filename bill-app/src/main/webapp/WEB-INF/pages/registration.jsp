<div class="middle-box text-center loginscreen   animated fadeInDown">
        <div>
            <h3>Register to IN+</h3>
            <p>Create account to see it in action.</p>
            <form class="m-t" role="form" action="<%=request.getContextPath()%>/registration" method="POST">
            	<div class="form-group">
                    <input type="text" class="form-control" name="firstName" placeholder="First Name" required="">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="lastName" placeholder="Last Name" required="">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="email" placeholder="Eamil" required="">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" name="password" placeholder="Password" required="">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="mobile" placeholder="Mobile" required="">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="userName" placeholder="User Nmae" required="">
                </div>
                <button type="submit" class="btn btn-primary block full-width m-b">Register</button>

                <p class="text-muted text-center"><small>Already have an account?</small></p>
                <a class="btn btn-sm btn-white btn-block" href="<%=request.getContextPath()%>/registration/login">Login</a>
            </form>
        </div>
 </div>