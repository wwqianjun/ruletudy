package com.qianjun.rules.core;

import java.util.Set;

public interface RulesEngine {

    String DEFAULT_NAME = "engine";

    void registerRule(Rule rule);

    void unregisterRule(Rule rule);

    Set<Rule> getRules();

    void executeRules();

    void clearRules();
}
