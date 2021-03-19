package com.example.projectdemo.version;


/**
 * 软件版本信息对象
 * 
 * @author Royal
 * 
 */
public class VersionInfo {
	private int id;
	private String appname;
	private String appfileurl;
	private String appupdatedesc;
	private String appupdatetime;
	private String type;
	private int forceupdatecnt;
	
	public VersionInfo()
	{
	}

	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id=id;
	}
	public String getAppname()
	{
		return appname;
	}
	public void setAppname(String appname)
	{
		this.appname=appname;
	}
	public String getAppfileurl()
	{
		return appfileurl;
	}
	public void setAppfileurl(String appfileurl)
	{
		this.appfileurl=appfileurl;
	}
	public String getAppupdatedesc()
	{
		return appupdatedesc;
	}
	public void setAppupdatedesc(String appupdatedesc)
	{
		this.appupdatedesc=appupdatedesc;
	}
	public String getAppupdatetime()
	{
		return appupdatetime;
	}
	public void setAppupdatetime(String appupdatetime)
	{
		this.appupdatetime=appupdatetime;
	}

	public int getForceupdatecnt() {
		return forceupdatecnt;
	}

	public void setForceupdatecnt(int forceupdatecnt) {
		this.forceupdatecnt = forceupdatecnt;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
