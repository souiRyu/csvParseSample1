package calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;
import java.math.BigInteger;

public class Stack_중위표기법변환2 {
    public static void main(String[] args) throws IOException{
        Calculator Cal1 = new Calculator();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        Scanner input = new Scanner(System.in);
        String userChoice = ""; 
        	 while(true)
             {
        		 System.out.print("식을 입력하세요 : ");
	             String infix = bf.readLine();
	             String postfix = Cal1.ConvertInfixToPostfix(infix);
	             System.out.println("후위표기식은 : "+ postfix);
	             System.out.println("계산결과 : "+ Cal1.EvaluPostfix(postfix));
	             System.out.println("계속하시겠습니까? (y/n)");
	             userChoice = input.next();
	             if (userChoice.equals("n"))
	             {
	            	 System.out.println("프로그램을 종료합니다. ");
		             	break;
	             }
             }
        }
        
    }

class Calculator{
    public static String ConvertInfixToPostfix(String infix){
        Stack Opstack = new Stack();
        String postfix= "";
        String [] Split = infix.split("");
        for(String one : Split){
            switch (one){
                case "0":
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":
                    postfix += one;
                    break;
                case "+":
                case "-":
                    if(Opstack.isEmpty()){
                        Opstack.push(one);
                        break;
                    }
                    else if("+".equalsIgnoreCase((String) Opstack.peek())||"-".equalsIgnoreCase((String) Opstack.peek())){
                        postfix += Opstack.pop();
                        Opstack.push(one);
                        break;
                    }
                    else if("*".equalsIgnoreCase((String) Opstack.peek())||"/".equalsIgnoreCase((String) Opstack.peek())){
                        while(!Opstack.isEmpty()){
                            if("(".equalsIgnoreCase((String) Opstack.peek()))
                                break;
                            postfix +=Opstack.pop();
                        }
                        Opstack.push(one);
                        break;
                    }
                    else
                        Opstack.push(one);
                        break;
                case "*":
                case "/":
                    if(Opstack.isEmpty()){
                        Opstack.push(one);
                        break;
                    }
                    else if("+".equalsIgnoreCase((String) Opstack.peek())||"-".equalsIgnoreCase((String) Opstack.peek())){
                        Opstack.push(one);
                        break;
                    }
                    else if("*".equalsIgnoreCase((String) Opstack.peek())||"/".equalsIgnoreCase((String) Opstack.peek())){
                        postfix+= Opstack.pop();
                        Opstack.push(one);
                        break;
                    }
                    else
                        Opstack.push(one);
                        break;
                case "(":
                    Opstack.push(one);
                    break;
                case ")":
                    while(!Opstack.isEmpty()){
                        if("(".equalsIgnoreCase((String) Opstack.peek())){
                            Opstack.pop();
                                break;
                        }
                            postfix += Opstack.pop();
                    }
                    break;
                default:
                    System.out.println("식이 맞지 않습니다. ");
                    return "에러";
            }
        }
        while(!Opstack.isEmpty())
                postfix+= Opstack.pop();
        return postfix;
    }
    
    public static int EvaluPostfix(String postfix){
        String [] Split = postfix.split("");
        Stack Numbers = new Stack();
        int temp1, temp2;
        for(String one : Split){   
            switch (one){
                case "0":
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":
                    int Intone = Integer.parseInt(one);
                    Numbers.push(Intone);
                    break;
                case "+":
                    temp2 = (int)Numbers.pop();
                    temp1 = (int)Numbers.pop();
                    Numbers.push(temp1+temp2);
                    break;
                case "-":
                    temp2 = (int)Numbers.pop();
                    temp1 = (int)Numbers.pop();
                    Numbers.push((int)temp1-temp2);
                    break;
                case "*":
                    temp2 = (int)Numbers.pop();
                    temp1 = (int)Numbers.pop();
                    Numbers.push((int)temp1*temp2);
                    break;
                case "/":
                    temp2 = (int)Numbers.pop();
                    temp1 = (int)Numbers.pop();
                    Numbers.push((int)temp1/temp2);
                    break;
                default:
                    System.out.println("계산 오류");
                    return -1;
            }
        } 
        return (int)Numbers.pop();
    }
}