package com.example.ross.shoppinglistapp.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.example.ross.shoppinglistapp.EditInterface;
import com.example.ross.shoppinglistapp.MainActivity;
import com.example.ross.shoppinglistapp.R;
import com.example.ross.shoppinglistapp.data.Todo;

import java.util.Collections;
import java.util.List;

public class TodoRecyclerAdapter extends
        RecyclerView.Adapter<TodoRecyclerAdapter.ViewHolder>
        implements TodoTouchHelperAdapter {

    private List<Todo> todoList;
    private EditInterface editInterface;
//    private ViewGroup parentActivity;

    public TodoRecyclerAdapter(EditInterface editInterface) {
        this.editInterface = editInterface;

        todoList = Todo.listAll(Todo.class);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View todoRow = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.todo_row,parent,false);
//        parentActivity = parent;
        return new ViewHolder(todoRow);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.cbDone.setChecked(todoList.get(position).isDone());
        holder.tvTodo.setText(todoList.get(position).getTodoTitle());
        holder.tvDescription.setText(todoList.get(position).getTodoDescription());
        holder.tvType.setText(todoList.get(position).getTodoType());
        holder.tvPrice.setText(todoList.get(position).getTodoPrice());

        if (holder.tvType.getText().equals("Book")) {
            holder.icon.setImageResource(R.drawable.book_ic);
        }
        else if (holder.tvType.getText().equals("Food")) {
            holder.icon.setImageResource(R.drawable.food_ic);
        }
        else if (holder.tvType.getText().equals("Cleaning")) {
            holder.icon.setImageResource(R.drawable.cleaning_ic);
        }
        else if (holder.tvType.getText().equals("Electronic")) {
            holder.icon.setImageResource(R.drawable.elec_ic);
        }


        holder.icEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editInterface.showEditDialog(
                        todoList.get(holder.getAdapterPosition()), holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    @Override
    public void onItemDismiss(int position) {
        todoList.get(position).delete();

        todoList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        /*if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(todoList, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(todoList, i, i - 1);
            }
        }*/

        //Collections.

        todoList.add(toPosition, todoList.get(fromPosition));
        todoList.remove(fromPosition);


        notifyItemMoved(fromPosition, toPosition);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private CheckBox cbDone;
        private TextView tvTodo;
        private TextView tvType;
        private TextView tvDescription;
        private TextView tvPrice;
        private ImageView icon;
        private ImageView icEdit;

        public ViewHolder(View itemView) {
            super(itemView);
            cbDone = (CheckBox) itemView.findViewById(R.id.cbDone);
            tvTodo = (TextView) itemView.findViewById(R.id.tvTodo);
            tvType = (TextView) itemView.findViewById(R.id.tvType);
            tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
            tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            icEdit = (ImageView) itemView.findViewById(R.id.icEdit);
        }
    }

    public void addTodo(Todo todo) {
        todo.save();
        todoList.add(0, todo);

        notifyItemInserted(0);
    }

    public void edit(Todo todo, int position) {
        todo.save();
        todoList.set(position, todo);
        notifyItemChanged(position);
    }

    public void deleteAllItems() {
        Todo.deleteAll(Todo.class);
        todoList.removeAll(todoList);
//        parentActivity.fillItemCount();
        notifyDataSetChanged();

    }
}