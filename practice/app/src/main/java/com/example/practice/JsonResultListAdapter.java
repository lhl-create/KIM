package com.example.practice;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class JsonResultListAdapter extends RecyclerView.Adapter<JsonResultListAdapter.InnerHolder> {

    private List<App.DataBean> data=new ArrayList<>();
    @NonNull
    @Override
    public JsonResultListAdapter.InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView =   LayoutInflater.from(parent.getContext()).inflate(R.layout.item_json_result,parent,false);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull JsonResultListAdapter.InnerHolder holder, int position) {
        ImageView icon= holder.itemView.findViewById(R.id.result_cover);
        TextView name= holder.itemView.findViewById(R.id.result_name);
        WebView link=holder.itemView.findViewById(R.id.result_link);
        final App.DataBean dataBean= data.get(position);
        name.setText(dataBean.getName());
        link.loadUrl(dataBean.getLink());
    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(App app) {
        data.clear();
        data.addAll(app.getData());
        notifyDataSetChanged();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
