package cn.mopow.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;

import cn.mopow.entity.Article;

public class MpUtil {
	//生成随机10位ID
	public static String createPicId(){
		String id=createRandomCharData(10);
		return id;
	}
	/**
	 * 随机数生成，生成length位随机数
	 * @param length
	 * @return
	 */
	

	public static String  createRandomCharData(int length) {
		StringBuilder sb = new StringBuilder();
		Random rand = new Random();// 随机用以下三个随机生成器
		Random randdata = new Random();
		int data = 0;
		for (int i = 0; i < length; i++) {
			int index = rand.nextInt(3);
			// 目的是随机选择生成数字，大小写字母
			switch (index) {
			case 0:
				data = randdata.nextInt(10);// 仅仅会生成0~9
				sb.append(data);
				break;
			case 1:
				data = randdata.nextInt(26) + 65;// 保证只会产生65~90之间的整数
				sb.append((char) data);
				break;
			case 2:
				data = randdata.nextInt(26) + 97;// 保证只会产生97~122之间的整数
				sb.append((char) data);
				break;
			}
		}
		String result = sb.toString();
		return result;
	}
	
	//MD5单向加密
	public static String md5(String msg) throws NoSuchAlgorithmException {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] input = msg.getBytes();
			byte[] output = md.digest(input);

			String result = Base64.encodeBase64String(output);
			return result;
		} 
	public static Article viewCreate(Article news){
		String img;
		String title = news.getN_title();
		String body = news.getN_body();
		news.setN_type("1");
		String id=news.getN_id();
		Pattern pattern = Pattern.compile("http[\\s\\S]{1,100}(.jpeg|.jpg|.png|.jif)");
		Matcher matcher = pattern.matcher(body);
		body=body.replaceAll("<[^<]{0,100}>", "");
		if(matcher.find()){
			img=matcher.group();
			body="<img class='news_img' src='"+img+"'><a href='news-"+id+".do' target='"+id+"_black'>"+title+"</a>";
			news.setN_type("2");
			news.setN_img(img);
		}else if(body.length()>=110){
			body=body.substring(0,110);
		}
		System.out.println(body);
		news.setN_view(body);
		return news;
}
	
}
