package org.example.repository;

import org.example.entity.MenuItem;
import org.example.service.MenuItemService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MenuItemRepo {
    private static final MenuItemRepo instance;
    private static File file;

    static {
        try {
            instance = new MenuItemRepo();
        }
        catch (RuntimeException e){
            throw new RuntimeException("@DUKE: Exception occurred in creating singleton object");
        }
    }

    private MenuItemRepo(){
        try {
            file = new File("menu.csv");
            if(!file.exists()) {
                file.createNewFile();
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static MenuItemRepo getInstance(){
        return instance;
    }

    private MenuItem createMenuItem(int id, String[] values){
        //Name, Description, Image, Price, Types
        if(values.length != 5) {
            return null;
        }
        return new MenuItem(id, values[0], values[1], values[2], Double.valueOf(values[3]) , values[4]);
    }

    public List<MenuItem> readMenuItems(){
        try {
            List<MenuItem> menuItems = new ArrayList<>();

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = "";
            int count = 0;

            while((line = bufferedReader.readLine()) != null){
                //Name, Description, Image, Price, Types
                String[] values = line.split(",");
                if(values[0].equals("Name")){
                    continue;
                }
                menuItems.add(this.createMenuItem(count, values));
                System.out.println(values);
                count++;
            }
            return menuItems;
        }
        catch (IOException exception){
            throw new RuntimeException(exception);
        }
    };

    public void writeAll(List<MenuItem> data){
        if(data == null) {
            return;
        }
        try {
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.println("Name, Description, Image, Price, Types");
            data.stream().map(MenuItem::toCSV).forEach(printWriter::println);
            printWriter.flush();
            printWriter.close();
        }
        catch (IOException exception){
            throw new RuntimeException(exception);
        }
    };
}