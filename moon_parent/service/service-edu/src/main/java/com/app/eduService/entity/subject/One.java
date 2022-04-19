package com.app.eduService.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class One {

    private String id;

    private String title;

    private List<Two> children = new ArrayList<Two>();

}
