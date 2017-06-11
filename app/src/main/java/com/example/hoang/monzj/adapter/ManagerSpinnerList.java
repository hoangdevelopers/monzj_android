package com.example.hoang.monzj.adapter;

import com.example.hoang.monzj.model.SpinnerItem;

/**
 * Created by hoangdev on 26/04/2017.
 */

public interface ManagerSpinnerList {
    SpinnerItem getItem(int position);

    int sizeItemList();

    void addItem(SpinnerItem item);
}
