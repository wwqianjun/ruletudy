package com.qianjun.rules.core.impl;

import com.qianjun.rules.core.Rule;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class BasicRule implements Rule, Comparable<Rule> {

    protected String  name;

    protected String  description;

    protected int     priority;
    
    protected Map<String, Object> params=null;

    public BasicRule() {
        this(Rule.DEFAULT_NAME, Rule.DEFAULT_DESCRIPTION, Rule.DEFAULT_PRIORITY);
    }

    public BasicRule(final Map<String, Object> ruleParam) {
        this(Rule.DEFAULT_NAME, Rule.DEFAULT_DESCRIPTION, Rule.DEFAULT_PRIORITY);
    }

    public BasicRule(final String name) {
        this(name, Rule.DEFAULT_DESCRIPTION, Rule.DEFAULT_PRIORITY);
    }

    public BasicRule(final String name, final String description) {
        this(name, description, Rule.DEFAULT_PRIORITY);
    }

    public BasicRule(final String name, final String description, final int priority) {
        this.name = name;
        this.description = description;
        this.priority = priority;
    }

    @Override
    public void execute() throws Exception {
        log.info("BasicRule execute");
    }

    @Override
    public void beforeExecute() {
        log.info("BasicRule beforeExecute");
    }

    @Override
    public void afterExecute() {
        log.info("BasicRule afterExecute");
    }

    @Override
    public void onFailure(Exception e) {
        log.info("BasicRule onFailure");
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(final int priority) {
        this.priority = priority;
    }
    
    @Override
    public void setParams(Map<String, Object> params) {
        this.params=params;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        BasicRule basicRule = (BasicRule) o;

        if (priority != basicRule.priority)
            return false;
        if (!name.equals(basicRule.name))
            return false;
        return !(description != null ? !description.equals(basicRule.description) : basicRule.description != null);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + priority;
        return result;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(final Rule rule) {
        if (getPriority() < rule.getPriority()) {
            return -1;
        } else if (getPriority() > rule.getPriority()) {
            return 1;
        } else {
            return getName().compareTo(rule.getName());
        }
    }

   
}
