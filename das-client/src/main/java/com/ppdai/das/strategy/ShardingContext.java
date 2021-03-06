package com.ppdai.das.strategy;

import java.util.Set;

import com.ppdai.das.client.Hints;
import com.ppdai.das.core.DasConfigure;

//Subclass with DbShardingContext and TableShardingContext?
public class ShardingContext {
    private String appId;
    private String logicDbName;
    private Set<String> allShards;
    private Hints hints;
    private ConditionList conditions;

    public ShardingContext(String appId, String logicDbName, Set<String> allShards, Hints hints, ConditionList conditions) {
        this.appId = appId;
        this.logicDbName = logicDbName;
        this.allShards = allShards;
        this.hints = hints;
        this.conditions = conditions;
    }
    
    public ShardingContext(DasConfigure config, String logicDbName, Hints hints, ConditionList conditions) {
        this(config.getAppId(), logicDbName, config.getDatabaseSet(logicDbName).getAllShards(), hints, conditions);
    }

    public String getAppId() {
        return appId;
    }

    public String getLogicDbName() {
        return logicDbName;
    }

    public Set<String> getAllShards() {
        return allShards;
    }

    public Hints getHints() {
        return hints;
    }

    public boolean hasShardValue() {
        return hints.hasShardValue();
    }

    public Object getShardValue() {
        return hints.getShardValue();
    }

    public ConditionList getConditions() {
        return conditions;
    }

    public ConditionContext create(ColumnCondition condition) {
        return new ConditionContext(this, condition);
    }
}
