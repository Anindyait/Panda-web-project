# Online Shopping Website in JAVA Using Servlet, JSP, JDBC, MySQL And Tomcat

<!-- PROJECT LOGO -->
<br />
<div align="center">
    <img src="Project1/src/main/webapp/Pics/panda.png" alt="Logo" width="80" height="80">
    <h3 align="center">The Panda Shop</h3>
    <p align="center">
        <!--PROJECT DESCRIPTION--!>
    </p>
</div>

## Requirements

Install the following as per your system's requirements:

- [Eclipse for Enterprise and Java Developers v2022-06](https://www.eclipse.org/downloads/packages/release/2022-06/r)
- [Tomcat v9.0](https://tomcat.apache.org/download-90.cgi)
- [MySQL v8.0](https://dev.mysql.com/downloads/installer/)

## Usage

1.  Clone the repo
   ```sh
   git clone https://github.com/Anindyait/Panda-web-project
   ```
2. Import Project1 into Eclipse
3. Run Startup.java on the Tomcat Server to get the database created.
4. You can login with a dummy user or register a user.

   - Dummy User Credentials  
     
     Email: 
     ```
     dummy@panda.com
     ```
     Password: 
     ```
     Qwerty1@
     ```

Now you can locally host the shopping website.

## Features

- User Registration and HTTP Cookie Based Login
- Product Search implemented
- Products can be sorted by size and gender
- Cart functionality
- Checkout Page
- Previous Orders Page
- Admin Functionality
   - Edit a Product
   - Delete a Product
   - See all Products


## File Structure

```
.
├── LICENSE ................................ Distributed under the MIT License.
├── Project1
│   ├── build
│   └── src
│       └── main
│           ├── java
│           │   └── pkg .................... Java package with Servlets that handle
│           │       │                        POST & GET requests for concerned jobs
│           │       ├── AdminLogin.java
│           │       ├── AdminLogout.java
│           │       ├── AdminProfile.java
│           │       ├── Cart.java
│           │       ├── Header.java
│           │       ├── Login.java
│           │       ├── Logout.java
│           │       ├── Order.java
│           │       ├── Product.java
│           │       ├── product.jsp
│           │       ├── ProductList.java
│           │       ├── Profile.java
│           │       ├── Register.java
│           │       ├── Search.java
│           │       ├── Startup.java ....... Responsible for initializing the database tables
│           │       └── Utilities.java
│           └── webapp ..................... Includes html, css, js, jsp files for displaying the webpages
│               ├── aboutUs.html
│               ├── addToCart.js
│               ├── adminLogin.html
│               ├── adminLogin.jsp
│               ├── adminProfile.html
│               ├── adminProfile.jsp
│               ├── Bootstrap
│               │   ├── CSS
│               │   │   └──style1.css
│               │   └── js
│               ├── cart.html
│               ├── cart.jsp
│               ├── checkout.html
│               ├── checkout.jsp
│               ├── delProduct.html
│               ├── delProduct.jsp
│               ├── editProduct.html
│               ├── editProduct.jsp
│               ├── error.html
│               ├── FAQ.html
│               ├── footer.html
│               ├── header.html
│               ├── header.jsp
│               ├── Index.html
│               ├── login.html
│               ├── login.jsp
│               ├── orderConfirmed.html
│               ├── order.html
│               ├── order.jsp
│               ├── paymentGate.html
│               ├── paymentGate.jsp
│               ├── Pics ................... Contains all banners and product images
│               ├── postRegister.html
│               ├── privacy.html
│               ├── productExperiment.jsp
│               ├── product.html
│               ├── product.jsp
│               ├── productList.html
│               ├── productList.jsp
│               ├── profile.html
│               ├── profile.jsp
│               ├── register.html
│               ├── register.jsp
│               ├── returnExchange.html
│               ├── seeProduct.html
│               ├── seeProduct.jsp
│               └── WEB-INF
│                   ├── lib
│                   │   └── mysql-connector-java-8.0.30.jar
│                   └── web.xml
├── README.md
└── Servers
    └── Tomcat v9.0 Server at localhost-config
```
