package com.semusi.ui

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.semusi.PlacesModule2Test.R
import com.semusi.AppICEApplication
import semusi.activitysdk.ContextSdk
import semusi.context.ui.appInbox.AppICEInboxMessage
import semusi.context.ui.appInbox.IAppICESuccessCallback

class InboxActivity : AppCompatActivity() {
    private  var edtCount: EditText? = null
    private  var edtMessage: EditText? = null
    private  var edtMessageForId: EditText? = null
    private  var edtUpdateNid: EditText? = null
    private  var edtUpdateType: EditText? = null
    private  var edtMessageForCampId: EditText? = null

    private  var btnCount: Button? = null
    private  var btnMessage: Button? = null
    private  var btnMessageForId: Button? = null
    private  var btnUpdateStatus: Button? = null
    private  var btnArrayCount: Button? = null
    private  var btnArrayMessage: Button? = null
    private  var btnMessageForCampId: Button? = null
    private  var btnMessageForCampIdUserId: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.testactivity)

        edtCount = findViewById(R.id.edt_count)
        edtMessage = findViewById(R.id.edt_msg)
        edtMessageForId = findViewById(R.id.edt_msg_for_id)
        edtUpdateNid = findViewById(R.id.edt_update_status)
        edtUpdateType = findViewById(R.id.edt_update_type)
        edtMessageForCampId = findViewById(R.id.edt_campid)


        btnCount = findViewById(R.id.btn_count)
        btnMessage = findViewById(R.id.btn_msg)
        btnMessageForId = findViewById(R.id.btn_msg_for_id)
        btnUpdateStatus = findViewById(R.id.btn_update_status)
        btnArrayCount = findViewById(R.id.btn_count_userid)
        btnArrayMessage = findViewById(R.id.btn_msg_userid)
        btnMessageForCampId = findViewById(R.id.btn_msg_campid)
        btnMessageForCampIdUserId = findViewById(R.id.btn_msg_userid_campid)
        btnClickListener()
    }

    private fun btnClickListener() {
        btnCount!!.setOnClickListener {
            val type: Int = edtCount!!.text.toString().toInt()
            if (type > 0) {
                appMessageCount(type)
            }
        }
        btnArrayCount?.setOnClickListener {
            val type: Int = edtCount!!.text.toString().toInt()
            if (type > 0) {
                appMessageCountForUserId(type)
            }
        }
        btnMessage?.setOnClickListener {
            val type: Int = edtMessage!!.text.toString().toInt()
            if (type>0) {
                appInbox(type)
            }
        }
        btnArrayMessage?.setOnClickListener {
            val type: Int = edtMessage!!.text.toString().toInt()
            if (type > 0) {
                appInboxForUserId(type)
            }
        }
        btnMessageForId?.setOnClickListener {
            val nid: String? = edtMessageForId!!.text.toString()
            if (nid != null && nid.isNotEmpty()) {
                appInboxSingle(nid)
            }
        }
        btnUpdateStatus?.setOnClickListener {
            val nid: String = edtUpdateNid!!.text.toString()
            val type: String = edtUpdateType!!.text.toString()
            if ((nid.isNotEmpty()) || (type.isNotEmpty())) {
                updateStatus(nid, type.toInt())
            }
        }
        btnMessageForCampId?.setOnClickListener {
            val type: String = edtMessageForCampId!!.text.toString()
            if (type.isNotEmpty()) {
                getAppInboxForCampId(type,"")
            }
        }
        btnMessageForCampIdUserId?.setOnClickListener {
            val type: String = edtMessageForCampId!!.text.toString()
            if (type.isNotEmpty()) {
                getAppInboxForCampId(type,"384e079e-d371-4c25-a8d0-8fd2c65190b8")
            }
        }
    }

    private fun appMessageCount(type: Int) {
        val count = ContextSdk.getMessageCount(type, this.applicationContext)
        println("notification : getMessageCount : $count")
    }

    private fun appMessageCountForUserId(type: Int) {
        val arr = arrayOf("ATCwW2i+L7SzsgWc6Vpy7A==", "38433079e-d371-4c25-a8d0-8fd2c65190b8")
        val count = ContextSdk.getMessageCount(type, arr, this.applicationContext)
        println("notification : getMessageCount : $count")
    }

    private fun getAppInboxForCampId(type: String, users: String) {
        if(users.isNotEmpty()) {
            ContextSdk.getMessageByCampaignId(
                type,
                {
                    val inboxItems: AppICEInboxMessage = it
                    if (inboxItems != null) {
                        val title = inboxItems.messageTitle
                        val message = inboxItems.messageBody
                        val id = inboxItems.messageId
                        val campType = inboxItems.messageCampType
                        val icon = inboxItems.messageIcon
                        val language = inboxItems.messageLanguage
                        val status = inboxItems.messageStatus
                        val et = inboxItems.messageExpiryTime
                        val map = inboxItems.customData
                        println(
                            "notification : getMessageByCampaignId using campId : title : " + title +
                                    ", message : " + message +
                                    ", id : " + id +
                                    ", icon : " + icon +
                                    ", lang : " + language +
                                    ", campType : " + campType +
                                    ", status : " + status +
                                    ", et : " + et +
                                    ", map : " + map
                        )
                    }},
                10,
                applicationContext
            )

            ContextSdk.getMessageByCampaignId(
                type,
                "38433079e-d371-4c25-a8d0-8fd2c65190b8",
                {
                    val inboxItems: AppICEInboxMessage = it
                    if (inboxItems != null) {
                        val title = inboxItems.messageTitle
                        val message = inboxItems.messageBody
                        val id = inboxItems.messageId
                        val campType = inboxItems.messageCampType
                        val icon = inboxItems.messageIcon
                        val language = inboxItems.messageLanguage
                        val status = inboxItems.messageStatus
                        val et = inboxItems.messageExpiryTime
                        val map = inboxItems.customData
                        println(
                            "notification : getMessageByCampaignId using campId : title : " + title +
                                    ", message : " + message +
                                    ", id : " + id +
                                    ", icon : " + icon +
                                    ", lang : " + language +
                                    ", campType : " + campType +
                                    ", status : " + status +
                                    ", et : " + et +
                                    ", map : " + map
                        )
                    }},
                10,
                applicationContext
            )
        }
    }

    private fun appInbox(type: Int) {
        val inboxItems: List<AppICEInboxMessage>? =
            ContextSdk.getInboxMessage(type, applicationContext)
        if (inboxItems != null && inboxItems.isNotEmpty()) {
            for (i in inboxItems.indices) {
                val title = inboxItems[i].messageTitle
                val message = inboxItems[i].messageBody
                val id = inboxItems[i].messageId
                val campType = inboxItems[i].messageCampType
                val icon = inboxItems[i].messageIcon
                val language = inboxItems[i].messageLanguage
                val status = inboxItems[i].messageStatus
                val et = inboxItems[i].messageExpiryTime
                val map = inboxItems[i].customData
                println(
                    "notification : getMessage : title : " + title +
                            ", message : " + message +
                            ", id : " + id +
                            ", icon : " + icon +
                            ", lang : " + language +
                            ", campType : " + campType +
                            ", status : " + status +
                            ", et : " + et +
                            ", map : " + map
                )
            }
        }
    }

    private fun appInboxForUserId(type: Int) {
        val inboxItems: List<AppICEInboxMessage>?
        val arr = arrayOf("ATCwW2i+L7SzsgWc6Vpy7A==", "38433079e-d371-4c25-a8d0-8fd2c65190b8")
        inboxItems = ContextSdk.getInboxMessage(type, arr, applicationContext)
        if (inboxItems != null && inboxItems.isNotEmpty()) {
            for (i in inboxItems.indices) {
                val title = inboxItems[i].messageTitle
                val message = inboxItems[i].messageBody
                val id = inboxItems[i].messageId
                val campType = inboxItems[i].messageCampType
                val icon = inboxItems[i].messageIcon
                val language = inboxItems[i].messageLanguage
                val status = inboxItems[i].messageStatus
                val et = inboxItems[i].messageExpiryTime
                val map = inboxItems[i].customData
                println(
                    ("notification : getMessage : title : " + title +
                            ", message : " + message +
                            ", id : " + id +
                            ", icon : " + icon +
                            ", lang : " + language +
                            ", campType : " + campType +
                            ", status : " + status +
                            ", et : " + et +
                            ", map : " + map)
                )
            }
        }
    }

    private fun appInboxSingle(messageId: String?) {
        val inboxItem: AppICEInboxMessage? = ContextSdk.getInboxMessageForId(messageId, this.applicationContext)
        if (inboxItem != null) {
            val title = inboxItem.messageTitle
            val message = inboxItem.messageBody
            val id = inboxItem.messageId
            val campType = inboxItem.messageCampType
            val icon = inboxItem.messageIcon
            val language = inboxItem.messageLanguage
            val status = inboxItem.messageStatus
            val et = inboxItem.messageExpiryTime
            val map = inboxItem.customData
            println(
                ("notification :getMessageForId : title : " + title +
                        ", message : " + message +
                        ", id : " + id +
                        ", icon : " + icon +
                        ", lang : " + language +
                        ", campType : " + campType +
                        ", status : " + status +
                        ", et : " + et +
                        ", map : " + map)
            )
        }
    }


    private fun updateStatus(messageId: String?, type: Int) {
        val isStatusChanged = ContextSdk.updatedInboxMessage(
            messageId,
            type,
            "38433079e-d371-4c25-a8d0-8fd2c65190b8",
            this.applicationContext
        )
        println("notification : isStatusChanged $isStatusChanged For nid : $messageId")
    }

    private class AppICESuccessCallback(var messageId: String) :
        IAppICESuccessCallback {
        override fun callback(flag: Boolean) {
            if (flag) {
                Log.i("syncI", "callback is true")
                val inboxItem = ContextSdk.getInboxMessageForId(
                    messageId, AppICEApplication.AppICE.mInstance.applicationContext
                )
                if (inboxItem != null) {
                    val title = inboxItem.messageTitle
                    val message = inboxItem.messageBody
                    val id = inboxItem.messageId
                    val campType = inboxItem.messageCampType
                    val icon = inboxItem.messageIcon
                    val language = inboxItem.messageLanguage
                    val status = inboxItem.messageStatus
                    val et = inboxItem.messageExpiryTime
                    val map = inboxItem.customData
                    println(
                        ("notification :getMessageForId : title : " + title +
                                ", message : " + message +
                                ", id : " + id +
                                ", icon : " + icon +
                                ", lang : " + language +
                                ", campType : " + campType +
                                ", status : " + status +
                                ", et : " + et +
                                ", map : " + map)
                    )
                    Log.i("syncI", "message title:" + inboxItem.messageTitle)
                }
            } else {
                Log.i("syncI", "callback is false")
            }
        }
    }

    fun synchronizeInbox(messageId: String) {
        Log.i("syncI", "synchronizeInbox method start")
        val callback = AppICESuccessCallback(messageId)
        ContextSdk.synchronizeInbox(callback, 10, application)
    }
}