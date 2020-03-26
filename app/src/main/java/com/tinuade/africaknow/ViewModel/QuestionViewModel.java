package com.tinuade.africaknow.ViewModel;

import android.util.Log;

import com.tinuade.africaknow.Api.ApiInterface;
import com.tinuade.africaknow.Api.RetrofitClient;
import com.tinuade.africaknow.Model.BaseResponse;
import com.tinuade.africaknow.Model.Question;
import com.tinuade.africaknow.Model.Score;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionViewModel extends ViewModel {
    public QuestionViewModel() {
        getQuestions();
        scores.postValue(new Score(0,0));
    }

    private MutableLiveData<BaseResponse> quiz = new MutableLiveData<>();
//    private MutableLiveData<Integer> correctAnswer = new MutableLiveData<>();

    public List<Question> allQuestions = Collections.emptyList();
    private MutableLiveData<Question> currentQuestion = new MutableLiveData<>();
    private MutableLiveData<Score> scores = new MutableLiveData<>();
    public LiveData<Score> getCurrentScore = scores;
    public LiveData<Question> getCurrentQuestion = currentQuestion;

    public Integer currentQuestionIndex = 0;

    public void submitAnswer(Score score){
        setNextQuestion();
        Score newScore = new Score( scores.getValue().getNoOfRightAnswers() + score.getNoOfRightAnswers(),
                scores.getValue().getNoOfWrongAnswers() + score.getNoOfWrongAnswers()
        );
        scores.postValue(newScore);
    }

    private void setNextQuestion() {
        ++currentQuestionIndex;

        Log.d("XXX", String.valueOf(currentQuestionIndex));

        if(currentQuestionIndex >= allQuestions.size()) {
            return ;
//            TODO: go to result activity to show result
        }
        currentQuestion.postValue(allQuestions.get(currentQuestionIndex));
    }

    private void getQuestions(){
        // Make API call
        ApiInterface api = RetrofitClient.getService();
        api.getALLQuestions().enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                setQuiz(response.body());
                Log.d("QuestionActivity", String.valueOf(response.code()));
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Log.d("QuestionActivity", t.getLocalizedMessage());
            }
        });
    }

    public LiveData<BaseResponse> getQuiz() {
        return quiz;
    }

    private void setQuiz(BaseResponse quiz) {
        this.quiz.postValue(quiz);
        allQuestions = quiz.getQuestions();
        currentQuestion.postValue(allQuestions.get(currentQuestionIndex));

    }

//    public MutableLiveData<Integer> getCorrectAnswer() {
//        return correctAnswer;
//    }
//
//    public void setCorrectAnswer(Integer correctAnswer) {
//        this.correctAnswer.postValue(correctAnswer);
//    }
}
