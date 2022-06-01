package com.semusi.services

import android.app.job.JobParameters
import android.app.job.JobService
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.RequiresApi
import com.semusi.receiver.CampaignCampReceiver
import java.lang.Exception
import java.util.*

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class NotificationEventService: JobService() {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    override fun onStartJob(params: JobParameters?): Boolean {
        if (params != null) {
            val bundle = params.extras
            val bundle1 = toBundle(bundle)
            if (bundle1 != null) {
                Timer("AppICE-NotifService1-Timer").schedule(object : TimerTask() {
                    override fun run() {
                        val rc = CampaignCampReceiver()
                        rc.sendCallback(bundle1, applicationContext)
                    }
                }, (2 * 1000).toLong())
            }
        }
        return true
    }

    private fun toBundle(persistableBundle: PersistableBundle?): Bundle? {
        try {
            if (persistableBundle == null) {
                return null
            }
            val bundle = Bundle()
            if (Build.VERSION.SDK_INT >= 21) bundle.putAll(persistableBundle)
            return bundle
        } catch (e: Exception) {
        }
        return null
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        return false
    }
}