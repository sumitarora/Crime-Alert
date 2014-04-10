<%@ include file="common/header.jsp"%>
   <!-- Full Page Image Header Area -->
    <div id="top" class="header">
        <div class="vert-text">
            <h1>Crime Alert</h1>
            <h3>
                <em>Report</em> All crimes and complaints,
                <em>You</em> Make Locality Better</h3>
                
            <sec:authorize access="isAnonymous()">
	            <a href="register" class="btn btn-default btn-lg">Sign Up</a>
	            <a href="login" class="btn btn-default btn-lg">Sign In</a>			    
			</sec:authorize>
			<sec:authorize access="isAuthenticated()">
				<a href="home" class="btn btn-default btn-lg">Home</a>
			</sec:authorize>			

        </div>
    </div>
    <!-- /Full Page Image Header Area -->

    <!-- Intro -->
    <div id="about" class="intro">
        <div class="container">
            <div class="row">
                <div class="col-md-6 col-md-offset-3 text-center">
                    <h2>Crime alert offers a 24/7 online facility to report a crime/complaint</h2>
                    <p class="lead">Reporting a crime or complaint made easy with Crime-Alert. Crime hotspots in Canada revealed. <a href="register">Register to view!!!</a></p>
                </div>
            </div>
        </div>
    </div>
    <!-- /Intro -->

    <!-- Services -->
    <div id="services" class="services">
        <div class="container">
            <div class="row">
                <div class="col-md-12 text-center">
                    <h2>Our Team</h2>
                    <hr>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4 text-center">
                    <div class="service-item">
                        <!-- <i class="service-icon fa fa-rocket"></i> -->
                        <img class="img-circle" src="http://m.c.lnkd.licdn.com/mpr/mpr/shrink_200_200/p/1/000/1a2/01b/3063a10.jpg" />
                        <h4>Sumit Arora</h4>
                        <p>Sumit is currently finishing up his Masters in Computer Science. He loves to code, having worked for JP Morgan, Cognizant, and Net Solutions. Sumit's major interest lies in data visualization. Karma is the lead influence in his life.</p>
                    </div>
                </div>
                <div class="col-md-4 text-center">
                    <div class="service-item">
                        <img class="img-circle" style="width:200px;height:200px;" src="resources/img/pic.jpg"></img>
                        <h4>Kiranjot Jheetey</h4>
                        <p>Developer in Java, HTML5, CSS , Kiran loves to try out new things.Currently gaining a degree in Softwares, she aims to be an independent and freelance developer.</p>
                    </div>
                </div>
                <div class="col-md-4 text-center">
                    <div class="service-item">
                        <img class="img-circle" style="width:200px;height:200px;" alt="" src="https://lh3.googleusercontent.com/-VPB3QCh0idM/UyIkId74rCI/AAAAAAAAAVQ/K2TLr6GkBjE/s283-no/DSC00821.JPG">
                        <h4>Jagroop Singh Pandher</h4>
                        <p>Planning a time travel trip to the middle ages? Preserve the space time continuum by blending in with period accurate armor and weapons.</p>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4 text-center">
                    <div class="service-item">
                        <img class="img-circle" style="width:200px;height:200px;" src="resources/img/asd.jpg"></img>
                        <h4>Deepanshu Dhir</h4>
                        <p>Deepanshu is currently pursuing his degree in Softwares. Having worked in Java and Android project,he is now shifting to Databases and JQuery to become an independent developer. Impossible Says I M POSSIBLE</p>
                    </div>
                </div>
                <div class="col-md-4 text-center">
                    <div class="service-item">
                        <img class="img-circle" style="width:200px;height:200px;" src="http://www.american.edu/uploads/profiles/large/chris_palmer_profile_11.jpg"></img>
                        <h4>Satnam Singh</h4>
                        <p>Need to know how magnets work? Our problem solving solutions team can help you identify problems and conduct exploratory research.</p>
                    </div>
                </div>
                <div class="col-md-4 text-center">
                    <div class="service-item">
                        <img class="img-circle" style="width:200px;height:200px;" alt=""  src="https://lh4.googleusercontent.com/-BmGtNXIX6Cs/U0Xi1mvEliI/AAAAAAAAANI/YfvXUW4pQuE/s512/Untitled.jpg">
                        <h4>Gurnek Singh Sandhu</h4>
                        <p>Gurnek is pursuing Diploma in Health Informatics, completed B. Tech in Computer Science in 2012. Have worked for Crezone Tech. Hard work in life is forever like Diamond.</p>
                    </div>
                </div>
            </div>            
        </div>
    </div>
    <!-- /Services -->

    <!-- Callout -->
    <div class="callout">
        <div class="vert-text">
            <h1>All Info at one Place</h1>
        </div>
    </div>
    <!-- /Callout -->

<!--     Portfolio
    <div id="portfolio" class="portfolio">
        <div class="container">
            <div class="row">
                <div class="col-md-4 col-md-offset-4 text-center">
                    <h2>Our Work</h2>
                    <hr>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4 col-md-offset-2 text-center">
                    <div class="portfolio-item">
                        <a href="#">
                            <img class="img-portfolio img-responsive" src="img/portfolio-1.jpg">
                        </a>
                        <h4>Cityscape</h4>
                    </div>
                </div>
                <div class="col-md-4 text-center">
                    <div class="portfolio-item">
                        <a href="#">
                            <img class="img-portfolio img-responsive" src="img/portfolio-2.jpg">
                        </a>
                        <h4>Is That On Fire?</h4>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4 col-md-offset-2 text-center">
                    <div class="portfolio-item">
                        <a href="#">
                            <img class="img-portfolio img-responsive" src="img/portfolio-3.jpg">
                        </a>
                        <h4>Stop Sign</h4>
                    </div>
                </div>
                <div class="col-md-4 text-center">
                    <div class="portfolio-item">
                        <a href="#">
                            <img class="img-portfolio img-responsive" src="img/portfolio-4.jpg">
                        </a>
                        <h4>Narrow Focus</h4>
                    </div>
                </div>
            </div>
        </div>
    </div>
    /Portfolio -->

    <!-- Call to Action -->
    <div class="call-to-action">
        <div class="container">
            <div class="row">
                <div class="col-md-8 col-md-offset-2 text-center">
                    <h2>Search Crimes and Complaints</h2>
                    <hr>
                </div>
            </div>        
            <div class="row">
                <div class="col-md-6 col-md-offset-3 text-center">
					<form class="row form-inline" role="form" method="get" action="${pageContext.request.contextPath}/search">
					  <div class="form-group">
					    <input class="form-control" type="text" id="criteria" placeholder="Search Criteria" name="criteria">
					  </div>
					  <div class="form-group">
					    <select class="form-control" name="type" id="searchType">
							<option value="cr">Crime</option>
							<option value="co">Complaints</option>
						</select>
					  </div>
					  <div class="form-group">
					    <select class="form-control" name="by" id="searchBy">
							<option value="t">Title & Description</option>
							<option value="a">Address</option>
						</select>
					  </div>	  
					  <button type="submit" class="btn btn-success btn-sm" id="btnSearch"><i class="fa fa-search"></i></button>
					</form>
                </div>
            </div>
        </div>
    </div>
    <!-- /Call to Action -->

    <!-- Map -->
    <div id="contact" class="map">
        <iframe
					src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d2880.4056037682453!2d-79.228155!3d43.785196!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x89d4d0f21c3f9703%3A0xdb64a8accc26643d!2s941!5e0!3m2!1sen!2sca!4v1396838228823"
					width="100%" height="100%" frameborder="0" style="border: 0"></iframe>
    </div>
    <!-- /Map -->

    <!-- Footer -->
    <footer>
        <div class="container">
            <div class="row">
                <div class="col-md-6 col-md-offset-3 text-center">
                    <ul class="list-inline">
                        <li><i class="fa fa-facebook fa-3x"></i>
                        </li>
                        <li><i class="fa fa-twitter fa-3x"></i>
                        </li>
                        <li><i class="fa fa-dribbble fa-3x"></i>
                        </li>
                    </ul>
                    <div class="top-scroll">
                        <a href="#top"><i class="fa fa-circle-arrow-up scroll fa-4x"></i></a>
                    </div>
                    <hr>
                    <p>Copyright &copy; Crime-Alert 2013</p>
                </div>
            </div>
        </div>
    </footer>
    <!-- /Footer -->

    <!-- JavaScript -->
    <script src="js/jquery-1.10.2.js"></script>
    <script src="js/bootstrap.js"></script>

    <!-- Custom JavaScript for the Side Menu and Smooth Scrolling -->
    <script>
    $("#menu-close").click(function(e) {
        e.preventDefault();
        $("#sidebar-wrapper").toggleClass("active");
    });
    </script>
    <script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#sidebar-wrapper").toggleClass("active");
    });
    </script>
    <script>
    $(function() {
        $('a[href*=#]:not([href=#])').click(function() {
            if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') || location.hostname == this.hostname) {

                var target = $(this.hash);
                target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');
                if (target.length) {
                    $('html,body').animate({
                        scrollTop: target.offset().top
                    }, 1000);
                    return false;
                }
            }
        });
    });
    </script>


<%@ include file="common/footer.jsp"%>
