package ex03;


import java.time.LocalDate;

//모든기능은 class에 구현한다.
//입력data
//사번,이름,입사일,월급,부서번호 
/*
100,사나,20110101,300.0,10      
200,모모,20120301,270.0,20      
300,정연,20091003,250.0,30      
400,나연,20110105,220.0,40      
500,미나,20180401,170.0,60      
600,쯔위,20150801,200.0,50      
 */

//입력 1줄 -> 결과 1줄
//입력data : 사번,이름,입사일,월급,부서번호
//		     num, name, hiredate, salary, deptno 
//출력     : 사번,이름,입사일,월급,보너스,수령액,부서명
//         num, name, hiredate, salary, bonus, pay, deptname

import java.util.Scanner;

interface Ipo {
	void input();
	void process();
	void output();
}

class Empvo {
	// Fields
	// 입력 : 사번,이름,입사일,월급,부서번호
	private int      num;
	private String   name;
	private String   hiredate;
	private Double   salary;
	private int      deptno;
	// 출력 : 보너스,수령액,부서명
	private Double   bonus;
	private Double   pay;
	private String   deptname;

	// Constructor
	public Empvo() {

	}
	public Empvo(int num, String name, String hiredate, Double salary, int deptno) {
		this.num = num;
		this.name = name;
		this.hiredate = hiredate;
		this.salary = salary;
		this.deptno = deptno;
	}

	// Getter Setter
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHiredate() {
		return hiredate;
	}
	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public Double getBonus() {
		return bonus;
	}
	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}
	public Double getPay() {
		return pay;
	}
	public void setPay(Double pay) {
		this.pay = pay;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	@Override
	public String toString() {
		return "Empvo [num=" + num + ", name=" + name + ", hiredate=" + hiredate + ", salary=" + salary + ", deptno="
				+ deptno + ", bonus=" + bonus + ", pay=" + pay + ", deptname=" + deptname + "]";
	}
}

class Emp implements Ipo{
	// Fields
	private  Empvo empvo;

	public void input() {
		Scanner   in      =    new Scanner(System.in);

		System.out.println("입력:사번,이름,입사일,월급,부서번호");
		String    line    =  in.nextLine();  
		String [] li      =  line.trim().split(",");

		int      num        =  Integer.parseInt(li[0].trim());
		String   name       =  li[1].trim();
		String   hiredate   =  li[2].trim();
		Double   salary     =  Double.parseDouble(li[3].trim());
		int      deptno     =  Integer.parseInt(li[4].trim());

		empvo = new Empvo(num, name, hiredate, salary, deptno);
	}

	//금액은 소수이하 두자리로 반올림
	public void process() {
		
		int  thisYear   = LocalDate.now().getYear();
		String hiredate = empvo.getHiredate();
		int  hireYear   = Integer.parseInt(hiredate.substring(0, 3+1));
		int  hireYears  = thisYear - hireYear;
		
		double   salary = empvo.getSalary();
		double    bonus = hireYears * salary * 0.005;
		double      pay = salary + bonus;
		String deptname = "";
		int deptno      = empvo.getDeptno();
		switch(deptno) {
		case 10: deptname = "인사"; break;
		case 20: deptname = "자재"; break;
		case 30: deptname = "총무"; break;
		case 40: deptname = "연구개발"; break;
		case 50: deptname = "생산"; break;
		case 60: deptname = "서비스"; break;
		}
		
		
		//보너스   =  근무연수에 따라 월급의 0.5% 로 계산한다
		empvo.setBonus(bonus);
		//수령액   =  월급 + 보너스 
		empvo.setPay(pay);
		//부서명   =  10:인사,20:자재,30:총무,40:연구개발,50:생산,60:서비스
		empvo.setDeptname(deptname);
	}
	public void output() {
		
		int    num         = empvo.getNum();
		String name        = empvo.getName();
		String hiredate    = empvo.getHiredate();
		double salary      = empvo.getSalary();
		double bonus       = empvo.getBonus();
		double pay         = empvo.getPay();
		String deptname    = empvo.getDeptname();
		
		
		System.out.println("사번 이름 입사일 월급 보너스 수령액 부서명");
		System.out.printf("%d  %s   %s  %.2f %.2f  %.2f %s",
				num, name, hiredate, salary, bonus, pay, deptname);

	}
}

public class TestEmp {

	public static void main(String[] args) {
		Emp emp = new Emp();
		emp.input();
		emp.process();
		emp.output(); 
 

	}

}



