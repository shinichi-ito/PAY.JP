package com.appspot.payjp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import jp.pay.Payjp;
import jp.pay.exception.APIConnectionException;
import jp.pay.exception.AuthenticationException;
import jp.pay.exception.CardException;
import jp.pay.exception.InvalidRequestException;
import jp.pay.exception.PayjpException;
import jp.pay.model.Customer;
import jp.pay.model.Subscription;

@SuppressWarnings("serial")
public class PayJpServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

       RequestDispatcher rd=getServletContext().getRequestDispatcher("/WEB-INF/ConnectPayJp.jsp");

		rd.forward(req, resp);

	}


	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String uid=req.getParameter("uid");
		String companyname=req.getParameter("companyname");
		String email=req.getParameter("email");
		String tel=req.getParameter("tel");
		String plan=req.getParameter("plan");
		String plan2=req.getParameter("plan2");
		String amount=req.getParameter("amount");
		String token=req.getParameter("payjp-token");
			
		Payjp.apiKey = "sk_test_110512e60806612af0a47435";
		
		Map<String, Object> customerParams = new HashMap<String, Object>();
		customerParams.put("description", companyname+"　：　"+tel);
		customerParams.put("email", email);
		Customer cs = null;
		 Subscription sb=null;
		try {
			cs = Customer.create(customerParams);//顧客作成
			
			Customer cu;	
			cu = Customer.retrieve(cs.getId());
			cu.createCard(token);//カード情報追加
			
			Map<String, Object> subscriptionParams = new HashMap<String, Object>();
			subscriptionParams.put("plan", plan);
			subscriptionParams.put("customer", cs.getId());	
			subscriptionParams.put("prorate", true);	
		    sb=Subscription.create(subscriptionParams);//プラント顧客を紐づけ
			
			
		} catch (CardException e) {
			  // Since it's a decline, CardException will be caught
			//  System.out.println("Status is: " + e.getCode());
			//  System.out.println("Message is: " + e.getMessage());
			  addError(uid,e.getCode(),e.getMessage());
			  RequestDispatcher rd=getServletContext().getRequestDispatcher("/WEB-INF/Error.jsp");
				rd.forward(req, resp);
			  
			  
			} catch (InvalidRequestException e) {
			  // Invalid parameters were supplied to Payjp's API
				
				 addError(uid,e.getCode(),e.getMessage());
				  RequestDispatcher rd=getServletContext().getRequestDispatcher("/WEB-INF/Error.jsp");
					rd.forward(req, resp);
				
				
				
			} catch (AuthenticationException e) {
			  // Authentication with Payjp's API failed
			  // (maybe you changed API keys recently)
				
				 addError(uid,"コード無し",e.getMessage());
				  RequestDispatcher rd=getServletContext().getRequestDispatcher("/WEB-INF/Error.jsp");
					rd.forward(req, resp);
				
				
			} catch (APIConnectionException e) {
			  // Network communication with Payjp failed
				 addError(uid,"コード無し",e.getMessage());
				  RequestDispatcher rd=getServletContext().getRequestDispatcher("/WEB-INF/Error.jsp");
					rd.forward(req, resp);
				
				
			} catch (PayjpException e) {
			  // Display a very generic error to the user, and maybe send
			  // yourself an email
				 addError(uid,"コード無し",e.getMessage());
				  RequestDispatcher rd=getServletContext().getRequestDispatcher("/WEB-INF/Error.jsp");
					rd.forward(req, resp);
				
				
			} catch (Exception e) {
			  // Something else happened, completely unrelated to Payjp
				 addError(uid,"コード無し",e.getMessage());
				  RequestDispatcher rd=getServletContext().getRequestDispatcher("/WEB-INF/Error.jsp");
					rd.forward(req, resp);
				
			}
						


						accessFirebase(uid,cs.getId(),sb.getId(),plan2,amount);//firebaseのtermを1に変更

					        RequestDispatcher rd=getServletContext().getRequestDispatcher("/WEB-INF/Success.jsp");
									rd.forward(req, resp);
		}



void accessFirebase(String uid,String customerID,String planID,String plan,String amount){
	
	int amount2 = Integer.parseInt(amount);
	//System.out.println(plan);
	//System.out.println(amount);
	   DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
       Map<String, Object> hopperUpdates = new HashMap<String, Object>();
      hopperUpdates.put("term", 1);
      hopperUpdates.put("label", plan);
      hopperUpdates.put("price", amount2);
      hopperUpdates.put("customerID", customerID);
      hopperUpdates.put("planID", planID);
       reference.child("companyData/"+uid+"/companyInfo").updateChildren(hopperUpdates);



}
void addError(String uid,String status,String massege){
	   DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    Map<String, Object> hopper = new HashMap<String, Object>();
   hopper.put("statusError", status);
   hopper.put("massegeError", massege);
     reference.child("companyData/"+uid+"/companyInfo").updateChildren(hopper);



}


}
