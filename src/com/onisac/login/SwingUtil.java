package com.onisac.login;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

public class SwingUtil {
	public static Point centreContainer(Dimension size) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();// 获得屏幕大小
		int x = (screenSize.width - size.width) / 2;// 计算左上角的x坐标
		int y = (screenSize.height - size.height) / 2;// 计算左上角的y坐标
		return new Point(x, y);// 返回左上角坐标
	}
}
