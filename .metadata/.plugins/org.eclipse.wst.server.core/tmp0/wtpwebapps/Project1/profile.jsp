<!doctype html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <script src="https://code.jquery.com/jquery-3.3.1.js"
			integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
			crossorigin="anonymous">
	</script>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link rel="stylesheet" href="Bootstrap/CSS/style1.css">
    
	<script>
		$(function () {
			$("#header").load("header.jsp");
			$("#footer").load("footer.html");
		});
	</script>
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Profile</title>
    <link rel="icon" href="Pics/panda.png">

	<script>
		var details = document.getElementsByClassName("admin");

		

		function show_details()
		{
			console.log("details");
			if(details[0].style.display == "none")
				details[0].style.display="block";
			else
                details[0].style.display = "none";
		}

		var new_address;
		var old_address;
		var res;

		function address_edit()
		{
			var regex = "^\\s+$";
			var edit_button = document.getElementById("edit-button");
			var submit_button = document.getElementById("submit-button");
			var address_box = document.getElementById("new-address-box");
			
			address_box.value = address.innerHTML;
			old_address = address_box.value;
			address.innerHTML = '';
			address_box.style.display = "block";
			address.style.display = "none";
			edit_button.style.display = "none";
			submit_button.style.display = "block";
		}

		function change_address()
		{
			var edit_button = document.getElementById("edit-button");
			var submit_button = document.getElementById("submit-button");
			var address_box = document.getElementById("new-address-box");
			

			new_address = address_box.value;
			address_box.style.display = "none";
			address.style.display = "block";
			edit_button.style.display = "block";
			submit_button.style.display = "none";
			
			var status = document.getElementById("status");


			if(new_address == old_address || new_address =="")
			{
				address.innerHTML = old_address;
			}
			else
			{
				

				address.innerHTML = new_address;
				var http = new XMLHttpRequest();
            	http.open("POST", "Profile", true);
            	http.setRequestHeader("Content-type","application/x-www-form-urlencoded");
            	var params = "new_address=" + new_address;
            	http.send(params);
            	

				http.onreadystatechange = function() {
					if (http.readyState == XMLHttpRequest.DONE) {
						console.log(http.response);
						
						res = Number(http.response.toString());

						if (res == 1) 
						{
							status.style.color = "green";
							status.innerHTML = "Address changed successfully!";
							console.log(res);
						}
						else
						{
							status.style.color = "red";
							status.innerHTML = "Sorry, could't change your address.";
							console.log(res);
						}
					}
				}
			}
		}
	</script>
    
</head>
<body>



    <div class="font">
		<div class="container-fluid">
			<div id="header"></div>
			   <div class="header-adjustment"></div>
                <br>
				<br>
                <h1 class="profile-heading">Welcome <%= request.getAttribute("first_name")%>!</h1>
			    <div class="profile-buttons">
					<button type="sub" class="btn bamboo form-submit" onclick="show_details()">Details</button>
					
					<a href="Cart" type="sub" class="btn form-submit bamboo">View Cart</a>
					<a href="Order?job=previous" type="sub" class="btn form-submit bamboo">View Orders</a>

					<a href="Logout">
						<button type="sub" class="btn btn-outline-danger form-submit">Logout</button>
					</a>
				</div>
				
				<div class="admin" style="display:none;">
			   	   <div class="admin-head">
				   	   <div class="row">
						<h2>Your details...</h2>
						   <div class="col">
							   <div class="admin-text">
							   		<div class="row">
					    				<div class="col-3 admin-col">Name:</div>
					    				<div class="col-4 admin-col"><%= request.getAttribute("first_name")%> <%= request.getAttribute("last_name")%></div>
				 					</div>
				 					<div class="row">
										<div class="col-3 admin-col">Email:</div>					
					 					<div class="col-4 admin-col"><%= request.getAttribute("email")%></div>
				 					</div>
				 					<div class="row">
					    				<div class="col-3 admin-col">Phone:</div>
					 					<div class="col-4 admin-col"><%= request.getAttribute("phone")%></div>
				 					</div>
				 					<div class="row">
					    				<div class="col-3 admin-col">DOB:</div>
					 					<div class="col-4 admin-col"><%= request.getAttribute("dob")%></div>
				 					</div>
				 					<div class="row">
					    				<div class="col-3 admin-col">Gender:</div>
					 					<div class="col-4 admin-col"><%= request.getAttribute("gender")%></div>
				 					</div>
				 					<div class="row">
					    				<div class="col-3 admin-col">Address:</div>
					 					<div class="col-7 admin-col">

											<div id="address"><%= request.getAttribute("address")%></div>
											<textarea name="address" class="form-control" id="new-address-box" rows="3" style="display:none;" required></textarea>
											<p id="status" style="font-size: 15px;"></p>
										</div>
										<div class="col-2">
											<i class="fa-solid fa-pen-to-square fa-xl" id="edit-button" onclick="address_edit()"></i>
											
											<button type="sub" class="btn bamboo" id="submit-button" style="display:none" onclick="change_address()">Submit</button>
											
										</div>
				 					</div>
							   </div>
						  </div>
					  </div>
				 </div>
			   </div>
			   <br>
		</div>
	</div>
<div id="footer"></div>
</body>
</html>
