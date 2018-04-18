package com.qianjun.rules.core;

import java.util.Map;

public interface Rule {

    String DEFAULT_NAME        = "rule";

    String DEFAULT_DESCRIPTION = "description";

    int    DEFAULT_PRIORITY    = Integer.MAX_VALUE - 1;

    String getName();

    String getDescription();

    int getPriority();

    void execute() throws Exception;

    void beforeExecute();

    void afterExecute();

    void onFailure(Exception e);

    void setParams(Map<String, Object> params);
}
