package com.lifeng.layoutmanager.been;

/**
 * 项目名称：LayoutManager
 * 类描述：
 * 作者：峰哥
 * 创建时间：2016/12/20 17:13
 * 邮箱：470794349@qq.com
 * 修改简介：
 */
public class Been {

    private int src;
    private String name;

    public Been(String name, int src) {
        this.name = name;
        this.src = src;
    }

    public int getSrc() {
        return src;
    }

    public void setSrc(int src) {
        this.src = src;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
