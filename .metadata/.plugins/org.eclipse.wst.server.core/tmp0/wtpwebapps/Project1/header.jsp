<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Header</title>
	<link rel="icon" href="Pics/panda.png">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    <script src="https://kit.fontawesome.com/13deb536c6.js" crossorigin="anonymous"></script>

    
    <link rel="stylesheet" href="Bootstrap/CSS/style1.css">
</head>
<body>
    <script>
        const icon = document.getElementsByClassName('.icon');
        const search = document.getElementsByClassName('.search');

        search.onclick = function(){
            search.classList.toggle('active')
        }
    </script>
    
    <script>
        $(document).ready(function() {
            $(".dropdown-toggle").dropdown();
        });
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
                        <a href="aboutUs.html" class="nav-link nav-link1 active">About Us</a>
                    </li>

                    <li class="nav-item">
                        <a href="#" class="nav-link nav-link1 active">Products</a>
                    </li>
                    
                    <div class="nav-item dropdown">
                        <div class="nav-link nav-link1 dropdown-toggle active" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Dropdown
                        </div>
                        <div class="dropdown-menu" style="border-radius:20px;">
                            <a class="dropdown-item" href="#">Action</a>
                            <a class="dropdown-item" href="#">Another action</a>
                            <a class="dropdown-item" href="#">Something else here</a>
                        </div>
                    </div>


                </ul>
            </div>
             
            

        </nav>


        <div class="nav-item right-icons">
            <i class="fa-solid fa-magnifying-glass fa-xl" data-bs-toggle="modal" data-bs-target="#searchModal"></i>

            <!--Search Modal-->
            <div class="modal fade search" id="searchModal" tabindex="-1" aria-labelledby="searchModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content search">
                        <form>
                        <div class="row">
                            <div class="col">
                                <input name="search-text" type = "text" class = "form-control"  id = "search-text" placeholder="Search..." required>
                            </div>
                            <div class="col-2">
                                <button type="submit" class="btn bamboo rounded-5"><i class="fa-solid fa-magnifying-glass fa-lg"></i></button>
                            </div>
                            
                            <div class="col-1">
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" style="margin-top:6px; margin-left:-15px;"></button>
                            </div>
                        </div>
                        </form>
                        
                    </div>
                </div>
            </div>

            <a href="#cart" class="right-links"><i class="fa fa-cart-shopping fa-xl"></i></a>

            <!-- <input type="button"  value="   " data-bs-toggle="collapse" class="btn btn-secondary login-button round"/> -->
            <a href="Profile" class="right-links"><i class="fa-solid fa-user fa-xl"></i></a>
            <a href="Profile" class="right-links"><img class ="profile-icon" src="Pics/icons8-male-user-50.png" class="profile-icon" hidden></a>

            
        </div>

    </div>
</body>
</html>