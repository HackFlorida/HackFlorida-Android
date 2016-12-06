package com.hackflorida.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;


abstract public class DynamicRecyclerAdapter<T, VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> {

    private Class<VH> mViewHolderClass;
    private ArrayList<T> mDataset;

    public DynamicRecyclerAdapter(ArrayList<T> dataset, Class<VH> viewHolderClass) {
        this.mViewHolderClass = viewHolderClass;
        this.mDataset = dataset;
    }

    public T getItem(int position) {
        return mDataset.get(position);
    }


    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewGroup view = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        try {
            Constructor<VH> constructor = mViewHolderClass.getConstructor(View.class);
            return constructor.newInstance(view);
        } catch (NoSuchMethodException | InvocationTargetException |
                IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onBindViewHolder(VH viewHolder, int position) {
        T model = getItem(position);
        populateViewHolder(viewHolder, model, position);
    }

    @Override
    abstract public int getItemViewType(int position);


    abstract protected void populateViewHolder(VH viewHolder, T model, int position);


}
