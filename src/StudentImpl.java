
public class StudentImpl implements Student {
	String name;
	double grade = 0;
	double gradeDenominator = 0;

	public StudentImpl(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public double getGrade() {
		if (gradeDenominator == 0) {
			return 0;
		} else {
			return (grade / gradeDenominator);
		}
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGrade(double grade, double gradeDenominator) {
		this.grade += grade;
		this.gradeDenominator += gradeDenominator;
	}

}
