package com.appice.sample.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.appice.sample.R;

import java.util.List;
import java.util.Map;

import semusi.activitysdk.ContextSdk;
import semusi.context.ui.appInbox.AppICEInboxMessage;
import semusi.context.ui.appInbox.IAppICESuccessCallback;

public class InboxActivity extends Activity {
    private EditText edtCount, edtMessage, edtMessageForId, edtUpdateNid, edtUpdateType;
    private Button btnCount, btnMessage, btnMessageForId, btnUpdateStatus, btnArrayCount, btnArrayMessage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testactivity);

        /* Edit text init */
        edtCount = findViewById(R.id.edt_count);
        edtMessage = findViewById(R.id.edt_msg);
        edtMessageForId = findViewById(R.id.edt_msg_for_id);
        edtUpdateNid = findViewById(R.id.edt_update_status);
        edtUpdateType = findViewById(R.id.edt_update_type);


        /* button init */
        btnCount = findViewById(R.id.btn_count);
        btnMessage = findViewById(R.id.btn_msg);
        btnMessageForId = findViewById(R.id.btn_msg_for_id);
        btnUpdateStatus = findViewById(R.id.btn_update_status);
        btnArrayCount = findViewById(R.id.btn_count_userid);
        btnArrayMessage = findViewById(R.id.btn_msg_userid);

        btnClickListener();
    }

    private void btnClickListener() {
        btnCount.setOnClickListener(v -> {
            int type = Integer.parseInt(edtCount.getText().toString());
            if (type > 0) {
                appMessageCount(type);
            }
        });

        btnArrayCount.setOnClickListener(view -> {
            int type = Integer.parseInt(edtCount.getText().toString());
            if (type > 0) {
                appMessageCountForUserId(type);
            }
        });

        btnMessage.setOnClickListener(v -> {
            int type = Integer.parseInt(edtMessage.getText().toString());
            if (type > 0) {
                appInbox(type);
            }
        });

        btnArrayMessage.setOnClickListener(v -> {
            int type = Integer.parseInt(edtMessage.getText().toString());
            if (type > 0) {
                appInboxForUserId(type);
            }
        });


        btnMessageForId.setOnClickListener(v -> {
            String nid = edtMessageForId.getText().toString();
            if (nid != null && nid.length() > 0) {
                appInboxSingle(nid);
            }
        });

        btnUpdateStatus.setOnClickListener(v -> {
            String nid = edtUpdateNid.getText().toString();
            String type = edtUpdateType.getText().toString();


            if ((nid != null && nid.length() > 0) || (type != null && type.length() > 0)) {
                updateStatus(nid, Integer.parseInt(type));
            }
        });
    }

    public void appMessageCount(int type) {
        int count = ContextSdk.getMessageCount(type,this.getApplicationContext());
        // Utility.loginfo("notification : getMessageCount : " + count);
    }

    public void appMessageCountForUserId(int type) {
        String[] arr = {"384e079e-d371-4c25-a8d0-8fd2c65190b8","38433079e-d371-4c25-a8d0-8fd2c65190b8"};

        int count = ContextSdk.getMessageCount(type, arr,this.getApplicationContext());
        System.out.println("notification : getMessageCount : " + count);
    }

    public void appInbox(int type) {
        List<AppICEInboxMessage> inboxItems;
        inboxItems = ContextSdk.getInboxMessage(type,getApplicationContext());

        if (inboxItems != null && inboxItems.size() > 0) {
            for (int i = 0; i < inboxItems.size(); i++) {

                String title = inboxItems.get(i).getMessageTitle();
                String message = inboxItems.get(i).getMessageBody();
                String id = inboxItems.get(i).getMessageId();
                String campType = inboxItems.get(i).getMessageCampType();
                String icon = inboxItems.get(i).getMessageIcon();
                String language = inboxItems.get(i).getMessageLanguage();
                String status = inboxItems.get(i).getMessageStatus();
                long et = inboxItems.get(i).getMessageExpiryTime();
                Map<String, Object> map = inboxItems.get(i).getCustomData();

                System.out.println("notification : getMessage : title : " + title +
                        ", message : " + message +
                        ", id : " + id +
                        ", icon : " + icon +
                        ", lang : " + language +
                        ", campType : " + campType +
                        ", status : " + status +
                        ", et : " + et+
                        ", map : "+map);
            }
        }
    }


    public void appInboxForUserId(int type) {
        List<AppICEInboxMessage> inboxItems;

        String[] arr = {"384e079e-d371-4c25-a8d0-8fd2c65190b8","38433079e-d371-4c25-a8d0-8fd2c65190b8"};
        inboxItems = ContextSdk.getInboxMessage(type,arr,getApplicationContext());

        if (inboxItems != null && inboxItems.size() > 0) {
            for (int i = 0; i < inboxItems.size(); i++) {

                String title = inboxItems.get(i).getMessageTitle();
                String message = inboxItems.get(i).getMessageBody();
                String id = inboxItems.get(i).getMessageId();
                String campType = inboxItems.get(i).getMessageCampType();
                String icon = inboxItems.get(i).getMessageIcon();
                String language = inboxItems.get(i).getMessageLanguage();
                String status = inboxItems.get(i).getMessageStatus();
                long et = inboxItems.get(i).getMessageExpiryTime();
                Map<String, Object> map = inboxItems.get(i).getCustomData();

                System.out.println("notification : getMessage : title : " + title +
                        ", message : " + message +
                        ", id : " + id +
                        ", icon : " + icon +
                        ", lang : " + language +
                        ", campType : " + campType +
                        ", status : " + status +
                        ", et : " + et+
                        ", map : "+map);
            }
        }
    }

    public void updateStatus(String messageId, int type) {
        boolean isStatusChanged = ContextSdk.updateInboxMessage(messageId, type, this.getApplicationContext());
         System.out.println("notification : isStatusChanged " + isStatusChanged + " For nid : " + messageId);
    }

    public void appInboxSingle(String messageId) {
         synchronizeInbox(messageId);
    }


    public class appICESuccessCallback implements IAppICESuccessCallback {
        String messageId;

        appICESuccessCallback(String messageId) {
            this.messageId = messageId;
        }

        @Override
        public void callback(boolean flag) {
            if(flag){
                System.out.println("callback is true");
                AppICEInboxMessage inboxItem = ContextSdk.getInboxMessageForId(messageId, getApplicationContext());

                if(inboxItem != null){
                    String title = inboxItem.getMessageTitle();
                    String message = inboxItem.getMessageBody();
                    String id = inboxItem.getMessageId();
                    String campType = inboxItem.getMessageCampType();
                    String icon = inboxItem.getMessageIcon();
                    String language = inboxItem.getMessageLanguage();
                    String status = inboxItem.getMessageStatus();
                    long et = inboxItem.getMessageExpiryTime();
                    Map<String, Object> map = inboxItem.getCustomData();

                    System.out.println("notification :getMessageForId : title : " + title +
                            ", message : " + message +
                            ", id : " + id +
                            ", icon : " + icon +
                            ", lang : " + language +
                            ", campType : " + campType +
                            ", status : " + status +
                            ", et : " + et+
                            ", map : "+map);

                    System.out.println("message title:"+ inboxItem.getMessageTitle());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(InboxActivity.this, inboxItem.getMessageTitle(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }else {
                System.out.println("callback is false");
            }
        }
    }

    public void synchronizeInbox(String messageId) {
        System.out.println("synchronizeInbox method start");
        appICESuccessCallback callback = new appICESuccessCallback(messageId);
        ContextSdk.synchronizeInbox(callback, 10,getApplication());
    }
}
