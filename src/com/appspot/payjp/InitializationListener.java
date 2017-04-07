package com.appspot.payjp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;



public class InitializationListener implements ServletContextListener{
	 public void contextInitialized(ServletContextEvent event){
		 String JSON_FILE_NAME = "WEB-INF/serviceAccounts/QCcloud2.json";//Firebaseに登録する
	        String URL = "https://qccloud-asia-northeast1.firebaseio.com/";
		        InputStream stream_json;
				try {
					stream_json = new FileInputStream(JSON_FILE_NAME);			
					  FirebaseOptions options = new FirebaseOptions.Builder()
						         .setServiceAccount(stream_json)
							            .setDatabaseUrl(URL)
							            .build();	
					  FirebaseApp.initializeApp(options);	
				} catch (FileNotFoundException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}	
				               
		 System.out.println("起動");
		 
		 
		   
		  }
		   
		

		@Override
		public void contextDestroyed(ServletContextEvent arg0) {
			// TODO 自動生成されたメソッド・スタブ
			
		}
	
	
	
}
