package com.wsafe.arica.entity;

/**
 * @Author:Zhangchi
 * @date:2021-02-24 下午3:32
 **/
public class ItemHtml extends Item {

    /**
     * 生成文件状态
     */
    private String htmlStatus;

    /**
     * 文件路径
     */
    private String location;

    public String getHtmlStatus() {
        return htmlStatus;
    }

    public void setHtmlStatus(String htmlStatus) {
        this.htmlStatus = htmlStatus;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
