# ProgressBarWithNumber
一个带有数字显示的进度条，从ProgressBar继承
跟着hyman大神学习的:-)

# 演示
![Sample Screenshots][1]

# 用法
###  布局文件

        横向进度条
        
        <com.teresazl.library.HorizontalProgressBarWithNumber
            android:id="@+id/h_progressbar"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginTop="32dp"
            android:padding="12dp"
            app:progress_reached_color="#FF0000"
            app:progress_text_color="#FF0000"
            app:progress_unreached_color="#44FF0000" />
            
            
         圆形进度条
         
         <com.teresazl.library.CircleProgressBarWithNumber
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginTop="32dp"
            android:padding="12dp"
            android:progress="80"
            android:layout_gravity="center_horizontal"
            app:progress_reached_color="#0000FF"
            app:progress_text_color="#0000FF"
            app:progress_unreached_color="#440000FF"
            app:radius="40dp" />
            
### Activity中
         mHProgressBar = (HorizontalProgressBarWithNumber) findViewById(R.id.h_progressbar);
         mHProgressBar.setProgress(++progress);

# 关于
欢迎star  
欢迎反馈问题：teresazl@yeah.net

[1]: https://github.com/teresazl/ProgressBarWithNumber/blob/master/screenshots/processbar_with_number.gif
