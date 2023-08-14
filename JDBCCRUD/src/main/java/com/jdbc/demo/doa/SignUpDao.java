package com.jdbc.demo.doa;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import com.jdbc.demo.model.Response;
import com.jdbc.demo.model.SignUpModel;

@Component
public class SignUpDao {

	Response res = new Response();
	String url = "jdbc:mysql://127.0.0.1:3306/kgm";
	String username = "root";
	String password = "Hari@iphone6";

	public Response createUser(SignUpModel values) {

		String uuid = UUID.randomUUID().toString();
		values.setsNo(uuid);
		values.setCreatedBy(uuid);
		values.setUpdatedBy(uuid);

		Date date = new Date(Calendar.getInstance().getTime().getTime());
		values.setCreatedDate(date);
		values.setUpdatedDate(date);
		values.setActive(true);

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			try (Connection conn = DriverManager.getConnection(url, username, password);
					Statement st = conn.createStatement();) {

				String insertQuery = "INSERT INTO kgm.user_deatils(s_no,first_Name,last_Name,gender,dob,email_id,password,phone_number,created_by,updated_by,created_date,updated_date,is_active)VALUES('"
						+ values.getsNo() + "','" + values.getFirstName() + "','" + values.getLastName() + "','"
						+ values.getGender() + "','" + values.getDob() + "','" + values.getEmailId() + "','"
						+ values.getPassword() + "'," + values.getPhoneNumber() + ",'" + values.getCreatedBy()
						+ "','" + values.getUpdatedBy() + "','" + values.getCreatedDate() + "','"
						+ values.getUpdatedDate() + "','"+ values.isActive()+"');";

				
				System.out.println(insertQuery);
				st.executeUpdate(insertQuery);
				

				res.setData("User Created Successfully!");
				res.setResponseCode(200);
				res.setRespondMsg("Sucess");

			} catch (Exception e) {
				e.printStackTrace();

				res.setData("Cannot create User!");
				res.setResponseCode(500);
				res.setRespondMsg("Error");
			}

		} catch (Exception e) {
			e.printStackTrace();

			res.setData("Driver class error");
			res.setResponseCode(200);
			res.setRespondMsg("Error");
		}

		return res;
	}

	@SuppressWarnings("unchecked")
	public Response getUser() {

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			String selectQuery = "select * from user_deatils;";

			try (Connection conn = DriverManager.getConnection(url, username, password);
					Statement st = conn.createStatement();
					ResultSet rs = st.executeQuery(selectQuery);) {

				JSONArray jsonArray = new JSONArray();
				while (rs.next()) {

					JSONObject jsonObject = new JSONObject();
					jsonObject.put("sNo", rs.getString("s_no"));
					jsonObject.put("firstName", rs.getString("first_name"));
					jsonObject.put("lastName", rs.getString("last_name"));
					jsonObject.put("emailId", rs.getString("email_id"));
					jsonObject.put("dob", rs.getDate("dob"));
					jsonObject.put("gender", rs.getString("gender"));
					jsonObject.put("phoneNumber", rs.getLong("phone_number"));
					jsonObject.put("password", rs.getString("password"));
					jsonObject.put("createdBy", rs.getString("created_by"));
					jsonObject.put("uodatedBy", rs.getString("updated_by"));
					jsonObject.put("createdDate", rs.getDate("created_date"));
					jsonObject.put("updatedDate", rs.getDate("updated_date"));

					jsonArray.add(jsonObject);

				}
				res.setData("User fetched Successfully!");
				res.setResponseCode(200);
				res.setRespondMsg("Sucess");
				res.setjData(jsonArray);

			} catch (Exception e) {
				e.printStackTrace();
				res.setData("Cannot fetch User!");
				res.setResponseCode(500);
				res.setRespondMsg("Error");
			}

		} catch (Exception e) {
			e.printStackTrace();
			res.setData("Driver class error");
			res.setResponseCode(200);
			res.setRespondMsg("Error");
		}
		return res;
	}
	
	@SuppressWarnings("unchecked")
	public Response getOneUser(String sno) {
		
		
	 try {
		 
		 Class.forName("com.mysql.cj.jdbc.Driver");
		 
		 String updateQuery = "select * from user_details where s_no = '"+sno +"'";
		 JSONObject jsonObject = new JSONObject();	
		  try (Connection con = DriverManager.getConnection(sno);
				 Statement st = con.createStatement();
				  ResultSet rs = st.executeQuery(updateQuery)){
			  
			  while(rs.next()) {
				  
				  jsonObject.put("sNo", rs.getString("s_no"));
					jsonObject.put("firstName", rs.getString("first_name"));
					jsonObject.put("lastName", rs.getString("last_name"));
					jsonObject.put("emailId", rs.getString("email_id"));
					jsonObject.put("dob", rs.getDate("dob"));
					jsonObject.put("gender", rs.getString("gender"));
					jsonObject.put("phoneNumber", rs.getLong("phone_number"));
					jsonObject.put("password", rs.getString("password"));
					jsonObject.put("createdBy", rs.getString("created_by"));
					jsonObject.put("uodatedBy", rs.getString("updated_by"));
					jsonObject.put("createdDate", rs.getDate("created_date"));
					jsonObject.put("updatedDate", rs.getDate("updated_date"));
			  }
			  res.setData("User Successfully!");
				res.setResponseCode(200);
				res.setjData(jsonObject);
				res.setRespondMsg("Sucess");
			
		} catch (Exception e) {
			e.printStackTrace();
			res.setData("Cannot get a User!");
			res.setjData(jsonObject);
			res.setResponseCode(200);
			res.setRespondMsg("Error");
		}
	 }
		 catch (Exception e) {
			 e.printStackTrace();
				res.setData("Driver class error");
				res.setResponseCode(500);
				res.setRespondMsg("Error");
		 }
	return res;
		  
	}
	
	public Response update() {
		try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 
//			 String selectQuery = select * from user_details where 
		} catch (Exception e) {
			 e.printStackTrace();
		} {
			
		}
		  
	}
	

}
