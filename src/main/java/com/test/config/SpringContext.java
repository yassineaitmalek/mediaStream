package com.test.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContext implements ApplicationContextAware {

    private static Holder<ApplicationContext> contextHolder = new Holder<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        contextHolder.hold(applicationContext);
    }

    public static ApplicationContext getContext() {
        return contextHolder.get();
    }

    public static class Holder<T> {

        private T value;

        public void hold(T value) {
            this.value = value;
        }

        public T get() {
            return value;
        }

    }

}
