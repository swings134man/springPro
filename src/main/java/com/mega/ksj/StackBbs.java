package com.mega.ksj;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import org.apache.jasper.tagplugins.jstl.core.If;

import com.mega.ksj.DTO.BbsDTO;

public class StackBbs {
	
	//stack 객체
	Stack<BbsDTO> stack = new Stack<BbsDTO>();
	// 오름차순 정렬 배열 : x
	// push 할 List : a
	// stack은 : stack
	
	//배열에 id 값 set
	public Stack<BbsDTO> StackBbs(List<BbsDTO> list) { // 호출될 메서드.
		int[] x = new int[list.size()];			// id 값만 정렬할 배열.
		
		for (int i = 0; i < list.size(); i++) {	// 
			x[i] = list.get(i).getPostid(); 	// 배열에 postid(int) 넣음.
		}//for
		
		
		bubble_sort(x, x.length); //버블정렬-> ID의 값(int)을 내림차순(desc)으로 정렬.
		
		for (int i = 0; i < x.length ; i++) { // 배열 x 의 값을 print 
			System.out.println(x[i]);
		}
		System.out.println("@@@@@@@@@아래부터 결과@@@@@@@@@@");
		
		// stack push
		for (int i = 0; i < x.length; i++) {
			for (BbsDTO dto : list) {
				if (dto.getPostid() == x[i]) {	// DTO내의 ID 값과, 정렬된 배열x(ID값 정렬) 비교 후 push.
					stack.push(dto);			// 낮은 id순서대로 push.
				}//if							// 제일 큰 id가 맨위 포지션.
			}//foreach
		}//for
		System.out.println(stack); // stack에 들어갔는지 확인.
		return stack;	//service로 게시물 id로 정렬된 DTO LIST return.
	}//메서드
	
	//----------------------------------버블정렬---------------------------
	private void bubble_sort(int[] a, int size) {
		
		for (int i = 1; i < size; i++) { // i 값이 0부터 시작하면 indexOutOfBounds!
			for (int j = 0; j < size - i; j++) { // 배열크기의 -1만큼 반복됨.
				if (a[j] < a[j + 1]) {	//앞 index와 바로뒤의 index 비교.
					swap(a, j, j + 1); // 값의 자리변경.
				} //if
			}//for j
		}//for i
	}// 버블정렬 내림차순(desc) -> 큰숫자가 첫번째 index.
	
	private static void swap(int[] a, int i, int j) { // 값의 자리변경 메서드.
		int num = a[i];
		a[i] = a[j];
		a[j] = num;
	}//swap
	
	
}//class
