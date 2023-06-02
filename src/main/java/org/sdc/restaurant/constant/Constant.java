package org.sdc.restaurant.constant;

public class Constant {
    public static final int NUMBER_OF_MENU_ITEM_FIELDS = 6;
    public static final int NUMBER_OF_BILL_ORDER_FIELDS = 5;

    public static final String COLUMN_VALUE_OF_MENU_ITEM = "Name, Description, Image, Price, Types, Deleted";

    public static final String COLUMN_VALUE_OF_BILL_ORDER = "Bill ID, Menu, Quantity, Ordered Time, Types";
    public static final String IGNORE_MENU_ITEM_VALUE = "Name";
    public static final String IGNORE_BILL_ORDER_VALUE = "Menu ID";
    public static final String BILL_ORDER_FILE = "bill_order.csv";
    public static final String MENU_FILE = "menu.csv";
    public static final int MENU_NAME_INDEX = 0;
    public static final int MENU_DESCRIPTION_INDEX = 1;
    public static final int MENU_IMAGE_INDEX = 2;
    public static final int MENU_PRICE_INDEX = 3;
    public static final int MENU_TYPES_INDEX = 4;
    public static final int MENU_DELETED_INDEX = 5;

    public static final int BILL_ID_INDEX = 0;
    public static final int BILL_MENU_NAME_INDEX = 1;
    public static final int BILL_QUANTITY_INDEX = 2;
    public static final int BILL_ORDERED_TIME_INDEX = 3;
    public static final int BILL_TYPES_INDEX = 4;

    public static final String FORMAT_DATE_TIME = "EEE MMM dd HH:mm:ss zzz yyyy";
}
