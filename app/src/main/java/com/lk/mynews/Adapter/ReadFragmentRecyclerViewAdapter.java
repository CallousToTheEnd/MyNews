package com.lk.mynews.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lk.mynews.Bean.ReadFragmentItemBean;
import com.lk.mynews.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.li on 2016/1/22.
 */
public class ReadFragmentRecyclerViewAdapter extends RecyclerView.Adapter implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private static final int IS_HEADER = 2;
    private static final int IS_NORMAL = 1;

    private MyViewHolder viewHolder;
    private List<ReadFragmentItemBean> mReads = new ArrayList<>();

    private Context mContext;
    private Activity mActivity;

    private OnRecyclerViewItemClickListener mItemClickListener = null;

    //Unfun dialog 选择的选项
    private List<CheckBox> checkBoxs = new ArrayList<>();

    private ImageLoader imageLoader;
    private ImageSize imageSize = new ImageSize(110, 70);

    public ReadFragmentRecyclerViewAdapter(List<ReadFragmentItemBean> mReads, Context mContext, Activity activity) {
        this.mReads = mReads;
        this.mContext = mContext;
        this.mActivity = activity;
    }

    @Override
    public void onClick(View v) {
        if (mItemClickListener != null) {
            mItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener){
        mItemClickListener = listener;
    }


    public static interface OnRecyclerViewItemClickListener{
        void onItemClick(View v, int position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == IS_HEADER) {
            View view = LayoutInflater.from(mContext)
                    .inflate(R.layout.read_recyclerview_header,parent, false);
            viewHolder = new MyViewHolder(view, viewType);
            return viewHolder;
        } else {
            View view = LayoutInflater.from(mContext)
                    .inflate(R.layout.read_recyclerview_viewholder,parent,false);
            viewHolder = new MyViewHolder(view,viewType);
            view.setOnClickListener(this);
            return viewHolder;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myh = (MyViewHolder) holder;
        if (myh.getItemType() == IS_HEADER) {
            myh.getBtnHead().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "立即登陆", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
//            myh.getImage().setImageResource(mReads.get(position - 1).getImage());
            imageLoader.getInstance().displayImage(mReads.get(position - 1).getImage(),
                    myh.getImage(), imageSize);
            myh.getTvDesc().setText(mReads.get(position - 1).getDesc());
            myh.getTvSource().setText(mReads.get(position -1 ).getSource());
            myh.getBtnUnfun().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showUnfunDialog(v);
                }
            });
            myh.itemView.setTag(position - 1);
        }

    }

    @Override
    public int getItemCount() {
        return mReads.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return IS_HEADER;
        } else {
            return IS_NORMAL;
        }
    }

    public void showUnfunDialog(View v) {
        checkBoxs.clear();
        int yyy;
        int xy[] = new int[2];
        final AlertDialog dialog = new AlertDialog.Builder(mContext).create();
        //获取应用程序范围
        Rect outRect = new Rect();
        mActivity.getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect);
        //状态栏的高度
        yyy = outRect.top;
        v.getLocationOnScreen(xy);
        dialog.show();

        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        window .setGravity( Gravity.TOP);
        lp.y = xy[1] + v.getHeight() - yyy;
        window.setAttributes(lp);
        window.setContentView(R.layout.dialog_read_fragment);

        CheckBox cbExplore = (CheckBox) window.findViewById(R.id.cbReadDialogExplore);
        CheckBox cbTitleParty = (CheckBox) window.findViewById(R.id.cbReadDialogTitleParty);
        CheckBox cbFalseNews = (CheckBox) window.findViewById(R.id.cbReadDialogFalseNews);
        CheckBox cbContentRepetition = (CheckBox) window.findViewById(R.id.cbReadDialogContentRepetition);
        CheckBox cbDada = (CheckBox) window.findViewById(R.id.cbReadDialogDada);
        CheckBox cbYellowViolent = (CheckBox) window.findViewById(R.id.cbReadDialogYellowViolent);
        CheckBox cbAnti_antisocial = (CheckBox) window.findViewById(R.id.cbReadDialogAnti_antisocial);
        cbExplore.setOnCheckedChangeListener(this);
        cbTitleParty.setOnCheckedChangeListener(this);
        cbFalseNews.setOnCheckedChangeListener(this);
        cbContentRepetition.setOnCheckedChangeListener(this);
        cbDada.setOnCheckedChangeListener(this);
        cbYellowViolent.setOnCheckedChangeListener(this);
        cbAnti_antisocial.setOnCheckedChangeListener(this);
        Button btnReadDialogOk = (Button) window.findViewById(R.id.btnReadDialogOk);
        btnReadDialogOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ss = "";
                if (checkBoxs.size()>0) {
                    for (int i = 0; i<checkBoxs.size(); i++) {
                        ss += checkBoxs.get(i).getText() + "\n";
                    }
                }
                Toast.makeText(mContext, "你选择了:" + ss, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            checkBoxs.add((CheckBox) buttonView);
        } else {
            if (checkBoxs.contains(buttonView)) {
                checkBoxs.remove(buttonView);
            }
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private View rootView;
        private ImageView image;
        private TextView tvDesc, tvSource;
        private Button btnUnfun, btnHead;

        private int itemType;

        public MyViewHolder(View itemView, int itemType) {
            super(itemView);
            this.rootView = itemView;
            this.itemType = itemType;
            if (itemType == IS_HEADER) {
                btnHead = (Button) rootView.findViewById(R.id.btnReadRvHead);
            } else {
                image = (ImageView) rootView.findViewById(R.id.ivReadRvViewHolderImage);
                tvDesc = (TextView) rootView.findViewById(R.id.tvReadRvViewHolderDesc);
                tvSource = (TextView) rootView.findViewById(R.id.tvReadRvViewHolderResource);
                btnUnfun = (Button) rootView.findViewById(R.id.btnReadRvViewHolderUnfun);
            }

        }

        public ImageView getImage() {
            return this.image;
        }

        public TextView getTvDesc() {
            return this.tvDesc;
        }

        public TextView getTvSource() {
            return this.tvSource;
        }

        public Button getBtnUnfun() {
            return this.btnUnfun;
        }

        public Button getBtnHead() {
            return this.btnHead;
        }

        public int getItemType() { return this.itemType; }

    }

}
