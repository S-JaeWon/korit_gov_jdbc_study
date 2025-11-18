package com.korit.Test;

import com.korit.Test.dao.ProduceDao;
import com.korit.Test.entity.Produce;

public class Main {
    public static void main(String[] args) {
        ProduceDao produceDao = ProduceDao.getInstance();

        Produce produce = new Produce();

//        produce.setName("사과");
//        produce.setPrice(3000);
//        produce.setColor("초록색");
//
//        produceDao.add(produce);

        System.out.println(produceDao.getProduce("사과"));


    }
}
