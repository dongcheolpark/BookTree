package com.booktree.common;

import androidx.lifecycle.MutableLiveData;
import java.util.ArrayList;
import java.util.List;

public class MutableListLiveData<T> extends MutableLiveData<ArrayList<T>> {
  public MutableListLiveData(){
    setValue(new ArrayList<>());
  }

  public void add(T item){
    ArrayList<T> items = getValue();
    items.add(item);
    setValue(items);
  }

  public void addAll(List<T> list){
    ArrayList<T> items = getValue();
    items.addAll(list);
    setValue(items);
  }
  public void addPostAll(List<T> list){
    ArrayList<T> items = getValue();
    items.addAll(list);
    postValue(items);
  }

  public void clear(boolean notify) {
    ArrayList<T> items = getValue();
    items.clear();
    if(notify){
      setValue(items);
    }
  }

  public void remove(T item){
    ArrayList<T> items = getValue();
    items.remove(item);
    setValue(items);
  }

  public void notifyChange(){
    ArrayList<T> items = getValue();
    setValue(items);
  }
}
