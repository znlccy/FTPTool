package org.znlccy.panel.queue;

import javax.swing.JPanel;

import org.znlccy.window.FTP_Client_Frame;

public class QueuePanel extends JPanel {

	/**
	 * 创建FTP传输的主界面
	 */
	private FTP_Client_Frame frame;
	
	/**
	 * 创建默认的无参构造函数
	 */
	public QueuePanel() {

	}

	/**
	 * 创建带有参数的构造函数
	 * @param client_Frame
	 */
	public QueuePanel(FTP_Client_Frame client_Frame) {
		super();
		this.frame = client_Frame;
	}

}
