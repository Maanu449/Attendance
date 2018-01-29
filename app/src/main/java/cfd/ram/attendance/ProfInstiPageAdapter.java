package cfd.ram.attendance;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mansi on 29/1/18.
 */

public class ProfInstiPageAdapter extends RecyclerView.Adapter<ProfInstiPageAdapter.MyViewHolder> {
    private List<Prof> profList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView profName, profEmail;

        public MyViewHolder(View view) {
            super(view);
            profName = (TextView) view.findViewById(R.id.profName);
            profEmail = (TextView) view.findViewById(R.id.profEmailId);
        }
    }


    public ProfInstiPageAdapter(List<Prof> profList) {
        this.profList = profList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.prof_row_insti_page, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Prof prof = profList.get(position);
        holder.profName.setText(prof.getProfName());
        holder.profEmail.setText(prof.getProfEmail());
    }

    @Override
    public int getItemCount() {
        return profList.size();
    }
}
