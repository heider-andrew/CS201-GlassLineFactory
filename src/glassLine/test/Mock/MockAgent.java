package glassLine.test.Mock;

public class MockAgent {
	private String name;

	public MockAgent(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return this.getClass().getName() + ": " + name;
	}

}