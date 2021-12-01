package com;

import com.model.BeanDefinition;

import java.util.List;

public interface BeanDefinitionReader {

    public List<BeanDefinition> readBeanDefinitions(String path);
}
