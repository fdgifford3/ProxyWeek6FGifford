import java.lang.reflect.*;

public class NonOwnerInvocationHandler implements InvocationHandler {
	Student student;
	
	public NonOwnerInvocationHandler(Student student) {
		this.student = student;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException {
		try {
			if(method.getName().equals("getName")) {
				return method.invoke(student, args);
			} else if (method.getName().equals("getGrade")) {
				throw new IllegalAccessException();
			} else if (method.getName().startsWith("set")) {
				throw new IllegalAccessException();
			}
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

}
