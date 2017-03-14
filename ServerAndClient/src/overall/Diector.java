package overall;

public class Diector {
	Builder builder;

	public void setBuilder(Builder builder) {
		this.builder = builder;
	}

	public void construct() {
		try {
			builder.buildarguments();
			builder.buildRmi();
			builder.buildGUI();
			builder.buildChatRoom();
			builder.buildFileTransfer();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
