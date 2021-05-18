/******************************************************************* 
 * Copyright (c) 2015 by USTC SINOVATE SOFTWARE ,Inc. 
 * All rights reserved. 
 * 
 * This file is proprietary and confidential to USTC SINOVATE SOFTWARE. 
 * No part of this file may be reproduced, stored, transmitted, 
 * disclosed or used in any form or by any means other than as 
 * expressly provided by the written permission from the project
 * team of mobile application platform
 * 
 * 
 * Create by SunChao on 2015/04/29
 * Ver1.0
 * 
 * ****************************************************************/
package com.example.projectdemo.http.async;

public class HttpInterfacesUrl {
	// 陕西
	public static final String HTTP_URL_BASE = new StringBuilder().append(Config.SERVER_IP_ADDRESS).append(":")
			.append(Config.SERVER_IP_PORT).append("/sop").toString();

	// 易支撑接口URL
	public static final String ACCOUNT_LOGIN = new StringBuilder().append(HTTP_URL_BASE).append("/sys/login").toString();
	public static final String CHECK_MAN = new StringBuilder().append(HTTP_URL_BASE).append("/api/getChildOrgTeamStaff").toString();
	public static final String ASK_QUESTION = new StringBuilder().append(HTTP_URL_BASE).append("/api/dispatch").toString();
	public static final String USER_NAME = new StringBuilder().append(HTTP_URL_BASE).append("/sys/user/current").toString();
	public static final String ASK_WHY = new StringBuilder().append(HTTP_URL_BASE).append("/sys/category").toString();
	public static final String EVALUATE_LIST = new StringBuilder().append(HTTP_URL_BASE).append("/api/getQuestionsByPage").toString();
	public static final String LIST_NUM = new StringBuilder().append(HTTP_URL_BASE).append("/api/getEvalCur").toString();
	public static final String EVALUATE_COMMIT = new StringBuilder().append(HTTP_URL_BASE).append("/api/saveEvaluationRecord").toString();
	public static final String ASK_LIST = new StringBuilder().append(HTTP_URL_BASE).append("/api/getQuestionsByPage").toString();
	public static final String SET_REJECT = new StringBuilder().append(HTTP_URL_BASE).append("/api/reTreat").toString();
	public static final String USER_INFO = new StringBuilder().append(HTTP_URL_BASE).append("/api/getCurrentStaffInfo").toString();
	public static final String que_class_app = new StringBuilder().append(HTTP_URL_BASE).append("/sys/category/children/tree/que_class_app").toString();
	public static final String que_shortcuts_calss = new StringBuilder().append(HTTP_URL_BASE).append("/sys/category/children/tree/que_shortcuts_calss").toString();
	public static final String queryMenuCount = new StringBuilder().append(HTTP_URL_BASE).append("/api/queryMenuCount").toString();
	public static final String FILE_UPLOAD = new StringBuilder().append(HTTP_URL_BASE).append("/api/fileUpload").toString();
	public static final String LOGOUT = new StringBuilder().append(HTTP_URL_BASE).append("/sys/logout?clientType=1").toString();


	// 账号单点登录
	public static final String IM_SINGLE_SIGN_ON = new StringBuilder().append(HTTP_URL_BASE).append("imSingleSignOn")
			.toString();

	// 仅仅用来获取党员身份的字段
	public static final String PERSONAL_INFOR_DANG = new StringBuilder().append(HTTP_URL_BASE).append("getPersonalInfo")
			.toString();
	public static final String PERSONAL_INFOR = new StringBuilder().append(HTTP_URL_BASE).append("getPersonalInfoCache")
			.toString();

	// 发送认证消息
	public static final String AUTHENTICATIONMSG = new StringBuilder().append(HTTP_URL_BASE).append("authenticationMsg")
			.toString();

	// 账号登出
	public static final String ACCOUNT_LOGOUT = new StringBuilder().append(HTTP_URL_BASE).append("accountLogout")
			.toString();

	// 保存用户参数信息
	public static final String SAVE_PUSH_INFO = new StringBuilder().append(HTTP_URL_BASE).append("savePushInfo")
			.toString();

	// 个人服务台配置查询
	public static final String PERSONAL_DESK_QUERY = new StringBuilder().append(HTTP_URL_BASE)
			.append("personalDeskQuery").toString();
	// 提问
//	public static final String ASK_QUESTION = new StringBuilder().append(HTTP_URL_BASE).append("askQuestion")
//			.toString();

	//提问
//	public static final String ASK_QUESTION = new StringBuilder().append(HTTP_URL_BASE).append("askQuestionNew")
//			.toString();
	
	public static final String ASKERORGSQUERY = new StringBuilder().append(HTTP_URL_BASE).append("askerOrgsQuery")
			.toString();

	// 问题列表-待评价 ，提问中
	public static final String GET_QUESTIONS_BY_PAGE = new StringBuilder().append(HTTP_URL_BASE)
			.append("getQuestionsByPage").toString();

	// 问题列表-历史
	public static final String GET_HISTORY_BY_PAGE = new StringBuilder().append(HTTP_URL_BASE)
			.append("getHistoryByPage").toString();

	// -问题列表-处理中(专家)
	public static final String GET_HANDLING_BY_PAGE = new StringBuilder().append(HTTP_URL_BASE)
			.append("getHandlingByPage").toString();

	// -问题列表-待接单
	public static final String ORDER_ACCEPT_WAIT = new StringBuilder().append(HTTP_URL_BASE).append("orderAcceptWait")
			.toString();

	// -问题列表-转派中
	public static final String QUESTIONS_SENDING = new StringBuilder().append(HTTP_URL_BASE).append("qusetionsSending")
			.toString();

	// -问题列表-协办中
	public static final String GET_ASSIST_HANDLING_BY_PAGE = new StringBuilder().append(HTTP_URL_BASE)
			.append("getAssistHandlingByPage").toString();

	// 根据问题主键查询问题详情及交互记录
	public static final String GET_QUESDETAIL_MSG_BY_QUESID = new StringBuilder().append(HTTP_URL_BASE)
			.append("getQuesDetailAndChatMsgByQuesId").toString();

	// 根据问题主键查询问题操作轨迹
	public static final String OPER_PROCESS_BY_QUESTIONID = new StringBuilder().append(HTTP_URL_BASE)
			.append("getOperProcessByQuestionId").toString();

	// 搜索
	public static final String SEARCH_QUESTIONS = new StringBuilder().append(HTTP_URL_BASE).append("searchQuestions")
			.toString();

	// 人员查询
	public static final String PER_INFORMATION = new StringBuilder().append(HTTP_URL_BASE)
			.append("getMembersByQuestionId").toString();

	// 评价项查询
	public static final String EVAL_ITEM_QUERY = new StringBuilder().append(HTTP_URL_BASE).append("evalItemQuery")
			.toString();

	// 评价保存
	public static final String SAVE_EVAL = new StringBuilder().append(HTTP_URL_BASE).append("saveEval").toString();

	// 首页公告
	public static final String GET_BULLETIN_CONTENT = new StringBuilder().append(HTTP_URL_BASE)
			.append("getBulletinContent").toString();

	// 详情
	public static final String DETAIL_QUSETIONS = new StringBuilder().append(HTTP_URL_BASE)
			.append("getQuesDetailAndChatMsgByQuesId").toString();

	// 个人信息
	public static final String GET_PERSONALINFO = new StringBuilder().append(HTTP_URL_BASE).append("getPersonalInfo")
			.toString();



	// 查询文件信息
	public static final String GET_FILE = new StringBuilder().append(HTTP_URL_BASE).append("getFileById").toString();

	// 查询子机构
	public static final String CHILD_ORGS_QUERRY = new StringBuilder().append(HTTP_URL_BASE).append("childOrgsQuery")
			.toString();

	// 查询成员的channelId checkchannelId
	public static final String QUERY_CHANNELID = new StringBuilder().append(HTTP_URL_BASE).append("checkchannelId")
			.toString();
	
	
	// 内部转单查询功能
	public static final String SEARCH_INTERNAL_DISPATCH_TARGRET = new StringBuilder().append(HTTP_URL_BASE).append("searchInternalDispatchTarget")
			.toString();

	// 设备发送消息到消息列表中
	public static final String PUSH_MESSAGE = new StringBuilder().append(HTTP_URL_BASE).append("pushMsg").toString();

	// 转派
	public static final String ORDER_DISPATCH = new StringBuilder().append(HTTP_URL_BASE).append("orderDispatch")
			.toString();//

	
	//内部 转派
	public static final String ORDER_INTERNAL_DISPATCH = new StringBuilder().append(HTTP_URL_BASE).append("orderInternalDispatch")
				.toString();
	// 协办
	public static final String ORDER_ASSIST = new StringBuilder().append(HTTP_URL_BASE).append("orderAssist")
			.toString();
	// 拆红包
	public static final String SEPARATE_RED_PACKAGE = new StringBuilder().append(HTTP_URL_BASE).append("takeHb")
			.toString();

	// 转派-派专家搜索
	public static final String SEARCH_DISPATCH_TARGET = new StringBuilder().append(HTTP_URL_BASE)
			.append("searchDispatchTarget").toString();
	
	// 协办
	public static final String SEARCH_ASSIS_TARGET = new StringBuilder().append(HTTP_URL_BASE)
			.append("searchAssistTarget").toString();

	// 个人信息
	public static final String GET_PERSONAL_INFO = new StringBuilder().append(HTTP_URL_BASE).append("getPersonalInfo")
			.toString();

	// 保存推送信息
	public static final String SAVE_STATE = new StringBuilder().append(HTTP_URL_BASE).append("saveState").toString();

	// 催一下
	public static final String PRESS_ORDER = new StringBuilder().append(HTTP_URL_BASE).append("pressOrder").toString();

	// 办结--根据问题大类查询问题小类
	public static final String GET_CHILDQUESTIONCLASSES = new StringBuilder().append(HTTP_URL_BASE)
			.append("getChildQuestionClasses").toString();

	// 办结--问题大类查询
	public static final String GET_QUESTIONCLASSES = new StringBuilder().append(HTTP_URL_BASE)
			.append("getQuestionClasses").toString();

	// 办结保存
	public static final String SAVE_HANDLE = new StringBuilder().append(HTTP_URL_BASE).append("saveHandle").toString();
	
	//群聊生成在线提问
	public static final String ONKEY_ASK_QUESTION = new StringBuilder().append(HTTP_URL_BASE).append("onKeyAskQuestion").toString();

	// 工单池接单
	public static final String ACCEPT_ORDER = new StringBuilder().append(HTTP_URL_BASE).append("acceptOrder")
			.toString();

	//  个人响应
	public static final String RESPONSE_ORDER = new StringBuilder().append(HTTP_URL_BASE).append("responseOrder")
			.toString();
	// 派服务台
	public static final String SEND_SERVICE = new StringBuilder().append(HTTP_URL_BASE).append("sendService")
			.toString();

	// 不满意选项查询
	public static final String UNSATISFIED_ITEM_QUERY = new StringBuilder().append(HTTP_URL_BASE)
			.append("unsatisfiedItemQuery").toString();

	// 根据文件ID下载文件
	public static final String DOWNLOAD_FILE_BY_FILEID = new StringBuilder().append(HTTP_URL_BASE)
			.append("downLoadByFileId").toString();

	// 待办工单数量查询
	public static final String COMMON_PROCESS_ORDERS = new StringBuilder().append(HTTP_URL_BASE).append("waitOrderSum")
			.toString();

	// 查询外部支撑
	public static final String OUT_SUPPORT_QUERY = new StringBuilder().append(HTTP_URL_BASE).append("outSupportQuery")
			.toString();

	// 查询常用流程权限
	public static final String QUERY_COMMON_LIMIT = new StringBuilder().append(HTTP_URL_BASE).append("queryCommonLimit")
			.toString();

	// 转派查询
	public static final String DISPATCH_QUERY = new StringBuilder().append(HTTP_URL_BASE).append("dispatchQuery")
			.toString();

	// 一键派单
	public static final String ONE_KEY_DISPATCH = new StringBuilder().append(HTTP_URL_BASE).append("oneKeyDispatch")
			.toString();

	// 历史列表搜索
	public static final String SEARCH_HISTORY_BY_PAGE = new StringBuilder().append(HTTP_URL_BASE)
			.append("searchHistoryByPage").toString();

	// 是否可以拆红包
	public static final String ABLE_TAKE_HB = new StringBuilder().append(HTTP_URL_BASE).append("ableTakeHb").toString();

	// 拆红包
	public static final String TAKE_HB = new StringBuilder().append(HTTP_URL_BASE).append("takeHbAnother").toString();

	// 协办结构与人查询
	public static final String CHILD_ORGS_QUERY_ASSIST = new StringBuilder().append(HTTP_URL_BASE)
			.append("childOrgsQuery4Assist").toString();

	// 提问人退回评价单
	public static final String ROLLBACK_EVALUATING_QUESTION = new StringBuilder().append(HTTP_URL_BASE)
			.append("rollbackEvaluatingQuestion").toString();

	
	//内部转单
	
	public static final String INTERNAL_DISPATCH = new StringBuilder().append(HTTP_URL_BASE)
			.append("internalDispatch").toString();
	// 督办
	public static final String RB_DISPATCH = new StringBuilder().append(HTTP_URL_BASE).append("reDispatch").toString();

	// 查询流量社区话题
	public static final String QUERY_FLOW_CLAN = new StringBuilder().append(HTTP_URL_BASE).append("queryFlowClan")
			.toString();

	// 查询热点话题
	public static final String QUERY_HOT_TALK = new StringBuilder().append(HTTP_URL_BASE).append("queryHotTalk")
			.toString();

	// 查询流量社区话题
	public static final String QUERY_FLOW_ENCYCLOPEDIA = new StringBuilder().append(HTTP_URL_BASE)
			.append("queryFlowEncyclopedia").toString();

	// 查询社区管理人
	public static final String QUERY_CLAN_MANAGER = new StringBuilder().append(HTTP_URL_BASE).append("queryClanManager")
			.toString();

	// 点赞，关注，评论
	public static final String PRAISE_AND_ATTENTION = new StringBuilder().append(HTTP_URL_BASE)
			.append("praiseAndAttention").toString();

	// 写评论
	public static final String EVAL_REC = new StringBuilder().append(HTTP_URL_BASE).append("evalRec").toString();

	// 查询评论记录
	public static final String QUERY_EVAL_REC = new StringBuilder().append(HTTP_URL_BASE).append("queryEvalRec")
			.toString();

	// 领导视图首页查询
	public static final String LEADER_VIEW_HOME_QUERY = new StringBuilder().append(HTTP_URL_BASE)
			.append("leaderViewHomeQuery").toString();

	// 领导视图二级查询
	public static final String LEADER_VIEW_QUERY = new StringBuilder().append(HTTP_URL_BASE).append("leaderViewQuery")
			.toString();

	// 发布话题
	public static final String SEND_TOPIC = new StringBuilder().append(HTTP_URL_BASE).append("sendTopic").toString();

	// 历史版本信息查询
	public static final String HISTORY_VERSION_QUERY = new StringBuilder().append(HTTP_URL_BASE)
			.append("historyVersionQuery").toString();

	// 记录用户行为信息
	public static final String ONE_STEP = new StringBuilder().append(HTTP_URL_BASE).append("oneStep").toString();

	// 查询转逆向派单失败原因
	public static final String ONE_KEY_DISPATCH_FAILED_REASON = new StringBuilder().append(HTTP_URL_BASE)
			.append("oneKeyDispatchFailedReason").toString();

	// 查询社区信息和数量
	public static final String QUERY_LABEL_INFO = new StringBuilder().append(HTTP_URL_BASE).append("queryLabelInfo")
			.toString();

	// 找专家子菜单查询
	public static final String CHILD_MAJORS_QUERY = new StringBuilder().append(HTTP_URL_BASE).append("childMajorsQuery")
			.toString();

	// 找专家子搜索接口
	public static final String SEARCH_EXPERTS_ELSE = new StringBuilder().append(HTTP_URL_BASE)
			.append("searchExpertsElse").toString();

	// 根据specialId查询百科
	public static final String QUERY_FLOW_CLAN_INFO = new StringBuilder().append(HTTP_URL_BASE)
			.append("queryFlowClanInfo").toString();

	// 根据docId查询FAQ
	public static final String QUERY_FAQ = new StringBuilder().append(HTTP_URL_BASE).append("queryFAQ").toString();
	
	//查询群组列表
	public static final String QUERY_QUNLIST=new StringBuilder().append(HTTP_URL_BASE).append("queryTeamLists").toString();
		//群聊天信息
		//getChatMsgsBySessionId
		
	public static final String QUERY_QUNNEWS=new StringBuilder().append(HTTP_URL_BASE).append("getChatMsgsBySessionId").toString();
	/**
	 * 群成员列表查询
	 */
		public static final String QUERY_QUNMEMBERS=new StringBuilder().append(HTTP_URL_BASE).append("queryTeamMembers").toString();
	/**
	 * 群组人员搜索
	 */public static final String SEARCH_QUNMEMBER=new StringBuilder().append(HTTP_URL_BASE).append("getOrgUsers").toString();
		
	
	
	 public static final String GETMSRECEIVE= new StringBuilder().append(HTTP_URL_BASE).append("getMsgReceive").toString();
	 public static final String SETMSRECEIVE= new StringBuilder().append(HTTP_URL_BASE).append("setMsgReceive").toString();
	
	 public static final String GETCHILDORGS= new StringBuilder().append(HTTP_URL_BASE).append("getChildOrgs").toString();

	 // 新建群 
	 public static final String NEWTEAM= new StringBuilder().append(HTTP_URL_BASE).append("newTeam").toString();
	 public static final String QUERYTEAMMEMBERS= new StringBuilder().append(HTTP_URL_BASE).append("queryTeamMembers").toString();
	 
	 public static final String DELTEAMMEMBER= new StringBuilder().append(HTTP_URL_BASE).append("delTeamMember").toString();
	 
	 public static final String UPADMIN= new StringBuilder().append(HTTP_URL_BASE).append("upAdmin").toString();
	 public static final String UPGRADE= new StringBuilder().append(HTTP_URL_BASE).append("updateSession").toString();
	 public static final String DELTEAM= new StringBuilder().append(HTTP_URL_BASE).append("delTeam").toString();
	 public static final String DOWNADMIN= new StringBuilder().append(HTTP_URL_BASE).append("downAdmin").toString();

	 public static final String GETCHATMSGSBYSESSIONID= new StringBuilder().append(HTTP_URL_BASE).append("getChatMsgsBySessionId").toString();

	 public static final String ADDTEAMMEMBERS= new StringBuilder().append(HTTP_URL_BASE).append("addTeamMembers").toString();
	 public static final String GETORGUSERS= new StringBuilder().append(HTTP_URL_BASE).append("getOrgUsers").toString();
	 public static final String UPDATESESSIONNAME= new StringBuilder().append(HTTP_URL_BASE).append("updateSessionName").toString();
	 public static final String DEALCHATMSG= new StringBuilder().append(HTTP_URL_BASE).append("dealChatMsg").toString();

	// 文件上传
	public static final String GETMSGID = new StringBuilder().append(HTTP_URL_BASE).append("getMsgId").toString();

	// 单点短信
	public static final String SENDPOINTMSG = new StringBuilder().append(HTTP_URL_BASE).append("sendPointMsg").toString();
	
	//获取群通知
	public static final String GET_DEALING_MSGS = new StringBuilder().append(HTTP_URL_BASE).append("getDealingMsgs").toString();

	//获取代理列表
	public static final String GET_PROXY_LIST = new StringBuilder().append(HTTP_URL_BASE).append("getProxy").toString();

	//设置代理
	public static final String SET_PROXY = new StringBuilder().append(HTTP_URL_BASE).append("setProxy").toString();

	//删除代理
	public static  final String DEL_PROXY = new StringBuilder().append(HTTP_URL_BASE).append("delProxy").toString();

	//代理审核
	public static  final String DEAL_PROXY = new StringBuilder().append(HTTP_URL_BASE).append("dealProxy").toString();

	//校验、获取集团帐号明细
	public static  final String CRM_SINGON = new StringBuilder().append(HTTP_URL_BASE).append("crmSingon").toString();

	//绑定集团账号和易支撑账号
	public static  final String SYNC_ACCOUNT = new StringBuilder().append(HTTP_URL_BASE).append("syncAccount").toString();

	//抽奖接口
	public static  final String drawLottery = new StringBuilder().append(HTTP_URL_BASE).append("drawLottery").toString();


	//领红包
	public static  final String takeHb = new StringBuilder().append(HTTP_URL_BASE).append("takeHb").toString();


	//是否可以去
	public static  final String getLotterysNew = new StringBuilder().append(HTTP_URL_BASE).append("getLotterysNew").toString();


	//知识云反馈意见
	public static  final String KNOW_FEED_BACKAPP = new StringBuilder().append(HTTP_URL_BASE).append("knowFeedBackApp").toString();

	//首页实时刷新的数据
	public static  final String GETPERSONAL_INFONOCACHE = new StringBuilder().append(HTTP_URL_BASE).append("getPersonalInfoNoCache").toString();
	public static  final String ACCESS_TOKEN = "http://202.102.221.47:9080/component-camelv/camel/eam_apps/oauth/token";
	public static  final String ACCESS_TOKEN_VPN = "http://202.102.221.47:9080/component-camelv/camel/eam_apps/oauth/token";
	//ceshi
//		public static  final String ACCESS_TOKEN_VPN = "http://134.64.22.251:9090/component-camelv/camel/eam_apps/oauth/token";

	//知识反馈原因查询
	public static  final String QUERY_FEED_BACK_REASON = new StringBuilder().append(HTTP_URL_BASE).append("queryFeedBackReason").toString();


	//是否显示分类界面
	public static final String GETQUESTIONINFO=new StringBuilder().append(HTTP_URL_BASE).append("getQuestionInfo").toString();

	//保存所选择的值
	public static final String SAVECLASSID=new StringBuilder().append(HTTP_URL_BASE).append("saveClassId").toString();

	//首页获取公告和通知接口（易支撑首页大改版）
	public static final String GETAPPHOMEBULLETIN=new StringBuilder().append(HTTP_URL_BASE).append("getAppHomeBulletin").toString();

	//主页手机app主页工单查询接口
	public static final String GETAPPHOMEQUESTIONS=new StringBuilder().append(HTTP_URL_BASE).append("getAppHomeQuestions").toString();

	//易支撑党员承诺界面承诺获取接口
	public static final String GET_PARTY_PROMISE=new StringBuilder().append(HTTP_URL_BASE).append("getPartyPromise").toString();
	//处理申请延期(处理中）
	public static final String APPLYQUESTIONPOSTPONE=new StringBuilder().append(HTTP_URL_BASE).append("applyQuestionPostpone").toString();
	//处理申请延期
	public static final String REPLYQUESTIONPOSTPONE=new StringBuilder().append(HTTP_URL_BASE).append("replyQuestionPostpone").toString();
	//震荡接口
	public static final String MARKQUESTIONCONCUSSION=new StringBuilder().append(HTTP_URL_BASE).append("markQuestionConcussion").toString();
	//获取待评测数字和数量
	public static final String GETSYSDATE=new StringBuilder().append(HTTP_URL_BASE).append("getSysDate").toString();
//	public static final String GETSYSDATE=new StringBuilder().append(HTTP_URL_BASE_TWO).append("getSysDate").toString();
	//获取纠错信息接口
public static final String GETCORRECTINFO=new StringBuilder().append(HTTP_URL_BASE).append("getCorrectInfo").toString();
	//知识纠错审核功能接口
	public static final String DEALCORRECT=new StringBuilder().append(HTTP_URL_BASE).append("dealCorrect").toString();
	//举手响应获取派单的机构名称
	public static final String HANDASKORGSQUERY=new StringBuilder().append(HTTP_URL_BASE).append("handAskOrgsQuery").toString();

}
