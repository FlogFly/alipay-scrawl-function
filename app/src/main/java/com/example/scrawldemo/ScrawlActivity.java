package com.example.scrawldemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.scrawldemo.config.AnnotationConfig;
import com.example.scrawldemo.util.BitmapUtil;
import com.example.scrawldemo.widget.ScrawlBoardView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScrawlActivity extends AppCompatActivity {

    @BindView(R.id.color_group)
    RadioGroup colorGroup;
    @BindView(R.id.board_view)
    ScrawlBoardView boardView;
    @BindView(R.id.scraw_group)
    RelativeLayout scrawGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        String path = getIntent().getStringExtra("path");
        final Bitmap bitmap = BitmapFactory.decodeFile(path);


        boardView.post(new Runnable() {
            @Override
            public void run() {
                final Bitmap resizeBitmap = BitmapUtil.resizeBitmap(bitmap,scrawGroup);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        boardView.setBackgroud(resizeBitmap);
                    }
                });

                ViewGroup.LayoutParams params = scrawGroup.getLayoutParams();
                params.height = resizeBitmap.getHeight();
                params.width = resizeBitmap.getWidth();
                scrawGroup.setLayoutParams(params);

            }
        });


        colorGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.red_radio:
                        boardView.setPaintType(AnnotationConfig.PaintType.Paint_Red);
                        break;
                    case R.id.orange_radio:
                        boardView.setPaintType(AnnotationConfig.PaintType.Paint_Orange);
                        break;
                    case R.id.yellow_radio:
                        boardView.setPaintType(AnnotationConfig.PaintType.Paint_Yellow);
                        break;
                    case R.id.green_radio:
                        boardView.setPaintType(AnnotationConfig.PaintType.Paint_Green);
                        break;
                    case R.id.blue_radio:
                        boardView.setPaintType(AnnotationConfig.PaintType.Paint_Blue);
                        break;
                    case R.id.purple_radio:
                        boardView.setPaintType(AnnotationConfig.PaintType.Paint_Purple);
                        break;
                    case R.id.eraser_radio:
                        boardView.setPaintType(AnnotationConfig.PaintType.Paint_Eraser);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @OnClick({R.id.cancel, R.id.rubbish,R.id.finish_btn,R.id.send_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cancel:
                boardView.cancelPath();
                break;
            case R.id.rubbish:
                boardView.clearScrawlBoard();
                break;
            case R.id.finish_btn:
                finish();
                break;
            case R.id.send_btn:
                Bitmap bitmap = boardView.getSrawBoardBitmap();

                //该处的bitmap是涂鸦好的图片。
                //该例子中是把涂鸦好的图片保存到本地
                try {
                    FileOutputStream outputStream = new FileOutputStream(ScrawlActivity.this.getExternalFilesDir(Environment.DIRECTORY_PICTURES)+"/ChintScreenShot.png");
                    bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
                    Toast.makeText(this, "保存到本地成功", Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                //我分享用的是友盟分享。也就是把上面获取到的bitmap传入到相应的友盟分享中的方法即可
                /*
                UMImage image = new UMImage(ScrawlActivity.this, bitmap);
                new ShareAction(DrawBaseActivity.this).withText("hello").withMedia(image)
                        .setDisplayList(SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN,SHARE_MEDIA.ALIPAY)
                        .setCallback(new UMShareListener() {
                            @Override
                            public void onStart(SHARE_MEDIA share_media) {

                            }

                            @Override
                            public void onResult(SHARE_MEDIA share_media) {
                                Toast.makeText(DrawBaseActivity.this, share_media + " 分享成功啦", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                                Toast.makeText(DrawBaseActivity.this,share_media + " 分享失败啦", Toast.LENGTH_SHORT).show();
                                if(throwable!=null){
                                    Log.d("throw","throw:"+throwable.getMessage());
                                }
                            }

                            @Override
                            public void onCancel(SHARE_MEDIA share_media) {
                                Toast.makeText(DrawBaseActivity.this,share_media + " 分享取消了", Toast.LENGTH_SHORT).show();
                            }
                        }).open();
                  */



                break;

            default:
                break;
        }
    }
}
