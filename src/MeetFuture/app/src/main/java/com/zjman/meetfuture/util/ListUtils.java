package com.zjman.meetfuture.util;

import java.util.List;

/**
 * Created by zym on 23/11/16.
 */

public class ListUtils {

    public static <V> boolean isEmpty(List<V> sourceList) {
        return (sourceList == null || sourceList.size() == 0);
    }
}
