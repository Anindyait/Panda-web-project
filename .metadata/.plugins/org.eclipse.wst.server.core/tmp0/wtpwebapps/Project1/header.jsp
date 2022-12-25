<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Header</title>
	<link rel="icon" href="Pics/panda.png">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link rel="stylesheet" href="Bootstrap/CSS/style1.css">
</head>
<body>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    <script>
        const icon = document.getElementsByClassName('.icon');
        const search = document.getElementsByClassName('.search');

        search.onclick = function(){
            search.classList.toggle('active')
        }
    </script>
    <div class="font">
        
        <nav class="navbar navbar-expand-lg header">
            <!--Store name and Logo-->

            <a class="navbar-brand" style="text-decoration:none" href="#">
                <div class="row">
                    <div class="col">
                        <img class="brand-img" src="Pics/panda.png" height="65">
                    </div>
                    <div class="col">
                        <div class="brand-name">Panda Shop</div>
                    </div>
                </div>
            </a>

            <!--Collapsed NavBars toggle-->
            <div class="menu-button-padding">
                <button class="navbar-toggler round" type="button" data-bs-toggle="collapse" data-bs-target="#NavBar" aria-expanded="false" aria-controls="#NavBar" aria-label="Toggle navigation" ><span class="navbar-toggler-icon"></span></button>

            </div>
           
            <!--Actual NavBar-->
            <div class="collapse navbar-collapse nav-part" id="NavBar">

                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a href="#" class="nav-link nav-link1 active">Home</a>
                    </li>

                    <li class="nav-item">
                        <a href="#" class="nav-link nav-link1 active">Offers</a>
                    </li>

                    <li class="nav-item">
                        <a href="#" class="nav-link nav-link1 active">Products</a>
                    </li>


                    <li class="nav-item dropdown">
                        <a class="nav-link nav-link1 dropdown-toggle active" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Dropdown
                        </a>
                        <ul class="dropdown-menu" style="border-radius:30px;">
                            <li class="nav-item"><a class="dropdown-item nav-link1" href="#">Action</a></li>
                            <li class="nav-item"><a class="dropdown-item nav-link1" href="#">Another action</a></li>
                            <li class="nav-item"><hr class="dropdown-divider nav-link1"></li>
                            <li class="nav-item"><a class="dropdown-item nav-link1" href="#">Something else here</a></li>
                        </ul>
                    </li>

                </ul>
             
            

        </nav>
        <div class="nav-item right-icons">
            <a href="#cart" class="right-links"><img src = "Pics/icons8-shopping-cart-50.png" class="cart-icon"></a>

            <!-- <input type="button"  value="   " data-bs-toggle="collapse" class="btn btn-secondary login-button round"/> -->

            <a href="Profile" class="right-links"><img class ="profile-icon" src="Pics/icons8-male-user-50<%= request.getAttribute("logged_in")%>.png" class="profile-icon"></a>
            <!--<a href="Profile" class="right-links"><img class ="profile-icon" src="Pics/icons8-male-user-50-ticked.png" class="profile-icon"></a>-->

            <input type="checkbox" id="box" style="visibility:hidden"/>
            <label  id="search" for="box"><input type="text" class="search-textbox"/><img src="Pics/search1.png" width="47px"></label>
            
        </div>
    </div>
</body>
</html>