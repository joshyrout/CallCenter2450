package core;

import java.time.LocalDateTime;
enum CallStatus
{
    Complete,
    InProgress
}
public class CallHistoryData
{
    private int callID;
    private int agentID;
    private int lengthOfCallInSeconds;
    private LocalDateTime dateOfCall; //received as YYYY-MM-DD hh: mm: ss.nnn
    private String reasonForCall;
    private CallStatus callStatus;

    public CallHistoryData(int callID, int agentID, int length, LocalDateTime date, String reason, CallStatus status)
    {
        this.callID = callID;
        this.agentID = agentID;
        this.lengthOfCallInSeconds = length;
        this.reasonForCall = reason;
        this.dateOfCall = date;
        setCallStatus(status);
    }

    public int getCallID()
    {
        return callID;
    }

    public int getAgentID()
    {
        return agentID;
    }

    public int getLengthOfCall()
    {
        return lengthOfCallInSeconds;
    }

    public LocalDateTime getDateOfCall()
    {
        return dateOfCall;
    }

    public String getReasonForCall()
    {
        return reasonForCall;
    }

    public CallStatus getCallStatus()
    {
        return callStatus;
    }

    public void setCallStatus(CallStatus callStatus)
    {
        this.callStatus = callStatus;
    }

    @Override
    public String toString() {
        /*
        System.out.println("Object: " + i   + " CallID: " + obj.getCallID()
                                                + " AgentID: " + obj.getAgentID()
                                                + " Length: " + obj.getLengthOfCall()
                                                + " Date: " + obj.getDateOfCall()
                                                + " Reason: " + obj.getReasonForCall()
                                                + " Status: " + obj.getCallStatus());
         */
        StringBuilder sb = new StringBuilder();
        String.format("");

        return "CallHistoryData{}";
    }
}
