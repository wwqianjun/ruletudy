package com.qianjun.rules.core.impl;

import com.qianjun.rules.core.Rule;
import com.qianjun.rules.core.RulesEngine;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.TreeSet;

class DefaultRulesEngine implements RulesEngine {

    private static final Logger LOGGER = LoggerFactory.getLogger(RulesEngine.class);

    protected Set<Rule>         rules;

    DefaultRulesEngine(final Set<Rule> rules) {
        this.rules = rules;
    }

    @Override
    public void registerRule(final Rule rule) {
        rules.add(rule);
    }

    @Override
    public void unregisterRule(final Rule rule) {
        rules.remove(rule);
    }

    @Override
    public Set<Rule> getRules() {
        return rules;
    }

    @Override
    public void clearRules() {
        rules.clear();
        LOGGER.info("Rules cleared.");
    }

    public void executeRulesBefor() {
        if (rules.isEmpty()) {
            LOGGER.warn("No rules registered! Nothing to apply");
            return;
        }
        sortRules();
        for (Rule rule : rules) {
            final String name = rule.getName();
            try {
                rule.beforeExecute();
            } catch (Exception exception) {
                rule.onFailure(exception);
                LOGGER.error(String.format("Rule '%s' performed with error", name), exception);
            }
        }
    }

    @Override
    public void executeRules() {
        if (rules.isEmpty()) {
            LOGGER.warn("No rules registered! Nothing to apply");
            return;
        }
        sortRules();
        for (Rule rule : rules) {
            final String name = rule.getName();
            try {
                rule.execute();
            } catch (Exception exception) {
                rule.onFailure(exception);
                LOGGER.error(String.format("Rule '%s' performed with error", name),
                        ToStringBuilder.reflectionToString(rule), exception);
            }
        }
    }

    public void executeRulesAfter() {
        if (rules.isEmpty()) {
            LOGGER.warn("No rules registered! Nothing to apply");
            return;
        }
        sortRules();
        for (Rule rule : rules) {
            final String name = rule.getName();
            try {
                rule.afterExecute();
            } catch (Exception exception) {
                rule.onFailure(exception);
                LOGGER.error(String.format("Rule '%s' performed with error", name), exception);
            }
        }
    }

    private void sortRules() {
        if (CollectionUtils.isNotEmpty(rules)) {
            rules = new TreeSet<>(rules);
        } else {
            rules = new TreeSet<>();
        }
    }

}
