package com.monetware.dispatchcenter.system.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;

/**
 * @Author: Cookie
 * @Date: 2020-09-16 13:37
 * @Description: 拷贝list
 */
public class BeanCopyUtil extends BeanUtils {

    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target) {
        List<T> list = new ArrayList<>(sources.size());
        for (S source : sources) {
            T t = target.get();
            copyProperties(source, t);
            list.add(t);
        }
        return list;
    }

    public static HashMap<String, Object> objectToMap(Object object) {
        return JSONObject.parseObject(JSONObject.toJSONString(object), HashMap.class);
    }
}
