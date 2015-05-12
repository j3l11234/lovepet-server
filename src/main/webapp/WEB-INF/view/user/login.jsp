<%@ page contentType="text/html;charset=utf-8"  language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../header.jsp" />

<div class="container-fluid container-margin-top">
  <form id="login-form" role="form" method="post" onsubmit="return onSubmit()">
    <h2>请登录</h2>
    <input type="text" id="username" class="form-control" placeholder="用户名" required="" autofocus="">
    <br>
    <input type="password" id="password" class="form-control" placeholder="密码" required="">
    <br>
    <button id="submit-button" class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
    <br>
    <h2 style="color: #F00; font-size: 15px" id="login-alert" style="display:none;"></h2>
  </form>
</div>

<script type="text/javascript">
  function onSubmit(){
    var submit_button = $("#submit-button");
    submit_button.button('loading');

    $("#login-form").ajaxSubmit({
      type:"post",
      url:"<c:url value="/user/doLogin.do"/>",
      success:function(data){
        try{
          if(data.success == 1){
            document.location.href = "{:U('Index/index')}";
          }else{
            showAlert(data.data);
            submit_button.button('reset');
          }
        }
        catch(err){
          submit_button.button('reset');
        }
      },
      error:function(){
        showAlert("服务器或网络错误");
        submit_button.button('reset');
      }
    });
    return false;
    
    function showAlert(text){
      $("#login-alert").fadeIn("fast");
      $("#login-alert").html(text);
    }
}
</script>

<jsp:include page="../footer.jsp" />