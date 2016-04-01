package com.estsoft.emailList.test;

import java.util.List;

import com.estsoft.db.MySQLWebDBConnection;
import com.estsoft.emailList.dao.EmailListDao;
import com.estsoft.emailList.vo.EmailListVO;


public class EmailListDaoTest {

	public static void main(String[] args) {
//		insertTest();
//		getListTest();
	}

	public static void insertTest(){
		EmailListVO vo = new EmailListVO();
		vo.setFirstName( "둘" );
		vo.setLastName( "리" );
		vo.setEmail( "dooly@gmail.com" );
		
		EmailListDao dao = new EmailListDao( new MySQLWebDBConnection() );
		dao.insert(vo);
	}	
	
	public static void getListTest(){
		EmailListDao dao = new EmailListDao( new MySQLWebDBConnection() );
		List<EmailListVO> list = dao.getList();
		for( EmailListVO vo : list ) {
			System.out.println( vo );
		}
	}
}

