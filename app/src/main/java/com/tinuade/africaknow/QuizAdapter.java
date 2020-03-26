package com.tinuade.africaknow;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.tinuade.africaknow.Model.Answer;
import com.tinuade.africaknow.Model.Question;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by SegunFrancis
 */
public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder> {

    private List<Question> mQuestionList;

    public QuizAdapter(List<Question> questionList) {
        mQuestionList = questionList;
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new QuizViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.question_fragment_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return mQuestionList.size();
    }

    class QuizViewHolder extends RecyclerView.ViewHolder {
        TextView question;
        RadioGroup radioGroup;

        QuizViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void bind(List<Answer> answers ){

        }

    }
}
