package com.troy.parking.common.utils;

import java.math.BigDecimal;

/**
 * @author troy
 * @date 2020/4/15
 **/
public class MoneyUtil {
    private static final BigDecimal HUNDRED = new BigDecimal(100);

    public MoneyUtil() {
    }

    public static BigDecimal fenToYuan(long fen) {
        return (new BigDecimal(fen)).setScale(2).divide(HUNDRED, 4);
    }

    public static long yuanToFen(BigDecimal yuan) {
        return yuan.multiply(HUNDRED).longValue();
    }
}
