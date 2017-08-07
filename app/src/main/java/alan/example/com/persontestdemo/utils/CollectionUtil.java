package alan.example.com.persontestdemo.utils;

import java.util.Collection;

/**
 * 集合处理
 * Created by qk14 on 2017/6/26.
 */

public class CollectionUtil {

    public static boolean isEmpty(Collection<?> list) {
        if (list == null) {
            return true;
        }
        return list.isEmpty();
    }

    public static int size(Collection<?> list) {
        if (CollectionUtil.isEmpty(list)) {
            return 0;
        }
        return list.size();
    }
}
