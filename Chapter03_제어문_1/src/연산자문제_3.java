/*
	int num1, num2;   
   num1 = 10/3;
   // '몫' 출력   
   num2 = 10%3;
   // '나머지' 출력
   System.out.printf("      몫: %d \n", num1);  3   
   System.out.printf("나머지: %d \n", num2);  1

 
 */
public class 연산자문제_3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num1, num2;   
		num1 = 10/3; // 정수/정수=정수 3
		// '몫' 출력   
		num2 = 10%3; // 1
		// '나머지' 출력
		System.out.printf("   몫: %d \n", num1); //3 %d-정수출력
		System.out.printf("   몫: %f \n", (double)num1); //3.000000 %f-실수출력  
		System.out.printf("나머지: %d \n", num2); //1
	}

}
