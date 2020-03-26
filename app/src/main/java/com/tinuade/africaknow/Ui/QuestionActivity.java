package com.tinuade.africaknow.Ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.tinuade.africaknow.Model.Answer;
import com.tinuade.africaknow.Model.Score;
import com.tinuade.africaknow.R;
import com.tinuade.africaknow.ViewModel.QuestionViewModel;

public class QuestionActivity extends AppCompatActivity {

    private QuestionViewModel mViewModel;
    private TextView mTotalQuestionsTextView, mCurrentQuestionTextView, questionTextView, scoreTextView, correctTextView, wrongTextView;
    private RadioGroup mAnswersRadioGroup;
    //    private ViewPager2 mPager2;
    private Button mNextButton;
    private int numberOfQuestions;
    private Score score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        mViewModel = new ViewModelProvider(this).get(QuestionViewModel.class);
        mTotalQuestionsTextView = findViewById(R.id.total_questions_textView);
        mCurrentQuestionTextView = findViewById(R.id.current_question_textView);
        questionTextView = findViewById(R.id.question_textView);
        mNextButton = findViewById(R.id.confirm_button);
        mAnswersRadioGroup = findViewById(R.id.answer_radio_group);
        scoreTextView = findViewById(R.id.score_textView);
        wrongTextView = findViewById(R.id.wrong_textView);
        correctTextView = findViewById(R.id.correct_textView);


        mViewModel.getQuiz().observe(this, baseResponse -> {
            mTotalQuestionsTextView.setText(String.valueOf(baseResponse.getQuestions().size()));
            numberOfQuestions = baseResponse.getQuestions().size();

            mNextButton.setVisibility(View.VISIBLE);
        });

        mViewModel.getCurrentQuestion.observe(this, question -> {
            questionTextView.setText(question.getQuestion());

            mCurrentQuestionTextView.setText(String.valueOf(mViewModel.currentQuestionIndex + 1));

            mAnswersRadioGroup.removeAllViews();
            mAnswersRadioGroup.check(-1);

            for (Answer answer : question.getAnswers()) {

                RadioButton radioButton = new RadioButton(this);
                radioButton.setText(answer.getOption());
                mAnswersRadioGroup.addView(radioButton);

                radioButton.setOnClickListener(v -> {
                    if (answer.getValue()) {
                        score = new Score(1, 0);
                    } else score = new Score(0, 1);
                });

            }

            mNextButton.setVisibility(View.VISIBLE);
        });

        mViewModel.getCurrentScore.observe(this, score -> {
            correctTextView.setText(String.valueOf(score.getNoOfRightAnswers()));
            wrongTextView.setText(String.valueOf(score.getNoOfWrongAnswers()));

        });

        mNextButton.setOnClickListener(v -> {
            if (mAnswersRadioGroup.getCheckedRadioButtonId() != -1)
                mViewModel.submitAnswer(score);
        });
    }

    private void enableButton(Button button) {
        button.setEnabled(true);
    }

    private void disableButton(Button button) {
        button.setEnabled(false);
    }
}
