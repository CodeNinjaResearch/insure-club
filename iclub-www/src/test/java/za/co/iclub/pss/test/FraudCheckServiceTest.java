package za.co.iclub.pss.test;

import org.apache.commons.codec.digest.DigestUtils;

public class FraudCheckServiceTest {

	public static void main(String args[]) {
		Long tStamp = System.currentTimeMillis();
		System.out.println(tStamp + " :: " + new String(DigestUtils.md5Hex("4050AeB7uvv5mO+dt7YH6OBal+JHESTppNG0GowXD/IJhT4=" + tStamp)));
	}
}
