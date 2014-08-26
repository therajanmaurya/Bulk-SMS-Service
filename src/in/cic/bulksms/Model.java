package in.cic.bulksms;

public class Model {
	private String x;
	private String x1;
	private boolean x2;

	public Model() {
		// TODO Auto-generated constructor stub
	}

	public Model(String x, String x2) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.x1 = x2;
	}

	public String getx() {
		return x;
	}

	public String getx1() {
		return x1;
	}

	public boolean isSelected() {
		return x2;
	}

	public void setSelected(boolean selected) {
		this.x2 = selected;
	}

	public void setx(String selected) {
		this.x = selected;
	}

	public void setx1(String selected) {
		this.x1 = selected;
	}
}