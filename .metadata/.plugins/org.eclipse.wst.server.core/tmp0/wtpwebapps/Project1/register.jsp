<!doctype html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <script src="https://code.jquery.com/jquery-3.3.1.js"
			integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
			crossorigin="anonymous">
	</script>
	<script>
		$(function () {
			$("#header").load("header.html");
			$("#footer").load("footer.html");
		});
	</script>

    <!--DOB-->

    <script>
        var Days = [31,28,31,30,31,30,31,31,30,31,30,31];// index => month [0-11]
$(document).ready(function(){
    var option = '<option selected value="" disabled>day</option>';
    var selectedDay="day";
    for (var i=1;i <= Days[0];i++){ //add option days
        option += '<option value="'+ i + '">' + i + '</option>';
    }
    $('#day').append(option);
    $('#day').val(selectedDay);

    var option = '<option selected disabled value="" >month</option>';
    var selectedMon ="month";
    for (var i=1;i <= 12;i++){
        option += '<option value="'+ i + '">' + i + '</option>';
    }
    $('#month').append(option);
    $('#month').val(selectedMon);

    var option = '<option selected disabled value="">month</option>';
    var selectedMon ="month";
    for (var i=1;i <= 12;i++){
        option += '<option value="'+ i + '">' + i + '</option>';
    }
    $('#month2').append(option);
    $('#month2').val(selectedMon);
  
    var d = new Date();
    var option = '<option selected disabled value="">year</option>';
    selectedYear ="year";
    for (var i=1930;i <= new Date().getFullYear();i++){// years start i
        option += '<option value="'+ i + '">' + i + '</option>';
    }
    $('#year').append(option);
    $('#year').val(selectedYear);
});
function isLeapYear(year) {
    year = parseInt(year);
    if (year % 4 != 0) {
	      return false;
	  } else if (year % 400 == 0) {
	      return true;
	  } else if (year % 100 == 0) {
	      return false;
	  } else {
	      return true;
	  }
}

function future_date()
{
    var currentDate = new Date();
    var year =$("#year").val();
    if(year == currentDate.getFullYear())
    {
        console.log("current year");
        if($("#month").val() >= (currentDate.getMonth()+1) && $("#day").val() > currentDate.getDate())
        {
            console.log("future");
            var d = currentDate.getDate();
            var m = currentDate.getMonth()+1;
            $(day).val(d);
            $(month).val(m);
        }
    }
   
}

function change_year(select)
{
    if( isLeapYear( $(select).val() ) )
	{
		Days[1] = 29;	    
    }
    else {
        Days[1] = 28;
    }
    if( $("#month").val() == 2)
	{
		var day = $('#day');
		var val = $(day).val();
		$(day).empty();
		var option = '<option disabled selected value="day">day</option>';
		for (var i=1;i <= Days[1];i++){ //add option days
		option += '<option value="'+ i + '">' + i + '</option>';
    }
	$(day).append(option);
	if( val > Days[ month ] )
	{
		val = 1;
	}
   
		$(day).val(val);
	}
}

function change_month(select) {
    var day = $('#day');
    var val = $(day).val();
    $(day).empty();
    var option = '<option disabled selected value="day">day</option>';
    var month = parseInt( $(select).val() ) - 1;
    for (var i=1;i <= Days[ month ];i++){ //add option days
        option += '<option value="'+ i + '">' + i + '</option>';
    }
    $(day).append(option);
    if( val > Days[ month ] )
    {
        val = 1;
    }
    $(day).val(val);
}
    </script>


	
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Sign Up</title>
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link rel="stylesheet" href="Bootstrap/CSS/style1.css">
    
</head>
<body>



    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    
    <div class="font">
    <div class="container-fluid">
    <div id="header"></div>
   	<div class="header-adjustment"></div>
        <div class = "form-holder">
            <h2 style="font-size: 45px;">Fill up your details...</h2>
            <br>

            <div class="server-side-check" <%= request.getAttribute("existing_phone") %> >Phone no. already exists!</div>
            <div class="server-side-check" <%= request.getAttribute("existing_email") %> >Email ID already exists!</div>


            <form action="Register" name="form1" class="needs-validation" onsubmit="return pwd_validation()" novalidate method="post">
                <div class="row g-2 input-row">
                    <div class="col-sm" style="padding-right: 20px;">
                        <div class="form-floating mb-3" >
                            <input name="first-name" type = "text" class = "form-control"  id = "first-name" placeholder="Mike" required>
                            <label for = "first-name" style="opacity:70%;">First name</label> 
                            <div class="invalid-feedback">
                                Enter first name
                            </div>
                            <div class="valid-feedback">
                                Looks good!
                            </div>
                        </div>
                    </div>

                    <div class="col-sm">
                        <div class="form-floating mb-3" >
                            <input name="last-name" type = "text" class = "form-control"  id = "last-name" placeholder="Hunt" required>
                            <label for = "last-name" style="opacity:70%;">Last name</label>
                            <div class="invalid-feedback">
                                Enter last name
                            </div>
                            <div class="valid-feedback">
                                Looks good!
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row g-2 input-row">
                    <div class="col-sm"  style="padding-right: 20px;">
                        <div class="form-floating mb-3" >
                             <input name="email" type = "email" class = "form-control"  id = "email" placeholder="mikehunt@panda.com" required>
                             <label for = "email" style="opacity:70%;">Email ID</label> 

                             <div class="invalid-feedback">
                                Enter valid email ID
                            </div>
                            <div class="valid-feedback">
                                Looks good!
                            </div>
                        </div>
                    </div>

                    <div class="col-sm">
                            <div class="form-floating mb-3" >
                                <input name="phone" type = "tel" class = "form-control"  id = "phone" pattern="^(\+91[\-\s]?)?[0]?(91)?[2-9]\d{9}$" placeholder="123456789" required>
                                <label for = "phone" style="opacity:70%;">Phone No.</label> 

                                <div class="invalid-feedback">
                                    Enter valid phone no.
                                </div>
                                <div class="valid-feedback">
                                    Looks good!
                                </div>
                            </div>
                    </div>

                </div>


                        <div class="row g-2 input-row">
                            <div class="col-sm" style="padding-right: 20px;">

                                <div class="row">
                                    <label class="form-label" style="opacity:90%;">DOB</label>
                                    <div class="col-sm">
                                        <select id ="day" class="form-select" name = "dd" required>
                                        </select>
                                        
                                    </div>

                                    <div class="col-sm">    
                                        <select  id ="month" class="form-select" name = "mm" onchange="change_month(this)" required>
                                        </select>
                                    </div>

                                    <div class="col-sm">
                                        <select id ="year" class="form-select" name = "yyyy" onchange="change_year(this)" required>
                                        </select>
                                    </div>
                                </div>
                            
                            </div>

                            
                            <div class="col-sm">
                                <label class="form-label" style="opacity:90%;">Gender</label>
                                <select id ="gender" class="form-select" name = "gender" required>
                                    <option selected value="" disabled>Choose...</option>
                                    <option value="male">Male</option>
                                    <option value="female">Female</option>
                                    <option value="others">Casio WS-1400H-4AVDF-D272</option>
                                </select>
                                <div class="invalid-feedback">
                                    Enter your gender
                                </div>
                                <div class="valid-feedback">
                                    Looks good!
                                </div>
                            </div>
                        </div>
                        
                        <div class="row g-2 input-row">
                            <div class="col">
                                <label for="exampleFormControlTextarea1" class="form-label" style="opacity:70%;">Address</label>
                                <textarea name="address" class="form-control" id="exampleFormControlTextarea1" rows="3" required></textarea>
                                <div class="invalid-feedback">
                                    Enter your address
                                </div>
                                <div class="valid-feedback">
                                    Looks good!
                                </div>
                            </div>
                        </div>

                        <div class="row g-2 input-row" style="padding-top: 30px;">
                            <div class="col-sm" style="padding-right: 20px;">
                                <div class="form-floating mb-3">
                                    <input name="password" type = "password" class = "form-control"  id = "password1" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,12}$" placeholder="1234" required>
                                    <label for = "password" style="opacity:70%;">Password</label> 
                                    
                                    <div class="invalid-feedback">
                                    Enter valid password
	                                </div>
	                                <div class="valid-feedback">
	                                Looks good!
	                                </div>
                                </div>
                            </div>

                            <div class="col-sm">
                                <div class="form-floating mb-3" >
                                    <input type = "password" class = "form-control"  id = "password2" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,12}$" placeholder="1234" required>
                                    <label for = "confirm-password" style="opacity:70%;">Confirm Password</label> 
                                    <span class="invalid-feedback" id="pwd-match">
                                        
                                    </span>
                                </div>
                            </div>

                        </div>
                        
                        <div class="col-sm" style="padding: 20px 1px;">
                            <div class="form-floating mb-3" >
                                <button type="submit" class="btn btn-dark form-submit">Sign up</button>
                            </div>
                        </div>
                
            </form>

        </div>
    </div>
</div>
<!---->
<script>
    // Example starter JavaScript for disabling form submissions if there are invalid fields
(() => {
'use strict'

// Fetch all the forms we want to apply custom Bootstrap validation styles to
const forms = document.querySelectorAll('.needs-validation')


// Loop over them and prevent submission
Array.from(forms).forEach(form => {
  form.addEventListener('submit', event => {

    if (!form.checkValidity()) {
      event.preventDefault()
      event.stopPropagation()
    }
    

    form.classList.add('was-validated')

  }, false)
})

})()
</script>
<script>
    function pwd_validation(){
        var pw1 = document.getElementById("password1").value;
        var pw2 = document.getElementById("password2").value;

        if(pw1 != pw2){

            document.getElementById("pwd-match").innerHTML="Password didn't match";
            document.getElementById("password1").value="";
            document.getElementById("password2").value="";
            return false;
        }
        else{
            return true;
        }
    }
</script>
<div id="footer"></div>
</body>
</html>