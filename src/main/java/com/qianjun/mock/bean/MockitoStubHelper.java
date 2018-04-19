package com.qianjun.mock.bean;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import java.io.File;
import java.lang.reflect.Method;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import com.alibaba.fastjson.JSON;

/**
 * @author qianjun
 * @Description: 创建工具类，实现mockito的打桩功能
 * @create 2018-04-19 16:09
 * @last modify by [qianjun 2018-04-19 16:09]
 **/
public class MockitoStubHelper<T> {
    private T mockObject;

    private String filePath;
    public MockitoStubHelper(T mockObject, String filePath) {
        this.mockObject = mockObject;
        this.filePath = filePath;
    }

    public void doStub() throws Exception {
        // 解析Json文件 获取方法名 入参 返参
        File file = new File(filePath);
        String jsonList = FileUtils.readFileToString(file);
        List<MockArguments> list = JSON.parseArray(jsonList, MockArguments.class);
        Method[] methods = mockObject.getClass().getMethods();
        for (MockArguments mockArguments : list) {
            // 调用mockito的when方法进行打桩(咱不支持参数个数相同类型不同的方法重载)
            doWhen(methods, mockArguments);
        }
    }
    /**
     * @param methods
     * @param mockArguments
     * @throws Exception
     */
    private void doWhen(Method[] methods, MockArguments mockArguments) throws Exception {
        // 找到方法
        Method curMethod = null;
        for (Method method : methods) {
            if (method.getName().equalsIgnoreCase(mockArguments.getMemthod())
                    && ((method.getParameterTypes().length==0 && (mockArguments.getInput() == null || mockArguments.getInput().length == 0))
                    || (mockArguments.getInput() != null && mockArguments.getInput().length == method.getParameterTypes().length))) {
                curMethod = method;
            }
        }
        if (curMethod == null) {
            throw new RuntimeException("method:" + mockArguments.getMemthod() + " is not exist.");
        }
        // 转换入参 JsonStr to Object
        String[] strs = mockArguments.getInput();
        if (strs == null) {
            strs = new String[0];
        }
        Class<?>[] parameterTypes = curMethod.getParameterTypes();
        Object[] args = new Object[strs.length];
        for (int i = 0; i < args.length; i++) {
            args[i] = innerParseObject(strs[i], parameterTypes[i]);
        }
        // 根据出参类型 进行打桩
        String output = mockArguments.getOutput();
        if (output == null) {
            // doNothing
            doNothing().when(curMethod.invoke(mockObject, args));
        } else if (output instanceof String
                && StringUtils.containsIgnoreCase((String) output, "exception")) {
            // doThrow
            doThrow((Throwable)Class.forName((String) output).newInstance()).when(curMethod.invoke(mockObject, args));
        } else {
            // 转换出参 JsonStr to Object
            Object object = innerParseObject(output, curMethod.getReturnType());
            // when...then...
            when(curMethod.invoke(mockObject, args)).thenReturn(object);
        }
    }

    private Object innerParseObject(String value, Class<?> parameterType) throws Exception {
        if (parameterType.equals(String.class)) {
            return value;
        } else if (parameterType.isPrimitive()) {
            if (parameterType.equals(Character.class)) {
                return value.charAt(0);
            }
            if (parameterType.equals(Void.class)) {
                return null;
            }
            Method method = parameterType.getMethod("valueOf", String.class);
            method.invoke(parameterType, value);
            return method.invoke(parameterType, value);
        } else {
            return JSON.parseObject(value, parameterType);
        }
    }
}
