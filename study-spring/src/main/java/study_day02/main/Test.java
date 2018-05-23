package study_day02.main;

/**   
* @Description 
* @author xiaoding
* @date 2018年3月22日 下午6:27:26   
*/
public class Test {

	
	public static void main(String[] args) {
		int i = 0 ;
		for(foo('A');foo('B')&&(i<2);foo('C')) {
			i++;
			foo('D');
		}
	}
	
	public static boolean foo(char c) {
		System.out.println(c);
		return true;
	}
}
