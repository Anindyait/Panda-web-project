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
					+ "stock varchar(50) not null, "
					+ "imgs varchar(255), "
					+ "descr varchar(255),"
					+ "cat1 varchar(255), "
					+ "cat2 varchar(255), "
					+ "cat3 varchar(255), "
					+ "cat4 varchar(255), "
					+ "primary key(product_id))";
			
			//Create cart_table if it does not exist
			String sqlCartOrderCreate = "create table if not exists cart_table"
					+ "(cart_id int auto_increment not null,"
					+ "user_id int not null,"
					+ "product_id varchar(15) not null, "
					+ "quantity int not null, "
					+ "size varchar(30) not null, "
					+ "order_id varchar(255), "
					+ "d_date date,"
					+ "foreign key(user_id) references user_table(user_id), "
					+ "foreign key(product_id) references product_table(product_id), "
					+ "primary key(cart_id,user_id,product_id))";
			
			//Create admin_table if it does not exist
			String sqlAdminCreate = "create table if not exists admin_table"
					+ "(admin_id int auto_increment not null, "
					+ "first_name varchar(255) not null, "
					+ "last_name varchar(255) not null, "
					+ "email varchar(255) unique not null, "
					+ "phone varchar(30) unique not null, "
					+ "password varchar(255) not null, "
					+ "primary key(admin_id))";
			
			//Declaring existing admins
			String sqlAdminInsert1 = "insert ignore into admin_table (first_name, last_name, email, phone, password)"
					+ "values('Mike', 'Hunt', 'mikehunt@gmail.com', '6666766890', "
					+ "'1000:5b42403736656435353238:1964f70cb9fc48c6ca92697072d776f7f85cc0089817485d9a3e72b833186724bd6231da03c6d7b4e67e2df60acf3cc6242cdd7823fb7b17e5c90d9747986c0f')";
					//Hashed password ThePanda23*
			String sqlAdminInsert2 = "insert ignore into admin_table (first_name, last_name, email, phone, password)"
					+ "values('Ford', 'Prefect', 'fordprefect@gmail.com', '9666766890', "
					+ "'1000:5b42403736656435353238:1964f70cb9fc48c6ca92697072d776f7f85cc0089817485d9a3e72b833186724bd6231da03c6d7b4e67e2df60acf3cc6242cdd7823fb7b17e5c90d9747986c0f')";		
		
			//Inserting products into the product_table
			String sqlProductInsert1 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0001','Hailey Style Two Piece Hoodie In Brown',899.00,"
					+ "'XS,S,M,XL','10,10,10,10','imgs/1a,imgs/2b','Material : Terry Fabric. "
					+ "Designed with multitasking with mind, this hoodie comes with a "
					+ "spaghetti separate to make you look chic yet comfortable all day long. "
					+ "Wear yours with activewear or denims.','Female,Others','Hoodies','Winter Collection')";
			
			String sqlProductInsert2 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0002','Hailey Style Two Piece Hoodie In Black',899.00,"
					+ "'XS,S,M,XL','10,10,10,10','imgs/2a,imgs/2b','Material : Terry Fabric. "
					+ "Designed with multitasking with mind, this hoodie comes with a "
					+ "spaghetti separate to make you look chic yet comfortable all day long. "
					+ "Wear yours with activewear or denims.','Female,Others','Hoodies','Winter Collection')";
			
			String sqlProductInsert3 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0003','Black Buckle Belt Top',599.00,'XS,S,M,XL','10,10,10,10',"
					+ "'imgs/3a,imgs/3b','This black buckle belt ribbed top is to notch up "
					+ "your black wardrobe game. Wear yours with black baggy cargos and boots "
					+ "for a statement look.','Female,Others','Tops','Best Sellers')";

			String sqlProductInsert4 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0004','Corset Ribbed Top With Casual Trouser In Black',1299.00,"
					+ "'XS,S,M,XL','10,10,10,10','imgs/4a,imgs/4b','Perfect for laidback days,this matching set is "
					+ "basic addition to the wardrobe. Wear yours with slides or sneakers.','Female,Others','Tops,Trousers','Best Sellers')";

			String sqlProductInsert5 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0005','Cool Girl Aesthetics Double Pocket Trousers Black',699.00,"
					+ "'XS,S,M,XL','10,10,10,10','imgs/5a,imgs/5b','Pair it with tees or sweatshirts','Female,Others','Trousers','Best Sellers')";

			String sqlProductInsert6 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0006','Green Ribbed Top With Casual Trouser',1099.00,"
					+ "'XS,S,M,XL','10,10,10,10','imgs/6a,imgs/6b','Perfect for laidback days,this matching "
					+ "set is basic addition to the wardrobe. Wear yours with slides or sneakers.','Female,Others','Tops,Trousers','Winter Collection')";

			String sqlProductInsert7 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0007','Set Of Three : Corset Style Hoodie With Sleeveless "
					+ "Crop Top & Bottom In Black',1799.00,'XS,S,M,XL','10,10,10,10','imgs/7a,imgs/7b',"
					+ "'Get ready for the cozy season with this basic set of three outfit. "
					+ "This comes with corset style hoodie, crop top & a trouser.','Female,Others','Hoodies,Tops,Trousers','Winter Collection')";

			String sqlProductInsert8 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0008','Tailored Aesthetics Mens Causal Loafer',1699.00,"
					+ "'35,36,40','10,10,10','imgs/8a,imgs/8b','Material : Synthetic leather','Male,Others','Shoes-Loafers','Formal Clothing Range')";

			String sqlProductInsert9 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0009','Printed Buckle Tied Chunky Style Loafer',1699.00,"
					+ "'35,36,37,40','10,10,10,10','imgs/9a,imgs/9b','Material : Synthetic leather','Male,Female,Others','Shoes-Loafers','Formal Clothing Range')";

			String sqlProductInsert10 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0010','Criss Cross Details Men''s Tan Shoe',1599.00,"
					+ "'35,37,40,42','10,10,10,10','imgs/10a,imgs/10b','Material : Synthetic leather','Male,Others','Shoes-Loafers','Formal Clothing Range')";

			String sqlProductInsert11 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0011','Brushed Olive Color Men''s Loafer',1699.00,"
					+ "'35,37,40,42','10,10,10,10','imgs/11a,imgs/11b','Material : Synthetic leather','Male,Others','Shoes-Loafers','Formal Clothing Range')";

			String sqlProductInsert12 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0012','Seoul City Aesthetics Pink Platform Pumps',"
					+ "1599.00,'35,37,39,40','10,10,10,10','imgs/12a,imgs/12b','Material : "
					+ "Synthetic leather. Heel height : 3 Inches. Rock a geek girl look with "
					+ "this chic platform shoes . Wear yours with dress and a vest.','Female,Others','Shoes-Platforms','New Year''s Collection')";

			String sqlProductInsert13 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0013','Nordic Cozy Aesthetics Fall Flats Offwhite',"
					+ "799.00,'35,37,39,40','10,10,10,10','imgs/13a,imgs/13b','Mateial : Faux Fur. "
					+ "Sole : TPR. Wear this faux fur nordic aesthetics flats with cute "
					+ "socks for your next coffee run. We bet these are the most comfotable "
					+ "pair of flats you will own.','Female,Others','Shoes-Flats','Winter Collection')";

			String sqlProductInsert14 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0014','Black Rhinestone Platform Heels',2399.00,"
					+ "'35,37,39,40,41','10,10,10,10,10','imgs/14a,imgs/14b','Material : Satin. "
					+ "Heel Height : 6 Inches. Embellished with a row of light reflecting "
					+ "rhinestones, these platform heels are having talk of the town moment. "
					+ "Style yours with sharp tonal tailoring.','Female,Others','Shoes-Platforms','Winter Collection,New Year''s Collection,Valentine''s Day Collection,Best Sellers')";

			String sqlProductInsert15 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0015','The Comfy Edit Dual Color Trouser In Cosmic Latte Tone',"
					+ "999.00,'XS,S,M,XL','10,10,10,10','imgs/15a,imgs/15b','These pants are designed to "
					+ "be worn with the matching piped cardigan, but you could team them "
					+ "with an oversized tee when lounging at home, too. Knitted from a "
					+ "soft blend of cotton & polyster, they have a high-rise waist and wide legs.','Female,Others','Trousers','Winter Collection')";

			String sqlProductInsert16 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0016','The Comfy Edit Dual Color Trouser In Pink & "
					+ "Cosmic Latte Tone',899.00,'XS,S,M,XL','10,10,10,10','imgs/16a,imgs/16b',"
					+ "'These pants are designed to be worn with the matching piped "
					+ "cardigan, but you could team them with an oversized tee when "
					+ "lounging at home, too. Knitted from a soft blend of cotton & polyster, "
					+ "they have a high-rise waist and wide legs.','Female,Others','Trousers','Winter Collection')";

			String sqlProductInsert17 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0017','Back To School Aesthetics Chain Detail Boot',1799.00,"
					+ "'35,37,39,40,41','10,10,10,10,10','imgs/17a,imgs/17b','These classic loafers are embellished "
					+ "with silver tone interlocking chains that add style & shine to silhouette. "
					+ "Made from supple leather, they sit on TPR soles that will keep you comfortable "
					+ "whole day.','Male,Female,Others','Shoes-Back To School','Best Sellers')";

			String sqlProductInsert18 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0018','Delicious Yellow Platforms',1199.00,"
					+ "'35,37,39,40,41','10,10,10,10,10','imgs/18a,imgs/18b','These sandals are "
					+ "set on a chunky platform which promises a comfortable but "
					+ "elevated fit. Material : Synthetic leather. Platform Height : 3 Inches','Female,Others','Shoes-Platforms','Saraswati Puja Collection')";

			String sqlProductInsert19 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0019','Back To School Aesthetic Cute Buckle Details Shoe',"
					+ "1699.00,'35,37,39,40,41','10,10,10,10,10','imgs/19a,imgs/19b','Crafted from supple "
					+ "synthetic leather & set on chunky soles that stand up to long days "
					+ "on the go . Wear these for your seal of approval for back to school aesthetics.','Male,Female,Others','Shoes-Back To School','Best Sellers')";

			String sqlProductInsert20 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2) "
					+ "values ('P0020','Comfort Wear Slip On Flats Black',699.00,"
					+ "'35,37,39,40,41','10,10,10,10,10','imgs/20a,imgs/20b','Pair it up a cozy loungewear set"
					+ " for all day comfort.','Male,Female,Others','Shoes-Flats')";

			String sqlProductInsert21 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0021','Nordic Cozy Aesthetics Fall Double Belt Comfort Flat',"
					+ "699.00,'35,37,39,40,41','10,10,10,10,10','imgs/21a,imgs/21b','Wear this faux fur nordic aesthetics"
					+ " flats with cute socks for your next coffee run. We bet these are the most"
					+ " comfotable pair of flats you will own.','Female,Others','Shoes-Flats','Winter Collection')";

			String sqlProductInsert22 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0022','Showstopper Vibe Eclectic Blue Rhinestone Platform Heels',"
					+ "2399.00,'35,37,39,40,41','10,10,10,10,10','imgs/22a,imgs/22b','Embellished with a row of "
					+ "light reflecting rhinestones, these platform heels are having talk of the"
					+ " town moment.Style yours with sharp tonal tailoring.','Female,Others','Shoes-Platforms','Winter Collection,New Year''s Collection')";

			String sqlProductInsert23 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0023','Contrast Trim Blue And White Top',599.00,"
					+ "'XS,S,M,XL','10,10,10,10','imgs/23a,imgs/23b','This contrast color ribbed top"
					+ " has a stylish cut out patter. Wear yours with denim for a flattering"
					+ " style. Material : Ribbed fabric','Female,Others','Tops','New Year''s Collection')";

			String sqlProductInsert24 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0024','Corset Style Tie Around Full Sleeve Ribbed Apricot Top',"
					+ "599.00,'XS,S,M,XL','10,10,10,10','imgs/24a,imgs/24b','Designed with corset style with "
					+ "tie around for a body hugging fit, this is a best take on trending corset"
					+ " style. Wear yours with leather pants and boots for diva vibe. Material : "
					+ "Ribbed fabric','Female,Others','Tops','New Year''s Collection')";

			String sqlProductInsert25 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0025','Cool Girl Aesthetics Collared Full Sleeve Top Black',"
					+ "599.00,'XS,S,M,XL','10,10,10,10','imgs/25a,imgs/25b','Pair yours with denim for casual"
					+ " look at best. Material : Poly Cotton','Female,Others','Tops','Best Sellers')";

			String sqlProductInsert26 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0026','Black Full Sleeve Top With White Trim',499.00,"
					+ "'XS,S,M,XL','10,10,10,10','imgs/26a,imgs/26b','Material : Cotton- 60% Polyster- 40%. "
					+ "Wear yours with wide legged trouser and a oxford for the cool girl aesthetics.','Female,Others','Tops','Best Sellers')";

			String sqlProductInsert27 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0027','Brown Long Sleeve Collar Top',499.00,'XS,S,M,XL','10,10,10,10',"
					+ "'imgs/27a,imgs/27b','Material : Cotton- 60% Polyster- 40%. Wear yours "
					+ "with wide legged trouser and a oxford for the cool girl aesthetics.','Male,Female,Others','Tops','Best Sellers')";

			String sqlProductInsert28 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0028','Blue Trendy Full Sleeve Ribbed Top',549.00,"
					+ "'XS,S,M,XL','10,10,10,10','imgs/28a,imgs/28b','This top is made from ribbed "
					+ "fabric for a comfortable slim fit that will keep its shape throughout the day. "
					+ "Wear yours with denim or skirt. Material :- Ribbed fabric','Female,Others','Tops','Best Sellers')";

			String sqlProductInsert29 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2) "
					+ "values ('P0029','Classic City Girl Flats Brown & Hot Pink',699.00,"
					+ "'35,37,39,40,41','10,10,10,10,10','imgs/29a,imgs/29b','Material : Synthetic Leather. "
					+ "Wear this classic pairs for your daytime affairs. Pairs best with denim"
					+ " to casual wear.','Female,Others','Shoes-Flats')";
			
			String sqlProductInsert30 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0030','Nordic Cozy Aesthetics Fall Flats Mocha',799.00,"
					+ "'35,37,39,40,41','10,10,10,10,10','imgs/30a,imgs/30b',"
					+ "'Mateial : Faux Fur, Sole - TPR, Wear this faux fur nordic aesthetics flats "
					+ "with cute socks for your next coffee run. We bet these are the most comfotable pair of "
					+ "flats you will own.','Female,Others','Shoes-Flats','Winter Collection')";
			
			String sqlProductInsert31 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0031','Nordic Cozy Aesthetics Fall Platforms Pink',1099.00,'36,37,38',"
					+ "'10,10,10','imgs/31a,imgs/31b','Material : Faux Fur. Wear this faux fur nordic aesthetics "
					+ "platforms with cute socks for your next coffee run. We bet these are the most comfotable pair "
					+ "of platforms you will own.','Female,Others','Shoes-Platforms','Winter Collection,Valentine''s Day Collection,Best Sellers')";
			
			String sqlProductInsert32 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0032','High Street Wear Corset Style Green Hoodie',799.00,'XS,S,M,L,XL',"
					+ "'10,10,10,10,10','imgs/32a,imgs/32b','Material : Terry Fabric. Add little drama to your wardrobe "
					+ "with this corset styled hoodie. Wear yours with trousers or denim to compete the look.','Female,Others','Hoodies',"
					+ "'Winter Collection,Best Sellers')";
			
			String sqlProductInsert33 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0033','Showstopper Vibe Hot Pink Rhinestone Platform Heels',2399.00,'34,35,36,37,38,39,40',"
					+ "'10,10,10,10,10,10,10','imgs/33a,imgs/33b','Material :  Satin. Heel Height : 6 Inches. "
					+ "Embellished with a row of light reflecting rhinestones, these platform heels are having talk of the town moment.Style yours with sharp tonal tailoring.','Female,Others','Shoes-Platforms',"
					+ "'Winter Collection,Valentine''s Day Collection,Best Sellers')";
			
			String sqlProductInsert34 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0034','Black Textured Slingback Men''s Shoes',1499.00,"
					+ "'35,36,37,40','10,10,10,10','imgs/34a,imgs/34b','Material : Synthetic leather','Male,Others','Shoes-Loafers','Formal Clothing Range')";
			
			String sqlProductInsert35 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2) "
					+ "values ('P0035','Premium quality braided Moroccan vibe Black flats',799.00,"
					+ "'35,37,39,40,41','10,10,10,10,10','imgs/35a,imgs/35b',"
					+ "'Material: Synthetic leather. "
					+ "These pair of slide are everything a casual summer sandals should be. "
					+ "The cushioned insole make them idea for all day wear.','Female,Others','Shoes-Flats')";
			
			String sqlProductInsert36 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0036','Candy store aesthetics pop yellow slides',699.00,"
					+ "'35,37,39,40,41','10,10,10,10,10','imgs/36a,imgs/36b',"
					+ "'Material: Synthetic leather. "
					+ "Stylish & functional these pairs are made with soft synthetic leather. "
					+ "Wear yours with denim to dresses to sarees for an elevated casual look.','Female,Others','Shoes-Flats','Saraswati Puja Collection')";
			
			String sqlProductInsert37 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0037','Color Pop Orange comfort quilted slides',899.00,"
					+ "'35,36,37,39,40,41','10,10,10,10,10','imgs/37a,imgs/37b',"
					+ "'Material: Synthetic leather. "
					+ "These pair of color pop slides are everything a casual summer/saree sandals should be. "
					+ "The cushioned insole make them idea for all day wear.','Female,Others','Shoes-Flats','Saraswati Puja Collection')";
			
			String sqlProductInsert38 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0038','Diva Of Pinterest Rose Pumps',1999.00,'35,36,37,38,39,40',"
					+ "'10,10,10,10,10,10','imgs/38a,imgs/38b','Material: Satin. Heel Height : 6 Inches. Most loved pair in "
					+ "Hollywood right now ,this pumps are chicest addition to your wardrobe. "
					+ "Wear yours with a black dress and add some pearl.',"
					+ "'Female,Others','Shoes-Platforms','Winter Collection,New Year''s Collection,Valentine''s Day Collection,Best Sellers')";
			
			String sqlProductInsert39 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0039','Disco era vibe shimmer Y2K platforms green',1299.00,'35,36,37,38,39,40',"
					+ "'10,10,10,10,10,10','imgs/39a,imgs/39b','Material : Synthetic leather. Platform height : 3 Inches. "
					+ "Want little shine to your attire ? These gorgeous pair is what you need now. "
					+ "Pair yours with retro vibe attire & you are ready to rock.',"
					+ "'Female,Others','Shoes-Platforms','New Year''s Collection')";
			
			String sqlProductInsert40 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0040','Old Hollywood Star Aesthetics Pink Platforms',1299.00,'35,36,37,38,39,40',"
					+ "'10,10,10,10,10,10','imgs/40a,imgs/40b','Material : Patent Synthetic leather. Platform height : 3 Inches. "
					+ "Channel your inner Hollywood star vibe with this beautifully handcrafted platforms.',"
					+ "'Female,Others','Shoes-Platforms','New Year''s Collection')";
			
			String sqlProductInsert41 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0041','Back To School Bow Platform shoe',"
					+ "1799.00,'37,38,39,40,41','10,10,10,10,10','imgs/41a,imgs/41b','Crafted from supple synthetic "
					+ "leather & set on chunky soles that stand up to long days on the go . Wear these for your seal "
					+ "of approval for back to school aesthetics.','Male,Female,Others','Shoes-Back To School','Best Sellers')";
			
			String sqlProductInsert42 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2) "
					+ "values ('P0042','Back To School Aesthetics White Chain Shoe',"
					+ "1699.00,'37,38,39,40,41','10,10,10,10,10','imgs/42a,imgs/42b','Crafted from supple synthetic "
					+ "leather & set on chunky soles that stand up to long days on the go . Wear these for your seal "
					+ "of approval for back to school aesthetics.','Male,Female,Others','Shoes-Back To School')";
			
			String sqlProductInsert43 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2) "
					+ "values ('P0043','Back To School Boyfriend Style Brown Oxfords',"
					+ "1899.00,'37,38,39,40,41','10,10,10,10,10','imgs/43a,imgs/43b','Upper: Synthetic leather/ sole - TPR. "
					+ "Chunky loafers are having a moment-styling options are endless and they work all year round. "
					+ "Wear yours with dress or denim.','Male,Female,Others','Shoes-Back To School')";
			
			String sqlProductInsert44 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0044','Rib Button Drawstring Midi Dress Green',899.00,"
					+ "'S,M,L,XL','10,10,10,10','imgs/44a,imgs/44b','Perfect for daily casual wear or a party, "
					+ "this ribbed dresses are basic addition to your wardrobe. Wear yours with a button up open "
					+ "sweaters for the chilly evenings.','Female,Others','Dresses','New Year''s Collection')";
			
			String sqlProductInsert45 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0045','Neck Tie Marble Print Dress',799.00,"
					+ "'S,M,L,XL','10,10,10,10','imgs/45a,imgs/45b','Rock your next party look with this chic "
					+ "marvel print dress. Wear yours with platforms for 90s chic look.','Female,Others',"
					+ "'Dresses','Valentine''s Day Collection')";
			
			String sqlProductInsert46 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0046','Marble Print Trending Trouser',799.00,"
					+ "'XS,S,M,L,XL','10,10,10,10,10','imgs/46a,imgs/46b','Rock your Y2k soul in this brown marble print "
					+ "trouser. Comfortable & stylish , you will make a statement.','Female,Others',"
					+ "'Trousers','New Year''s Collection')";
			
			String sqlProductInsert47 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0047','Hello New Year Aesthetics White Top With Trouser',999.00,"
					+ "'XS,S,M,L,XL','10,10,10,10,10','imgs/47a,imgs/47b','Material : Soft Polyster. This set reinterprets "
					+ "classic suiting this season with ''70s-style separates that''ll become new wardrobe heroes. "
					+ "These pants are made from wool enhanced with a hint of stretch and have flared hems. "
					+ "Underpin yours with boots.','Female,Others','Tops,Trousers','New Year''s Collection')";
			
			String sqlProductInsert48 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0048','Psychedelic print pink casual Dress',799.00,"
					+ "'S,M,L,XL','10,10,10,10','imgs/48a,imgs/48b','Fabric : Polyster & spandex','Female,Others',"
					+ "'Dresses','Valentine''s Day Collection')";
			
			String sqlProductInsert49 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0049','Royal Green Knot Cocktail Dress',1145.00,"
					+ "'S,M,L,XL','10,10,10,10','imgs/49a,imgs/49b','Fabric : Polyster. Moderate Stretchy. Wear this chic cocktail "
					+ "dress to your New Year''s Eve Party and throw everyone''s eyesight on you !','Female,Others',"
					+ "'Dresses','New Year''s Collection')";
			
			String sqlProductInsert50 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0050','Niche Blue Frilly Knot Cocktail Dress',1400.00,"
					+ "'S,M,L,XL','10,10,10,10','imgs/50a,imgs/50b','Fabric : Polyster. Moderate Stretchy. "
					+ "Wear this chic cocktail dress to your New Year''s Eve Party and throw everyone''s eyesight on you !','Female,Others',"
					+ "'Dresses','New Year''s Collection,Best Sellers')";
			
			String sqlProductInsert51 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0051','Polka Dotted Knot Cocktail Dress',2299.00,"
					+ "'S,M,L,XL','10,10,10,10','imgs/51a,imgs/51b','Fabric : Polyster. Moderate Stretchy. "
					+ "Wear this chic cocktail dress to your New Year''s Eve Party and throw everyone''s eyesight on you !','Female,Others',"
					+ "'Dresses','New Year''s Collection')";
			
			String sqlProductInsert52 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0052','Princess Peach Simplicity A-line Dress',2400.00,"
					+ "'S,M,L,XL','10,10,10,10','imgs/52a,imgs/52b','Fabric : Polyster. Moderate Stretchy. "
					+ "Wear this chic cocktail dress to your New Year''s Eve Party and throw everyone''s eyesight on you !','Female,Others',"
					+ "'Dresses','New Year''s Collection')";
			
			String sqlProductInsert53 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0053','Pretty Pink Buttoned A-line Dress',2299.00,"
					+ "'S,M,L,XL','10,10,10,10','imgs/53a,imgs/53b','Fabric : Polyster. Throw this number on your date on "
					+ "Valentine''s day and have the most wonderful Valentine date.','Female,Others',"
					+ "'Dresses','Valentine''s Day Collection')";
			
			String sqlProductInsert54 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0054','Velvety Burgundy A-line Dress',2499.00,"
					+ "'S,M,L,XL','10,10,10,10','imgs/54a,imgs/54b','Fabric : Velvet. Throw this number on your date on "
					+ "Valentine''s day and have the most wonderful Valentine date.','Female,Others',"
					+ "'Dresses','Valentine''s Day Collection')";
			
			String sqlProductInsert55 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0055','Sparkly Red and Romantic Sheer Cocktail Dress',999.00,"
					+ "'S,M,L,XL','10,10,10,10','imgs/55a,imgs/55b','Fabric : Polyester. Throw this number on your date on "
					+ "Valentine''s day and have the most wonderful Valentine date.','Female,Others',"
					+ "'Dresses','Valentine''s Day Collection')";
			
			String sqlProductInsert56 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0056','Silk Red and Fur Sleeved Cocktail Dress',2499.00,"
					+ "'S,M,L,XL','10,10,10,10','imgs/56a,imgs/56b','Fabric : Silk. Throw this number on your date on "
					+ "Valentine''s day and have the most wonderful Valentine date.','Female,Others',"
					+ "'Dresses','Valentine''s Day Collection')";
			
			String sqlProductInsert57 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0057','Dark Red Velvet Ruched Cocktail Dress',2199.00,"
					+ "'S,M,L,XL','10,10,10,10','imgs/57a,imgs/57b','Fabric : Velvet. Throw this number on your date on "
					+ "Valentine''s day and have the most wonderful Valentine date.','Female,Others',"
					+ "'Dresses','Valentine''s Day Collection')";
			
			String sqlProductInsert58 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0058','Mustard Yellow Sweater',1299.00,"
					+ "'XS,S,M,XL','10,10,10,10','imgs/58a,imgs/58b','Material: Wool. Wear this warm, cozy sweater on chilly "
					+ "winter days. Can double as a modern saree top!','Female,Others','Tops','Saraswati Puja Collection')";
			
			String sqlProductInsert59 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0059','Knot Tied Pretty Yellow Top',699.00,"
					+ "'XS,S,M,XL','10,10,10,10','imgs/59a,imgs/59b','Material: Ribbed. Wear this pretty, sunshine yellow "
					+ "top on chilly winter days. Can double as a pretty modern saree blouse!','Female,Others','Tops','Saraswati Puja Collection')";
			
			String sqlProductInsert60 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0060','Sunshine Yellow Crop Top',699.00,"
					+ "'XS,S,M,XL','10,10,10,10','imgs/60a,imgs/60b','Material: Ribbed. Wear this pretty, sunshine yellow top"
					+ " on summer/spring days. Can double as a pretty modern saree blouse!','Female,Others','Tops','Saraswati Puja Collection')";

			String sqlProductInsert61 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0061','Plaid Black & White Tailored Suit',3999.00,"
					+ "'36,38,40,42,44','10,10,10,10,10','imgs/61a,imgs/61b','Material : Polyester Printed Plaid. "
					+ "Perfect for a casual formal look to impress your peers!','Male,Others','Suits','Formal Clothing Range')";
			
			String sqlProductInsert62 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0062','Mellow Blue Formal Blazer',4299.00,"
					+ "'36,38,40,42,44','10,10,10,10,10','imgs/62a,imgs/62b','Material : Linen. "
					+ "Perfect for an occasional formal look to impress your peers with soft tones!','Male,Others','Suits','Formal Clothing Range')";
			
			String sqlProductInsert63 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0063','Textured Men''s Wear Suit with Tailored Trousers',6999.00,"
					+ "'36,38,40,42,44','10,10,10,10,10','imgs/63a,imgs/63b','Material : Polyester Viscose. "
					+ "Official suitwear in the workplace to impress people by your presence!','Male,Others','Suits,Trousers','Formal Clothing Range')";
			
			String sqlProductInsert64 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0064','Tuxedo Vibe Suit with Tailored Trousers',7999.00,"
					+ "'36,38,40,42,44','10,10,10,10,10','imgs/64a,imgs/64b','Material : Cotton Blend. "
					+ "Official suitwear in the workplace to impress people by your presence! "
					+ "Doubles as a tux for wearing to weddings.','Male,Others','Suits,Trousers','Formal Clothing Range')";
			
			String sqlProductInsert65 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0065','Buttoned Up Black Suit with Tailor Fit Trousers',7299.00,"
					+ "'36,38,40,42,44','10,10,10,10,10','imgs/65a,imgs/65b','Material : Cotton Blend. "
					+ "Official suitwear in the workplace to impress people by your presence! "
					+ "Doubles as a tux for wearing to weddings.','Male,Others','Suits,Trousers','Formal Clothing Range')";
			
			String sqlProductInsert66 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0066','Mellow Yellow Pure Linen Suit',1999.00,"
					+ "'36,38,40,42,44','10,10,10,10,10','imgs/66a,imgs/66b','Material : Linen. "
					+ "Perfect for an occasional formal look to impress your peers with soft tones!','Male,Others','Suits','Formal Clothing Range')";
			
			String sqlProductInsert67 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0067','Velvet Black Top Suit',2999.00,"
					+ "'36,38,40,42,44','10,10,10,10,10','imgs/67a,imgs/67b','Material : Velvet. "
					+ "Perfect for a wedding formal look to standout in a crowd of wedding hassle!','Male,Others','Suits','Formal Clothing Range')";
			
			String sqlProductInsert68 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0068','Classic White Shirt',899.00,"
					+ "'36,38,40,42,44','10,10,10,10,10','imgs/68a,imgs/68b','Material : Cotton. "
					+ "Classic Formal Shirt that''s a staple in everyone''s wardrobe','Male,Others','Shirts','Formal Clothing Range')";

			String sqlProductInsert69 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0069','Classic Black Shirt',899.00,"
					+ "'36,38,40,42,44','10,10,10,10,10','imgs/69a,imgs/69b','Material : Cotton. "
					+ "Classic Formal Shirt that''s a staple in everyone''s wardrobe','Male,Others','Shirts','Formal Clothing Range')";

			String sqlProductInsert70 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0070','Formal Checked Grey Trousers',2799.00,"
					+ "'32,34,36,38,40','10,10,10,10,10','imgs/70a,imgs/70b','Material : Viscose. "
					+ "Comfortable yet incredibly formal looking work trousers that''s a staple in everyone''s wardrobe','Male,Others','Trousers','Formal Clothing Range')";

			String sqlProductInsert71 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0071','Formal Slim Fit Black Trousers',1999.00,"
					+ "'32,34,36,38,40','10,10,10,10,10','imgs/71a,imgs/71b','Material : Polyester Viscose. "
					+ "Comfortable yet incredibly formal looking work trousers that''s a staple in everyone''s wardrobe','Male,Others','Trousers','Formal Clothing Range')";

			String sqlProductInsert72 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0072','Formal Slim Fit Light Grey Trousers',1799.00,"
					+ "'32,34,36,38,40','10,10,10,10,10','imgs/72a,imgs/72b','Material : Linen. "
					+ "Comfortable yet incredibly formal looking work trousers that''s a staple in everyone''s wardrobe','Male,Others','Trousers','Formal Clothing Range')";

			String sqlProductInsert73 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0073','Formal Dark Grey Trousers',999.00,"
					+ "'32,34,36,38,40','10,10,10,10,10','imgs/73a,imgs/73b','Material : Linen. "
					+ "Comfortable yet incredibly formal looking work trousers that''s a staple in everyone''s wardrobe','Male,Others','Trousers','Formal Clothing Range')";

			String sqlProductInsert74 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0074','Slim Fit Navy Blue Trousers',1199.00,"
					+ "'32,34,36,38,40','10,10,10,10,10','imgs/74a,imgs/74b','Material : Cotton Stretch. "
					+ "Comfortable yet incredibly formal looking work trousers that''s a staple in everyone''s wardrobe','Male,Others','Trousers','Formal Clothing Range')";

			String sqlProductInsert75 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0075','Coffee Cream Formal Blazer',3799.00,"
					+ "'36,38,40,42,44','10,10,10,10,10','imgs/75a,imgs/75b','Material : Cotton. "
					+ "Perfect for an occasional formal look to impress your peers with soft tones!','Female,Others','Suits','Formal Clothing Range')";
			
			String sqlProductInsert76 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0076','Textured and Collared Grey Formal Blazer',4299.00,"
					+ "'36,38,40,42,44','10,10,10,10,10','imgs/76a,imgs/76b','Material : Cotton. "
					+ "Official suitwear in the workplace to impress people by your presence!','Female,Others','Suits','Formal Clothing Range')";
			
			String sqlProductInsert77 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0077','Stylish Olive Green Blazer',2499.00,"
					+ "'36,38,40,42,44','10,10,10,10,10','imgs/77a,imgs/77b','Material : Polyester. "
					+ "Perfect for an occasional formal look to impress your peers with dark tones!','Female,Others','Suits','Formal Clothing Range')";
			
			String sqlProductInsert78 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0078','Stylish Bark Shaded Brown Blazer',2199.00,"
					+ "'36,38,40,42,44','10,10,10,10,10','imgs/78a,imgs/78b','Material : Polyester. "
					+ "Perfect for an occasional formal look to impress your peers with dark tones!','Female,Others','Suits','Formal Clothing Range')";
			
			String sqlProductInsert79 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0079','Stylish Black Blazer',2499.00,"
					+ "'36,38,40,42,44','10,10,10,10,10','imgs/79a,imgs/79b','Material : Polyester. "
					+ "Perfect for an occasional formal look to impress your peers with dark tones!','Female,Others','Suits','Formal Clothing Range')";
			
			String sqlProductInsert80 = "insert ignore into product_table "
					+ "(product_id, p_name,price,sizes,stock,imgs,descr,cat1,cat2,cat3) "
					+ "values ('P0080','Stylish Checked and Collared Grey Suit',2499.00,"
					+ "'36,38,40,42,44','10,10,10,10,10','imgs/80a,imgs/80b','Material : Linen. "
					+ "Perfect for an occasional formal look to impress your peers!','Female,Others','Suits','Formal Clothing Range')";

		    Statement stmt = con.createStatement();
		    
			stmt.addBatch(sqlUserCreate);
            stmt.addBatch(sqlProductCreate);
            stmt.addBatch(sqlCartOrderCreate);
            stmt.addBatch(sqlAdminCreate);
            
            stmt.addBatch(sqlAdminInsert1);
            stmt.addBatch(sqlAdminInsert2);
            
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
            stmt.addBatch(sqlProductInsert31);
            stmt.addBatch(sqlProductInsert32);
            stmt.addBatch(sqlProductInsert33);
            stmt.addBatch(sqlProductInsert34);
            stmt.addBatch(sqlProductInsert35);
            stmt.addBatch(sqlProductInsert36);
            stmt.addBatch(sqlProductInsert37);
            stmt.addBatch(sqlProductInsert38);
            stmt.addBatch(sqlProductInsert39);
            stmt.addBatch(sqlProductInsert40);
            stmt.addBatch(sqlProductInsert41);
            stmt.addBatch(sqlProductInsert42);
            stmt.addBatch(sqlProductInsert43);
            stmt.addBatch(sqlProductInsert44);
            stmt.addBatch(sqlProductInsert45);
            stmt.addBatch(sqlProductInsert46);
            stmt.addBatch(sqlProductInsert47);
            stmt.addBatch(sqlProductInsert48);
            stmt.addBatch(sqlProductInsert49);
            stmt.addBatch(sqlProductInsert50);
            stmt.addBatch(sqlProductInsert51);
            stmt.addBatch(sqlProductInsert52);
            stmt.addBatch(sqlProductInsert53);
            stmt.addBatch(sqlProductInsert54);
            stmt.addBatch(sqlProductInsert55);
            stmt.addBatch(sqlProductInsert56);
            stmt.addBatch(sqlProductInsert57);
            stmt.addBatch(sqlProductInsert58);
            stmt.addBatch(sqlProductInsert59);
            stmt.addBatch(sqlProductInsert60);
            stmt.addBatch(sqlProductInsert61);
            stmt.addBatch(sqlProductInsert62);
            stmt.addBatch(sqlProductInsert63);
            stmt.addBatch(sqlProductInsert64);
            stmt.addBatch(sqlProductInsert65);
            stmt.addBatch(sqlProductInsert66);
            stmt.addBatch(sqlProductInsert67);
            stmt.addBatch(sqlProductInsert68);
            stmt.addBatch(sqlProductInsert69);
            stmt.addBatch(sqlProductInsert70);
            stmt.addBatch(sqlProductInsert71);
            stmt.addBatch(sqlProductInsert72);
            stmt.addBatch(sqlProductInsert73);
            stmt.addBatch(sqlProductInsert74);
            stmt.addBatch(sqlProductInsert75);
            stmt.addBatch(sqlProductInsert76);
            stmt.addBatch(sqlProductInsert77);
            stmt.addBatch(sqlProductInsert78);
            stmt.addBatch(sqlProductInsert79);
            stmt.addBatch(sqlProductInsert80);

		    stmt.executeBatch();
			
		}catch(Exception e) {}
		
		request.getRequestDispatcher("Login").include(request, response);
	}

}