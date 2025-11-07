package com.example.microlocal;

import java.util.List;

public interface IServiceLocal {
    List<Local> getAll();
    Local getById(String id);
    Local create(Local local);
    Local update(String id, Local local);
    void delete(String id);
}
