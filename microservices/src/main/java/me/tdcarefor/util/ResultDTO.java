package me.tdcarefor.util;


import io.swagger.annotations.ApiModelProperty;

/**
 * 返回结果DTO
 * 用于返回统一的结果
 *
 * @author tzw
 * @createDate 2015年3月26日 下午1:44:51
 */
public class ResultDTO<T> {

	@ApiModelProperty(value="状态 (0:失败 1:成功 -1:异常",required=true)
	private int status;
	
	@ApiModelProperty(value="消息",required=true)
	private String message;
	
	@ApiModelProperty(value="返回数据",required=true)
	private T data;

	public ResultDTO(){

	}

	public ResultDTO(int status,String message){
		this.status=status;
		this.message=message;
	}

	public ResultDTO(int status,String message,T data){
		this.status=status;
		this.message=message;
		this.data=data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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


}
