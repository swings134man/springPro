package com.mega.ksj;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import org.apache.jasper.tagplugins.jstl.core.If;

import com.mega.ksj.DTO.BbsDTO;

public class StackBbs {

	private int num;
	
//	List<BbsDTO> dto2 = (List<BbsDTO>) new BbsDTO();
	
	Stack<BbsDTO> stack = new Stack<BbsDTO>();
	
	List<BbsDTO> a = new ArrayList<BbsDTO>();
	
	//배열에 id 값 set
	public Stack<BbsDTO> StackBbs(List<BbsDTO> list) {
		int[] x = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			x[i] = list.get(i).getPostid(); // 배열에 postid(int) 넣음.
		}//for
		
		// 오름차순 정렬 배열 : x
		// push 할 List : a
		// stack은 : stack 
		
		bubble_sort(x, x.length);
		for (int i = 0; i < x.length ; i++) {
			System.out.println(x[i]);
		}
		
		int min = x[0];
		int max = x.length;
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@");
		
		//선생님 push
		for (int i = 0; i < x.length; i++) {
			for (BbsDTO dto : list) {
				if (dto.getPostid() == x[i]) {
					stack.push(dto);
				}
			}//foreach
		}//for
		System.out.println(stack);
		return stack;
			
		//pop
//		for (int i = 0; i < max; i++) {
//			a.add(stack.pop());
//		}//for
		
	}//생성자


	
	//--------------------버블정렬---------------------------
	private void bubble_sort(int[] a, int size) {
		// 배열크기의 -1만큼 반복됨.
		for (int i = 1; i < size; i++) {
			
			for (int j = 0; j < size - i; j++) {
				
				if (a[j] < a[j + 1]) {
					swap(a, j, j + 1);
				}
				
			}//2nd for
			
		}//1 for
	}// 내부처리
	
	private static void swap(int[] a, int i, int j) {
		int num = a[i];
		a[i] = a[j];
		a[j] = num;
	}//swap
	
	
}//class
