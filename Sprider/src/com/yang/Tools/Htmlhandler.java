package com.yang.Tools;

import java.io.File;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.net.HttpURLConnection;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.python.core.Py;
import org.python.core.PyFunction;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;

import com.yang.Models.BookInfo;

public class Htmlhandler {
	private StringBuilder sb_htmlsrc;
	private String _htmlsrc;
	// 标签设置
	private String tag;
	// 排序标准
	private String orderby;
	private int start;
	private List<BookInfo> _tempres;
	
	
	public int download(String url, String encoding) {
		try {
			this.sb_htmlsrc = new StringBuilder();
			URL page_url = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) page_url
					.openConnection();
			connection.setRequestProperty("User-Agent", "MSIE 7.0");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), encoding));
			String line = null;
			while ((line = br.readLine()) != null) {
				this.sb_htmlsrc.append(line);
				this.sb_htmlsrc.append("\r\n");
			}
			connection.disconnect();
			return 0;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Log.write(e.toString());
			return 1;
		}

	}

	public int download(String url) {
		return download(url, "UTF-8");
	}

	// 解析html
	public int parser(int eval_num) {
		this._tempres = new ArrayList<BookInfo>();
		this._htmlsrc = this.sb_htmlsrc.toString();
		int res = 0;
		try {
			PythonInterpreter interpreter = new PythonInterpreter();
			PySystemState sys = Py.getSystemState();
			sys.path.add("D:\\jython2.5.2\\Lib");
			interpreter.execfile("src//com//yang//Tools//parse.py");
			PyFunction func = (PyFunction) interpreter.get("parse",
					PyFunction.class);
			PyObject pyobj = func.__call__(new PyString(this._htmlsrc));
			
			String[] infos = pyobj.toString().split("_;_");
			for (int index = 0; index < infos.length; index++) {
				
				String info = infos[index];
				int zuo_pos = info.lastIndexOf("(");
				int pingjia_pos = info.indexOf("人评价");

				String name = info.substring(0, zuo_pos);
				int evaluates = Integer.parseInt(info.substring(zuo_pos + 1,
						pingjia_pos));
				if (evaluates <= eval_num)
					continue;
				double goal = Double.valueOf(info.substring(info.length() - 3));
				BookInfo book = new BookInfo(info, name, evaluates, goal);
				this._tempres.add(book);
				
			}
		} catch (Exception e) {
			res = -1;
			Log.write(e.toString());
		}
		return res;
	}

	//在parse之后使用，用于返回结果
	public List<BookInfo> getparse_res(){
		return this._tempres;
	}

	public void savetofile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				Log.write(e.toString());
				return;
			}
		}
		OutputStreamWriter out;
		try {
			out = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
			out.write(this.sb_htmlsrc.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Log.write(e.toString());
		}

	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}
}
