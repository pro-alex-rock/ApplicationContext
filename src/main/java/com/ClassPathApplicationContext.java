package com;

import com.model.Bean;
import com.model.BeanDefinition;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ClassPathApplicationContext implements ApplicationContext {

    private BeanDefinitionReader beanDefinitionReader;
    private List<Bean> beans;

    public ClassPathApplicationContext(String... xmlFiles) {
        beanDefinitionReader = new XmlBeanDefinitionReader();
        List<BeanDefinition> beanDefinitions = getBeanDefinitions(xmlFiles);
        List<Bean> beans = createBeans(beanDefinitions);
        injectValueDependencies(beanDefinitions, beans);
        injectRefDependencies(beanDefinitions, beans);
    }

    @Override
    public Object getBean(String id) {
        return null;
    }

    @Override
    public <T> T getBean(Class<T> clazz) {
        return null;
    }

    @Override
    public <T> T getBean(String id, Class<T> clazz) {
        return null;
    }

    @Override
    public List<String> getBeanNames() {
        return null;
    }

    private List<BeanDefinition> getBeanDefinitions(String... xmlFiles) {
        return Arrays.stream(xmlFiles)
                .map(beanDefinitionReader::readBeanDefinitions)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<Bean> createBeans(List<BeanDefinition> beanDefinitions) {
        return List.of();
    }

    private void injectValueDependencies(List<BeanDefinition> definitions, List<Bean> beans) {

    }

    private void injectRefDependencies(List<BeanDefinition> definitions, List<Bean> beans) {

    }

    public void setBeanDefinitionReader(BeanDefinitionReader beanDefinitionReader) {
        this.beanDefinitionReader = beanDefinitionReader;
    }

    public List<Bean> getBeans() {
        return beans;
    }
}
