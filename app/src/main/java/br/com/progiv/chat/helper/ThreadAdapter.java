package br.com.progiv.chat.helper;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.progiv.chat.R;

public class ThreadAdapter extends RecyclerView.Adapter<ThreadAdapter.ViewHolder>{

    //user id
    private int userId;
    private Context context;

    //Tag para rastrear propria mensagem
    private int SELF = 786;

    //Lista de mensagens contendo todas as mensagens da thread
    private ArrayList<Message> messages;

    //Construtor
    public ThreadAdapter(Context context, ArrayList<Message> messages, int userId){
        this.userId = userId;
        this.messages = messages;
        this.context = context;
    }

    //Metodo para rastrear propria meensagem
    @Override
    public int getItemViewType(int position) {
        //peegando mensagem na posicao atual
        Message message = messages.get(position);

        //se ID for igual id logado
        if (message.getUsersId() == userId) {
            //Returning self
            return SELF;
        }
        //se nao, retorna posicao
        return position;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Creating view
        View itemView;
        //if view type is self
        if (viewType == SELF) {
            //Inflating the layout self
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.chat_thread, parent, false);
        } else {
            //else inflating the layout others
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.chat_thread_other, parent, false);
        }
        //returing the view
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Adding messages to the views
        Message message = messages.get(position);
        holder.textViewMessage.setText(message.getMessage());
        holder.textViewTime.setText(message.getName()+", "+message.getSentAt());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    //Initializing views
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewMessage;
        public TextView textViewTime;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewMessage = (TextView) itemView.findViewById(R.id.textViewMessage);
            textViewTime = (TextView) itemView.findViewById(R.id.textViewTime);
        }
    }
}
