package com.qianfeng.lucene;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements  BookDao {
    @Override
    public List<Book> selectBookList() {
        List<Book> list=new ArrayList<>();
        String driver="jdbc:mysql:///lucene";
        String username="root";
        String password="123";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection= DriverManager.getConnection(driver,username,password);
            PreparedStatement statement=connection.prepareStatement("select * from book");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Book book=new Book();
                book.setId(resultSet.getInt("id"));
                book.setDescription(resultSet.getString("description"));
                book.setName(resultSet.getString("name"));
                book.setPic(resultSet.getString("pic"));
                book.setPrice(resultSet.getFloat("price"));
                list.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  list;
    }
}
