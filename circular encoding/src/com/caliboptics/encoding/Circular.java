package com.caliboptics.encoding;

import java.util.*;

public class Circular {
	private Set<String> encodings;
	private Set<String> rawEncodings;
	public Circular(){
		rawEncodings = new HashSet<String>();
		encodings = new HashSet<String>();
	}
	
	private Set<String> appendToSet(Set<String> set, char c){
		Set<String> output = new HashSet<String>();
		for(String s:set){
			output.add(s+c);
		}
		return output;
	}
	
	private String leftShiftOneBit(String s){
		String output = s.substring(1) + s.charAt(0);
		return output;
	}
	
	public void encode(int bits){
		rawEncodings.clear();
		encodings.clear();
		int i;
		String empty = "";
		rawEncodings.add(empty);
		for(i=0;i<bits;i++){
			Set<String> newSet = new HashSet<String>();
			newSet.addAll(appendToSet(rawEncodings, '0'));
			newSet.addAll(appendToSet(rawEncodings, '1'));
			rawEncodings = newSet;
		}
		
		//System.out.println(rawEncodings.size());
		for(String code: rawEncodings){
			String dup = new String(code);
			for(i = 0;i<bits-1;i++){
				dup = leftShiftOneBit(dup);
				if(encodings.contains(dup)){
					break;
				}
				
			}
			if(i ==bits-1){
				encodings.add(code);
			}
		}
		
		System.out.println(bits + "位可以做出" + encodings.size()+"个不同编码");
		System.out.println("是否输出编码结果？Y/N");
		Scanner scan = new Scanner(System.in);
		String x = scan.nextLine();
		if(x.equals("Y") || x.equals("y")){
			for(String s:encodings){
				System.out.println(s);
			}
			System.out.println(" ");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		while(true){
			System.out.println("输入编码位数：");
			Scanner scan = new Scanner(System.in);
			int x = scan.nextInt();
			Circular cir = new Circular();
		
			cir.encode(x);
		}
	}

}
