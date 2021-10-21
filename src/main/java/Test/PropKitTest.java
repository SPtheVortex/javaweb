package Test;

import Utils.PropKit;

public class PropKitTest {
	static {
		 PropKit.use("application.properties");
		 }
		 public static void main(String[] args) {
		 System.out.println(PropKit.get("jdbc.username"));
		 System.out.println(PropKit.get("jdbc.password"));
		 }
}