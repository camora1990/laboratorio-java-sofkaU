package org.sofka;

import org.sofka.bootstrap.DataBaseConnection;
import org.sofka.dao.CategoryDao;
import org.sofka.models.Category;

public class Main {
    public static void main(String[] args) {


        System.out.println(  DataBaseConnection.getDatabase().getName());
        CategoryDao categoryDao = new CategoryDao();
        System.out.println(categoryDao.getCategoriesByLevel(1).first().toJson());
    }
}