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
package com.example.projectdemo.util.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.example.projectdemo.R;


public class MyProgressDialog extends ProgressDialog {

	private String message;

	private TextView define_progress_msg;

	public MyProgressDialog(Context context) {
		this(context ,"正在载入...");
	}
	public MyProgressDialog(Context context, String message){
		super(context);
		this.message = message;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.progress_dialog);
		define_progress_msg = (TextView) findViewById(R.id.progress_msg);
		define_progress_msg.setText(message);
	}
	
	@Override
	public void setMessage(CharSequence message) {
		define_progress_msg.setText(message);
	}

}
