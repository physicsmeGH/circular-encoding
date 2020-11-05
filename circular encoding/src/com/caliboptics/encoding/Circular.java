package com.caliboptics.encoding;

import java.util.*;

public class Circular {
	private Set<String> encodings;
	private Set<String> rawEncodings;
	public Circular(){
		rawEncodings = new HashSet<String>();
		encodings = new HashSet<String>();
	}
	
	public static int encodingToBase10(String encoding) {
		int return_val = 0;
		int powOf2 = 1;
		for(int i = encoding.length()-1; i>=0; i--) {
			return_val += powOf2 * Character.getNumericValue(encoding.charAt(i));
			powOf2 *= 2;
		}
		return return_val;
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
			String min = new String(code);
			int firstOneIndex = -1;
			for(i = 0;i<bits;i++){
				
				dup = leftShiftOneBit(dup);
				int oneIndex = dup.indexOf('1');
				if(oneIndex >= firstOneIndex) {
					firstOneIndex = oneIndex;
					min = new String(dup);
				}
				if(encodings.contains(dup)){
					break;
				}
				
			}
			if(i ==bits){
				encodings.add(min);
			}
		}
		
		System.out.println(bits + "位可以做出" + encodings.size()+"个不同编码");
		System.out.println( encodings.size()+" encodings can be made with " +bits + " bits");
		System.out.println("是否输出编码结果？Y/N");
		System.out.println("output encoding result? Y/N");
		Scanner scan = new Scanner(System.in);
		String x = scan.nextLine();
		//scan.close();
		if(x.equals("Y") || x.equals("y")){
			for(String s:encodings){
				System.out.print(encodingToBase10(s));
				System.out.print(",");
				for(int index = 0; index <s.length(); index++) {
					System.out.print(s.charAt(index));
					System.out.print(",");
				}
				System.out.print("\n");
			}
			System.out.println(" ");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		while(true){
			System.out.println("输入编码位数：");
			System.out.println("input number of bits used:");
			
			int x = scan.nextInt();
			if(x<=0){
				break;
			}
			Circular cir = new Circular();
		
			cir.encode(x);
			
		}
		scan.close();
	}

}
