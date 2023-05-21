package org.sdc.restaurant;

import org.sdc.restaurant.constant.SearchMenuType;
import org.sdc.restaurant.controller.MenuItemController;
import org.sdc.restaurant.entity.MenuItem;

import java.util.List;

public class MenuManagement {
//    private static final MenuItemController controller = new MenuItemController();
//
//    private static void addItemsToMenu(){
//        controller.create(new MenuItem("Hawaiian Pizza", "All-time favourite toppings, Hawaiian pizza in Tropical Hawaii style", "https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu1.jpg", 300, "Italian Ham Pineapple"));
//        controller.create(new MenuItem("Chicken Tom Yum Pizza", "Best marinated chicken with pineapple and mushroom on Spicy Lemon sauce. Enjoy our tasty Thai style pizza.", "https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu2.jpg", 350, "Italian Thai Chicken Mushroom Hot"));
//        controller.create(new MenuItem("Xiaolongbao", "Chinese steamed bun", "https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu3.jpg", 200, "Chinese Pork Recommended"));
//        controller.create(new MenuItem("Kimchi", "Traditional side dish made from salted and fermented vegetables", "https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu4.jpg", 50, "Korean Radish Cabbage"));
//        controller.create(new MenuItem("Oolong tea", "Partially fermented tea grown in the Alishan area", "https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu5.jpg", 30, "Hot Non-alcohol"));
//        controller.create(new MenuItem("Beer", "Fantastic flavors and authentic regional appeal beer", "https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu6.jpg", 60, "Alcohol"));
//    }
//
//    private static void retrieveMenuItems() {
//        controller.getMenu().forEach(System.out::println);
//    }
//
//    private static void updateMenuItems() {
//        controller.updateMenuItemByName("Hawaiian Pizza", new MenuItem("Chinese Pizza", "All-time favourite toppings, Chinese pizza in Tropical China style", "https://s3-ap-southeast-1.amazonaws.com/interview.ampostech.com/backend/restaurant/menu1.jpg", 200, "Chinese Ham Pineapple"));
//    }
//
//    private static void deleteItemById() {
//        controller.deleteMenuItemById(1);
//    }
//
//    private static void searchKeyWord() {
//        System.out.println("\n Result of 'Thai' in menu list: ");
//        List<MenuItem> result = controller.search("China", SearchMenuType.ALL);
//        if(result == null) {
//            System.out.println("Can't find any item!");
//            return;
//        }
//        result.forEach(System.out::println);
//    }
//
//    public static void runDemo(){
//        System.out.println("\nAdd 6 item to menu");
//        addItemsToMenu();
//        retrieveMenuItems();
//        System.out.println("\nUpdate Chinese Pizza to Hawaiian Pizza");
//        updateMenuItems();
//        System.out.println("\nDelete item has index 1");
//        deleteItemById();
//        retrieveMenuItems();
//        searchKeyWord();
//    }
}