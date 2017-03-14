package overall;

public abstract class Builder {
	End overall = new End();
	
	public abstract void buildGUI()throws Exception ;

	public abstract void buildChatRoom()throws Exception ;

	public abstract void buildFileTransfer()throws Exception ;

	public abstract void buildRmi()throws Exception ;
	
	public abstract void buildarguments()throws Exception ;

	End getResault() {
		return overall;
	}
}
