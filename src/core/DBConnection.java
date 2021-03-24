package core;

import java.awt.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *  This creates a single instance of DBConnection which is used to interface with the database for the
 *  call center application. This class uses the customer's CustomerID to retrieve all relevant information
 *  which is then cached within the instance to be used by the other classes.
 *
 *  @author Joshua Routledge
 *  @version 1.0
 */
public class DBConnection 
{
    private static DBConnection instance = null;
    private Customer customerInfo;
    private CallHistoryData[] callHistoryArray;
    private PaymentHistoryData[] paymentHistoryArray;

    private DBConnection() {}


    /**
     * Returns the current DBConnection instance. If there isn't a current instance, it creates one.
     * @return DBConnection
     */
    public static DBConnection getInstance()
    {
        if (instance == null) instance = new DBConnection();
        return instance;
    }

    public void retrieveCustomerData(int customerID){

        List<CallHistoryData> callHistory = new ArrayList<CallHistoryData>();
        List<PaymentHistoryData> paymentHistory = new ArrayList<PaymentHistoryData>();

        String connectionString = "jdbc:mysql://database-1.crldyhae3ofh.us-east-2.rds.amazonaws.com:3306/testdb";
        String dbLogin = "javauser";
        String dbPassword = "TotallySecure";
        Connection conn = null;
        String sql = "SELECT * FROM testdb.callHistory WHERE customerID = " + customerID;

        try
        {
            conn = DriverManager.getConnection(connectionString, dbLogin, dbPassword);
            if (conn != null)
            {
                try (Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery(sql))
                {
                    while (rs.next())
                    {
                        try
                        {
                            CallStatus callStatus = CallStatus.valueOf(rs.getString("status"));
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                            LocalDateTime dateTime = LocalDateTime.parse(rs.getString("date"), formatter);
                            CallHistoryData call = new CallHistoryData(rs.getInt("callID"),
                                    rs.getInt("agentID"), rs.getInt("length"),
                                    dateTime, rs.getString("reason"), callStatus);
                            callHistory.add(call);
                        } catch (Exception e)
                        {
                            System.err.println(e);
                            continue;
                        }
                    }
                    callHistoryArray = new CallHistoryData[callHistory.size()];
                    for(int i = 0; i < callHistory.size(); i++)
                    {
                        callHistoryArray[i] = callHistory.get(i);

                    }
                }
                catch (SQLException ex)
                {
                    System.out.println(ex.getMessage());
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Database connection failed.");
            e.printStackTrace();
        }


    }

    public Customer getCustomerInfo()
    {
        return customerInfo;
    }

    public CallHistoryData[] getCallHistoryArray()
    {
        return callHistoryArray;
    }

    public PaymentHistoryData[] getPaymentHistoryArray()
    {
        return paymentHistoryArray;
    }

    private void setCustomerInfo(Customer customerInfo)
    {
        this.customerInfo = customerInfo;
    }

    private void setCallHistoryArray(CallHistoryData[] callHistoryArray)
    {
        this.callHistoryArray = callHistoryArray;
    }

    private void setPaymentHistoryArray(PaymentHistoryData[] paymentHistoryArray)
    {
        this.paymentHistoryArray = paymentHistoryArray;
    }

    public static void main(String[] args)
    {
        DBConnection instance = DBConnection.getInstance();
        instance.retrieveCustomerData(15);
        CallHistoryData[] testArray = instance.getCallHistoryArray();

        for(int i = 0; i < testArray.length; i++)
        {
            CallHistoryData obj = testArray[i];
            System.out.println(obj.toString(40));
        }
    }
}
