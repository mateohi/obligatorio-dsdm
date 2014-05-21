package com.findme.service.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class BeanCycleReport implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        String clase = bean.getClass().toString();
        System.out.println("Nombre: " + beanName + " - Clase: " + clase);
        return bean;
    }
}
