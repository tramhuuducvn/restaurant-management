# Restaurant Management

![](https://financesonline.com/uploads/2020/01/resto-mgt-soft-1-1024x536.jpg)

***Target:*** *Restaurant Management is a program useful for managing dishes 
in the restaurant's menu and bill order list.
Build a Restaurant Management System using Spring Boot Framework.*


## Setup & Run project

### Setup

Java 17\
Gradle\
Spring Boot version 2.7.12\
IntelliJ Idea

### Run project

Open project by IntelliJ Idea.\
For run demo: Run main method in RestaurantManagementApplication class.\
For test run: Look up to test folder.

## Functional Requirement
### A. Menu Management
Each ***Menu Item*** includes name, description, image, price, additional detail.

1. **Create menu item:**\
    If found elements with the same name as the element you want to create then throw an exception, otherwise create a new one.
2. **Retrieve menu items:**\
    Return all menu items.
3. **Retrieve menu item by id:**\
    Return menu item matched with the given id.
4. **Search menu item:**\
    Return list menu items have content that contains at least a word matched with the given keywords.
5. **Update menu item by id:**\
    Update menu item has given id if the given id exist.
    In the case of deleted item, updating is not allow.
6. **Delete menu item:**\
    Soft delete menu item has the given id.

### B. Bill Order Management

1. **Create bill order:**\
    Create new bill order with given menu items.
2. **Retrieve bill order:**\
    Return all bill order.
3. **Retrieve bill order by id:**\
    Return list of item include name, quantities, ordered time, additional, total price detail by the given bill id.
4. **Update bill order:**
    + Add new menu item to bill (Check if menu items exists or deleted).
    + Remove menu item from bill.
    + Update quantity of menu item (Check if menu items exists or deleted).

