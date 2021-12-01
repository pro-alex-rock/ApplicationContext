package com;

import com.model.BeanDefinition;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class XmlBeanDefinitionReaderTest {
    XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader();

    @Test
    public void shouldReturnBeanDefinitionsTrue() {
        String[] files = new String[]{"test1.xml", "test2.xml"}; //TODO create two files with two beans in sum
        List<BeanDefinition> expectedBeanDefinitions = new ArrayList<>(); //TODO write two BeanDefinitions in list
        List<BeanDefinition> actualBeanDefinitions = Arrays.stream(files)
                .map(beanDefinitionReader::readBeanDefinitions)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        assertEquals(expectedBeanDefinitions, actualBeanDefinitions);
    }
}