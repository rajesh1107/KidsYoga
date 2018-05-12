package com.signity.kidsyoga.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.signity.kidsyoga.R;
import com.signity.kidsyoga.Utilities.AnimUtil;
import com.signity.kidsyoga.Utilities.FontUtil;
import com.signity.kidsyoga.Utilities.Utils;

public class TOCActivity extends AppCompatActivity implements View.OnClickListener {

    TextView mMarqueeText, mFromTheBeiginnningText, mStartAtSequenceText, mReadMeTheBookText, mStrikeThePoseGameText, mTurnThePageText;
    RelativeLayout mFromTheBeiginnning, mStartAtSequence, mReadMeTheBook, mStrikeThePoseGame, mTurnThePage;
    ImageView mInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setVolumeControlStream(3);
        requestWindowFeature(1);
        setContentView(R.layout.activity_toc);

//        mMarqueeText = (TextView) findViewById(R.id.marqText);
//        mMarqueeText.setSelected(true);
//
//        mMarqueeText.setTypeface(Utils.setCustomFont(TOCActivity.this, FontUtil.FONT_SC_GUM));

        mFromTheBeiginnningText = (TextView) findViewById(R.id.from_the_beginnning_text);
        mFromTheBeiginnningText.setTypeface(Utils.setCustomFont(TOCActivity.this, FontUtil.FONT_SC_GUM));
        mStartAtSequenceText = (TextView) findViewById(R.id.start_at_sequence_text);
        mStartAtSequenceText.setTypeface(Utils.setCustomFont(TOCActivity.this, FontUtil.FONT_SC_GUM));

        mReadMeTheBookText = (TextView) findViewById(R.id.read_me_the_book_text);
        mReadMeTheBookText.setTypeface(Utils.setCustomFont(TOCActivity.this, FontUtil.FONT_SC_GUM));
        mStrikeThePoseGameText = (TextView) findViewById(R.id.strike_the_pose_game_text);
        mStrikeThePoseGameText.setTypeface(Utils.setCustomFont(TOCActivity.this, FontUtil.FONT_SC_GUM));

        mTurnThePageText = (TextView) findViewById(R.id.turn_the_page_text);
        mTurnThePageText.setTypeface(Utils.setCustomFont(TOCActivity.this, FontUtil.FONT_SC_GUM));

        mFromTheBeiginnning = (RelativeLayout) findViewById(R.id.from_the_beginnning);
        mFromTheBeiginnning.setOnClickListener(this);
        mStartAtSequence = (RelativeLayout) findViewById(R.id.start_at_sequence);
        mStartAtSequence.setOnClickListener(this);
        mReadMeTheBook = (RelativeLayout) findViewById(R.id.read_me_the_book);
        mReadMeTheBook.setOnClickListener(this);
        mStrikeThePoseGame = (RelativeLayout) findViewById(R.id.strike_the_pose_game);
        mStrikeThePoseGame.setOnClickListener(this);
        mTurnThePage = (RelativeLayout) findViewById(R.id.turn_the_page);
        mTurnThePage.setOnClickListener(this);

        mInfo = (ImageView) findViewById(R.id.info);
        mInfo.setOnClickListener(this);

    }


    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AnimUtil.slideFromLeftAnim(TOCActivity.this);
    }

    private void about() {
        Intent intent = new Intent(TOCActivity.this, AboutUs.class);
        TOCActivity.this.startActivity(intent);
        AnimUtil.slideFromRightAnim(TOCActivity.this);
    }

    private void intent(int object) {
        Intent intent = null;

        if (object == 1) {
            intent = new Intent(TOCActivity.this, AllAboutYogaActivity.class);
        } else if (object == 2) {
            intent = new Intent(TOCActivity.this, SequenceActivity.class);
        } else if (object == 3) {
            intent = new Intent(TOCActivity.this, SimpleActivity.class);
        } else if (object == 4) {
            intent = new Intent(TOCActivity.this, StrikePoseGameActivity.class);
        } else if (object == 5) {
            intent = new Intent(TOCActivity.this, AutoActivity.class);
        }
        startActivity(intent);
        AnimUtil.slideFromRightAnim(TOCActivity.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.from_the_beginnning:
                intent(1);
                break;

            case R.id.start_at_sequence:
                intent(2);
                break;

            case R.id.read_me_the_book:
                intent(3);
                break;

            case R.id.strike_the_pose_game:
                intent(4);
                break;

            case R.id.turn_the_page:
                intent(5);
                break;

            case R.id.info:
                about();
                break;
        }
    }
}
