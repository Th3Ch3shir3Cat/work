package com.task.work.core.json;

public class Views {
    public interface OnlyId {}
    public interface ForTable extends OnlyId {}
    public interface SimpleObject extends ForTable {}
    public interface AllStudents extends SimpleObject {}
}
