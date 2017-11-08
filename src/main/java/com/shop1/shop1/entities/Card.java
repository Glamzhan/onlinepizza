package com.shop1.shop1.entities;

import java.util.ArrayList;
import java.util.List;

public class Card {
    private ArrayList<Good> list = new ArrayList<Good>();

    public ArrayList<Good> getList() {
        return list;
    }

    public void setList(ArrayList<Good> list) {
        this.list = list;
    }

    public void addListItem(Good good){
        this.list.add(this.list.size(),good);
    }

}
