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

    //TODO: This is really ugly, if i have time ill come back and update it.
    public String toString(int maxLength) {
        StringBuilder sb = new StringBuilder();
        String format = "%-5s %-7s %-4s %-20s %-" + maxLength + "s %-10s";
        int position = 0;
        if((reasonForCall.length() - position) > maxLength)
        {
            String reasonConcat = reasonForCall.substring(position, (position + maxLength - 1));
            sb.append(String.format(format,callID, agentID, lengthOfCallInSeconds, dateOfCall, reasonConcat, callStatus));
            position = (position + maxLength - 1);

            while((reasonForCall.length() - position) > 0)
            {
                if ((reasonForCall.length() - position) > maxLength)
                {
                    reasonConcat = reasonForCall.substring(position,(position + maxLength - 1));
                } else
                {
                    reasonConcat = reasonForCall.substring(position);
                }
                sb.append(String.format("\n" + format, "", "", "", "", reasonConcat, ""));
                position = (position + maxLength - 1);
            }
        } else sb.append(String.format(format,callID, agentID, lengthOfCallInSeconds, dateOfCall, reasonForCall, callStatus));

    return sb.toString();
    }
}
