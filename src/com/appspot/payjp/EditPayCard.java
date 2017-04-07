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
import jp.pay.model.Card;
import jp.pay.model.Customer;

public class EditPayCard extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

	       RequestDispatcher rd=getServletContext().getRequestDispatcher("/WEB-INF/EditPay.jsp");

			rd.forward(req, resp);

		}
	
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String uid=req.getParameter("uid");
		String token=req.getParameter("payjp-token");
		String customerID=req.getParameter("customerID");
		
		
		Payjp.apiKey = "sk_test_110512e60806612af0a47435";
					Customer cu;
						try {
							cu = Customer.retrieve(customerID);
							Card card=cu.createCard(token);
						//	System.out.println(card.getId());//カードID取得
							Map<String, Object> updateParams = new HashMap<String, Object>();
							updateParams.put("default_card", card.getId());
							cu.update(updateParams);
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
										
					        RequestDispatcher rd=getServletContext().getRequestDispatcher("/WEB-INF/Success.jsp");
									rd.forward(req, resp);
		}

	void addError(String uid,String status,String massege){
		   DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
	    Map<String, Object> hopper = new HashMap<String, Object>();
	   hopper.put("statusError", status);
	   hopper.put("massegeError", massege);
	     reference.child("companyData/"+uid+"/companyInfo").updateChildren(hopper);



	}
	
	
	
	
}
