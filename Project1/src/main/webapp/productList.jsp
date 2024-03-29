<!doctype html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <meta http-equiv="Expires" CONTENT="0">  
    <meta http-equiv="Cache-Control" CONTENT="no-cache">  
    <meta http-equiv="Pragma" CONTENT="no-cache">  
	<script>
        if(performance.navigation.type == 2){
            location.reload(true);
         }
    </script>
    <script src="https://code.jquery.com/jquery-3.3.1.js"
			integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
			crossorigin="anonymous">
	</script>
   

	
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    
	<script>
		$(document).on("scroll", function() {
			var pageTop = $(document).scrollTop();
			var pageBottom = pageTop + $(window).height();
			var tags = $(".tag");
		  
			for (var i = 0; i < tags.length; i++) {
			  var tag = tags[i];
			  if ($(tag).position().top < pageBottom) {
				$(tag).addClass("visible");
			  } else {
				$(tag).removeClass("visible");
			  }
			}
		  });
	</script>
    <script src="https://kit.fontawesome.com/13deb536c6.js" crossorigin="anonymous"></script>

	<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Product List</title>
    <link rel="icon" href="Pics/panda.png">
    
    <script>

        var parameter = "<%= request.getAttribute("param")%>"

        var prices = document.getElementsByClassName("list-price");
        var products = document.getElementsByClassName("list-padding");


        function filter(){
            var selected_gender = document.getElementById("gender_filter");
            var selected_size = document.getElementById("size_filter");
            var lp = document.getElementById("lower_price");
            var up = document.getElementById("upper_price");
            for (var i = 0; i < prices.length; i++) 
            {

                if(Number(up.value) < Number(lp.value)){
                    up.value = lp.value;
                }
                
                

                if (((up.value!="" || lp.value!="") && Number(prices[i].innerHTML.slice(1,-14)) > Number(up.value)) || Number(prices[i].innerHTML.slice(1,-14)) < Number(lp.value) ||!products[i].dataset.sizes.includes(selected_size.value) || !products[i].dataset.genders.includes(selected_gender.value))
                {
                    
                    products[i].style.display="none";
                }
                else {
                    products[i].style.display="block";
                }
                
        }
    }

    function param()
    {
        var selected_gender = document.getElementById("gender_filter");
        console.log(parameter);

        if(parameter == "Male")
        {
            console.log("It is Male");
            selected_gender.value = ",Male,";
            selected_gender.dispatchEvent(new Event("change"));
        }
        if(parameter == "Female")
        {
            console.log("It is Female");
            selected_gender.value = ",Female,";
            selected_gender.dispatchEvent(new Event("change"));
        }
        else
        {
            console.log("All");

        }
        
    }


    </script>

    <script>
  
        function sort_js()
        {
            var divList = $(".list-padding");
            var option = document.getElementById("sort");
            
            if(option.value == "PriceLowToHigh")
            {
                divList.sort(function(a, b){ return $(a).data("price")-$(b).data("price")});    
                $("#product-holder").html(divList);
            }
            else if(option.value == "PriceHighToLow")
            {
                divList.sort(function(a, b){ return $(b).data("price")-$(a).data("price")});
                $("#product-holder").html(divList);
            }
            else if(option.value == "NewestFirst")
            {
                divList.sort(function(a, b){ return $(b).data("pid").slice(1)-$(a).data("pid").slice(1)});
                $("#product-holder").html(divList);
            }
            else if(option.value == "OldestFirst")
            {
                divList.sort(function(a, b){ return $(a).data("pid").slice(1)-$(b).data("pid").slice(1)});
                $("#product-holder").html(divList);
            }
        }

    </script>

    <script>
		$(function () {
			$("#header").load("header.jsp");
			$("#footer").load("footer.html");
		});
	</script>

    <link rel="stylesheet" href="Bootstrap/CSS/style1.css">



</head>
<body onload="param()">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>	

    <div class="font">
    <div class="container-fluid">
        <div id="header"></div>
   	    <div class="header-adjustment"></div>
           <div class="product-category"><i class="fa-solid fa-arrow-right fa-sm"></i>&nbsp&nbsp<%= request.getAttribute("category_name")%></div>
           <br>
           <br>
        
            
        <div class="row justify-content-center">
            <div class="col" style="margin-bottom: 20px;">
                <div class="filters">
                    <h6>Price Range</h6>
                    
                   
                            <input name="lower_price" type = "number" class = "form-control"  id = "lower_price" min="100" placeholder="Min." onchange = "filter()" required/>
                        
                            <input name="upper_price" type = "number" class = "form-control"  id = "upper_price" min="100" placeholder="Max." onchange = "filter()" required/>
                        
                    <br>
                    
                    <h6>Size</h6>
                    <select id ="size_filter" class="form-select" name = "size_filter" onchange = "filter()" required>
                        <option selected value="">All</option>
                        <option value=",XS,">XS</option>
                        <option value=",S,">S</option>
                        <option value=",M,">M</option>
                        <option value=",L,">L</option>
                        <option value=",XL,">XL</option>
                        <option value=",34,">34</option>
                        <option value=",35,">35</option>
                        <option value=",36,">36</option>
                        <option value=",37,">37</option>
                        <option value=",38,">38</option>
                        <option value=",39,">39</option>
                        <option value=",40,">40</option>
                        <option value=",41,">41</option>
                        <option value=",42,">42</option>
                        <option value=",43,">43</option>
                        <option value=",44,">44</option>
                        <option value=",45,">45</option>
                    </select>

                    

                    <br>

                    <h6>Gender</h6>
                        <select id ="gender_filter" class="form-select" name = "gender" onchange = "filter()"  onload = "param()" required>
                            <option selected value="">All</option>
                            <option value=",Male,">Male</option>
                            <option value=",Female,">Female</option>
                        </select>


                        
                        <br>
                        <h6>Sort</h6>
                        <select id ="sort" class="form-select" name = "sort" onchange = "sort_js()" required>
                            <option value="PriceLowToHigh">Price: Low to High</option>
                            <option value="PriceHighToLow">Price: High to Low</option>
                            <option value="NewestFirst" selected>Newest first</option>
                            <option value="OldestFirst">Oldest first</option>
                        </select>
                   

                </div>
            </div>
      

            <div class="col-10">
                <div class="card-container">
                
                    <div class="row justify-content-center" id="product-holder">

                        <%= request.getAttribute("product_cards")%>
    
                        <br>
                        <br>
                    
                        <h4 style="padding-top:40px;"> <i class="fa-solid fa-circle-check fa-lg"></i>&nbsp&nbspThat's all!</h4>
                
                    </div>
                </div>
            </div>
        </div>


    </div>
    </div>
    
<div id="footer"></div>
</body>
</html>
