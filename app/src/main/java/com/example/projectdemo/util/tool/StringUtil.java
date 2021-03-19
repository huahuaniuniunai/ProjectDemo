package com.example.projectdemo.util.tool;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

public class StringUtil {

    /**
     * 将ISO字符转化为GBK字符
     *
     * @param str
     */
    public static String getGBKFromISO(String str) {
        try {
            if (str == null) str = "";
            byte[] buf = str.getBytes("iso-8859-1");
            byte[] buf2 = str.getBytes("gbk");
            if (!str.equals(new String(buf2, "gbk"))) {
                str = new String(buf, "gbk");
            }

            return str;
        }
        catch (UnsupportedEncodingException e) {

        }
        return str;
    }

    /**
     * 将列表转换为字符串输出
     *
     * @param arr
     * @param split
     */
    public static String getStrByArray(ArrayList arr, String split) {
        String outstr = "";
        for (int i = 0; i < arr.size(); i++) {
            outstr += arr.get(i) + split;
        }
        if (outstr.length() > 0) {
            outstr = outstr.substring(0, outstr.length() - 1);
        }
        return outstr;
    }

    /**
     * 将GBK字符转化为ISO字符
     *
     * @param str
     */
    public static String getISOFromGBK(String str) {
        try {
            if (str == null) str = "";
            byte[] buf = str.getBytes("gbk");
            byte[] buf2 = str.getBytes("iso-8859-1");
            if (!str.equals(new String(buf2, "iso-8859-1"))) {
                str = new String(buf, "iso-8859-1");
            }
        }
        catch (UnsupportedEncodingException e) {

        }
        return str;
    }

    /**
     * 将字符串handleStr中以pointStr以分隔的每个字符串存放在向量中返回
     *
     * @param handleStr
     * @param pointStr
     */
    public static Vector explode(String handleStr, String pointStr) {
        Vector v = new Vector();
        int pos1, pos2;
        try {
            if (handleStr.length() > 0) {
                pos1 = handleStr.indexOf(pointStr);
                pos2 = 0;
                while (pos1 != -1) {
                    v.addElement(handleStr.substring(pos2, pos1));
                    pos2 = pos1 + pointStr.length();
                    pos1 = handleStr.indexOf(pointStr, pos2);
                }
                v.addElement(handleStr.substring(pos2));
            }
        }
        catch (Exception e) {

        }
        return v;
    }

    /**
     * 在字符串handleStr中的字符串pointStr以repStr代替
     *
     * @param handleStr
     * @param pointStr
     * @param repStr
     */
    public static String replace(String handleStr, String pointStr, String repStr) {
        String str = new String();
        int pos1, pos2;
        try {
            if (handleStr.length() > 0) {
                pos1 = handleStr.indexOf(pointStr);
                pos2 = 0;
                while (pos1 != -1) {
                    str += handleStr.substring(pos2, pos1);
                    str += repStr;
                    pos2 = pos1 + pointStr.length();
                    pos1 = handleStr.indexOf(pointStr, pos2);
                }
                str += handleStr.substring(pos2);
            }
        }
        catch (Exception e) {

        }
        return str;
    }

 

  
    /**
     * 将字符串中的"\n"以"<br>&nbsp;&nbsp;" 替换后返回
     *
     * @param handleStr
     */
    public static String returnChar2BR(String handleStr) {
        String str = handleStr;
        str = replace(str, "\n", "<br>&nbsp;&nbsp;");
        return str;
    }


    /**
     * 将字符串中的"\n"以"<br>&nbsp;&nbsp;" 替换后返回
     *
     * @param handleStr
     */
    public static String returnChar2BRno(String handleStr) {
        String str = handleStr;
        if (str != null) {
            str = replace(str, "\n", "<br>");
        } else {
            str = "";
        }
        return str;
    }

    /**
     * 将handler中的内容取出转换为字符串并将其每个以separator分割开后返回。
     *
     * @param handler
     * @param separator
     */
    public static String implode(Vector handler, String separator) {
        StringBuffer strbuf = new StringBuffer();
        try {
            if (!handler.isEmpty()) {
                int len = handler.size();
                for (int loopi = 0; loopi < len; loopi++) {
                    strbuf.append((String) handler.get(loopi));
                    if (loopi != len - 1)
                        strbuf.append(separator);
                }
            }
        }
        catch (Exception e) {
        }
        return strbuf.toString();
    }

    /**
     * Return appointed String from a String Vector
     * param1: String Vector
     * param2: appointed Index
     * param3: include Excel CSV process.
     */
    public static String getField(Vector vt, int i, boolean isExcel) {
        String str = "";
        try {
            str = (String) vt.get(i);
            if (str != null && str.length() > 2 && isExcel) {
                if (str.substring(0, 1).compareTo("\"") == 0) {
                    str = str.substring(1, str.length() - 1);
                    str = StringUtil.replace(str, "\"\"", "\"");
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return "";
        }
        return str;
    }

    /**
     * 当字符串长度小于Len时，有字符InsChar填冲到str的左边或右边，当intDirect为0时为左，1时为右
     * param1: father string
     * param2: need fill in char
     * param3: 0 is left fill in
     * 1 is right fill in
     * param4: total string length after fill in char
     */
    public static String insStr(String str, String InsChar, int intDirect,
                                int Len) {
        int intLen = str.length();
        StringBuffer strBuffer = new StringBuffer(str);

        if (intLen < Len) {
            int inttmpLen = Len - intLen;
            for (int i = 0; i < inttmpLen; i++) {
                if (intDirect == 1) {
                    str = str.concat(InsChar);
                } else if (intDirect == 0) {
                    strBuffer.insert(0, InsChar);
                    str = strBuffer.toString();
                }
            }
        }
        return str;
    }

    /**
     * 返回在字符串str中，首次出现字符串divided的位置。若没有找到返回-1
     *
     * @param str
     * @param divided
     */
    public static int searchDiv(String str, String divided) {
        try {
            divided = divided.trim();
            int divpos = -1;

            if (str.length() > 0) {
                divpos = str.indexOf(divided);

                return divpos;
            } else
                return divpos;
        }
        catch (Exception e) {
            return -1;
        }
    }

    /**
     * 在字符串str中取首次出现startdiv到首次出现enddiv之间的字符串并返回，如果没有找到返回“”
     *
     * @param str
     * @param startdiv
     * @param enddiv
     */
    public static String extractStr(String str, String startdiv, String enddiv) {
        int startdivlen = startdiv.length();
        str = str.trim();

        int startpos = -1;
        int endpos = -1;

        startdiv = startdiv.trim();
        enddiv = enddiv.trim();
        startpos = searchDiv(str, startdiv);
        if (str.length() > 0) {
            if (startpos >= 0) {
                str = str.substring(startpos + startdivlen);
                str = str.trim();
            }
            endpos = searchDiv(str, enddiv);
            if (endpos == -1)
                return "";
            str = str.substring(0, endpos);
            str = str.trim();
        }
        return str;
    }

    /**
     * 返回一个不为空的字符串
     *
     * @param str
     */
    public static String isNull(String str) {
        return isNull(str, "&nbsp;");
    }

    /**
     * 返回一个不为空的字符串，当为空时返回def
     *
     * @param str
     * @param def
     */
    public static String isNull(String str, String def) {
        if (str == null)
            return def;
        else if (str.length() == 0)
            return def;
        else
            return str;
    }

    /**
     * 将字符串类型转换为整数类型
     *
     * @param str
     */
    public static int StringToInt(String str) {
        return StringToInt(str, 0);
    }

    /**
     * 将字符串类型转换为整数类型，出错时有def值返回
     *
     * @param str
     * @param def
     */
    public static int StringToInt(String str, int def) {
        int intRet = def;
        try {
            if (str == null || str.trim().equals(""))
                str = def + "";
            intRet = Integer.parseInt(str);
        }
        catch (NumberFormatException e) {

        }
        return intRet;
    }

    /**
     * 将字符串类型转换为浮点类型
     *
     * @param str
     */
    public static float StringToFloat(String str) {
        return StringToFloat(str, 0);
    }

    /**
     * 将字符串类型转换为浮点类型，出错时有def值返回
     *
     * @param str
     * @param def
     */
    public static float StringToFloat(String str, float def) {
        float fRet = def;
        try {
            if (str == null || str.trim().equals(""))
                str = "0";
            fRet = Float.parseFloat(str);
        }
        catch (NumberFormatException e) {
        }
        return fRet;
    }

    /**
     * 将字符串类型转换为双精度类型
     *
     * @param str
     */
    public static double StringToDouble(String str) {
        return StringToDouble(str, (double) 0);
    }

    /**
     * 将字符串类型转换为双精度类型，出错时有def值返回
     *
     * @param str
     * @param def
     */
    public static double StringToDouble(String str, double def) {
        double dRet = (double) def;
        try {
            if (str == null || str.trim().equals(""))
                str = "0";
            dRet = Double.parseDouble(str);
        }
        catch (NumberFormatException e) {
        }
        return dRet;
    }

    /**
     * 将字符串类型转换为双精度类型
     *
     * @param str
     */
    public static long StringToLong(String str) {
        return StringToLong(str, (long) 0);
    }

    /**
     * 将字符串类型转换为双精度类型，出错时有def值返回
     *
     * @param str
     * @param def
     */
    public static long StringToLong(String str, long def) {
        long dRet = (long) def;
        try {
            if (str == null || str.trim().equals(""))
                str = "0";
            dRet = Long.parseLong(str);
        }
        catch (NumberFormatException e) {
        }
        return dRet;
    }

  
    /**
     * 获得安全字符串，使得字符串不为空，并去掉前后的空格
     *
     * @param str
     */
    public static String getSafeString(String str) {
        if (str == null)
            return "";
        else
            return str.trim();
    }

    /**
     * 将字符串在指定的长度内显示，超出后以..代替
     *
     * @param str  in string
     * @param iLen specify length
     *             out string
     */
    public static String substr(String str, int iLen) {
        if (str == null)
            return "";
        if (iLen > 2) {
            if (str.length() > iLen - 2) {
                str = str.substring(0, iLen - 2) + "..";
            }

        }
        return str;
    }

    public static String substr(String oldString, int length, String add) {
        int oldL = oldString.length();
        if (oldL < length) {
            for (int i = 0; i < (length - oldL); i++) oldString += add;
        } else if (oldL > length) oldString = oldString.substring(0, 8);
        return oldString;
    }


   



    /**
     * 将对象转换了字符型
     *
     * @param s
     */
    public static String null2String(Object s) {
        return s == null || s.equals("null") || s.equals("NULL") ? "" : s.toString();
    }

    /**
     * 将对象转换为字符型,默认显示def
     * @param s
     * @param def
     * @return
     */
    public static String null2String(Object s, String def) {
    	String str = null2String(s);
    	if("".equals(str)){
    		str = def;
    	}
        return str;
    }

    /**
     * 将字符串数组转换为（'a','b'）的格式后返回，来方便数据库的操作
     *
     * @param names
     * @return String
     */
    public static String getStrsplit(String[] names) {
        if (names == null || names.length == 0) return "()";
        String result = "(";
        for (int i = 0; i < names.length; i++) {
            if (i == names.length - 1) result = result + "'" + names[i] + "'";
            else
                result = result + "'" + names[i] + "',";
        }
        result = result + ")";
        return result;
    }

    /**
     * 将字符串数组转换为（'a','b'）的格式后返回，来方便数据库的操作
     *
     * @param names
     * @return String
     */
    public static String getStrsplit(ArrayList names) {
        if (names == null || names.size() == 0) return "('')";
        String result = "(";
        for (int i = 0; i < names.size(); i++) {
            if (i == names.size() - 1) result = result + "'" + (String) names.get(i) + "'";
            else
                result = result + "'" + (String) names.get(i) + "',";
        }
        result = result + ")";
        return result;
    }

    /**
     * 将整型数组转换为（1，2）的格式后返回，来方便数据库的操作
     *
     * @param ids
     * @return String
     */
    public static String getIdsplit(String[] ids) {
        if (ids == null || ids.length == 0) return "('')";
        String result = "(";
        for (int i = 0; i < ids.length; i++) {
            if (i == ids.length - 1) result = result + ids[i];
            else
                result = result + ids[i] + ",";
        }
        result = result + ")";
        return result;
    }

    /**
     * 将向量转换为（1，2）的格式后返回，来方便数据库的操作
     *
     * @param ids
     * @return String
     */
    public static String getIdsplit(ArrayList ids) {
        if (ids == null || ids.size() == 0) return "()";
        String result = "(";
        for (int i = 0; i < ids.size(); i++) {
            if (i == ids.size() - 1) result = result + (String) ids.get(i);
            else
                result = result + (String) ids.get(i) + ",";
        }
        result = result + ")";
        return result;
    }

    /**
     * 将字符串数组转换为（'a','b'）的格式后返回，来方便数据库的操作
     *
     * @param names
     * @return String
     */
    public static String getStrByArr(ArrayList names, String split) {
        if (names == null || names.size() == 0) return "";
        String result = "";
        for (int i = 0; i < names.size(); i++) {
            if (i == names.size() - 1) result += names.get(i) + "";
            else
                result = names.get(i) + split;
        }
        return result;
    }

    /**
     * 将字符串数组转换为（'a','b'）的格式后返回，来方便数据库的操作
     *
     * @param names
     * @return String
     */
    public static String getStrByArr(String names[], String split) {
        if (names == null || names.length == 0) return "";
        String result = "";
        for (int i = 0; i < names.length; i++) {
            if (i == names.length - 1) result += names[i] + "";
            else
                result = names[i] + split;
        }
        return result;
    }

    
    public static Hashtable<String, String> getHTFromJsonStr(String JsonStr) {
    	try{
    		JsonStr = null2String(JsonStr);
    		
    		if(JsonStr.length()<2){
    			return null;
    		}
    		
	        String tempStr = JsonStr;
	        tempStr = tempStr.substring(1,tempStr.length()-1);
	        Hashtable<String, String> returnHT = new Hashtable<String, String>();
//	        System.out.println(tempStr);
	        if(!"".equals(tempStr.trim())){
	        	String sxs[]=tempStr.split("jsonsplit");
		        for(int i=0;i<sxs.length;i++){
		        	String sx[] = sxs[i].split("sxsplit");
		        	if(sx.length==2){
		        		returnHT.put(sx[0], sx[1]);
		        	}
		        }
	        }
	        return returnHT;	
        }catch (Exception e) {
        	e.printStackTrace();
        	return null;
        }
    }

    public static String getValues(String str) {
        if (str == null || "".equals(str)) return "0";
        return str;
    }
}
