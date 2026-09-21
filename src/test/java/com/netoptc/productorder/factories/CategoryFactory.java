package com.netoptc.productorder.factories;

import com.netoptc.productorder.entities.Category;

public class CategoryFactory {

    public static Category createCategory() {
        return new Category("New category");
    }
}
