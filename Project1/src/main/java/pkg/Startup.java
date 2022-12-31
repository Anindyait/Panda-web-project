package pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet; //To work on the result of getTables
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.jdbc.DatabaseMetaData; //to use getTables

/**
 * Servlet implementation class Startup
 */
@WebServlet("/Startup")
public class Startup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Startup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext context=getServletContext(); 
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		try {
			
			Connection con, con1;
			PreparedStatement pstm; 			       //class to prepare statement
			
			Class.forName("com.mysql.cj.jdbc.Driver"); //Class is a class
			
			//Creating Database
			con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "abcd");
			String sqlDbCreate = "create database if not exists servlet";
			Statement stmt1 = con1.createStatement();
			
			stmt1.executeUpdate(sqlDbCreate);
			System.out.println("Created database");
			
			//Using servlet database
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet", "root", "abcd"); //DriverManager is a class 
																	//jdbc:mysql then ip address then port no. then db name
					
			DatabaseMetaData databaseMetaData = (DatabaseMetaData) con.getMetaData();
			
			ResultSet resultSet = databaseMetaData.getTables(null, null, null, new String[] {"TABLE"});
			
			//Print names of tables present in given database
			while (resultSet.next()) {
			String name = resultSet.getString("TABLE_NAME");
			String schema = resultSet.getString("TABLE_SCHEM");
			System.out.println(name + " on schema " + schema);
			}
			//Create user_table if it does not exist
			String sqlUserCreate = "create table if not exists user_table"
					+ "(user_id int auto_increment not null, "
					+ "first_name varchar(255) not null, "
					+ "last_name varchar(255) not null, "
					+ "email varchar(255) unique not null, "
					+ "phone varchar(30) unique not null, "
					+ "dob date,"
					+ "gender varchar(20), "
					+ "address varchar(255), "
					+ "password varchar(255) not null, "
					+ "primary key(user_id))";
			
			//Create product_table if it does not exist
			String sqlProductCreate = "create table if not exists product_table"
					+ "(product_id varchar(15) not null,"
					+ "p_name varchar(255) not null,"
					+ "price float(12,3) not null, "
					+ "sizes varchar(50) not null, "
					+ "stock int not null, "
					+ "imgs varchar(255), "
					+ "descr varchar(255),"
					+ "cat1 varchar(255), "
					+ "cat2 varchar(255), "
					+ "cat3 varchar(255), "
					+ "cat4 varchar(255), "
					+ "primary key(product_id))";
			
			//Inserting products into the product_table
			String sqlProductInsert1 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2) "
					+ "values ('P0001','Hailey Style Two Piece Hoodie In Brown',899.00,"
					+ "'XS,S,M,XL',5,'imgs/1a,imgs/2b','Material : Terry Fabric. "
					+ "Designed with multitasking with mind, this hoodie comes with a "
					+ "spaghetti separate to make you look chic yet comfortable all day long. "
					+ "Wear yours with activewear or denims.','F,O','Hoodie')";
			
			String sqlProductInsert2 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2) "
					+ "values ('P0002','Hailey Style Two Piece Hoodie In Black',899.00,"
					+ "'XS,S,M,XL',5,'imgs/2a,imgs/2b','Material : Terry Fabric. "
					+ "Designed with multitasking with mind, this hoodie comes with a "
					+ "spaghetti separate to make you look chic yet comfortable all day long. "
					+ "Wear yours with activewear or denims.','F,O','Hoodie')";
			
			String sqlProductInsert3 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2) "
					+ "values ('P0003','Black Buckle Belt Top',599.00,'XS,S,M,XL',5,"
					+ "'imgs/3a,imgs/3b','This black buckle belt ribbed top is to notch up "
					+ "your black wardrobe game. Wear yours with black baggy cargos and boots "
					+ "for a statement look.','F,O','Top')";

			String sqlProductInsert4 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2) "
					+ "values ('P0004','Corset Ribbed Top With Casual Trouser In Black',1299.00,"
					+ "'XS,S,M,XL',5,'imgs/4a,imgs/4b','Perfect for laidback days,this matching set is "
					+ "basic addition to the wardrobe. Wear yours with slides or sneakers.','F,O','Top')";

			String sqlProductInsert5 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2) "
					+ "values ('P0005','Cool Girl Aesthetics Double Pocket Trousers Black',699.00,"
					+ "'XS,S,M,XL',5,'imgs/5a,imgs/5b','Pair it with tees or sweatshirts','F,O','Pants')";

			String sqlProductInsert6 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2) "
					+ "values ('P0006','Green Ribbed Top With Casual Trouser',1099.00,"
					+ "'XS,S,M,XL',5,'imgs/6a,imgs/6b','Perfect for laidback days,this matching "
					+ "set is basic addition to the wardrobe. Wear yours with slides or sneakers.','F,O','Pants')";

			String sqlProductInsert7 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2) "
					+ "values ('P0007','Set Of Three : Corset Style Hoodie With Sleeveless "
					+ "Crop Top & Bottom In Black',1799.00,'XS,S,M,XL',5,'imgs/7a,imgs/7b',"
					+ "'Get ready for the cozy season with this basic set of three outfit. "
					+ "This comes with corset style hoodie, crop top & a trouser.','F,O','Hoodie,Top,Pants')";

			String sqlProductInsert8 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2) "
					+ "values ('P0008','Tailored Aesthetics Mens Causal Loafer',1699.00,"
					+ "'35,36,40',5,'imgs/8a,imgs/8b','Material : Synthetic leather','M,O','Shoes')";

			String sqlProductInsert9 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2) "
					+ "values ('P0009','Printed Buckle Tied Chunky Style Loafer',1699.00,"
					+ "'35,36,37,40',5,'imgs/9a,imgs/9b','Material : Synthetic leather','M,F,O','Shoes')";

			String sqlProductInsert10 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2) "
					+ "values ('P0010','Criss Cross Details Men’s Tan Shoe',1599.00,"
					+ "'35,37,40,42',5,'imgs/10a,imgs/10b','Material : Synthetic leather','M,O','Shoes')";

			String sqlProductInsert11 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2) "
					+ "values ('P0011','Brushed Olive Color Men’s Loafer',1699.00,"
					+ "'35,37,40,42',5,'imgs/11a,imgs/11b','Material : Synthetic leather','M,O','Shoes')";

			String sqlProductInsert12 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2) "
					+ "values ('P0012','Seoul City Aesthetics Pink Platform Pumps',"
					+ "1599.00,'35,37,39,40',5,'imgs/12a,imgs/12b','Material : "
					+ "Synthetic leather. Heel height : 3 Inches. Rock a geek girl look with "
					+ "this chic platform shoes . Wear yours with dress and a vest.','F,O','Shoes')";

			String sqlProductInsert13 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2) "
					+ "values ('P0013','Nordic Cozy Aesthetics Fall Flats Offwhite',"
					+ "799.00,'35,37,39,40',5,'imgs/13a,imgs/13b','Mateial : Faux Fur. "
					+ "Sole : TPR. Wear this faux fur nordic aesthetics flats with cute "
					+ "socks for your next coffee run. We bet these are the most comfotable "
					+ "pair of flats you will own.','F,O','Shoes')";

			String sqlProductInsert14 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2) "
					+ "values ('P0014','Black Rhinestone Platform Heels',2399.00,"
					+ "'35,37,39,40,41',5,'imgs/14a,imgs/14b','Material : Satin. "
					+ "Heel Height : 6 Inches. Embellished with a row of light reflecting "
					+ "rhinestones, these platform heels are having talk of the town moment. "
					+ "Style yours with sharp tonal tailoring.','F,O','Shoes')";

			String sqlProductInsert15 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2) "
					+ "values ('P0015','The Comfy Edit Dual Color Trouser In Cosmic Latte Tone',"
					+ "999.00,'XS,S,M,XL',5,'imgs/15a,imgs/15b','These pants are designed to "
					+ "be worn with the matching piped cardigan, but you could team them "
					+ "with an oversized tee when lounging at home, too. Knitted from a "
					+ "soft blend of cotton & polyster, they have a high-rise waist and wide legs.','F,O','Pants')";

			String sqlProductInsert16 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2) "
					+ "values ('P0016','The Comfy Edit Dual Color Trouser In Pink & "
					+ "Cosmic Latte Tone',899.00,'XS,S,M,XL',5,'imgs/16a,imgs/16b',"
					+ "'These pants are designed to be worn with the matching piped "
					+ "cardigan, but you could team them with an oversized tee when "
					+ "lounging at home, too. Knitted from a soft blend of cotton & polyster, "
					+ "they have a high-rise waist and wide legs.','F,O','Pants')";

			String sqlProductInsert17 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2) "
					+ "values ('P0017','Back To School Aesthetics Chain Detail Boot',1799.00,"
					+ "'35,37,39,40,41',5,'imgs/17a,imgs/17b','These classic loafers are embellished "
					+ "with silver tone interlocking chains that add style & shine to silhouette. "
					+ "Made from supple leather, they sit on TPR soles that will keep you comfortable "
					+ "whole day.','M,F,O','Shoes')";

			String sqlProductInsert18 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2) "
					+ "values ('P0018','Delicious Yellow Platforms',1199.00,"
					+ "'35,37,39,40,41',5,'imgs/18a,imgs/18b','These sandals are "
					+ "set on a chunky platform which promises a comfortable but "
					+ "elevated fit. Material : Synthetic leather. Platform Height : 3 Inches','F,O','Shoes')";

			String sqlProductInsert19 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2) "
					+ "values ('P0019','Back To School Aesthetic Cute Buckle Details Shoe',"
					+ "1699.00,'35,37,39,40,41',5,'imgs/19a,imgs/19b','Crafted from supple "
					+ "synthetic leather & set on chunky soles that stand up to long days "
					+ "on the go . Wear these for your seal of approval for back to school aesthetics.','M,F,O','Shoes')";

			String sqlProductInsert20 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2) "
					+ "values ('P0020','Comfort Wear Slip On Flats Black',699.00,"
					+ "'35,37,39,40,41',5,'imgs/20a,imgs/20b','Pair it up a cozy loungewear set"
					+ " for all day comfort.','M,F,O','Shoes')";

			String sqlProductInsert21 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2) "
					+ "values ('P0021','Nordic Cozy Aesthetics Fall Double Belt Comfort Flat',"
					+ "699.00,'35,37,39,40,41',5,'imgs/21a,imgs/21b','Wear this faux fur nordic aesthetics"
					+ " flats with cute socks for your next coffee run. We bet these are the most"
					+ " comfotable pair of flats you will own.','F,O','Shoes')";

			String sqlProductInsert22 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2) "
					+ "values ('P0022','Showstopper Vibe Eclectic Blue Rhinestone Platform Heels',"
					+ "2399.00,'35,37,39,40,41',5,'imgs/22a,imgs22b','Embellished with a row of "
					+ "light reflecting rhinestones, these platform heels are having talk of the"
					+ " town moment.Style yours with sharp tonal tailoring.','F,O','Shoes')";

			String sqlProductInsert23 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2) "
					+ "values ('P0023','Contrast Trim Blue And White Top',599.00,"
					+ "'XS,S,M,XL',5,'imgs/23a,imgs/23b','This contrast color ribbed top"
					+ " has a stylish cut out patter. Wear yours with denim for a flattering"
					+ " style. Material : Ribbed fabric','F,O','Top')";

			String sqlProductInsert24 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2) "
					+ "values ('P0024','Corset Style Tie Around Full Sleeve Ribbed Apricot Top',"
					+ "599.00,'XS,S,M,XL',5,'imgs/24a,imgs/24b','Designed with corset style with "
					+ "tie around for a body hugging fit, this is a best take on trending corset"
					+ " style. Wear yours with leather pants and boots for diva vibe. Material : "
					+ "Ribbed fabric','F,O','Top')";

			String sqlProductInsert25 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2) "
					+ "values ('P0025','Cool Girl Aesthetics Collared Full Sleeve Top Black',"
					+ "599.00,'XS,S,M,XL',5,'imgs/25a,imgs/25b','Pair yours with denim for casual"
					+ " look at best. Material : Poly Cotton','F,O','Top')";

			String sqlProductInsert26 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2) "
					+ "values ('P0026','Black Full Sleeve Top With White Trim',499.00,"
					+ "'XS,S,M,XL',5,'imgs/26a,imgs/26b','Material : Cotton- 60% Polyster- 40%. "
					+ "Wear yours with wide legged trouser and a oxford for the cool girl aesthetics.','F,O','Top')";

			String sqlProductInsert27 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2) "
					+ "values ('P0027','Brown Long Sleeve Collar Top',499.00,'XS,S,M,XL',5,"
					+ "'imgs/27a,imgs/27b','Material : Cotton- 60% Polyster- 40%. Wear yours "
					+ "with wide legged trouser and a oxford for the cool girl aesthetics.','M,F,O','Top')";

			String sqlProductInsert28 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2) "
					+ "values ('P0028','Blue Trendy Full Sleeve Ribbed Top',549.00,"
					+ "'XS,S,M,XL',5,'imgs/28a,imgs/28b','This top is made from ribbed "
					+ "fabric for a comfortable slim fit that will keep its shape throughout the day. "
					+ "Wear yours with denim or skirt. Material :- Ribbed fabric','F,O','Top')";

			String sqlProductInsert29 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2) "
					+ "values ('P0029','Classic City Girl Flats Brown & Hot Pink',699.00,"
					+ "'35,37,39,40,41',5,'imgs/29a,imgs/29b','Material : Synthetic Leather. "
					+ "Wear this classic pairs for your daytime affairs. Pairs best with denim"
					+ " to casual wear.','F,O','Shoes')";
			
			String sqlProductInsert30 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2) "
					+ "values ('P0030','Nordic Cozy Aesthetics Fall Flats Mocha',799.00,"
					+ "'35,37,39,40,41',5,'imgs/30a,imgs/30b',"
					+ "'Mateial : Faux Fur, Sole - TPR, Wear this faux fur nordic aesthetics flats "
					+ "with cute socks for your next coffee run. We bet these are the most comfotable pair of "
					+ "flats you will own.','F,O','Shoes')";
			

		    Statement stmt = con.createStatement();
		    
			stmt.addBatch(sqlUserCreate);
            stmt.addBatch(sqlProductCreate);
            
            stmt.addBatch(sqlProductInsert1);
            stmt.addBatch(sqlProductInsert2);
            stmt.addBatch(sqlProductInsert3);
            stmt.addBatch(sqlProductInsert4);
            stmt.addBatch(sqlProductInsert5);
            stmt.addBatch(sqlProductInsert6);
            stmt.addBatch(sqlProductInsert7);
            stmt.addBatch(sqlProductInsert8);
            stmt.addBatch(sqlProductInsert9);
            stmt.addBatch(sqlProductInsert10);
            stmt.addBatch(sqlProductInsert11);
            stmt.addBatch(sqlProductInsert12);
            stmt.addBatch(sqlProductInsert13);
            stmt.addBatch(sqlProductInsert14);
            stmt.addBatch(sqlProductInsert15);
            stmt.addBatch(sqlProductInsert16);
            stmt.addBatch(sqlProductInsert17);
            stmt.addBatch(sqlProductInsert18);
            stmt.addBatch(sqlProductInsert19);
            stmt.addBatch(sqlProductInsert20);
            stmt.addBatch(sqlProductInsert21);
            stmt.addBatch(sqlProductInsert22);
            stmt.addBatch(sqlProductInsert23);
            stmt.addBatch(sqlProductInsert24);
            stmt.addBatch(sqlProductInsert25);
            stmt.addBatch(sqlProductInsert26);
            stmt.addBatch(sqlProductInsert27);
            stmt.addBatch(sqlProductInsert28);
            stmt.addBatch(sqlProductInsert29);
            stmt.addBatch(sqlProductInsert30);

		    stmt.executeBatch();
			
		}catch(Exception e) {}
		
		request.getRequestDispatcher("Login").include(request, response);
	}

}