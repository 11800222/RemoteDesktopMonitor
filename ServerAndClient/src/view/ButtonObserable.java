package view;

import java.util.Observable;

public class ButtonObserable extends Observable {

	public void notifyObservers(Object arg)
	{
		this.setChanged();
		super.notifyObservers(arg);  //  argÎª°´Å¥
	}
}
