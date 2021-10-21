package Utils;

import java.io.*;
import java.util.Properties;

public class Prop {
    protected Properties properties;

    public Prop() {
        this.properties = new Properties();
    }

    public Prop(String fileName) {
        this(fileName, "UTF-8");
    }

    public Prop(String fileName, String encoding) {
        InputStream inputStream = null;
        try {
            inputStream = this.getClassLoader().getResourceAsStream(fileName);
            if (inputStream == null) {
                throw new IllegalArgumentException("Properties file not found inclasspath: " + fileName);
            }
            this.properties = new Properties();
            this.properties.load(new InputStreamReader(inputStream, encoding));
        } catch (IOException var12) {
            throw new RuntimeException("Error loading properties file.", var12);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException var11) {
                    var11.printStackTrace();
                }
            }
        }
    }

    private ClassLoader getClassLoader() {
        ClassLoader ret = Thread.currentThread().getContextClassLoader();
        return ret != null ? ret : this.getClass().getClassLoader();
    }

    public Prop(File file) {
        this(file, "UTF-8");
    }

    public Prop(File file, String encoding) {
        if (file == null) {
            throw new IllegalArgumentException("File can not be null.");
        } else if (!file.isFile()) {
            throw new IllegalArgumentException("File not found : " + file.getName());
        } else {
            FileInputStream inputStream = null;
            try {
                inputStream = new FileInputStream(file);
                this.properties = new Properties();
                this.properties.load(new InputStreamReader(inputStream, encoding));
            } catch (IOException var12) {
                throw new RuntimeException("Error loading properties file.", var12);
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException var11) {
                        var11.printStackTrace();
                    }
                }
            }
        }
    }

    public Prop append(Prop prop) {
        if (prop == null) {
            throw new IllegalArgumentException("prop can not be null");
        } else {
            this.properties.putAll(prop.getProperties());
            return this;
        }
    }

    public Prop append(String fileName, String encoding) {
        return this.append(new Prop(fileName, encoding));
    }

    public Prop append(String fileName) {
        return this.append(fileName, "UTF-8");
    }

    public Prop appendIfExists(String fileName, String encoding) {
        try {
            return this.append(new Prop(fileName, encoding));
        } catch (Exception var4) {
            return this;
        }
    }

    public Prop appendIfExists(String fileName) {
        return this.appendIfExists(fileName, "UTF-8");
    }

    public Prop append(File file, String encoding) {
        return this.append(new Prop(file, encoding));
    }

    public Prop append(File file) {
        return this.append(file, "UTF-8");
    }

    public Prop appendIfExists(File file, String encoding) {
        if (file.isFile()) {
            this.append(new Prop(file, encoding));
        }
        return this;
    }

    public Prop appendIfExists(File file) {
        return this.appendIfExists(file, "UTF-8");
    }

    public String get(String key) {
        String value = this.properties.getProperty(key);
        return value != null && value.length() != 0 ? value.trim() : null;
    }

    public String get(String key, String defaultValue) {
        String value = this.properties.getProperty(key);
        return value != null && value.length() != 0 ? value.trim() : defaultValue;
    }

    public Integer getInt(String key) {
        return this.getInt(key, (Integer) null);
    }

    public Integer getInt(String key, Integer defaultValue) {
        String value = this.properties.getProperty(key);
        return value != null ? Integer.parseInt(value.trim()) : defaultValue;
    }

    public Long getLong(String key) {
        return this.getLong(key, (Long) null);
    }

    public Long getLong(String key, Long defaultValue) {
        String value = this.properties.getProperty(key);
        return value != null ? Long.parseLong(value.trim()) : defaultValue;
    }

    public Double getDouble(String key) {
        return this.getDouble(key, (Double) null);
    }

    public Double getDouble(String key, Double defaultValue) {
        String value = this.properties.getProperty(key);
        return value != null ? Double.parseDouble(value.trim()) : defaultValue;
    }

    public Boolean getBoolean(String key) {
        return this.getBoolean(key, (Boolean) null);
    }

    public Boolean getBoolean(String key, Boolean defaultValue) {
        String value = this.properties.getProperty(key);
        if (value != null) {
            value = value.toLowerCase().trim();
            if ("true".equals(value)) {
                return true;
            } else if ("false".equals(value)) {
                return false;
            } else {
                throw new RuntimeException("The value can not parse to Boolean : " +
                        value);
            }
        } else {
            return defaultValue;
        }
    }

    public boolean containsKey(String key) {
        return this.properties.containsKey(key);
    }

    public boolean isEmpty() {
        return this.properties.isEmpty();
    }

    public boolean notEmpty() {
        return !this.properties.isEmpty();
    }

    public Properties getProperties() {
        return this.properties;
    }
}