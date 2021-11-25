import java.awt.*;
import java.sql.*;
import java.util.Scanner;

import static java.lang.Class.forName;

/**
 * Project name(项目名称)：sql的使用
 * Package(包名): PACKAGE_NAME
 * Class(类名): sql
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2021/11/24
 * Time(创建时间)： 21:42
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class sql
{
    public void getSelect()
    {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try
        {
            // 加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //com.mysql.jdbc.Driver
            // 建立数据库连接getconnection(jdbc:mysql://地址:端口号/数据库名,数据库用户名，密码)
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "20010713");
            // 能过Statement执行SQL语句
            String sql = "select * from information";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            // 遍历读取Sql的数据
            while (rs.next())
            {
                String no = rs.getString("no"); //学号字段名
                String name = rs.getString("name"); //名字字段名
                String sex = rs.getString("sex"); //性别
                String age = rs.getString("age"); //年龄
                System.out.println("学号：" + no + "   姓名:" + name + "    性别：" + sex + "    年龄：" + age);
            }
        }
        catch (ClassNotFoundException e)
        {
            Toolkit.getDefaultToolkit().beep();
            System.out.println("找不到类异常：");
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            Toolkit.getDefaultToolkit().beep();
            System.out.println("数据库异常：");
            e.printStackTrace();
        }
        catch (Exception e)
        {
            Toolkit.getDefaultToolkit().beep();
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (st != null)
                {
                    st.close();
                }
                if (con != null)
                {
                    con.close();
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void insert_into()
    {
        Scanner input = new Scanner(System.in);
        int no;
        String name;
        String sex;
        int age;
        //控制台输入变量:no
        int errCount = 0;
        while (true)
        {
            try
            {
                //min:0
                //max:10000000
                System.out.print("请输入学号：");
                no = input.nextInt();
                if (no >= 0 && no <= 10000000)
                {
                    break;
                }
                else
                {
                    errCount++;
                    Toolkit.getDefaultToolkit().beep();
                    if (errCount > 10)
                    {
                        System.err.println("错误次数过多！！！退出");
                        System.exit(1);
                    }
                    System.out.println("输入的数据不在范围内! 范围：[0,10000000]");
                }
            }
            catch (Exception e)
            {
                errCount++;
                if (errCount > 5)
                {
                    Toolkit.getDefaultToolkit().beep();
                    System.err.println("错误次数过多！！！退出");
                    System.exit(1);
                }
                else
                {
                    Toolkit.getDefaultToolkit().beep();
                    System.out.println("输入错误！！！请重新输入！");
                    input.nextLine();
                }
            }
        }
        while (true)
        {
            System.out.print("请输入姓名：");
            name = input.next();
            if (name.length() <= 20)
            {
                break;
            }
            else
            {
                Toolkit.getDefaultToolkit().beep();
                System.out.println("输入的字符串太长，请重新输入！！！");
            }
        }
        while (true)
        {
            System.out.print("请输入性别：");
            sex = input.next();
            if (sex.length() <= 4)
            {
                break;
            }
            else
            {
                Toolkit.getDefaultToolkit().beep();
                System.out.println("输入的字符串太长，请重新输入！！！");
            }
        }
        //控制台输入变量:age
        errCount = 0;
        while (true)
        {
            try
            {
                //min:0
                //max:100
                System.out.print("请输入年龄：");
                age = input.nextInt();
                if (age >= 0 && age <= 100)
                {
                    break;
                }
                else
                {
                    errCount++;
                    Toolkit.getDefaultToolkit().beep();
                    if (errCount > 10)
                    {
                        System.err.println("错误次数过多！！！退出");
                        System.exit(1);
                    }
                    System.out.println("输入的数据不在范围内! 范围：[0,100]");
                }
            }
            catch (Exception e)
            {
                errCount++;
                if (errCount > 5)
                {
                    Toolkit.getDefaultToolkit().beep();
                    System.err.println("错误次数过多！！！退出");
                    System.exit(1);
                }
                else
                {
                    Toolkit.getDefaultToolkit().beep();
                    System.out.println("输入错误！！！请重新输入！");
                    input.nextLine();
                }
            }
        }
        //输入完成，写入数据库
        String sql = "INSERT INTO information VALUES (" + no + ",'" + name + "'"+","+"'" + sex + "'"+"," + age + ");";
        System.out.println(sql);
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try
        {
            // 加载数据库驱动
            //Class.forName("com.mysql.cj.jdbc.Driver");
            //com.mysql.jdbc.Driver
            // 建立数据库连接getconnection(jdbc:mysql://地址:端口号/数据库名,数据库用户名，密码)
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "20010713");
            // 能过Statement执行SQL语句
            st = con.createStatement();
            st.executeUpdate(sql);
        }
        /*
        catch (ClassNotFoundException e)
        {
            Toolkit.getDefaultToolkit().beep();
            System.out.println("找不到类异常：");
            e.printStackTrace();
        }

         */
        catch (SQLException e)
        {
            Toolkit.getDefaultToolkit().beep();
            System.out.println("数据库异常：");
            e.printStackTrace();
        }
        catch (Exception e)
        {
            Toolkit.getDefaultToolkit().beep();
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (st != null)
                {
                    st.close();
                }
                if (con != null)
                {
                    con.close();
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args)
    {
        //------------------------------------------------------
        long startTime = System.nanoTime();   //获取开始时间
        //------------------------------------------------------
        sql sql_information = new sql(); // 声明当前类
        System.out.println("查询：");
        sql_information.getSelect(); // 执行方法
        System.out.println("增加：");
        sql_information.insert_into();
        System.out.println("查询：");
        sql_information.getSelect(); // 执行方法
        System.out.println();
        //------------------------------------------------------
        long endTime = System.nanoTime(); //获取结束时间
        if ((endTime - startTime) < 1000000)
        {
            double final_runtime;
            final_runtime = (endTime - startTime);
            final_runtime = final_runtime / 1000;
            System.out.println("算法运行时间： " + final_runtime + "微秒");
        }
        else if ((endTime - startTime) >= 1000000 && (endTime - startTime) < 10000000000L)
        {
            double final_runtime;
            final_runtime = (endTime - startTime) / 1000;
            final_runtime = final_runtime / 1000;
            System.out.println("算法运行时间： " + final_runtime + "毫秒");
        }
        else
        {
            double final_runtime;
            final_runtime = (endTime - startTime) / 10000;
            final_runtime = final_runtime / 100000;
            System.out.println("算法运行时间： " + final_runtime + "秒");
        }
        Runtime r = Runtime.getRuntime();
        float memory;
        memory = r.totalMemory();
        memory = memory / 1024 / 1024;
        System.out.printf("JVM总内存：%.3fMB\n", memory);
        memory = r.freeMemory();
        memory = memory / 1024 / 1024;
        System.out.printf(" 空闲内存：%.3fMB\n", memory);
        memory = r.totalMemory() - r.freeMemory();
        memory = memory / 1024 / 1024;
        System.out.printf("已使用的内存：%.4fMB\n", memory);
        //------------------------------------------------------
    }
}
