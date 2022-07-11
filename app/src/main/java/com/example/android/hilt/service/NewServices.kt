package com.example.android.hilt.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.android.hilt.R
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ServiceScoped
import javax.inject.Inject

/**
 * author:       lans
 * date:         2022/7/8 10:52
 * description:
 **/
@AndroidEntryPoint
class NewServices : Service() {
    @Inject
    lateinit var fromString: String
    override fun onCreate() {
        super.onCreate()
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(12,createNotificationChannel())

        log()
        return super.onStartCommand(intent, flags, startId)
    }

    fun log() {
        Log.e("NewServices",fromString)
    }

    private fun createNotificationChannel(): Notification {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // 唯一的通知通道的id.
        val notificationChannelId = "notification_channel_id_01"

        // Android8.0以上的系统，新建消息通道
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //用户可见的通道名称
            val channelName = "Foreground Service Notification"
            //通道的重要程度
            val importance = NotificationManager.IMPORTANCE_HIGH
            val notificationChannel = NotificationChannel(notificationChannelId, channelName, importance)
            notificationChannel.description = "Channel description"
            //LED灯
            notificationChannel.enableLights(false)
            notificationChannel.lightColor = Color.RED
            //震动
            notificationChannel.vibrationPattern = longArrayOf(0, 1000, 500, 1000)
            notificationChannel.enableVibration(false)
            notificationManager.createNotificationChannel(notificationChannel)
        }

        val builder: NotificationCompat.Builder = NotificationCompat.Builder(this, notificationChannelId)
        //通知小图标
        builder.setSmallIcon(R.mipmap.ic_launcher_round)
        //通知标题
        builder.setContentTitle("授权助手正在运行中...")
        //通知内容
        builder.setContentText("")
        //设定通知显示的时间
        builder.setWhen(System.currentTimeMillis())
        builder.setOnlyAlertOnce(true)
        //设定启动的内容
//        val activityIntent = Intent(this, OrderDetailActivity::class.java)
//        val pendingIntent = PendingIntent.getActivity(this, 1, activityIntent, PendingIntent.FLAG_UPDATE_CURRENT)
//        builder.setContentIntent(pendingIntent)

        //创建通知并返回
        return builder.build()
    }

}