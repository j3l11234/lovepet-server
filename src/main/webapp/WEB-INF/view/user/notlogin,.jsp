<%@ page contentType="text/html;charset=utf-8"  language="java" %>
<jsp:include page="../header.jsp" />

<div class="container-fluid container-margin-top">
  <form class="form-signin">
    <h2 class="form-signin-heading">Please sign in</h2>
    <input type="email" id="inputEmail" class="form-control" placeholder="Username" required="" autofocus="">
    <br>
    <input type="password" id="inputPassword" class="form-control" placeholder="Password" required="">
    <br>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
  </form>
</div>

<jsp:include page="../footer.jsp" />