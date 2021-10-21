package Utils;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

public class PropKit {
    private static Prop prop = null;
    private static final ConcurrentHashMap<String, Prop> map = new ConcurrentHashMap();

    private PropKit() {
    }

    public static Prop useFirstFound(String... fileNames) {
        String[] var1 = fileNames;
        int var2 = fileNames.length;
        int var3 = 0;
        while (var3 < var2) {
            String fn = var1[var3];
            try {
                return use(fn, "UTF-8");
            } catch (Exception var6) {
                ++var3;
            }
        }
        throw new IllegalArgumentException("没有配置⽂件可被使⽤");
    }

    public static Prop use(String fileName) {
        return use(fileName, "UTF-8");
    }

    public static Prop use(String fileName, String encoding) {
        Prop result = (Prop) map.get(fileName);
        if (result == null) {
            Class var3 = PropKit.class;
            synchronized (PropKit.class) {
                result = (Prop) map.get(fileName);
                if (result == null) {
                    result = new Prop(fileName, encoding);
                    map.put(fileName, result);
                    if (prop == null) {
                        prop = result;
                    }
                }
            }
        }
        return result;
    }

    public static Prop use(File file) {
        return use(file, "UTF-8");
    }

    public static Prop use(File file, String encoding) {
        Prop result = (Prop) map.get(file.getName());
        if (result == null) {
            Class var3 = PropKit.class;
            synchronized (PropKit.class) {
                result = (Prop) map.get(file.getName());
                if (result == null) {
                    result = new Prop(file, encoding);
                    map.put(file.getName(), result);
                    if (prop == null) {
                        prop = result;
                    }
                }
            }
        }
        return result;
    }

    public static Prop useless(String fileName) {
        Prop previous = (Prop) map.remove(fileName);
        if (prop == previous) {
            prop = null;
        }
        return previous;
    }

    public static void clear() {
        prop = null;
        map.clear();
    }

    public static Prop append(Prop prop) {
        Class var1 = PropKit.class;
        synchronized (PropKit.class) {
            if (PropKit.prop != null) {
                PropKit.prop.append(prop);
            } else {
                PropKit.prop = prop;
            }
            return PropKit.prop;
        }
    }

    public static Prop append(String fileName, String encoding) {
        return append(new Prop(fileName, encoding));
    }

    public static Prop append(String fileName) {
        return append(fileName, "UTF-8");
    }

    public static Prop appendIfExists(String fileName, String encoding) {
        try {
            return append(new Prop(fileName, encoding));
        } catch (Exception var3) {
            return prop;
        }
    }

    public static Prop appendIfExists(String fileName) {
        return appendIfExists(fileName, "UTF-8");
    }

    public static Prop append(File file, String encoding) {
        return append(new Prop(file, encoding));
    }

    public static Prop append(File file) {
        return append(file, "UTF-8");
    }

    public static Prop appendIfExists(File file, String encoding) {
        if (file.exists()) {
            append(new Prop(file, encoding));
        }
        return prop;
    }

    public static Prop appendIfExists(File file) {
        return appendIfExists(file, "UTF-8");
    }

    public static Prop getProp() {
        if (prop == null) {
            throw new IllegalStateException("Load propties file by invokingPropKit.use(String fileName) method first.");
        } else {
            return prop;
        }
    }

    public static Prop getProp(String fileName) {
        return (Prop) map.get(fileName);
    }

    public static String get(String key) {
        return getProp().get(key);
    }

    public static String get(String key, String defaultValue) {
        return getProp().get(key, defaultValue);
    }

    public static Integer getInt(String key) {
        return getProp().getInt(key);
    }

    public static Integer getInt(String key, Integer defaultValue) {
        return getProp().getInt(key, defaultValue);
    }

    public static Long getLong(String key) {
        return getProp().getLong(key);
    }

    public static Long getLong(String key, Long defaultValue) {
        return getProp().getLong(key, defaultValue);
    }

    public static Double getDouble(String key) {
        return getProp().getDouble(key);
    }

    public static Double getDouble(String key, Double defaultValue) {
        return getProp().getDouble(key, defaultValue);
    }

    public static Boolean getBoolean(String key) {
        return getProp().getBoolean(key);
    }

    public static Boolean getBoolean(String key, Boolean defaultValue) {
        return getProp().getBoolean(key, defaultValue);
    }

    public static boolean containsKey(String key) {
        return getProp().containsKey(key);
    }
}