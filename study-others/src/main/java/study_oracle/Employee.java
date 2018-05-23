package study_oracle;

import java.math.BigDecimal;

/**   
* @Description 
* @author xiaoding
* @date 2018年4月1日 下午2:47:51   
*/
public class Employee {
	
	private BigDecimal empno;
	private String ename;
	private String job;
	private BigDecimal mgr;
	private String date;
	private BigDecimal sal;
	private String comm;
	private BigDecimal deptno;
	public Employee(BigDecimal empno, String ename, String job, BigDecimal mgr, String date, BigDecimal sal,
			String comm, BigDecimal deptno) {
		super();
		this.empno = empno;
		this.ename = ename;
		this.job = job;
		this.mgr = mgr;
		this.date = date;
		this.sal = sal;
		this.comm = comm;
		this.deptno = deptno;
	}
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Employee [empno=" + empno + ", ename=" + ename + ", job=" + job + ", mgr=" + mgr + ", date=" + date
				+ ", sal=" + sal + ", comm=" + comm + ", deptno=" + deptno + "]";
	}
	public BigDecimal getEmpno() {
		return empno;
	}
	public void setEmpno(BigDecimal empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public BigDecimal getMgr() {
		return mgr;
	}
	public void setMgr(BigDecimal mgr) {
		this.mgr = mgr;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public BigDecimal getSal() {
		return sal;
	}
	public void setSal(BigDecimal sal) {
		this.sal = sal;
	}
	public String getComm() {
		return comm;
	}
	public void setComm(String comm) {
		this.comm = comm;
	}
	public BigDecimal getDeptno() {
		return deptno;
	}
	public void setDeptno(BigDecimal deptno) {
		this.deptno = deptno;
	}
	
	
}
