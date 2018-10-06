package 자바수행;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeSet;

// 한 명으로 시작해  2018년부터 11월 ~ 2019년 3월까지, 24명을 모두 무사히 졸업시키면 게임이 끝난다.
// 한 명이라도 자퇴하면 게임이 끝난다.

// 학생: 행복지수, 체력, 배고픔, 공부능력 
//50인 캐릭터를 100으로 만들면 관리가 필요없는 학생이 된다.

// 랜덤 이벤트가 있다. (기념일)
// 5번 수업 시 1달이 지나간다.

// 선택 - 매점, 교실
// 게임은 수업을 통해 진행된다. (취업, 일본, 직생, 영어, 네보, 문학, 정통, 자율, 미적, 서버, 자바, 운동, 알고)

// 증가
// 행복지수: 수업시간에 잠 10 증가
// 체력: 수업시간에 잠 20 증가
// 배고픔: 수업시간 중간에 매점  5증가 
// 공부능력: 수업시간에 대답 10증가

// 감소
// 행복지수: 수업시간에 안잠 5감소
// 체력: 수업시간에 안잠 15감소
// 배고픔: 매점을 안가면 10 감소
// 공부능력: 수업시간에 잠 20 감소 

// 캐릭터의 상태는 매시간 보여준다.
// 한 수업 당 캐릭터 1개만 움직일 수 있다.

// 상태가 20이하일 때 이벤트 발생

// ------------------------------------------------------------------
// Description : 학생 선택 
// ------------------------------------------------------------------
class Choice {
	public static String student_choice() {
		String stu_name;
        
		@SuppressWarnings("resource")
		Scanner student_name_scan = new Scanner(System.in);    
		
        stu_name = student_name_scan.nextLine();      

        return stu_name;
	}
}

// ------------------------------------------------------------------
// Description : 학생관련 정보 (이름, 행복지수, 체력, 배고픔, 공부능력, 자퇴)
// ------------------------------------------------------------------
class Student {
	
	String name;
	private int happy_num = 50;
	private int hearth_num = 50;
	private int hungry_num = 50; 
	private int exam_num = 50;
	private boolean isDead = false;
	private final String[] event = {"갑자기 태풍이 심해져, 지금 당장 하교한다는 말을 들었다.", "강천이가 자신의 조이스틱을 선물했다.", "원상이가 선물로 휴지를 주었다."};

	public Student(String name) {
		this.name = name;
	}
	

	
	// ------------------------------------------------------------------
	// Description : 랜덤으로 학생별로 별도의 이벤트 발생 (대청소 날이여서 집에 감, 마마무 공연 당첨) - 공통으로 바뀌어야 하는 상태 변경 (행복지수, 체력, 배고픔, 공부능력)
	// ------------------------------------------------------------------		
	public void updateEventState() {
	
		Random r = new Random();

		int randomHappyNum = r.nextInt(200) + -30;
		int randomHealthNum = r.nextInt(200) + -30;
		int randomHungryNum = r.nextInt(200) + -30; 
		int randomExamhNum = r.nextInt(200) + -30;
		
		this.setHappyNum(this.getHappyNum() + randomHappyNum);
		this.setHealthNum(this.getHealthNum() + randomHealthNum);
		this.setHungryNum(this.getHungryNum() + randomHungryNum);
		this.setExamNum(this.getExamNum() + randomExamhNum);		
		
		if(this.happy_num <= 0 || this.hearth_num <= 0 || this.hungry_num <= 0 || this.exam_num <= 0) {
			this.isDead = true;
		}
	}
	
	public void updateEven() {
		Random r = new Random();
		int randomNum = r.nextInt(this.event.length);
		
		System.out.println("이벤트 발생!!");
		System.out.println(event[randomNum]);
		
		updateEventState();
	}

	// ------------------------------------------------------------------
	// Description : 진행 이벤트
	// ------------------------------------------------------------------		
	public void updateState(int randomHappyNum, int randomHealthNum, int randomHungryNum, int randomExamhNum) {
	
			this.setHappyNum(this.getHappyNum() + randomHappyNum);
			this.setHealthNum(this.getHealthNum() + randomHealthNum);
			this.setHungryNum(this.getHungryNum() + randomHungryNum);
			this.setExamNum(this.getExamNum() + randomExamhNum);		
			
			if(this.happy_num <= 0 || this.hearth_num <= 0 || this.hungry_num <= 0 || this.exam_num <= 0) {
				updateEven();
				   System.out.println("<" + this.name + " 학생의 상태>");
				   System.out.println("행복" + this.happy_num);
				   System.out.println("건강" +  this.hearth_num);
				   System.out.println("배고픔" + this.hungry_num);
				   System.out.println("시험" + this.exam_num);
				   
				
				if(this.happy_num <= 0 || this.hearth_num <= 0 || this.hungry_num <= 0 || this.exam_num <= 0) {
					this.isDead = true;
				}
			}
		}

	// 수업시간에 잠 (증가: 행복지수, 체력 / 감소: 배고픔, 공부능력)
	public void updateSleepState() {
		
		Random r = new Random();

		int randomHappyNum = r.nextInt(40) + 1;  // 0 ~ 40
		int randomHealthNum = r.nextInt(50) + 1; // 0 ~ 50
		
		int randomHungryNum = r.nextInt(6) + -5; // 0 ~ -5
		int randomExamhNum = r.nextInt(6) + -5; // 0 ~ -5
		
		updateState(randomHappyNum, randomHealthNum, randomHungryNum, randomExamhNum);
		
	}

	// 수업시간에 대답 (증가: 공부능력 / 감소: 행복지수, 체력, 배고픔)
	public void updateAmentState() {

		Random r = new Random();

		int randomExamhNum = r.nextInt(50) + 1; // 0 ~ 50
		
		int randomHappyNum = r.nextInt(11) + -5; // 0 ~ -5
		int randomHealthNum = r.nextInt(6) + - 5;  // 0 ~ -5
		int randomHungryNum = r.nextInt(6) + - 5; // 0 ~ -5 
		
		updateState(randomHappyNum, randomHealthNum, randomHungryNum, randomExamhNum);
	}
	
	// 수업시간에 매접 (증가: 행복, 배고픔 / 감소: 체력, 공부능력)
	public void updateStoreState() {
		
		Random r = new Random();

		int randomHappyNum = r.nextInt(40) + 1; // 0 ~ 40
		int randomHungryNum = r.nextInt(50) + 1; //  0 ~ 50
		
		int randomHealthNum = r.nextInt(6) + -5; //  0 ~ -5
		int randomExamhNum = r.nextInt(6) + -5; // 0 ~ -5
		
		updateState(randomHappyNum, randomHealthNum, randomHungryNum, randomExamhNum);
		
	}

	// ------------------------------------------------------------------
	// Description : 가져오기 (행복지수, 체력, 배고픔, 공부능력, 자퇴)
	// ------------------------------------------------------------------		
	// 가져오기 - 행복지수
	public int getHappyNum() {
		return this.happy_num;
	}
	
	// 가져오기 - 체력
	public int getHealthNum() {
		return this.hearth_num;
	}
	
	// 가져오기 - 배고픔
	public int getHungryNum() {
		return this.hungry_num;
	}
	
	// 가져오기 - 공부능력
	public int getExamNum() {
		return this.exam_num;
	}

	// 가져오기 - 자퇴
	public boolean getIsDead() {
		return this.isDead;
	}
	
	// ------------------------------------------------------------------
	// Description : 변경하기 (행복지수, 체력, 배고픔, 공부능력)
	// ------------------------------------------------------------------		
	// 번경하기 - 행복지수
	public void setHappyNum(int number) {
		if(number >= 100) {
			System.out.println("너무 행복해요♥");
			this.happy_num = 100;
			
		}else if(number <= 20){
			System.out.println("행복지수가 낮아 극도의 불안감을 느끼고 있다.");
			this.happy_num = number;
			
		}else {
			this.happy_num = number;
		}
	}
	
	// 번경하기 - 체력
	public void setHealthNum(int number) {
		if(number >= 100) {
			System.out.println("매~우 건강하다.");
			this.hearth_num = 100;
			
		}else if(number <= 20){
			System.out.println("건강이 좋지 않아, 무단으로 교내 엘레베이터를 타고 다니기 시작하였다.");
			this.hearth_num = number;
			
		}else {
			this.hearth_num = number;
		}
	}

	// 번경하기 - 배고픔
	public void setHungryNum(int number) {
		if(number >= 100) {
			System.out.println("심각하게 배부르다.");
			this.hungry_num = 100;	
			
		}else if(number <= 20){
			System.out.println("당장 매점으로 뛰어가야한다.");
			this.hungry_num = number;
	
		}else {
			this.hungry_num = number;
		}
	}

	// 번경하기 - 공부능력
	public void setExamNum(int number) {
		if(number >= 100) {
			System.out.println("공부능력이 만렙이 되었다. 유지해야 한다.");
			this.exam_num = 100;

		}else if(number <= 20){
			System.out.println("위험하다. 공부능력이 바닥이다.");
			this.exam_num = number;
			
		}else {
			this.exam_num = number;
		}
	}
}

// ------------------------------------------------------------------
// Description : 메인 클래스
// ------------------------------------------------------------------		
public class GrowUp {

	public static void main(String[] args) {
		Map<String, Student> students = new HashMap<>(); //모든 학생 딕셔너리
		TreeSet<String> students_choice = new TreeSet<>(); // 선택된 학생 이름 리스트 
		
		// 과목 배열
		String[] lschedules = {"취업의 권순경쌤", "일본어의 천현철쌤", "직생의 박은산쌤", "영어의 이훈쌤", "네보의 연정흠쌤", "문학의 최윤지쌤", "정통의 강지혜쌤", "미적분의 김동명쌤", "서버의 이승호쌤", "자바의 이소영쌤", "운동의 최은석[김소은]쌤", "알고의 김상현쌤"};
		// 1반 24명의 학생들
		String[] student_name = {"강재관", "김강천", "김민혁"};
		
		String student_choice_val;
		int lschedules_name;
		
		// 시작 메시지
		System.out.println("1반 키우기 !!!");
		
		// { 학생이름 : 학생객체 } 저장
		for(String name : student_name) {
			students.put(name, new Student(name));
		}
		
		// 게임 루프 (끝나는 조건 - 한 명이라도 전학)
		while(true) {

			// 학생 3명을 유지
			while(students_choice.size() <= 2) {

				// 학생 고르기
				System.out.println("현재 우리반 학생 명단");
				for(String name : student_name) {
					System.out.print(name + " 학생 / ");
				}
				System.out.println("");
				
				System.out.println("학생을 골라라: ");
				student_choice_val = Choice.student_choice(); // 예) return값: "강제관" 
				
				if(Arrays.asList(student_name).contains(student_choice_val)) {
					// 추가
					students_choice.add(student_choice_val);
				}else {
					System.out.println("이상한 값을 입력하지마세요.");
					continue;
				}
				
				// 현재 학생 선택 상황
				int i = 1;
				System.out.println("현재 키우고 있는, 학생의 이름명단이다.");
				for(String name : students_choice) {
					System.out.println(i + "번: " + name + " 학생 ");
					i = i + 1;
				}
				System.out.println("");
			}
			
			// 이번 시간표
			Random r = new Random();
			int randomNum = r.nextInt(10) + 1;  // 과목 12개
			
			System.out.println("이번시간은 " + lschedules[randomNum] + " 시간이다.");
			
			// 원하는 학생 선택 (수업시간에 잠, 발표, 매점)
			System.out.println("이번 시간에 플레이할 학생을 골라라: ");
			student_choice_val = Choice.student_choice();
	
			//이상한 값 입력 
			while(true) {
				try {
					@SuppressWarnings("resource")
					Scanner lschedules_scan = new Scanner(System.in);    
					System.out.println("1.수업시간에 잠자기 / 2.수업시간에 대답하기 / 3.수업시간에 매점가기");
					lschedules_name = lschedules_scan.nextInt();    
		
					// 원하는 학생의 행동에 따라 상태 값 변경 
					   switch (lschedules_name) {
		            case 1: students.get(student_choice_val).updateSleepState();
		            	break;
		            case 2: students.get(student_choice_val).updateAmentState();
		                break;
		            case 3: students.get(student_choice_val).updateStoreState();
		                break;
		            default: System.out.println("이상한 값을 입력하지마세요.");
		                break;
				   }
				   
				   // 현재 캐릭터 상태
				   System.out.println(students.get(student_choice_val).getIsDead());
				   if(students.get(student_choice_val).getIsDead()) {
					   System.out.println(student_choice_val + ": 선생님 저 자퇴하려고요...");
					   System.out.println("게임 종료");
					   break;
				   }
				   
				   System.out.println("<" + student_choice_val + " 학생의 상태>");
				   System.out.println("행복" + students.get(student_choice_val).getHappyNum());
				   System.out.println("건강" + students.get(student_choice_val).getHealthNum());
				   System.out.println("배고픔" + students.get(student_choice_val).getHungryNum());
				   System.out.println("시험" + students.get(student_choice_val).getExamNum());
				   
				}catch(NullPointerException ne){
					System.out.println("이상한 값을 입력하지마세요.");
					continue;
				}catch(InputMismatchException ne) {
					System.out.println("이상한 값을 입력하지마세요.");
					continue;
				}
			}
		}
	}

}
