/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class quizServ {
    private static Connection conn;
    private static PreparedStatement ps;
    static{
        try{
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver Loaded Successfully");
            conn=DriverManager.getConnection("jdbc:oracle:thin:@//LAPTOP-8K341KCI","advancejavabatch","mystudents");
            ps=conn.prepareStatement("Select bookname,bookprice from allbooks where subject=?");
        }
        catch(Exception ex)
        {
            System.out.println("Exception is:"+ex);
        }
    }     
        public static ArrayList<Book> getBooksBySubject(String subject)throws SQLException
        {
            ArrayList bookList=new ArrayList();
                ps.setString(1,subject);
                ResultSet rs=ps.executeQuery();
                while(rs.next())
                {
                  String bookname=rs.getString(1);
                  double bookprice=rs.getDouble(2);
                  Book b=new Book();
                  b.setBookname(bookname);
                  b.setBookprice(bookprice);
                  bookList.add(b);
                }
            return bookList;
}
public static void closeConnection()
{
    try
    {
        if(conn!=null)
        conn.close();
    }
     catch(SQLException ex)
        {
            System.out.println("DB Error in closing the Connection:"+ex);
        }
}
}