package com.sist.client;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class ControllPanel extends JPanel{
	CardLayout card=new CardLayout();
	HomePanel homeP;
	BookDetailPanel bookDP;
	FindPanel findP;
	BoardListPanel boardLP;
	BoardInsertPanel boardIP;
	BoardDetailPanel boardDTP;
	BoardDeletePanel boardDLP;
	BoardUpdatePanel boardUP;
	ChatPanel chatP=new ChatPanel();
	MypagePanel myP=new MypagePanel();
	// 웹에서 화면변경 => Controller
	/*
	 *  처리 - Model
	 *  화면 - View
	 *  화면변경 - Controller
	 *  =====================> MVC 
	 */
	public ControllPanel() {
		setLayout(card);
		homeP=new HomePanel(this);
		bookDP=new BookDetailPanel(this);
		findP=new FindPanel(this);
		boardLP=new BoardListPanel(this);
		boardIP=new BoardInsertPanel(this);
		boardDTP=new BoardDetailPanel(this);
		boardDLP=new BoardDeletePanel(this);
		boardUP=new BoardUpdatePanel(this);
		//add("BOOKLIST",bookListP);
		add("HOME", homeP);
		add("DETAIL", bookDP);
		add("FIND", findP);
		add("BOARD", boardLP);
		add("BOARD_IN", boardIP);
		add("BOARD_DT", boardDTP);
		add("BOARD_UP", boardUP);
		add("BOARD_DL", boardDLP);
		add("CHAT", chatP);
		add("MYPAGE", myP);
	}
}
