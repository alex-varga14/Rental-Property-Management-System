package model;

public interface Subject 
{
	public void notifyAllObservers();
    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
}
