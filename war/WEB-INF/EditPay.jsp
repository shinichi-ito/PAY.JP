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
      クレジットカード変更
    </div>
    <div class="panel-body">

<p>◎クレジットカードの情報を既存のものから変更します。<br>

下記のボタンを押していただきますとダイアログが開きます。<br>
カード番号、有効期限、セキュリティコード(裏面お名前横の3桁の数字)、カード名義人を登録していただきます。<br>
入力が終わりましたら、【トークンを作成する】ボタンを押してください。ダイアログが閉じます。<br>
バックグラウンドで、カードの有効性や必要事項を自動で登録作業を行いますので、<br>
<div style="text-decoration: underline;"> 完了画面に切り替わるまでそのままお待ちください。注意!!</div><br>

では、下記のボタンを押していただきカード情報を入力画面へと進めてください。<br>

</p><br>

 <form action="/editpay" method="post">
  <script src="https://checkout.pay.jp/" class="payjp-button" data-key="pk_test_79295e10fd4fce5d565348bb"></script>
     <input type="hidden" name="uid" value=<%=uid%>><br />
     <input type="hidden" name="customerID" value=<%=customerID%>><br />   
   
     
     
</form>


</div>
</div>




</body>
</html>