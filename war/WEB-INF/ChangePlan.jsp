<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
     <%

    String uid = request.getParameter("uid");
     String planID = request.getParameter("planID");
     String newplan = request.getParameter("newplan");
     String newplan2 = request.getParameter("newplan2");
     String amount = request.getParameter("amount");
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
      プラン変更手続き
    </div>
    <div class="panel-body">

<p>◎下記のボタンを押していただくと画面が切り替わりプランが変更になります。<br>


</p><br>
<p>【<%=newplan2%>プラン】</p>
 <form action="/changeplan" method="post">
 <input class="btn btn-primary" type="submit" value="プランを変更する">
     <input type="hidden" name="uid" value=<%=uid%>><br />
     <input type="hidden" name="planID" value=<%=planID%>><br />   
  <input type="hidden" name="newplan" value=<%=newplan%>><br />  
      <input type="hidden" name="newplan2" value=<%=newplan2%>><br />  
        <input type="hidden" name="amount" value=<%=amount%>><br />
     
</form>


</div>
</div>




</body>
</html>