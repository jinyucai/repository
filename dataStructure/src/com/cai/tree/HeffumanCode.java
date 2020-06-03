package com.cai.tree;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/**
 * 赫夫曼编码
 * @author Administrator
 *
 */
public class HeffumanCode {
	public static void main(String[] args) {
		String content = "i like like like java do you like a java";
		byte[] bytes = content.getBytes();
		List<CodeNode> nodes = getNodes(bytes);
		//得到一个赫夫曼树
		CodeNode hffumanCode = createHffumanCode(nodes);
//		preOrder(hffumanCode);
		Map<Byte, String> heffumanCodes = getHeffumanCodes(hffumanCode);
//		for (Map.Entry<Byte, String> entry: heffumanCodes.entrySet()) {
//			System.out.println(entry.getKey() + "===>" + entry.getValue());
//		}
		byte[] heffumanZip = heffumanZip(bytes);
		byte[] decode = decode(heffumanZip, heffumanCodes);
		System.out.println(new String(decode));
		System.out.println(bytes.length);
		
//		zipFile("C:\\Users\\Administrator\\Pictures\\5863302ee95a6.jpg", "E:\\apk\\aa.zip");
		unZipFile("E:\\apk\\aa.zip", "E:\\apk\\test.jpg");
	}
	
	/**
	 * 解压文件
	 * @param zipFile
	 * @param deskFile
	 */
	public static void unZipFile(String zipFile, String deskFile) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		ObjectInputStream ois = null;
		
		try {
			fis = new FileInputStream(zipFile);
			ois = new ObjectInputStream(fis);
			byte[] heffumanBytes = (byte[]) ois.readObject();
			Map<Byte, String> heffumanCodes = (Map<Byte, String>) ois.readObject();
			//解码
			byte[] decode = decode(heffumanBytes, heffumanCodes);
			fos = new FileOutputStream(deskFile);
			fos.write(decode);
			fos.flush();
			fos.close();
			ois.close();
			fis.close();
			System.out.println("解压成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 文件压缩
	 * @param srcFile
	 * @param deskFile
	 */
	public static void zipFile(String srcFile, String deskFile) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fis = new FileInputStream(srcFile);
			byte[] bytes = new byte[fis.available()];
			fis.read(bytes);
			byte[] heffumanZip = heffumanZip(bytes);
			fos = new FileOutputStream(deskFile);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(heffumanZip);
			Map<Byte, String> heffumanCodes = heffumanCodes(heffumanZip);
			oos.writeObject(heffumanCodes);//写入赫夫曼编码参照表
			oos.flush();
			oos.close();
			fos.close();
			fis.close();
			System.out.println("压缩完成");
		} catch (Exception e) {
			
		}
	}
	
	
	/**
	 * 解码
	 * @param bytrStr
	 * @return
	 */
	public static byte[] decode(byte[] heffumanZip, Map<Byte, String> heffumanCode) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < heffumanZip.length; i++) {
			boolean flag = (i == heffumanZip.length - 1);
			String byteToString = byteToString(!flag, heffumanZip[i]);//最后一位不需要转码
			stringBuilder.append(byteToString);
		}
		HashMap<String, Byte> map = new HashMap<String, Byte>();
		for (Map.Entry<Byte, String> entry: heffumanCode.entrySet()) {
			map.put(entry.getValue(), entry.getKey());
		}
		List<Byte> list = new ArrayList<Byte>();
		for (int i = 0; i < stringBuilder.length(); ) {
			int index = 1;
			while (true) {
				String substring = stringBuilder.substring(i, i+index);
				if(map.containsKey(substring)) {
					Byte byte1 = map.get(substring);
					list.add(byte1);
					break;
				}else {
					index++;
				}
			}
			i = i + index;
		}
		byte[] bytes = new byte[list.size()];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = list.get(i);
		}
		return bytes;
	}
	
	
	/**
	 * 将byte转成二进制字符串
	 * @param flag true,需要转码， false 不需要
	 * @param b
	 * @return
	 */
	public static String byteToString(boolean flag, byte b) {
		int temp = b;
		if(flag) {
			temp |= 256;
		}
		String str = Integer.toBinaryString(temp);
		if(flag) {
			return str.substring(str.length() - 8);
		}
		return str;
	}
	
	
	/**
	 * 得到赫夫曼编码，解压用的参照表
	 * @param bytes
	 * @return
	 */
	public static Map<Byte, String> heffumanCodes(byte[] bytes) {
		List<CodeNode> nodes = getNodes(bytes); //赫夫曼节点
		CodeNode hffumanCode = createHffumanCode(nodes); //赫夫曼头结点
		Map<Byte, String> heffumanCodes = getHeffumanCodes(hffumanCode);
		return heffumanCodes;
	}
	
	
	/**
	 * 解压
	 * @param bytes
	 * @return
	 */
	public static byte[] heffumanZip(byte[] bytes) {
		List<CodeNode> nodes = getNodes(bytes); //赫夫曼节点
		CodeNode hffumanCode = createHffumanCode(nodes); //赫夫曼头结点
//		preOrder(hffumanCode);
		Map<Byte, String> heffumanCodes = getHeffumanCodes(hffumanCode);
		byte[] zip = zip(bytes, heffumanCodes);
		return zip;
	}
	
	/**
	 * 压缩赫夫曼编码
	 * @param bytes  传过来的字符串转的赫夫曼编码，是按照单词顺序的
	 * @param heffumanCode
	 * @return 返回  -83 -65这些
	 */
	public static byte[] zip(byte[] bytes, Map<Byte, String> heffumanCode) {
		StringBuilder stringBuilder = new StringBuilder();
		for (Byte code : bytes) {
			String heffuman = heffumanCode.get(code);
			stringBuilder.append(heffuman);
		}
		//获取长度
		int len = 0;
		if(stringBuilder.length() / 8 == 0) {
			len = stringBuilder.length() / 8;
		}else{
			len = stringBuilder.length() / 8 + 1;
		}
		byte[] heffumanBytes = new byte[len];
		
		//拆成8位
		int index = 0;
		for (int i = 0; i < stringBuilder.length(); i+=8) {
			String by;
			if(i+8 > stringBuilder.length()) {
				by = stringBuilder.substring(i);
			}else {
				by = stringBuilder.substring(i, i+8);
			}
			byte b = (byte)Integer.parseInt(by, 2);
			heffumanBytes[index] = b;
			index++;
		}
		return heffumanBytes;
	}
	
	//重载
	public static Map<Byte, String> getHeffumanCodes(CodeNode root){
		if(root == null) {
			return new HashMap<Byte, String>();
		}
		Map<Byte, String> heffumanCodes = getHeffumanCodes(root, "", new HashMap<Byte, String>(), new StringBuilder());
		return heffumanCodes;
	}
	
	/**
	 * 生成赫夫曼编码
	 * 树的左节点为0， 右节点为1
	 * @return
	 */
	public static Map<Byte, String> getHeffumanCodes(CodeNode codeNode, String code, Map<Byte, String> map, StringBuilder stringBuilder){
		if(codeNode != null) {
			StringBuilder stringBuilder2 = new StringBuilder(stringBuilder); //回溯的时候好用的
			stringBuilder2.append(code);
			if(codeNode.code == null) {
				//向左
				getHeffumanCodes(codeNode.left, "0", map, stringBuilder2);
				//向右
				getHeffumanCodes(codeNode.right, "1", map, stringBuilder2);
			}else {
				map.put(codeNode.code, stringBuilder2.toString());
			}
		}
		return map;
	}
	
	/**
	 * 创建赫夫曼编码树
	 * @return
	 */
	public static CodeNode createHffumanCode(List<CodeNode> nodes) {
		while (nodes.size() > 1) {
			Collections.sort(nodes);
			CodeNode left = nodes.get(0);
			CodeNode right = nodes.get(1);
			//因为code都在叶子结点，所以parent节点为null
			CodeNode parent = new CodeNode(null, left.weight + right.weight);
			parent.left = left;
			parent.right = right;
			nodes.remove(left);
			nodes.remove(right);
			nodes.add(parent);
		}
		return nodes.get(0);
	}
	
	/**
	 * 根据字节数组获取赫夫曼节点
	 * @param bytes
	 * @return
	 */
	public static List<CodeNode> getNodes(byte[] bytes){
		List<CodeNode> nodes = new ArrayList<CodeNode>();
		Map<Byte, Integer> contents = new HashMap<Byte, Integer>();
		for (Byte code : bytes) {
			Integer weight = contents.get(code);
			if(weight == null) {
				contents.put(code, 1);
			}else {
				contents.put(code, weight+1);
			}
		}
		for (Map.Entry<Byte, Integer> entry: contents.entrySet()) {
			nodes.add(new CodeNode(entry.getKey(), entry.getValue()));
		}
		return nodes;
	}
	
	//前序输出
	public static void preOrder(CodeNode root) {
		if(root == null) {
			return;
		}
		System.out.println(root);
		if(root.left != null) {
			preOrder(root.left);
		}
		if(root.right != null) {
			preOrder(root.right);
		}
	}
}


class CodeNode implements Comparable<CodeNode> {
	int weight; //权重，每个字符的个数
	Byte code; //字符
	CodeNode left;
	CodeNode right;
	
	public CodeNode(Byte code, int weight) {
		this.code = code;
		this.weight = weight;
	}

	@Override
	public int compareTo(CodeNode o) {
		return this.weight - o.weight;
	}

	@Override
	public String toString() {
		return "CodeNode [weight=" + weight + ", code=" + code + "]";
	}
	
	
}
