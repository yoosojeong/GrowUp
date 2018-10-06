package �ڹټ���;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeSet;

// �� ������ ������  2018����� 11�� ~ 2019�� 3������, 24���� ��� ������ ������Ű�� ������ ������.
// �� ���̶� �����ϸ� ������ ������.

// �л�: �ູ����, ü��, �����, ���δɷ� 
//50�� ĳ���͸� 100���� ����� ������ �ʿ���� �л��� �ȴ�.

// ���� �̺�Ʈ�� �ִ�. (�����)
// 5�� ���� �� 1���� ��������.

// ���� - ����, ����
// ������ ������ ���� ����ȴ�. (���, �Ϻ�, ����, ����, �׺�, ����, ����, ����, ����, ����, �ڹ�, �, �˰�)

// ����
// �ູ����: �����ð��� �� 10 ����
// ü��: �����ð��� �� 20 ����
// �����: �����ð� �߰��� ����  5���� 
// ���δɷ�: �����ð��� ��� 10����

// ����
// �ູ����: �����ð��� ���� 5����
// ü��: �����ð��� ���� 15����
// �����: ������ �Ȱ��� 10 ����
// ���δɷ�: �����ð��� �� 20 ���� 

// ĳ������ ���´� �Žð� �����ش�.
// �� ���� �� ĳ���� 1���� ������ �� �ִ�.

// ���°� 20������ �� �̺�Ʈ �߻�

// ------------------------------------------------------------------
// Description : �л� ���� 
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
// Description : �л����� ���� (�̸�, �ູ����, ü��, �����, ���δɷ�, ����)
// ------------------------------------------------------------------
class Student {
	
	String name;
	private int happy_num = 50;
	private int hearth_num = 50;
	private int hungry_num = 50; 
	private int exam_num = 50;
	private boolean isDead = false;
	private final String[] event = {"���ڱ� ��ǳ�� ������, ���� ���� �ϱ��Ѵٴ� ���� �����.", "��õ�̰� �ڽ��� ���̽�ƽ�� �����ߴ�.", "�����̰� ������ ������ �־���."};

	public Student(String name) {
		this.name = name;
	}
	

	
	// ------------------------------------------------------------------
	// Description : �������� �л����� ������ �̺�Ʈ �߻� (��û�� ���̿��� ���� ��, ������ ���� ��÷) - �������� �ٲ��� �ϴ� ���� ���� (�ູ����, ü��, �����, ���δɷ�)
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
		
		System.out.println("�̺�Ʈ �߻�!!");
		System.out.println(event[randomNum]);
		
		updateEventState();
	}

	// ------------------------------------------------------------------
	// Description : ���� �̺�Ʈ
	// ------------------------------------------------------------------		
	public void updateState(int randomHappyNum, int randomHealthNum, int randomHungryNum, int randomExamhNum) {
	
			this.setHappyNum(this.getHappyNum() + randomHappyNum);
			this.setHealthNum(this.getHealthNum() + randomHealthNum);
			this.setHungryNum(this.getHungryNum() + randomHungryNum);
			this.setExamNum(this.getExamNum() + randomExamhNum);		
			
			if(this.happy_num <= 0 || this.hearth_num <= 0 || this.hungry_num <= 0 || this.exam_num <= 0) {
				updateEven();
				   System.out.println("<" + this.name + " �л��� ����>");
				   System.out.println("�ູ" + this.happy_num);
				   System.out.println("�ǰ�" +  this.hearth_num);
				   System.out.println("�����" + this.hungry_num);
				   System.out.println("����" + this.exam_num);
				   
				
				if(this.happy_num <= 0 || this.hearth_num <= 0 || this.hungry_num <= 0 || this.exam_num <= 0) {
					this.isDead = true;
				}
			}
		}

	// �����ð��� �� (����: �ູ����, ü�� / ����: �����, ���δɷ�)
	public void updateSleepState() {
		
		Random r = new Random();

		int randomHappyNum = r.nextInt(40) + 1;  // 0 ~ 40
		int randomHealthNum = r.nextInt(50) + 1; // 0 ~ 50
		
		int randomHungryNum = r.nextInt(6) + -5; // 0 ~ -5
		int randomExamhNum = r.nextInt(6) + -5; // 0 ~ -5
		
		updateState(randomHappyNum, randomHealthNum, randomHungryNum, randomExamhNum);
		
	}

	// �����ð��� ��� (����: ���δɷ� / ����: �ູ����, ü��, �����)
	public void updateAmentState() {

		Random r = new Random();

		int randomExamhNum = r.nextInt(50) + 1; // 0 ~ 50
		
		int randomHappyNum = r.nextInt(11) + -5; // 0 ~ -5
		int randomHealthNum = r.nextInt(6) + - 5;  // 0 ~ -5
		int randomHungryNum = r.nextInt(6) + - 5; // 0 ~ -5 
		
		updateState(randomHappyNum, randomHealthNum, randomHungryNum, randomExamhNum);
	}
	
	// �����ð��� ���� (����: �ູ, ����� / ����: ü��, ���δɷ�)
	public void updateStoreState() {
		
		Random r = new Random();

		int randomHappyNum = r.nextInt(40) + 1; // 0 ~ 40
		int randomHungryNum = r.nextInt(50) + 1; //  0 ~ 50
		
		int randomHealthNum = r.nextInt(6) + -5; //  0 ~ -5
		int randomExamhNum = r.nextInt(6) + -5; // 0 ~ -5
		
		updateState(randomHappyNum, randomHealthNum, randomHungryNum, randomExamhNum);
		
	}

	// ------------------------------------------------------------------
	// Description : �������� (�ູ����, ü��, �����, ���δɷ�, ����)
	// ------------------------------------------------------------------		
	// �������� - �ູ����
	public int getHappyNum() {
		return this.happy_num;
	}
	
	// �������� - ü��
	public int getHealthNum() {
		return this.hearth_num;
	}
	
	// �������� - �����
	public int getHungryNum() {
		return this.hungry_num;
	}
	
	// �������� - ���δɷ�
	public int getExamNum() {
		return this.exam_num;
	}

	// �������� - ����
	public boolean getIsDead() {
		return this.isDead;
	}
	
	// ------------------------------------------------------------------
	// Description : �����ϱ� (�ູ����, ü��, �����, ���δɷ�)
	// ------------------------------------------------------------------		
	// �����ϱ� - �ູ����
	public void setHappyNum(int number) {
		if(number >= 100) {
			System.out.println("�ʹ� �ູ�ؿ䢾");
			this.happy_num = 100;
			
		}else if(number <= 20){
			System.out.println("�ູ������ ���� �ص��� �ҾȰ��� ������ �ִ�.");
			this.happy_num = number;
			
		}else {
			this.happy_num = number;
		}
	}
	
	// �����ϱ� - ü��
	public void setHealthNum(int number) {
		if(number >= 100) {
			System.out.println("��~�� �ǰ��ϴ�.");
			this.hearth_num = 100;
			
		}else if(number <= 20){
			System.out.println("�ǰ��� ���� �ʾ�, �������� ���� ���������͸� Ÿ�� �ٴϱ� �����Ͽ���.");
			this.hearth_num = number;
			
		}else {
			this.hearth_num = number;
		}
	}

	// �����ϱ� - �����
	public void setHungryNum(int number) {
		if(number >= 100) {
			System.out.println("�ɰ��ϰ� ��θ���.");
			this.hungry_num = 100;	
			
		}else if(number <= 20){
			System.out.println("���� �������� �پ���Ѵ�.");
			this.hungry_num = number;
	
		}else {
			this.hungry_num = number;
		}
	}

	// �����ϱ� - ���δɷ�
	public void setExamNum(int number) {
		if(number >= 100) {
			System.out.println("���δɷ��� ������ �Ǿ���. �����ؾ� �Ѵ�.");
			this.exam_num = 100;

		}else if(number <= 20){
			System.out.println("�����ϴ�. ���δɷ��� �ٴ��̴�.");
			this.exam_num = number;
			
		}else {
			this.exam_num = number;
		}
	}
}

// ------------------------------------------------------------------
// Description : ���� Ŭ����
// ------------------------------------------------------------------		
public class GrowUp {

	public static void main(String[] args) {
		Map<String, Student> students = new HashMap<>(); //��� �л� ��ųʸ�
		TreeSet<String> students_choice = new TreeSet<>(); // ���õ� �л� �̸� ����Ʈ 
		
		// ���� �迭
		String[] lschedules = {"����� �Ǽ����", "�Ϻ����� õ��ö��", "������ �������", "������ ���ƽ�", "�׺��� �������", "������ ��������", "������ ��������", "�������� �赿���", "������ �̽�ȣ��", "�ڹ��� �̼ҿ���", "��� ������[�����]��", "�˰��� �������"};
		// 1�� 24���� �л���
		String[] student_name = {"�����", "�谭õ", "�����"};
		
		String student_choice_val;
		int lschedules_name;
		
		// ���� �޽���
		System.out.println("1�� Ű��� !!!");
		
		// { �л��̸� : �л���ü } ����
		for(String name : student_name) {
			students.put(name, new Student(name));
		}
		
		// ���� ���� (������ ���� - �� ���̶� ����)
		while(true) {

			// �л� 3���� ����
			while(students_choice.size() <= 2) {

				// �л� ����
				System.out.println("���� �츮�� �л� ���");
				for(String name : student_name) {
					System.out.print(name + " �л� / ");
				}
				System.out.println("");
				
				System.out.println("�л��� ����: ");
				student_choice_val = Choice.student_choice(); // ��) return��: "������" 
				
				if(Arrays.asList(student_name).contains(student_choice_val)) {
					// �߰�
					students_choice.add(student_choice_val);
				}else {
					System.out.println("�̻��� ���� �Է�����������.");
					continue;
				}
				
				// ���� �л� ���� ��Ȳ
				int i = 1;
				System.out.println("���� Ű��� �ִ�, �л��� �̸�����̴�.");
				for(String name : students_choice) {
					System.out.println(i + "��: " + name + " �л� ");
					i = i + 1;
				}
				System.out.println("");
			}
			
			// �̹� �ð�ǥ
			Random r = new Random();
			int randomNum = r.nextInt(10) + 1;  // ���� 12��
			
			System.out.println("�̹��ð��� " + lschedules[randomNum] + " �ð��̴�.");
			
			// ���ϴ� �л� ���� (�����ð��� ��, ��ǥ, ����)
			System.out.println("�̹� �ð��� �÷����� �л��� ����: ");
			student_choice_val = Choice.student_choice();
	
			//�̻��� �� �Է� 
			while(true) {
				try {
					@SuppressWarnings("resource")
					Scanner lschedules_scan = new Scanner(System.in);    
					System.out.println("1.�����ð��� ���ڱ� / 2.�����ð��� ����ϱ� / 3.�����ð��� ��������");
					lschedules_name = lschedules_scan.nextInt();    
		
					// ���ϴ� �л��� �ൿ�� ���� ���� �� ���� 
					   switch (lschedules_name) {
		            case 1: students.get(student_choice_val).updateSleepState();
		            	break;
		            case 2: students.get(student_choice_val).updateAmentState();
		                break;
		            case 3: students.get(student_choice_val).updateStoreState();
		                break;
		            default: System.out.println("�̻��� ���� �Է�����������.");
		                break;
				   }
				   
				   // ���� ĳ���� ����
				   System.out.println(students.get(student_choice_val).getIsDead());
				   if(students.get(student_choice_val).getIsDead()) {
					   System.out.println(student_choice_val + ": ������ �� �����Ϸ����...");
					   System.out.println("���� ����");
					   break;
				   }
				   
				   System.out.println("<" + student_choice_val + " �л��� ����>");
				   System.out.println("�ູ" + students.get(student_choice_val).getHappyNum());
				   System.out.println("�ǰ�" + students.get(student_choice_val).getHealthNum());
				   System.out.println("�����" + students.get(student_choice_val).getHungryNum());
				   System.out.println("����" + students.get(student_choice_val).getExamNum());
				   
				}catch(NullPointerException ne){
					System.out.println("�̻��� ���� �Է�����������.");
					continue;
				}catch(InputMismatchException ne) {
					System.out.println("�̻��� ���� �Է�����������.");
					continue;
				}
			}
		}
	}

}
