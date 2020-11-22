package com.stillcrowdfunding.until;

/**
 * 统一整个项目中Ajax请求返回的结果（未来也可以用于分布式架构各个模块间调用时返回统一类型）
 * @author Administrator
 * @date 2020/6/15 18:35
 * @description
 **/
//声明一个泛型类ResultEntity<T>
public class ResultEntity<T> {

    public static final String SUCCESS = "SUCCESS";

    public static final String FAILED = "FAILED";

    //用来封装当前处理的结果是成功还是失败
    private String result;

    //请求失败时返回的错误信息
    private String message;

    //要返回的数据
    private T data;



    /**
     * 请求处理成功时且不需要返回数据的工具方法
     * @param <Type>
     * @return
     */
    //<E>表示声明一个静态的泛型；ResultEntity<E>表示使用泛型<E>
    public static <Type> ResultEntity<Type> successWithoutData(){
        return new ResultEntity<Type>(SUCCESS,null,null);
    }

    /**
     * 请求处理成功时需要返回数据的工具方法
     * @param data
     * @param <Type>
     * @return
     */
    public static <Type> ResultEntity<Type> successWithoutData(Type data){
        return new ResultEntity<Type>(SUCCESS,null,data);
    }

    /**
     *
     * @param message
     * @param <Type>
     * @return
     */
    public static <Type> ResultEntity<Type> failed(String message){
        return new ResultEntity<Type>(FAILED,message,null);
    }

    public ResultEntity() {
    }

    public ResultEntity(String result, String message, T data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultEntity{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
