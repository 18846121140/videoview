package com.example.androidpractice_3;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

import static android.media.MediaMetadataRetriever.OPTION_CLOSEST;
import static android.os.Build.VERSION_CODES.M;

public class MainActivity extends AppCompatActivity {
    @RequiresApi(api = M)
    private VideoView videoView_1;
    private VideoView videoView_2;
    private VideoView videoView_3;

    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;
    //    private ImageView trigger;

    private MediaController videoController1;
    private MediaController videoController2;
    private MediaController videoController3;

    boolean flag=true;

    int width;
    int height;
    int ratio;

//    RelativeLayout.LayoutParams layoutParams;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
/* ***********************状态栏透明**************************/
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_main);
//        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
/* ***********************获取手机屏幕宽高比**************************/
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        WindowManager windowManager = getWindowManager();
//        Display display = windowManager.getDefaultDisplay();
//        display.getMetrics(dm);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        width=dm.widthPixels;
        height=dm.heightPixels;
        ratio=width/height;
/* ***********************实例VideoView封面**************************/
        iv1 = (ImageView) findViewById(R.id.mmr_bitmap1);
        iv2 = (ImageView) findViewById(R.id.mmr_bitmap2);
        iv3 = (ImageView) findViewById(R.id.mmr_bitmap3);
/* ***********************实例化控件VideoView**************************/
        videoView_1 = (VideoView) findViewById(R.id.video_1);
        videoView_2 = (VideoView) findViewById(R.id.video_2);
        videoView_3 = (VideoView) findViewById(R.id.video_3);
/* ***********************获取VideoView路径**************************/
        //        File file2= new File(Environment.getExternalStorageDirectory(),"youlookscared.mp4");
        //        videoView_2.setVideoPath(file2.getPath());
        //        File file1= new File(Environment.getExternalStorageDirectory(),"Piper.mp4");
        //        videoView_1.setVideoPath(file1.getPath());
        //        File file3= new File(Environment.getExternalStorageDirectory(),"Embarked.mp4");
        //        videoView_3.setVideoPath(file3.getPath());

        String uri1 = "android.resource://" + MainActivity.this.getPackageName() + "/" + R.raw.piper;
        Uri uri_1 = Uri.parse(uri1);
        videoView_1.setVideoURI(uri_1);
        String uri2 = "android.resource://" + MainActivity.this.getPackageName() + "/" + R.raw.youlookscared;
        Uri uri_2 = Uri.parse(uri2);
        videoView_2.setVideoURI(uri_2);
        String uri3 = "android.resource://" + MainActivity.this.getPackageName() + "/" + R.raw.forthebirds;
        Uri uri_3 = Uri.parse(uri3);
        videoView_3.setVideoURI(uri_3);
/* ***********************RalativeLayout全屏化**************************/
//        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
//                RelativeLayout.LayoutParams.MATCH_PARENT,
//                RelativeLayout.LayoutParams.MATCH_PARENT);
//        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
//        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
//        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
//        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

//      videoView_1.setLayoutParams(layoutParams);
//      videoView_2.setLayoutParams(layoutParams);
//      videoView_3.setLayoutParams(layoutParams);

//      iv1.setLayoutParams(layoutParams);
//      iv2.setLayoutParams(layoutParams);
//      iv3.setLayoutParams(layoutParams);

/* ***********************第二幅图片Bitmap化**************************/
        Bitmap second = BitmapFactory.decodeResource(getResources(), R.drawable.second_bitmap);
/* ***********************合成VideoView—1的bitmap素材**************************/
        MediaMetadataRetriever mediametadataretriever1 = new MediaMetadataRetriever();
        mediametadataretriever1.setDataSource(this, uri_1);
        Bitmap bitmap1 = mediametadataretriever1.getFrameAtTime(100000000, OPTION_CLOSEST);
        mediametadataretriever1.release();
/* ***********************合成帧封面**************************/
        Bitmap bitmap_1 = mergeBitmap(bitmap1, second);
        iv1.setImageBitmap(bitmap_1);

/* ***********************VideoView-1监听事件**************************/
        //        iv1.setOnClickListener(new View.OnClickListener(){
        //            @Override
        //            public void onClick(View v){
        //                if(flag) {
        //                    videoView_1.setVisibility(View.VISIBLE);
        //                    videoView_1.start();
        //                    flag=false;
        //                }
        //            }
        //        });
/* ***********************合成VideoView—2的bitmap素材**************************/
        MediaMetadataRetriever mediametadataretriever2 = new MediaMetadataRetriever();
        mediametadataretriever2.setDataSource(this, uri_2);
        Bitmap bitmap2 = mediametadataretriever2.getFrameAtTime(43000000, OPTION_CLOSEST);
        mediametadataretriever2.release();
/* ************************合成帧封面*************************/
        Bitmap bitmap_2 = mergeBitmap(bitmap2, second);
        iv2.setImageBitmap(bitmap_2);

/* ************************VideoView-2监听事件*************************/
        //        iv2.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //                if (flag) {
        //                    videoView_2.setVisibility(View.VISIBLE);
        //                    videoView_2.start();
        //                    flag = false;
        //                }
        //            }
        //        });
/* ***********************合成VideoView—3的bitmap素材**************************/
        MediaMetadataRetriever mediametadataretriever3 = new MediaMetadataRetriever();
        mediametadataretriever3.setDataSource(this, uri_3);
        Bitmap bitmap3 = mediametadataretriever3.getFrameAtTime();
        mediametadataretriever3.release();
/* ************************合成帧封面*************************/
        Bitmap bitmap_3 = mergeBitmap(bitmap3, second);
        iv3.setImageBitmap(bitmap_3);
/* ************************VideoView-3监听事件*************************/
//        iv3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (flag) {
//                    videoView_3.setVisibility(View.VISIBLE);
//                    videoView_3.start();
//                    flag = false;
//                }
//            }
//        });
/* ************************触屏监视事件*************************/
        iv1.setOnTouchListener(new MyOnTouch());
        iv2.setOnTouchListener(new MyOnTouch());
        iv3.setOnTouchListener(new MyOnTouch());
/* ************************初始化进度条*************************/
        initControllerBar();
    }
/* ************************运行时权限申请*************************/
//        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
//        } else {
//            initVideoPath();
//        }
/* ************************帧合成*************************/
    private Bitmap mergeBitmap(Bitmap first_bitmap, Bitmap second_bitmap) {
        int w = second_bitmap.getWidth();
        int h = second_bitmap.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(first_bitmap.getWidth(), first_bitmap.getHeight(), first_bitmap.getConfig());
        Canvas canvas = new Canvas(bitmap);
        canvas.drawBitmap(first_bitmap, new Matrix(), null);
        canvas.drawBitmap(second_bitmap, null, new RectF(10, 550, 10 + w, 550 + h), null);
        return bitmap;
    }
    /* ************************运行时权限申请结果*************************/
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        switch (requestCode) {
//            case 1:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    initVideoPath();
//                } else {
//                    Toast.makeText(this, "拒绝权限将无法使用本程序", Toast.LENGTH_SHORT).show();
//                    finish();
//                }
//                break;
//            default:
//        }
//    }

    /* ************************进度条展示*************************/
    private void initControllerBar() {
        videoController1 = new MediaController(MainActivity.this);
        videoView_1.setMediaController(videoController1);
        videoController1.setMediaPlayer(videoView_1);
        videoController1.setAnchorView(videoView_1);
        videoController1.setPadding(0, 0, 0, 0);

        videoController2 = new MediaController(MainActivity.this);
        videoView_2.setMediaController(videoController2);
        videoController2.setMediaPlayer(videoView_2);
        videoController2.setAnchorView(videoView_2);
        videoController2.setPadding(0, 0, 0, 0);

        videoController3 = new MediaController(MainActivity.this);
        videoView_3.setMediaController(videoController3);
        videoController3.setMediaPlayer(videoView_3);
        videoController3.setAnchorView(videoView_3);
        videoController3.setPadding(0, 0, 0, 0);
/* ************************进度条上下视频切换*************************/
        videoController1.setPrevNextListeners(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView_1.pause();
                videoController1.setVisibility(View.INVISIBLE);
                videoController3.setVisibility(View.INVISIBLE);
                videoView_2.setVisibility(View.VISIBLE);
                videoView_2.start();
                videoController2.setVisibility(View.VISIBLE);
            }
        }, new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "已到顶部", Toast.LENGTH_SHORT).show();
            }
        });

        videoController2.setPrevNextListeners(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView_2.pause();
                videoController1.setVisibility(View.INVISIBLE);
                videoController2.setVisibility(View.INVISIBLE);
                videoView_3.setVisibility(View.VISIBLE);
                videoView_3.start();
                videoController3.setVisibility(View.VISIBLE);
            }
        }, new View.OnClickListener() {
            public void onClick(View v) {
                videoView_2.pause();
                videoController2.setVisibility(View.INVISIBLE);
                videoController3.setVisibility(View.INVISIBLE);
                videoView_1.setVisibility(View.VISIBLE);
                videoView_1.start();
                videoController1.setVisibility(View.VISIBLE);
            }
        });

        videoController3.setPrevNextListeners(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "已到底部", Toast.LENGTH_SHORT).show();
            }
        }, new View.OnClickListener() {
            public void onClick(View v) {
                videoView_3.pause();
                videoController3.setVisibility(View.INVISIBLE);
                videoController1.setVisibility(View.INVISIBLE);
                videoView_2.setVisibility(View.VISIBLE);
                videoView_2.start();
                videoController2.setVisibility(View.VISIBLE);
            }
        });
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Configuration cfg=getResources().getConfiguration();
        if(cfg.orientation == Configuration.ORIENTATION_LANDSCAPE){
//            getSupportActionBar(toolbar).hide();
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            iv1.setLayoutParams(layoutParams);
            iv2.setLayoutParams(layoutParams);
            iv3.setLayoutParams(layoutParams);
            videoView_1.setLayoutParams(layoutParams);
            videoView_2.setLayoutParams(layoutParams);
            videoView_3.setLayoutParams(layoutParams);
//            Toast.makeText(this,"现在是横屏",Toast.LENGTH_SHORT).show();
        }
        if(cfg.orientation==ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
            Toast.makeText(this,"现在是竖屏",Toast.LENGTH_SHORT).show();

        }
        super.onConfigurationChanged(newConfig);
    }

/* ************************触摸监听事件*************************/

    class MyOnTouch implements View.OnTouchListener {
        int x;
        int y;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int event_action = event.getAction();

            switch (event_action) {
                case MotionEvent.ACTION_DOWN:
                    x = (int) event.getX();
                    y = (int) event.getY();
                    break;

                case MotionEvent.ACTION_MOVE:
                    break;

                case MotionEvent.ACTION_UP:
                    if (x > 10 && x < 125 && y > 300 && y <400) {
                        if(flag) {
                            switch (v.getId()) {
                                case R.id.mmr_bitmap1:
//                                        MainActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//                                        videoView_1.setLayoutParams(layoutParams);
                                        videoView_1.setVisibility(View.VISIBLE);
                                        videoView_1.start();
                                        flag = false;
                                    break;
                                case R.id.mmr_bitmap2:
//                                        MainActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//                                        videoView_2.setLayoutParams(layoutParams);
                                        videoView_2.setVisibility(View.VISIBLE);
                                        videoView_2.start();
                                        flag = false;
                                        break;
                                case R.id.mmr_bitmap3:
                                        videoView_3.setVisibility(View.VISIBLE);
                                        videoView_3.start();
                                        flag = false;
//                                    MainActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//                                    videoView_3.setLayoutParams(layoutParams);
                                        break;
                                default:
                            }
                        }
                        }
                        break;
                    }
            return true;
        }
    }
}

