package app.model;

import java.util.Arrays;
import java.util.List;

/**
 * Created by cjl20 on 2016/7/4.
 */
public class QueryResponse {

    private ErrorMsg ErrorMsg;
    private List<ProductItem> ResponseResult;
    private String ResponseStatus;
    private int TotalResults;

    public QueryResponse() {
    }

    public app.model.ErrorMsg getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(app.model.ErrorMsg errorMsg) {
        ErrorMsg = errorMsg;
    }

    public List<ProductItem> getResponseResult() {
        return ResponseResult;
    }

    public void setResponseResult(List<ProductItem> responseResult) {
        ResponseResult = responseResult;
    }

    public String getResponseStatus() {
        return ResponseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        ResponseStatus = responseStatus;
    }

    public int getTotalResults() {
        return TotalResults;
    }

    public void setTotalResults(int totalResults) {
        TotalResults = totalResults;
    }

    @Override
    public String toString() {
        return "QueryResponse{" +
                "ErrorMsg=" + ErrorMsg +
//                ", ResponseResult=" + ResponseResult.toString() +
                ", ResponseStatus='" + ResponseStatus + '\'' +
                ", TotalResults=" + TotalResults +
                '}';
    }
}
