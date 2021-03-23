package core;

import java.time.LocalDateTime;
enum CallStatus{
    Complete,
    InProgress
}
public class CallHistoryData {
    private int callID;
    private int agentID;
    private int lengthOfCallInSeconds;
    private LocalDateTime dateOfCall; //received as YYYY-MM-DD hh: mm: ss.nnn
    private String reasonForCall;
    private CallStatus callStatus;

    public CallHistoryData(int callID, int agentID, int length, LocalDateTime date, String reason, CallStatus status){

    }
}
