package org.sdc.restaurant.service;

import org.sdc.restaurant.constant.SearchMenuType;
import org.sdc.restaurant.entity.MenuItem;

import java.util.List;

public interface MenuItemService {
    /**
     * Find item index by id
     *
     * @param id id of dish in menu
     * @return index of dish in menu
     */
    int findIndexById(int id);

    /**
     * Find index by name
     *
     * @param name name of dish in menu
     * @return index of dish in menu
     */
    int findIndexByName(String name);

    /**
     * Create new menu item
     *
     * @param menuItem data of new dish
     * @return MenuItem
     */
    MenuItem create(MenuItem menuItem);

    /**
     * Find item by id
     *
     * @param id id of dish in menu
     * @return if item was found return it, else return null
     */
    MenuItem findById(int id);

    /**
     * Find by name
     *
     * @param name name of dish in menu.
     * @return if item was found return it, else return null.
     */
    MenuItem findByName(String name);

    /**
     * Update by id.
     *
     * @param id   id of dish in menu need to update.
     * @param item new data of dish will be updated.
     */
    void updateById(int id, MenuItem item);

    /**
     * Update by name.
     *
     * @param name name of dish in menu need to update.
     * @param item new data of dish will be updated.
     */
    void updateByName(String name, MenuItem item);

    /**
     * Delete by id.
     *
     * @param id index each item in menu, start from 1.
     * @return if delete success return true, else return false.
     */
    boolean deleteById(int id);

    /**
     * Delete by name.
     *
     * @param name name of dish in menu.
     * @return true if delete successful else return false.
     */
    boolean deleteByName(String name);

    /**
     * Get all item
     *
     * @return list of menu item
     */
    List<MenuItem> getAll();

    List<MenuItem> listingMenu();

    /**
     * Search menu item with given keyword by all, by name or by description, by type.
     *
     * @param keywords       the keywords that users want to search
     * @param searchMenuType user can search by Name, by Description,...
     * @return result of all item in menu that matched to the given keyword.
     */
    List<MenuItem> search(String keywords, SearchMenuType searchMenuType);

    /**
     * Export all item in menu to file csv.
     */
    void saveToFile();

    /**
     * Clear all item in menu list.
     */
    void clearAll();
}
