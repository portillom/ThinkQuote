package com.readthinkwrite.android.thinkquote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.TextView;
import android.widget.ImageButton;

import java.util.Random;

public class QuoteActivity extends AppCompatActivity {

    private static final String TAG = "ThinkQuiz";
    private static final String KEY_INDEX = "index";

    private TextView mQuoteTextView;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;

    private int mCurrentIndex = 0;

    private Quote[] mQuoteBank = new Quote[]{
            new Quote(R.string.quote_sacrifice_present),
            new Quote(R.string.bear_responsibility),
            new Quote(R.string.be_dangerous),
            new Quote(R.string.quote_4)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);

        if(savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        mQuoteTextView = (TextView) findViewById(R.id.quote_text_view);
        shuffleQuoteOrder(mQuoteBank);

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

    //The Fisher-Yates shuffle
    private void shuffleQuoteOrder(Quote[] mQuoteBank){
        int index;
        Quote temp;
        Random random = new Random();
        for(int i = mQuoteBank.length -1; i > 0; i--){
            index = random.nextInt(i+1);
            temp = mQuoteBank[index];
            mQuoteBank[index] = mQuoteBank[i];
            mQuoteBank[i] = temp;
        }

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
