package com.alibaba.edison;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 背包问题.不能用贪心算法求解。
 * Created by 钧戈 on 2020/5/23.
 */
public class PackageAlgorithm {

    public void buy() {
        int totalWeigh = 150;
        List<ItemModel> itemModelList = new ArrayList<>();
        itemModelList.add(new ItemModel(35,10));
        itemModelList.add(new ItemModel(30,40));
        itemModelList.add(new ItemModel(60,30));
        itemModelList.add(new ItemModel(50,50));
        itemModelList.add(new ItemModel(40,35));
        itemModelList.add(new ItemModel(10,40));
        itemModelList.add(new ItemModel(25,30));

        ItemModel[] itemModels = new ItemModel[itemModelList.size()];
        Arrays.sort(itemModels, new Comparator<ItemModel>() {
            @Override
            public int compare(ItemModel o1, ItemModel o2) {
                if (o1.pw > o2.pw) {
                    return 1;
                } else if (o1.pw < o2.pw) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        List<ItemModel> buylist = new ArrayList<>();

        for (int i = itemModels.length - 1; i >= 0; i--) {
            if (itemModels[i].weigh > totalWeigh) {
                continue;
            } else {
                buylist.add(itemModels[i]);
                totalWeigh -= itemModels[i].weigh;
            }
        }
        String s = "abgddg";

    }


    public static class ItemModel {
        public int weigh;
        public int price;
        public float pw;
        public boolean buy;

        public ItemModel(int weigh, int price) {
            this.weigh = weigh;
            this.price = price;
            pw = ((float)price)/weigh;
            buy = false;
        }
    }
}
