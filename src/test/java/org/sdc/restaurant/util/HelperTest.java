package org.sdc.restaurant.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.SQLException;
import java.util.*;

@RunWith(MockitoJUnitRunner.class)
public class HelperTest {
    @Test
    public void containKeyword_SourceContainKeyWord_True() {
        String src = "Hello World, My name is Pitbull";
        String keywords = "world name";
        boolean result = Helper.containKeyword(src, keywords);

        Assert.assertTrue(result);
    }

    @Test
    public void containKeyword_SourceNotContainKeyWord_False() {
        String src = "Hello World, My name is Pitbull";
        String keywords = "bully";
        boolean result = Helper.containKeyword(src, keywords);

        Assert.assertFalse(result);
    }

    @Test
    public void getInputDouble() {
        double result = Helper.getInputDouble("Enter your price", "Invalid value, please try again");
        Assert.assertEquals(9.9, result);
    }

    @Test
    public void removeSpacesAndCommas(){
        String target = "Hello World!";
        String actual = Helper.reformatText("Hello   ,   World!");
        Assert.assertEquals(target, actual);
    }

    @Mock
    List mockList;

    @Test
    public void testMokito(){
        Mockito.when(mockList.size()).thenReturn(100);
        Mockito.when(mockList.size()).thenThrow(NullPointerException.class);
        Mockito.when(mockList.get(Mockito.any())).thenThrow(NullPointerException.class);

        Assert.assertEquals(mockList.size(), 100);
    }

    @Spy
    List<String> list = new ArrayList<>();

    @Test
    public void spyTest(){
        list.add("one");
        list.add("two");

        System.out.println(list);

        Mockito.verify(list).add("one");
        Mockito.verify(list).add("two");

        Assert.assertEquals(2, list.size());
    }

    @Mock
    List<Object> capList;

    @Captor
    ArgumentCaptor<Object> captor;

    @Test
    public void testCaptor1() {
        capList.add(1);
        // Capture lần gọi add vừa rồi có giá trị là gì
        Mockito.verify(capList).add(captor.capture());
        System.out.println(captor.getAllValues());
        Assert.assertEquals(1, captor.getValue());
    }

    @Mock
    DatabaseDriver driver;

    @InjectMocks
    SuperService superService;

    @Test(expected = SQLException.class)
    public void testInject() throws SQLException{
        Mockito.when(driver.get()).thenReturn(Arrays.asList(1,2,3));
        Assert.assertEquals(driver, superService.getDriver());
        // Test xem superService trả ra ngoài có đúng không?
        Assert.assertEquals(Arrays.asList(1, 2, 3), superService.getObjects());

        // Giả lập cho driver bắn exception
        Mockito.when(driver.get()).thenThrow(SQLException.class);
        superService.getObjects();
    }

}


interface DatabaseDriver {
    List<Object> get() throws SQLException;
}

@Data
@AllArgsConstructor
class SuperService {
    DatabaseDriver driver;

    public List<Object> getObjects() throws SQLException {
        System.out.println("LOG: Getting objects");
        List<Object> list = driver.get();

        System.out.println("LOG: Sorting");
        Collections.sort(list, Comparator.comparingInt(value -> Integer.valueOf(value.toString())));

        System.out.println("LOG: Done");
        return list;
    }
}