package app.model;

/**
 * Created by cjl20 on 2016/7/4.
 */
public class ErrorMsg {
    private int Code;//错误代码
    private String Message;//错误信息

    public ErrorMsg() {
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    @Override
    public String toString() {
        return "ErrorMsg{" +
                "Code=" + Code +
                ", Message='" + Message + '\'' +
                '}';
    }
}
