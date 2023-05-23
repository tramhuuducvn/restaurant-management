package org.sdc.restaurant.repository;

import org.sdc.restaurant.constant.SpecialCharacters;
import org.sdc.restaurant.entity.MenuItem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MenuItemRepository {
    private static final MenuItemRepository instance;
    private static File file;

    static {
        try {
            instance = new MenuItemRepository();
        }
        catch (RuntimeException e){
            throw new RuntimeException("Exception occurred in creating singleton object");
        }
    }

    private MenuItemRepository(){
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

    public static MenuItemRepository getInstance(){
        return instance;
    }

    /**
     * Create menu item from raw data
     * @param id id of menu item
     * @param values raw data
     * @return
     */
    private MenuItem createMenuItem(int id, String[] values){
        //Name, Description, Image, Price, Types, Deleted
        if(values.length != 6) {
            return null;
        }
        return new MenuItem(id, values[0], values[1], values[2], Double.parseDouble(values[3]) , values[4], Boolean.parseBoolean(values[5]));
    }

    /**
     * Import data from file csv and convert it to MenuItem Object.
     * @return list of menu item.
     */
    public List<MenuItem> readMenuItems() {
        try {
            List<MenuItem> menuItems = new ArrayList<>();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            int count = 0;

            while((line = bufferedReader.readLine()) != null){
                //Name, Description, Image, Price, Types
                String[] values = line.split(SpecialCharacters.COMMA_SPACE);
                if(values[0].equals("Name")){
                    continue;
                }
                menuItems.add(this.createMenuItem(count, values));
                count++;
            }
            bufferedReader.close();
            return menuItems;
        }
        catch (IOException exception){
            throw new RuntimeException(exception);
        }
    }

    /**
     * Export all menu item to file csv
     * @param data list of menu item
     */
    public void writeAll(List<MenuItem> data){
        if(data == null) {
            return;
        }
        try {
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.println("Name, Description, Image, Price, Types, Deleted");
            data.stream().map(MenuItem::toCSV).forEach(printWriter::println);
            printWriter.flush();
            printWriter.close();
        }
        catch (IOException exception){
            throw new RuntimeException(exception);
        }
    }
}