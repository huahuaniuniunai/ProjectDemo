package com.example.projectdemo.util.lztx;

import java.util.Comparator;


public class PinyinComparator implements Comparator<SortModel> {

	@Override
	public int compare(SortModel arg0, SortModel arg1) {
		if (arg0.getLetter().equals("@") || arg1.getLetter().equals("#")) {
			return -1;     
		} else if (arg0.getLetter().equals("#") || arg1.getLetter().equals("@")) {
			return 1;
		} else {
			return arg0.getLetter().compareTo(arg1.getLetter());
		//    return arg1.getLetter().compareTo(arg0.getLetter());
		}
	}
}
