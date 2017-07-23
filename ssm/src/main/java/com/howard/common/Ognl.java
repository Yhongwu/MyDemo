package com.howard.common;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * 扩展Ongl, 用于判断mybatis mapper文件中的变量
 *
 * @author ningyb
 */
public class Ognl {
  /**
   * 可以用于判断 Map,Collection,String,Array是否为空
   *
   * @param o
   * @return
   */
  @SuppressWarnings("rawtypes")
  public static boolean isEmpty(Object o) throws IllegalArgumentException {
    if (o == null) {
      return true;
    }
    if (o instanceof String) {
      return StringUtils.isEmpty((String) o);
    } else if (o instanceof Collection) {
      return ((Collection) o).isEmpty();
    } else if (o.getClass().isArray()) {
      return ArrayUtils.isEmpty((Object[]) o);
    } else if (o instanceof Map) {
      return ((Map) o).isEmpty();
    } else if (o instanceof Date) {
      return o == null;
    } else if (o instanceof Number) {
      return o == null;
    } else if (o instanceof Boolean) {
      return o == null;
    } else {
      throw new IllegalArgumentException(
          "Illegal argument type,must be : Map,Collection,Array,String. but was:" + o.getClass());
    }
  }

  /**
   * 可以用于判断 Map,Collection,String,Array是否不为空
   *
   * @param c
   * @return
   */
  public static boolean isNotEmpty(Object o) {
    return !isEmpty(o);
  }

  public static boolean isNotEmpty(Object... objects) {
    if (objects == null) {
      return false;
    }
    for (Object obj : objects) {
      if (isEmpty(obj)) {
        return false;
      }
    }
    return true;
  }

  public static boolean hasValueEither(Object o1, Object o2) {
    return isNotEmpty(o1) || isNotEmpty(o2);
  }

  public static boolean isNotBlank(Object o) {
    return !isBlank(o);
  }

  public static boolean isNumber(Object o) {
    if (o instanceof Number) {
      return true;
    } else if (o instanceof String) {
      return NumberUtils.isNumber((String) o);
    } else {
      return false;
    }
  }

  public static boolean isBlank(Object o) {
    return StringUtils.isBlank((String) o);
  }

  public static boolean isBlank(String str) {
    return StringUtils.isBlank(str);
  }

  public static boolean isStringEqual(String str1, String str2) {
    if (str1 == null || str2 == null) {
      return false;
    }
    return str1.equals(str2);
  }

  public static String alias(String sql, String name, String alias) {
    if (StringUtils.isBlank(sql)) {
      return "";
    }
    return sql.replace(name, alias);
  }

  public static String alias(String sql, String name1, String alias1, String name2, String alias2) {
    if (StringUtils.isBlank(sql)) {
      return "";
    }
    return sql.replace(name1, alias1).replace(name2, alias2);
  }

  public static String alias(String sql, String name1, String alias1, String name2, String alias2,
      String name3, String alias3) {
    if (StringUtils.isBlank(sql)) {
      return "";
    }
    return sql.replace(name1, alias1).replace(name2, alias2).replace(name3, alias3);
  }

}
