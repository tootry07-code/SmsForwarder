package com.smsforwarder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences prefs = context.getSharedPreferences("SmsForwarder", Context.MODE_PRIVATE);

        if (!prefs.getBoolean("enabled", false)) return;

        String gmail   = prefs.getString("gmail", "");
        String pass    = prefs.getString("pass", "");
        String fwdTo   = prefs.getString("forward_to", "");

        if (gmail.isEmpty() || pass.isEmpty() || fwdTo.isEmpty()) return;

        Bundle bundle = intent.getExtras();
        if (bundle == null) return;

        Object[] pdus = (Object[]) bundle.get("pdus");
        if (pdus == null) return;

        for (Object pdu : pdus) {
            SmsMessage sms = SmsMessage.createFromPdu((byte[]) pdu);
            String from = sms.getOriginatingAddress();
            String body = sms.getMessageBody();

            String subject = "📩 নতুন SMS এসেছে: " + from;
            String message = "প্রেরক: " + from + "\n\nবার্তা:\n" + body;

            // Send email in background thread
            new Thread(() -> {
                EmailSender.send(gmail, pass, fwdTo, subject, message);
            }).start();
        }
    }
}
