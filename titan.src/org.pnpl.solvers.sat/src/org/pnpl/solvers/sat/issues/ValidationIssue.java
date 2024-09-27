package org.pnpl.solvers.sat.issues;

public class ValidationIssue {
	private IssueLevel level;
	private String issue;
	private boolean isStopping = false;
	
	public ValidationIssue(String issue, IssueLevel level) {
		this.issue = issue;
		this.level = level;
	}
	
	public ValidationIssue(String issue, IssueLevel level, boolean isStopping) {
		this.issue = issue;
		this.level = level;
		this.isStopping = isStopping;
	}
	
	public boolean isStopping() {
		return this.isStopping;
	}
	
	public IssueLevel getLevel() {
		return level;
	}

	public String getIssue() {
		return issue;
	}

	@Override
	public String toString() {
		return this.issue;
	}
	
	@Override 
	public boolean equals(Object o) {
		if (o==this) return true;
		if (!(o instanceof ValidationIssue)) return false;
		ValidationIssue vi = (ValidationIssue)o;
		return vi.issue.equals(this.issue);
	}
	
	@Override
	public int hashCode() {
		return this.issue.hashCode();
	}
}
