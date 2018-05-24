package deployment;

public interface Observer {

	public void update();
	// no need for a payload

	public void setObservable(Observable subj);
	// code to hook observer to subject

	/*
	 * observable contains observer. Observer <- Observable (observable links
	 * observers through subjects). Be careful registering subjects to observables,
	 * otherwise programm might crash!
	 * 
	 */
}