package com.qianjun.rules.bean;

import java.io.Serializable;

/**
 * @author
 */
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    // 默认从第一页开始
    private int               page             = 1;
    // 每页10行
    private int               rows             = 20;
    // 排序
    private String            sort;
    // 升序或者降序
    private String            order;

    private String            condition;

    private Long              id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    /**
     * @return the start
     */
    public int getStart() {
        return (page - 1) * rows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        if (page <= 0) {
            page = 1;
        }
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if (rows <= 0) {
            rows = 20;
        }
        this.rows = rows;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

}
