package study_bolue_bridge;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import antlr.collections.impl.BitSet;

/**   
* @Description 
* @author xiaoding
* @date 2018年5月19日 上午11:16:30   
*/
public class KaoShi {

	
	/**
	 * 有一堆煤球，堆成三角棱锥形。具体：
		第一层放1个，
		第二层3个（排列成三角形），
		第三层6个（排列成三角形），
		第四层10个（排列成三角形），
		....
		如果一共有100层，共有多少个煤球？

	 */
	@Test
	public void Test_1() {
		 int sum = 0;  
        for(int i = 1; i <= 100; i++){  
            for(int j = 1; j <= i; j++){  
                sum = sum + j;  
            }  
        }  
        System.out.println(sum); //171700  
        BitSet set = new BitSet();
        
	}
}
