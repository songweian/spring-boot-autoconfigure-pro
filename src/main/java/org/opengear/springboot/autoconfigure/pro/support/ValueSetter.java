package org.opengear.springboot.autoconfigure.pro.support;

import org.springframework.beans.factory.ObjectProvider;

public class ValueSetter {

    private ValueSetter() {
    }

    public static ValueSetter of() {
        return new ValueSetter();
    }

    public <R, V> ValueSetter setIfValueNotNull(Setter1<R, V> setter, V value) {
        if (value != null) {
            setter.set(value);
        }
        return this;
    }

    public <R, V> ValueSetter setIfValueNotNull(Setter1<R, V> setter, V value, V defaultValue) {
        if (value != null) {
            setter.set(value);
        } else {
            setter.set(defaultValue);
        }
        return this;
    }

    public <R, V> ValueSetter setIfValueNotNull(Setter1<R, V> setter, ObjectProvider<V> value, V defaultValue) {
        if (value != null) {
            value.forEach(setter::set);
        } else {
            setter.set(defaultValue);
        }
        return this;
    }

    public <R, V> ValueSetter setIfAbsent(Setter1<R, V> setter, V value) {
        if (value != null) {
            setter.set(value);
        }
        return this;
    }

}
