工程建立 流程
### Andorid开发指南
http://www.rongcloud.cn/docs/android.html#integration

### 
1 集成sdk
   newFile -import Mode 
2 ndk {
          abiFilters "armeabi-v7a", "x86"
 }
3 修改IMLib库下的
Appkey  uwd1c0sxup0x1

4 融云初始化
RongIM.init(this);

5 将pushlab 包下的放入lib executable 放入main\assets 文件中
  然后在buildGrde 中完成
     sourceSets {
          main {
              jniLibs.srcDirs = ['libs']
          }
      }
6 很抽象 到此IM服务链接成功，有个如下几个坑：
  1 getToken 参数是form表单 
  2 集成so库 需要sqliteLib ，这个需要单独下载
  访问如下地址就行
  http://rongcloud-web.qiniudn.com/698f304ce3de445d34eb32fe963425ce.gz?attname=libsqlite_3150200.tar.gz
  解压后加入到IMLIB中
7 完成通信DEMO 
  -------------->>>>>>>>>>>>>>>>
 添加  <meta-data
          android:name="RONG_CLOUD_APP_KEY"
          android:value="" />
 添加 provide 
   <provider
              android:name="android.support.v4.content.FileProvider"
              android:authorities="com.sutdy.work.rongyproject.FileProvider"
              android:exported="false"
              android:grantUriPermissions="true">
              <meta-data
                  android:name="android.support.FILE_PROVIDER_PATHS"
                  android:resource="@xml/file_paths"
                  />
   </provider>  
  添加 xmL文件
          
  8 添加activity ConversationListActivity
                 ConversationActivity
                 SubConversationListActivtiy
                 
                 添加相应的布局
                 
                 启动方式 RongIM.getInstance.start....
   9 集成  消息与会话列表  
               
                    /**
                      * 初始化会话列表
                      * @return fragment 
                      */
                     private Fragment initConversationList() {
                         if (frag_msg == null) {
                             ConversationListFragment listFragment = new ConversationListFragment();
                             Uri uri=Uri.parse("rong://"+getApplicationInfo().packageName).buildUpon()
                                     .appendPath("conversationlist")
                                     .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "true") //设置私聊会话是否聚合显示
                                     .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "true")//群组
                                     .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//系统
                                     .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "true")
                                     .build();
                             listFragment.setUri(uri);
                             return  listFragment;
                         }else{
                             return frag_msg;
                         }
                     }
                 
    
    10 消息接收注册：
    
      public class WenwenNotificationReceiver  extends PushMessageReceiver {
          @Override
          public boolean onNotificationMessageArrived(Context context, PushNotificationMessage pushNotificationMessage) {
              return false;
          }
      
          @Override
          public boolean onNotificationMessageClicked(Context context, PushNotificationMessage pushNotificationMessage) {
              return false;
          }
      }
    
    
    以上集成了消息图片的显示，继续完善DEMO,发送的信息的敏感词 需要申请
    关于消息的弹出 根据文档只有断开或者进入后台3分钟后才可以收到push
    
    
    
         
    
    
    
          
          
            
