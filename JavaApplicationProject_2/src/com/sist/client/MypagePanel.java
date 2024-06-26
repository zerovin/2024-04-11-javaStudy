package com.sist.client;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.List;
import java.net.*;
import com.sist.commons.*;
import com.sist.dao.*;
public class MypagePanel extends JPanel implements MouseListener{
	JLabel titleLa;
	JTable table;
	DefaultTableModel model;
	GoodsDAO dao;
	ControllPanel ctrP;
	
	public MypagePanel(ControllPanel ctrP) {
		this.ctrP=ctrP;
		dao=GoodsDAO.newInstance();
		
		titleLa=new JLabel("장바구니",JLabel.CENTER); 
		titleLa.setFont(new Font("맑은 고딕", Font.BOLD, 30)); //<h3></h3>
		setLayout(null);
		titleLa.setBounds(70, 15, 700, 50);
		add(titleLa);
		
		String[] col={"번호","상품명","","구매가격","수량"};
		Object[][] row=new Object[0][5];
		model=new DefaultTableModel(row, col) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
			@Override //이미지출력
			public Class<?> getColumnClass(int columnIndex) {
				// TODO Auto-generated method stub
				return getValueAt(0, columnIndex).getClass();
			}
		};
		table=new JTable(model);
		table.setRowHeight(50);
		JScrollPane js=new JScrollPane(table);
		
		js.setBounds(70, 75, 800, 400);
		add(js);
		
		table.addMouseListener(this);
	}
	public void print() {
		String id=ctrP.cliMain.myId;
		List<CartVO> list=dao.cartSelect(id);
		for(int i=model.getRowCount()-1;i>=0;i--) {
			model.removeRow(i);
		}
		for(CartVO vo:list) {
			try {
				URL url=new URL(vo.getGvo().getGoods_poster());
				Image img=ImageChange.getImage(new ImageIcon(url), 45, 45);
				Object[] data= {
						vo.getCno(),
						vo.getGvo().getGoods_name(),
						new ImageIcon(img),
						vo.getPrice(),
						vo.getAccount()
				};
				model.addRow(data);
			}catch(Exception ex) {
				
			}
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==table) {
			if(e.getClickCount()==2) {
				int row=table.getSelectedRow();
				String cno=model.getValueAt(row, 0).toString();
				int sel=JOptionPane.showConfirmDialog(this, "장바구니에서 삭제할까요?", "삭제", JOptionPane.YES_NO_OPTION);
				if(sel==JOptionPane.YES_OPTION) {
					dao.cartCancel(Integer.parseInt(cno));
					print();
				}
			}
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	/*
	JLabel title, memberInfo, id, name, email, addr, phone;
	JLabel myId, myName, myEmail, myAddr, myPhone;
	JLabel cart, totalCount, totalPrice;
	JTable cartTable;
	DefaultTableModel cartModel;
	JComboBox count;
	JButton orderBtn, listBtn;
		
	public MypagePanel() {
		
		setLayout(null);
		
		title=new JLabel("마이페이지", JLabel.CENTER);
		title.setFont(new Font("맑은 고딕",Font.BOLD,25));
	    title.setBounds(10,20,940,40);
	  	add(title);
	  	
	  	memberInfo=new JLabel("회원정보");
	  	memberInfo.setFont(new Font("맑은 고딕",Font.BOLD,20));
	  	memberInfo.setBounds(30, 80, 80, 30);
	  	add(memberInfo);
	  	
	  	id=new JLabel("ID");
	  	id.setFont(new Font("맑은 고딕",Font.BOLD,15));
	  	id.setBounds(30, 150, 80, 30);
	  	add(id);
	  	
	  	name=new JLabel("이름");
	  	name.setFont(new Font("맑은 고딕",Font.BOLD,15));
	  	name.setBounds(30, 190, 80, 30);
	  	add(name);
	  	
	  	email=new JLabel("E-mail");
	  	email.setFont(new Font("맑은 고딕",Font.BOLD,15));
	  	email.setBounds(30, 230, 80, 30);
	  	add(email);
	  	
	  	addr=new JLabel("주소");
	  	addr.setFont(new Font("맑은 고딕",Font.BOLD,15));
	  	addr.setBounds(30, 270, 80, 30);
	  	add(addr);
	  	
	  	phone=new JLabel("전화번호");
	  	phone.setFont(new Font("맑은 고딕",Font.BOLD,15));
	  	phone.setBounds(30, 310, 80, 30);
	  	add(phone);
	  	
	  	cart=new JLabel("장바구니");
	  	cart.setFont(new Font("맑은 고딕",Font.BOLD,20));
	  	cart.setBounds(470, 80, 80, 40);
	  	add(cart);
	  	
	  	String[] col={"상품명", "가격", "수량", "삭제"};
	  	String[][] row=new String[0][4];
	  	cartModel=new DefaultTableModel(row, col);
	  	cartTable=new JTable(cartModel);
	  	JScrollPane cartPane=new JScrollPane(cartTable);
	  	cartPane.setBounds(470, 130, 440, 370);
	  	add(cartPane);
	  	
	  	cartTable.getColumn("상품명").setPreferredWidth(230);
	  	cartTable.getColumn("가격").setPreferredWidth(80);
	  	cartTable.getColumn("수량").setPreferredWidth(50);
	  	cartTable.getColumn("삭제").setPreferredWidth(80);
	  	
	  	count=new JComboBox();
	  	for(int i=1;i<=10;i++) {
	  		count.addItem(i); 	  		
	  	}
	  	TableColumn column = cartTable.getColumnModel().getColumn(3);
	  	column.setCellEditor(new DefaultCellEditor(count)); 
	  	
	  	totalCount=new JLabel("총 수량");
	  	totalCount.setFont(new Font("맑은 고딕",Font.BOLD,15));
	  	totalCount.setBounds(470, 530, 50, 30);
	  	add(totalCount);
	  	
	  	totalPrice=new JLabel("총 가격");
	  	totalPrice.setFont(new Font("맑은 고딕",Font.BOLD,15));
	  	totalPrice.setBounds(470, 570, 50, 30);
	  	add(totalPrice);
	  	
	  	orderBtn=new JButton("주문하기");
	  	orderBtn.setBounds(810, 530, 100, 30);
	  	add(orderBtn);
	  	
	  	listBtn=new JButton("목록으로");
	  	listBtn.setBounds(810, 570, 100, 30);
	  	add(listBtn);
	}
	*/

}
