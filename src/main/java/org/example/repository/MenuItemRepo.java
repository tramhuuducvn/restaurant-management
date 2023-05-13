package org.example.repository;

import org.example.service.MenuItemService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MenuItemRepo {
    private static final MenuItemRepo instance;
    private static final File file;

    static {
        try {
            instance = new MenuItemRepo();
            file = new File("menu.csv");
            if(!file.exists()){
                file.createNewFile();

            }
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            System.out.println(bufferedReader.readLine());
        }
        catch (RuntimeException e){
            throw new RuntimeException("@DUKE: Exception occurred in creating singleton object");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private MenuItemRepo(){

    }

    public static MenuItemRepo getInstance(){
        return instance;
    }
}