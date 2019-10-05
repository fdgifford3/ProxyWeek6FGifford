/*
 * Frederick Gifford
 * CPSC-60000 Week 6 Proxy Pattern
 * There were several interesting variations of the proxy pattern, and I chose
 * A dynamic proxy to introduce access protection for a student or a teacher accessing a profile
 * 10.4.2019
 * Prof Nowak
 */

import java.lang.reflect.*;
import java.util.*;

public class ProxyWeek6FGifford {
	ArrayList<Student> studentList = new ArrayList<Student>();

	public static void main(String[] args) {
	
		System.out.println("Frederick Gifford\nCPSC-60000\nWeek 6 Proxy Pattern\n");
		
		ProxyWeek6FGifford test = new ProxyWeek6FGifford();
		test.drive();


	}
	
	public ProxyWeek6FGifford() {
		initializeRoster();
	}
	void initializeRoster() {
		Student studentFred = new StudentImpl("Fred Gifford");
		studentFred.setGrade(98, 100);
		studentFred.setGrade(96, 100);
		studentFred.setGrade(99, 100);
		studentFred.setGrade(95, 100);
		
		Student studentJoe = new StudentImpl("Joe Smith");
		studentJoe.setGrade(68, 100);
		studentJoe.setGrade(76, 100);
		studentJoe.setGrade(69, 100);
		studentJoe.setGrade(85, 100);
		
		studentList.add(studentFred);
		studentList.add(studentJoe);
	}
	
	public void drive() {
		Student fred = studentList.get(0);
		Student joe = studentList.get(1);
		
		//Test 1, create owner profile and try to getName(), getGrade(), and setGrade()
		Student ownerProxy = getOwnerProxy(fred);
		System.out.println("\nAttempting to access " + ownerProxy.getName() + "'s record with Owner's login.");
		System.out.println("Attempting to get " + ownerProxy.getName() + "'s grade as Owner.");
		
		try {
			System.out.println(ownerProxy.getName() + "'s grade is " + ownerProxy.getGrade() + ".");
		} catch(Exception e) {
			System.out.println("Cannot get " + ownerProxy.getName() + "'s grade as Owner.");
		}
		
		System.out.println("Attempting to set " + ownerProxy.getName() + "'s grade as Owner.");
		try {
			ownerProxy.setGrade(100, 100);
			System.out.println("Grade successfully changed. New grade for " + ownerProxy.getName() + " is now " + ownerProxy.getGrade() + ".");
		} catch(Exception e) {
			System.out.println("Cannot set " + ownerProxy.getName() + "'s grade as Owner.");
		}
		
		//Test 2, create non owner profile and try to getName(), getGrade(), and setGrade()
		Student nonOwnerProxy = getNonOwnerProxy(fred);
		System.out.println("\nAttempting to access " + nonOwnerProxy.getName() + "'s record with NonOwner's login.");
		System.out.println("Attempting to get " + nonOwnerProxy.getName() + "'s grade as NonOwner.");
		
		try {
			System.out.println(nonOwnerProxy.getName() + "'s grade is " + nonOwnerProxy.getGrade() + ".");
		} catch(Exception e) {
			System.out.println("Cannot get " + nonOwnerProxy.getName() + "'s grade as NonOwner.");
		}
		
		System.out.println("Attempting to set " + nonOwnerProxy.getName() + "'s grade as NonOwner.");
		try {
			nonOwnerProxy.setGrade(100, 100);
			System.out.println("Grade successfully changed. New grade for " + nonOwnerProxy.getName() + " is now " + nonOwnerProxy.getGrade() + ".");
		} catch(Exception e) {
			System.out.println("Cannot set " + nonOwnerProxy.getName() + "'s grade as NonOwner.");
		}
		
		//Test 3, create teacher profile and try to getName(), getGrade(), and setGrade()
		Student teacherProxy = getTeacherProxy(joe);
		System.out.println("\nAttempting to access " + teacherProxy.getName() + "'s record with Teacher's login.");
		System.out.println("Attempting to get " + teacherProxy.getName() + "'s grade as Teacher.");
		
		try {
			System.out.println(teacherProxy.getName() + "'s grade is " + teacherProxy.getGrade() + ".");
		} catch(Exception e) {
			System.out.println("Cannot get " + teacherProxy.getName() + "'s grade as Teacher.");
		}
		
		System.out.println("Attempting to set " + teacherProxy.getName() + "'s grade as Teacher.");
		try {
			teacherProxy.setGrade(100, 100);
			System.out.println("Grade successfully changed. New grade for " + teacherProxy.getName() + " is now " + teacherProxy.getGrade() + ".");
		} catch(Exception e) {
			System.out.println("Cannot set " + teacherProxy.getName() + "'s grade as Teacher.");
		}
		
		
	}
	
	Student getOwnerProxy(Student student) {
 		
        return (Student) Proxy.newProxyInstance( 
            	student.getClass().getClassLoader(),
            	student.getClass().getInterfaces(),
                new OwnerInvocationHandler(student));
	}

	Student getNonOwnerProxy(Student student) {
		
        return (Student) Proxy.newProxyInstance( 
            	student.getClass().getClassLoader(),
            	student.getClass().getInterfaces(),
                new NonOwnerInvocationHandler(student));
	}

	Student getTeacherProxy(Student student) {
		
        return (Student) Proxy.newProxyInstance( 
            	student.getClass().getClassLoader(),
            	student.getClass().getInterfaces(),
                new TeacherInvocationHandler(student));
	}
	
}
