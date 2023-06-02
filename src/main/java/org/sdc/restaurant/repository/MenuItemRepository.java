package org.sdc.restaurant.repository;

import org.sdc.restaurant.constant.Constant;
import org.sdc.restaurant.constant.SpecialCharacters;
import org.sdc.restaurant.entity.MenuItem;

import java.io.File;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.FileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * Class MenuItemRepository is designed follow singleton design pattern
 * This class includes the necessary method for read/write data if menu item .
 */
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
            file = new File(Constant.MENU_FILE);
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
     *
     * @param id     id of menu item
     * @param values raw data
     * @return Menu Item created from raw data
     */
    public MenuItem createMenuItem(int id, String[] values) {
        //Name, Description, Image, Price, Types, Deleted
        if (values.length != Constant.NUMBER_OF_MENU_ITEM_FIELDS) {
            return null;
        }
        return new MenuItem(id, values[Constant.MENU_NAME_INDEX], values[Constant.MENU_DESCRIPTION_INDEX], values[Constant.MENU_IMAGE_INDEX], Double.parseDouble(values[Constant.MENU_PRICE_INDEX]), values[Constant.MENU_TYPES_INDEX], Boolean.parseBoolean(values[Constant.MENU_DELETED_INDEX]));
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
                if(values[Constant.MENU_NAME_INDEX].equals(Constant.IGNORE_MENU_ITEM_VALUE)){
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
            printWriter.println(Constant.COLUMN_VALUE_OF_MENU_ITEM);
            data.stream().map(MenuItem::toCSV).forEach(printWriter::println);
            printWriter.flush();
            printWriter.close();
        }
        catch (IOException exception){
            throw new RuntimeException(exception);
        }
    }
}