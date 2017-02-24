package com.example.myservice.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;
import android.widget.RemoteViews;

import com.example.myservice.R;
import com.example.myservice.ui.TestActivity;
import com.example.myservice.util.DebugLog;


public class MyService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
        DebugLog.e("in onCreate");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        DebugLog.e("in onStartCommand ");

        //普通的消息通知
//        NotificationManager manager = (NotificationManager) this.getSystemService(this.NOTIFICATION_SERVICE);
//        Notification.Builder builder = new Notification.Builder(this);//新建Notification.Builder对象
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, TestActivity.class), 0);
//        builder.setContentTitle("service test");
//        builder.setContentText("message");
//        builder.setSmallIcon(R.mipmap.ic_launcher);
//        builder.setContentIntent(pendingIntent);
//        Notification notification = builder.getNotification();//将builder对象转换为普通的notification
//        notification.flags |= Notification.FLAG_AUTO_CANCEL;//点击通知后通知消失
//        manager.notify(1, notification);//运行notification
//        startForeground(1, notification);

        //自定义布局带大图的消息通知
        NotificationManager systemService = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notification);
        remoteViews.setImageViewResource(R.id.iv_imageview,R.mipmap.programmer);
        remoteViews.setTextViewText(R.id.tv_notification,"我是程序员。。。。。。");
        remoteViews.setImageViewResource(R.id.iv_logo,R.mipmap.ic_launcher);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setOngoing(false);
        builder.setAutoCancel(false);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("主题名称");
        builder.setContentText("测试测试测试");
        Notification notification = builder.build();
        notification.defaults = Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_LIGHTS;
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        notification.when = System.currentTimeMillis();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            notification.bigContentView = remoteViews;
        }
        Intent i = new Intent(this,TestActivity.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, i, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.contentIntent = pendingIntent;
        systemService.notify(0, notification);
        startForeground(1, notification);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        DebugLog.e("in onDestroy ");
    }
}
