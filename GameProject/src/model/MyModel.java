package model;

import java.util.ArrayList;
import java.util.Observable;

import model.algorithems.Action;
import model.algorithems.SearchDomain;
import model.algorithems.Searcher;
import Task.Task;




public class MyModel extends Observable implements Model,Task 
{
	private SearchDomain searchDomain;
	private Searcher searcher;
	
	private SearcherFactory searcherFactory;
	private Solution solution;
	private SolutionManager solutionManager;
	private DomainFactory domainFactory;
	
	public MyModel()
	{
		searcherFactory = new SearcherFactory();
		solutionManager = SolutionManager.getInstance();
		domainFactory = new DomainFactory();
		
	}
	
	public void selectDomain(String args) {
		
		String[] arr = args.split(":");
		String domainName = arr[0];
		String domainArgs = arr[1];
		
		searchDomain = domainFactory.CreateDomain(domainName);
	}

	public void selectAlgorithem(String algorithemName) {
		// TODO Auto-generated method stub
		System.out.println("Done!!!");
		 searcher = searcherFactory.createAlgorithem(algorithemName);
	}
	
	public void solveDomain() {	
		System.out.println("Now I'm Solving this domain");
		String problemDescription = searchDomain.getProblemDescription();
		
		this.solution = solutionManager.getSolution(problemDescription);
		
		if (solution == null) {	
			System.out.println("Creating a new solution");
			ArrayList<Action> actions = searcher.search(searchDomain);
			
			solution = new Solution();
			solution.setActions(actions);
			solutionManager.addSolution(solution);
		}
		
		this.setChanged();
		this.notifyObservers();
	}

	public Solution getSolution() {
		
		return solution;
	}
	
	

	public void doTask() {
		
		solveDomain();
	}

	public void selectalgorithm(String algorithemName) {
		// TODO Auto-generated method stub
		Searcher searcher = searcherFactory.createAlgorithem(algorithemName);
		
	}

	

}
