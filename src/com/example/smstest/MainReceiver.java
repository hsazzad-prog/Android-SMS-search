package com.example.smstest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.TextView;

public class MainReceiver extends BroadcastReceiver {

	
	@SuppressWarnings("deprecation")
	public void onReceive(Context context, Intent i) {
		Bundle bundle = i.getExtras();
		
		
		Object[] messages = (Object[]) bundle.get("pdus");
		SmsMessage[] sms = new SmsMessage[messages.length];

		for (int n = 0; n < messages.length; n++) {
			sms[n] = SmsMessage.createFromPdu((byte[]) messages[n]);
		}
		for (SmsMessage msg : sms) {
			if (msg.getMessageBody().contains("test")) {
				abortBroadcast();
				i = new Intent(context, MainActivity.class);
				i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				PendingIntent pi = PendingIntent.getActivity(context, 0, i, 0);
				 
				 
				 NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
				 
				 Builder builder = new NotificationCompat.Builder(context);
					builder.setAutoCancel(true);
					builder.setContentIntent(pi);
					builder.setTicker(context.getString(R.string.app_name));
					builder.setSmallIcon(R.drawable.ic_launcher);
					builder.setContentText("Sms recieved from MIMOS");
					builder.setContentTitle(context.getString(R.string.app_name));	
					nm.notify(0, builder.build());
				         
			}
		}
	}
}
