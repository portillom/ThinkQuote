package com.readthinkwrite.android.thinkquote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ImageButton;

public class QuoteActivity extends AppCompatActivity {

    private static final String TAG = "ThinkQuiz";
    private static final String KEY_INDEX = "index";
    private static final String KEY_QUOTE_COUNTER = "quote_counter";

    private TextView mQuoteTextView;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;

    private int mCurrentIndex = 0;

    private Quote[] mQuoteBank = new Quote[]{
            new Quote(R.string.quote_sacrifice_present),
            new Quote(R.string.bear_responsibility),
            new Quote(R.string.be_dangerous)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);

        if(savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        mQuoteTextView = (TextView) findViewById(R.id.quote_text_view);

        mPrevButton = (ImageButton) findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mCurrentIndex > 0){
                    mCurrentIndex -= 1;
                }
                updateQuote();
            }
        });

        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mCurrentIndex < mQuoteBank.length-1){
                    mCurrentIndex += 1;
                }
                updateQuote();
            }
        });

        updateQuote();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    private void updateQuote(){
        int quote = mQuoteBank[mCurrentIndex].getTextResId();
        mQuoteTextView.setText(quote);
        setButton();
    }

    private void setButton(){
        if(mCurrentIndex == mQuoteBank.length-1){
            mNextButton.setEnabled(false);
        }
        else if(mCurrentIndex == 0){
            mPrevButton.setEnabled(false);
        }
        else{
            mNextButton.setEnabled(true);
            mPrevButton.setEnabled(true);
        }
    }
}
