package com.yang.Sprider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.yang.Models.BookInfo;
import com.yang.Tools.CSVFileHelper;
import com.yang.Tools.Constant;
import com.yang.Tools.Htmlhandler;
import com.yang.Tools.Log;

public class MainAction {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//希望找100篇
		int Count = 100;
		//评论人数要求
		int EVAL_NUM = 2000;
		
		new Thread(new Thread_internet_sprider(Count,EVAL_NUM)).start();
		new Thread(new Thread_algorithm_sprider(Count,EVAL_NUM)).start();
		new Thread(new Thread_program_sprider(Count,EVAL_NUM)).start();
	}

}
	class Thread_internet_sprider implements Runnable{
		private int _count;
		private int _num;
		public Thread_internet_sprider(int count,int num){
			this._count = count;
			this._num = num;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			internet_sprider(this._count,this._num);
			Log.write("internet_sprider success");
		}
		private void internet_sprider(int number,int eval_num) {
			Htmlhandler obj = new Htmlhandler();
			CSVFileHelper csvfile = new CSVFileHelper("internet.csv");
			List<BookInfo> books = new ArrayList<BookInfo>();

			obj.setTag(Constant.tag_internet);
			obj.setOrderby(Constant.orderby_evaluate);
			for (int index = 0;; index += 20) {
				obj.setStart(index);
				String url = "https://book.douban.com/tag/" + obj.getTag()
						+ "?start=" + obj.getStart() + "&type=" + obj.getOrderby();
				int res = obj.download(url);
				if (res == 0) {
					// obj.savetofile("test.html");
					if(obj.parser(eval_num) >= 0){
						books.addAll(obj.getparse_res());
					}
					else{
						break;
					}
					//找到超过number条数据
					if (books.size() >= number)
						break;
				} else {
					System.out.println("some errors happed");
					break;
				}
			}
			Collections.sort(books);
			Collections.reverse(books);
			if(books.size() <= number)
				csvfile.write(books);
			else{
				csvfile.write(books.subList(0, number));
			}
		}
		
	}
	class Thread_algorithm_sprider implements Runnable{
		private int _count;
		private int _num;
		public Thread_algorithm_sprider(int count,int num){
			this._count = count;
			this._num = num;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			algorithm_sprider(this._count,this._num);
			Log.write("algorithm_sprider success");
		}
		private void algorithm_sprider(int number,int eval_num) {
			Htmlhandler obj = new Htmlhandler();
			CSVFileHelper csvfile = new CSVFileHelper("algorithm.csv");
			List<BookInfo> books = new ArrayList<BookInfo>();

			obj.setTag(Constant.tag_algorithm);
			obj.setOrderby(Constant.orderby_evaluate);
			for (int index = 0;; index += 20) {
				obj.setStart(index);
				String url = "https://book.douban.com/tag/" + obj.getTag()
						+ "?start=" + obj.getStart() + "&type=" + obj.getOrderby();
				int res = obj.download(url);
				if (res == 0) {
					// obj.savetofile("test.html");
					if(obj.parser(eval_num) >= 0){
						books.addAll(obj.getparse_res());
					}
					else{
						break;
					}
					//找到超过number条数据
					if (books.size() >= number)
						break;
				} else {
					System.out.println("some errors happed");
					break;
				}
			}
			Collections.sort(books);
			Collections.reverse(books);
			if(books.size() <= number)
				csvfile.write(books);
			else{
				csvfile.write(books.subList(0, number));
			}
		}
	}
	class Thread_program_sprider implements Runnable{
		private int _count;
		private int _num;
		public Thread_program_sprider(int count,int num){
			this._count = count;
			this._num = num;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			program_sprider(this._count,this._num);
			Log.write("program_sprider success");
		}
		public void program_sprider(int number,int eval_num) {
			Htmlhandler obj = new Htmlhandler();
			CSVFileHelper csvfile = new CSVFileHelper("program.csv");
			List<BookInfo> books = new ArrayList<BookInfo>();

			obj.setTag(Constant.tag_program);
			obj.setOrderby(Constant.orderby_evaluate);
			for (int index = 0;; index += 20) {
				obj.setStart(index);
				String url = "https://book.douban.com/tag/" + obj.getTag()
						+ "?start=" + obj.getStart() + "&type=" + obj.getOrderby();
				int res = obj.download(url);
				if (res == 0) {
					// obj.savetofile("test.html");
					if(obj.parser(eval_num) >= 0){
						books.addAll(obj.getparse_res());
					}
					else{
						break;
					}
					//找到超过number条数据
					if (books.size() >= number)
						break;
				} else {
					System.out.println("some errors happed");
					break;
				}
			}
			Collections.sort(books);
			Collections.reverse(books);
			if(books.size() <= number)
				csvfile.write(books);
			else{
				csvfile.write(books.subList(0, number));
			}
		}
	
	}


