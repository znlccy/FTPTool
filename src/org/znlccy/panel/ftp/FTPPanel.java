package org.znlccy.panel.ftp;

import javax.swing.JPanel;

import org.znlccy.window.FTP_Client_Frame;

/**
 * @author ZNLCCY
 * @version 0.0.1
 * @date 2017-10-9
 * @instroduce 这是FTP的 面板类
 */
public class FTPPanel extends JPanel {
	
	/**
	 * 创建FTP传输的主界面
	 */
	private FTP_Client_Frame frame;

	/**
	 * 创建默认的无参构造函数
	 */
	public FTPPanel() {

	}

	/**
	 * 创建带有参数的构造函数
	 * @param client_Frame
	 */
	public FTPPanel(FTP_Client_Frame client_Frame){
		frame = client_Frame;
	}	
}
