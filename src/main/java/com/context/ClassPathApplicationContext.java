package com.context;

import com.EmailSender;
import com.context.model.Bean;
import com.context.model.BeanDefinition;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ClassPathApplicationContext implements ApplicationContext {

    private BeanDefinitionReader beanDefinitionReader;
    private List<Bean> beans;

    public ClassPathApplicationContext(String... xmlFiles) throws IOException {
        beanDefinitionReader = new XmlBeanDefinitionReader();
        List<BeanDefinition> beanDefinitions = getBeanDefinitions(xmlFiles);
        beans = createBeans(beanDefinitions);
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

    private List<Bean> createBeans(List<BeanDefinition> beanDefinitions) throws IOException {
        List<Bean> beans = new ArrayList<>();
        for (BeanDefinition beanDefinition : beanDefinitions) {
            beans.add(extractBean(beanDefinition));
        }
        for (Bean bean : beans) {
            System.out.println(bean.getId() + "---" + bean.getValue().toString());
        }
        return beans;
    }

    private Bean extractBean(BeanDefinition beanDefinition) {
        String classPath = beanDefinition.getClassName();
        String id = beanDefinition.getId();
        Bean bean = new Bean();
        Object instance = null;
        Class clazz = null;
        try {
            clazz = Class.forName(classPath);
            instance = clazz.getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            throw new RuntimeException("Couldn't get bean`s constructor", e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException("Couldn't create bean", e);
        }
        bean.setId(id);
        bean.setValue(instance);
        return bean;
    }

    private void injectValueDependencies(List<BeanDefinition> definitions, List<Bean> beans) {
        for (Bean bean : beans) {
            for (BeanDefinition definition : definitions) {
                if (bean.getId().equals(definition.getId())) {
                    //bean.getValue()
                }
            }
        }
    }

    private void injectRefDependencies(List<BeanDefinition> definitions, List<Bean> beans) {

    }

    public void setBeanDefinitionReader(BeanDefinitionReader beanDefinitionReader) {
        this.beanDefinitionReader = beanDefinitionReader;
    }

    public List<Bean> getBeans() {
        return beans;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(EmailSender.class.getName());
        new ClassPathApplicationContext("context.xml");
    }
}
