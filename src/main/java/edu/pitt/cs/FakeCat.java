package edu.pitt.cs;

import edu.pitt.cs.Cat;

// 继承自Cat的真实实现类（如CatImpl），确保符合接口规范（不能修改接口）
public class FakeCat implements Cat {
    // 硬编码预设的ID和名称（文档第35页：Fake类通过成员变量存储模拟状态）
    private int id;
    private String name;
    private boolean rented;


    public FakeCat(int id, String name) {
        this.id=id;
        this.name=name;
        // TODO: Fill in
    }

    public void rentCat() {
        // TODO: Fill in
        this.rented=true;
    }

    public void returnCat() {
        // TODO: Fill in
        this.rented=false;
    }

    public void renameCat(String name) {
        // TODO: Fill in
        this.name=name;
    }

    public String getName() {
        // TODO: Fill in
        return name;
    }

    public int getId() {
        // TODO: Fill in
        return id;
    }

    public boolean getRented() {
        // TODO: Fill in
        return rented;
    }

    public String toString() {
        // TODO: Fill in
        return "ID "+this.id+ ". "+this.name+"\n";
    }
}