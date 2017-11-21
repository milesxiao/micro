package me.tdcarefor.util;

import net.sf.cglib.beans.BeanCopier;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 类: CachedBeanCopier<br/>
 * 描述: 带缓存的对象属性拷贝 <br>
 * 作者: tzw <br>
 * 时间: 2016 16/5/7 下午10:34 <br>
 */
public class CachedBeanCopier {
    static final Map<String, BeanCopier> BEAN_COPIERS = new HashMap<String, BeanCopier>();

    public static void copy(Object srcObj, Object destObj) {
        copyPropertiesIgnoreNull(srcObj,destObj);
//        String key = genKey(srcObj.getClass(), destObj.getClass());
//        BeanCopier copier = null;
//        if (!BEAN_COPIERS.containsKey(key)) {
//            copier = BeanCopier.create(srcObj.getClass(), destObj.getClass(), false);
//            BEAN_COPIERS.put(key, copier);
//        } else {
//            copier = BEAN_COPIERS.get(key);
//        }
//        copier.copy(srcObj, destObj, null);
    }

    private static String genKey(Class<?> srcClazz, Class<?> destClazz) {
        return srcClazz.getName() + destClazz.getName();
    }

    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static void copyPropertiesIgnoreNull(Object src, Object target){
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }
}
