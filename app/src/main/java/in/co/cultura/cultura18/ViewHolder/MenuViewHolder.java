package in.co.cultura.cultura18.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import in.co.cultura.cultura18.Interface.ItemClickListener;
import in.co.cultura.cultura18.R;


public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

     public TextView txtMenuName;
     public ImageView imageView;

     private ItemClickListener itemClickListener;



    public MenuViewHolder(View itemView) {
        super(itemView);

        imageView=(ImageView) itemView.findViewById(R.id.menu_image);

        itemView.setOnClickListener(this);


    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {

        itemClickListener.onClick(v,getAdapterPosition(),false);
    }
}
