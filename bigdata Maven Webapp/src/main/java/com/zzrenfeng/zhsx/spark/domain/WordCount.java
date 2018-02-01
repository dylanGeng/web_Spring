package com.zzrenfeng.zhsx.spark.domain;

import scala.Serializable;

public class WordCount implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String word;
	private Integer count;
	public WordCount(){}
	public WordCount(String v1, int l) {
		word = v1;
		count = l;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "WordCount [word=" + word + ", count=" + count + "]";
	}
	
}
