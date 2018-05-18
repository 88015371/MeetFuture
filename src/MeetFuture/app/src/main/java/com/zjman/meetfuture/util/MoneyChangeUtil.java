package com.zjman.meetfuture.util;

import java.text.DecimalFormat;

/**
 * Created by yunchao.cao on 2017/3/18.
 */

public class MoneyChangeUtil {
    /**
     * 0 元--->分 1 分--->元
     *
     * @param yM
     * @param type
     * @return
     */
    public static String moneyTran(float yM, int type) {
        String result = "0";
        double f = 0;
        double money = 0;

        try {
            money = Double.valueOf(yM);
        } catch (Exception e) {

        }

        try {
            if (type == 0) {
                // 元转分
                f = money * 100;
                result = new DecimalFormat("0").format(f);
            } else if (1 == type) {
                // 分转元
                f = money / 100;
                result = new DecimalFormat("0.00").format(f);
            } else if (2 == type) {
                // 分转元
                f = money / 100;
                result = new DecimalFormat("#,##0.00").format(f);
            } else if (3 == type) {
                f = money / 10000;
                result = new DecimalFormat("0.00").format(f);
            } else if (11 == type) {
                // 分转万元
                f = money / 100;
                if (f >= 10000) {
                    f = f / 10000;
                    result = String.valueOf((int) f) + "万元";
                } else {
                    result = String.valueOf((int) f) + "元";

                }
            }
        } catch (Exception e) {
        }
        return result;
    }
}
