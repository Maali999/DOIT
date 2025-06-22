package com.s23010658.doit.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.s23010658.doit.R;
import com.s23010658.doit.models.RequestModel;

import java.util.List;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.RequestViewHolder> {

    private List<RequestModel> requestList;

    public RequestAdapter(List<RequestModel> requestList) {
        this.requestList = requestList;
    }

    @Override
    public RequestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_request, parent, false);
        return new RequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RequestViewHolder holder, int position) {
        RequestModel model = requestList.get(position);
        holder.textSubject.setText(model.getSubject());
        holder.textDeadline.setText(model.getDeadline());
        holder.textAmount.setText(model.getAmount());
        holder.imageProfile.setImageResource(model.getImageRes());
    }

    @Override
    public int getItemCount() {
        return requestList.size();
    }

    public static class RequestViewHolder extends RecyclerView.ViewHolder {
        TextView textSubject, textDeadline, textAmount;
        ImageView imageProfile;

        public RequestViewHolder(View itemView) {
            super(itemView);
            textSubject = itemView.findViewById(R.id.txtSubject);
            textDeadline = itemView.findViewById(R.id.txtDeadline);
            textAmount = itemView.findViewById(R.id.txtPrice);
            imageProfile = itemView.findViewById(R.id.imgProfile);
        }
    }
}
