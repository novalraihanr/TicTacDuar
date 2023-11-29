package com.example.tictacduar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ViewHolder> {
    Context context;
    List<Score> scoreList;
    public ScoreAdapter(Context context, List<Score> scoreLists){
        this.context = context;
        this.scoreList = scoreLists;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(scoreList != null){
            Score score = scoreList.get(position);
            holder.time.setText(score.getTime());
            holder.player1.setText(score.getPlayer1());
            holder.player2.setText(score.getPlayer2());
            holder.status.setText(score.getWinner());
        }else{
            return;
        }
    }

    @Override
    public int getItemCount() {
        return this.scoreList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView time, player1, player2, status;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            time = itemView.findViewById(R.id.column_time);
            player1 = itemView.findViewById(R.id.column_player1);
            player2 = itemView.findViewById(R.id.column_player2);
            status = itemView.findViewById(R.id.column_status);
        }
    }
}
