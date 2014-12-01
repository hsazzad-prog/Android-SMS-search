package com.example.smstest;

import java.util.Date;

import android.app.Activity;
import android.content.ContextWrapper;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

	static TextView view;

	//TextView view2 =(TextView) findViewById (R.id.text_notification);
	//static ContextWrapper context = null;
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
     	query();
  }
  
public void query() 
{
	view = (TextView) findViewById(R.id.textview_sms);
	 Uri uriSMSURI = Uri.parse("content://sms/inbox");
	  
 	 Cursor cursor = getContentResolver().query(uriSMSURI, null,null, null,null);
       long date1=0;
       StringBuffer sms = new StringBuffer();
       if (!cursor.moveToFirst()) {
     	  sms.append(" No content found!"); 
       }
       else{

 			for (int i = 0; i < cursor.getCount(); i++) {
 				String body = cursor.getString(cursor.getColumnIndexOrThrow("body")).toString();
 				
 				String number = cursor.getString(cursor.getColumnIndexOrThrow("address")).toString();
 				
 				long date2 = cursor.getLong(4);
 				String date = cursor.getString(cursor.getColumnIndexOrThrow("date")).toString();
 				Date smsDayTime = new Date(Long.valueOf(date));
 				
 				cursor.moveToNext();
 				
 				 if (body.contains("RM")) {
 					   				if(date1 <= date2)
 					   				{
 				sms.append(  " \nbody: "+ body + "\ndate : "+smsDayTime + "\nFrom:"+number+ "\n\n");         
 					   				}
 					   				date1=date2;
 					   				}
 		}
 		}
       view.setText(sms);
       cursor.close();
	
  }

  /*
  public void updateMessageBox(StringBuffer sms)
  {
	  view.setText(sms);
  	//textview_sms.append(msg);
  }*/
  
}