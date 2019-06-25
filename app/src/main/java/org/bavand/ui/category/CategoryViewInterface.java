package org.bavand.ui.category;

import org.bavand.models.CategoryModel;

public interface CategoryViewInterface {
    void showToast(String s);

    void displayCategory(CategoryModel category);

    void displayError(String s);
}
