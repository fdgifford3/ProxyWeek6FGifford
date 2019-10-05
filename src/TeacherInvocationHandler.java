import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TeacherInvocationHandler implements InvocationHandler {
	Student student;
	
	public TeacherInvocationHandler(Student student) {
		this.student = student;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException {
		try {
			if(method.getName().equals("getName")) {
				return method.invoke(student, args);
			} else if (method.getName().equals("getGrade")) {
				return method.invoke(student, args);
			} else if (method.getName().startsWith("set")) {
				return method.invoke(student, args);
			}
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

}
