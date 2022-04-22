package com.rocket.src.viewcomponents;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.rocket.src.R;
import com.rocket.src.quiz.Answer;
import com.rocket.src.quiz.QuizQuestion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ResultViewHolder> {
    private final List<QuizQuestion> questionList;
    private final List<Answer> answerList;


    @RequiresApi(api = Build.VERSION_CODES.N)
    public RecyclerAdapter(HashMap<QuizQuestion, Answer> answerHashMap) {
        questionList = new ArrayList<>();
        answerList = new ArrayList<>();
        answerHashMap.forEach((key, value) -> {
            questionList.add(key);
            answerList.add(value);
        });
    }

    @NonNull
    @Override
    public RecyclerAdapter.ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                               int viewType) {
        View itemView =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new RecyclerAdapter.ResultViewHolder(itemView, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {
        String question = questionList.get(position).getQuestion();
        String user_answer = answerList.get(position).getAnswer();
        String correct_answer =
                questionList.get(position).getCorrectAnswer().getAnswer();
        MarkdownWrapper.applyMarkdown(holder.context,holder.question,question);
        MarkdownWrapper.applyMarkdown(holder.context,holder.answer,user_answer);
        MarkdownWrapper.applyMarkdown(holder.context,holder.correctAnswer,correct_answer);

    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public class ResultViewHolder extends RecyclerView.ViewHolder {
        private final TextView question;
        private final TextView answer;
        private final TextView correctAnswer;
        private Context context;
        public ResultViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.context = context;
            question = itemView.findViewById(R.id.tv_question);
            answer = itemView.findViewById(R.id.tv_user_answer);
            correctAnswer = itemView.findViewById(R.id.tv_correct_answer);
        }
    }
}
