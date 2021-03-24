package core;

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
}
