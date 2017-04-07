<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
     <%

    String uid = request.getParameter("uid");
     String customerID = request.getParameter("customerID");
    
%>   
    
    
    
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<title>LotsJOY</title>
</head>
<body>

  <div class="panel panel-default" style="width: 60%;margin: 0 auto;margin-top:100px">
    <div class="panel-heading">
      退会手続き
    </div>
    <div class="panel-body">

<p>◎下記のボタンを押していただくと画面が切り替わり退会処理を行います。<br>


</p><br>

 <form action="/deletecustomer" method="post">
 <input class="btn btn-primary" type="submit" value="退会する">
     <input type="hidden" name="uid" value=<%=uid%>><br />
     <input type="hidden" name="customerID" value=<%=customerID%>><br />   
 
     
</form>


</div>
</div>




</body>
</html>