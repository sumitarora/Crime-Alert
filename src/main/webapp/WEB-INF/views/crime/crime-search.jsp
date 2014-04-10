<%@ page import="java.util.List" %>
<%@ page import="com.crimealert.model.Crime" %>
<%@ page import="com.crimealert.model.GeoSummary" %>

<%@ include file="../common/header.jsp"%>

<div class="container">
	<div class="col-md-12">
	<form class="row form-inline text-center" role="form" method="get" action="${pageContext.request.contextPath}/search">
	  <div class="form-group">
	    <input class="form-control" type="text" placeholder="Search Criteria" name="criteria"
	    value="<%= request.getParameter("criteria") %>">
	  </div>
	  <div class="form-group">
	    <select class="form-control" name="type">
	    	<%
	    		String type = request.getParameter("type");
	    		if(type.equals("cr")) {
			%>
				<option selected value="cr">Crime</option>
				<option value="co">Complaints</option>
			<%	    			
	    		} else {
	    	%>
				<option value="cr">Crime</option>
				<option selected value="co">Complaints</option>
			<%	    			
	    		}	    	
	    	%>
		</select>
	  </div>
	  <div class="form-group">
	    <select class="form-control" name="by" id="searchBy">
	    	<%
	    		String by = request.getParameter("by");
	    		if(by.equals("t")) {
			%>
				<option value="t" selected>Title & Description</option>
				<option value="a">Address</option>
			<%	    			
	    		} else {
	    	%>
				<option value="t">Title & Description</option>
				<option value="a" selected>Address</option>
			<%	    			
	    		}	    	
	    	%>	    
		</select>
	  </div>	  
	  
	  <button type="submit" class="btn btn-success btn-sm"><i class="fa fa-search"></i></button>
	</form>
	<div class="text-center">
		<br/>
    	<%
    		String list = request.getParameter("list");
    		if(list != null && list.equals("m")) {	
		%>
			<a class="btn btn-success btn-sm" href="${pageContext.request.contextPath}/search?criteria=<%= request.getParameter("criteria") %>&type=<%= request.getParameter("type") %>&list=m&by=<%= request.getParameter("by") %>">
				<i class="fa fa-map-marker"></i> View Map
			</a>	
			<a class="btn btn-info btn-sm" href="${pageContext.request.contextPath}/search?criteria=<%= request.getParameter("criteria") %>&type=<%= request.getParameter("type") %>&list=l&by=<%= request.getParameter("by") %>">
				<i class="fa fa-list"></i> View List
			</a>
		<%	    			
    		} else {
    	%>
			<a class="btn btn-info btn-sm" href="${pageContext.request.contextPath}/search?criteria=<%= request.getParameter("criteria") %>&type=<%= request.getParameter("type") %>&list=m&by=<%= request.getParameter("by") %>">
				<i class="fa fa-map-marker"></i> View Map
			</a>	
			<a class="btn btn-success btn-sm" href="${pageContext.request.contextPath}/search?criteria=<%= request.getParameter("criteria") %>&type=<%= request.getParameter("type") %>&list=l&by=<%= request.getParameter("by") %>">
				<i class="fa fa-list"></i> View List
			</a>
		<%	    			
    		}	    	
    	%>	
	</div>
	
	<hr/>
	<%
		if(list != null && list.equals("m")) {	
	%>
	<h3>
		<div id="marker-tooltip" class="text-center">Mouse over marker to view info</div>
	</h3>
	<div class="container" id="map-canvas"></div>
	
	<script type="text/javascript">
        var locations = [
     	<%
    			List<Crime> crimes = (List<Crime>) request.getAttribute("crimes");
         		for(int i = 0; i < crimes.size() ; i++) {
         			Crime c = crimes.get(i);
         			out.print("['"+c.getAddress()+", "+c.getLocality()+", "+c.getCountry()+"', "+c.getLatitude()+", "+c.getLongitude()+"," + (i+1) + "]");
         			if(i < crimes.size() -1) {
         				out.print(",");
         			}
         		}
        %>
      ];


       function createMarker(pos, t, m) {
    	    var marker = new google.maps.Marker({       
    	        position: pos, 
    	        map: m,  // google.maps.Map 
    	        title: t      
    	    });

            google.maps.event.addListener(marker, 'mouseover', function () {
                $('#marker-tooltip').html(locations[t][0]).show();
            });

            google.maps.event.addListener(marker, 'mouseout', function () {
                $('#marker-tooltip').html("Mouse over marker to view info");
            });

            var infoWindow = new google.maps.InfoWindow({
                content: '<h5>' + locations[t][0] + '</h5>'
            });
            google.maps.event.addListener(marker, 'click', function () {
                infoWindow.open(m, marker);
            });
           
    	    return marker;  
    	}
       
      function initialize() {
    	  
    	var bounds = new google.maps.LatLngBounds();
    	  
        var mapOptions = {
          zoom: 15
        };
        
        var map = new google.maps.Map(document.getElementById("map-canvas"),
            mapOptions);
        
        for (var i = 0; i < locations.length; i++) {  
          var m = createMarker(new google.maps.LatLng(locations[i][1], locations[i][2]), "" + i, map);
          bounds.extend(m.position);
        }
        
        map.fitBounds(bounds);
      }
      google.maps.event.addDomListener(window, 'load', initialize); 
      

<%--       var map;

      function initialize() {
    	  
    	  var bounds = new google.maps.LatLngBounds();
    	  
          var mapOptions = {
            zoom: 2,
            center: new google.maps.LatLng(2.8, -187.3),
            mapTypeId: google.maps.MapTypeId.TERRAIN            
          };
          
          map = new google.maps.Map(document.getElementById("map-canvas"),mapOptions);

          var locations = [
           	<%
          			List<GeoSummary> summary = (List<GeoSummary>) request.getAttribute("summary");
               		/* for(int i = 0; i < summary.size() ; i++) {
               			Crime c = summary.get(i);
               			out.print("['"+c.getAddress()+", "+c.getLocality()+", "+c.getCountry()+"', "+c.getLatitude()+", "+c.getLongitude()+"," + (i+1) + "]");
               			if(i < summary.size() -1) {
               				out.print(",");
               			}
               		} */
              %>
            ];
          
		for (var i = 0; i < results.features.length; i++) {
    		  console.log(map, i);
          var earthquake = results.features[i];
          var coords = earthquake.geometry.coordinates;
          var latLng = new google.maps.LatLng(coords[1],coords[0]);
          var marker = new google.maps.Marker({
            position: latLng,
            map: map,
            icon: getCircle(earthquake.properties.mag)
          });
        }        
        
        map.fitBounds(bounds);
      } --%>

    /*  window.eqfeed_callback = function(results) {
    	  console.log(results);        
    	  for (var i = 0; i < results.features.length; i++) {
    		  console.log(map, i);
          var earthquake = results.features[i];
          var coords = earthquake.geometry.coordinates;
          var latLng = new google.maps.LatLng(coords[1],coords[0]);
          var marker = new google.maps.Marker({
            position: latLng,
            map: map,
            icon: getCircle(earthquake.properties.mag)
          });
        }
    	  
      } */

     function getCircle(magnitude) {
        return {
          path: google.maps.SymbolPath.CIRCLE,
          fillColor: 'red',
          fillOpacity: .2,
          scale: Math.pow(2, magnitude) / Math.PI,
          strokeColor: 'white',
          strokeWeight: .5
        };
      }
      
      google.maps.event.addDomListener(window, 'load', initialize);
      
    </script> 		
		
	<%		
		} else {
	%>
		<c:forEach items="${crimes}" var="c">
			<h2>${c.title}</h2>
			<p> ${c.address}, ${c.locality}, ${c.administrative_area_level_1}, ${c.country}</p>
		    <div>
		        <span class="label label-primary">Posted ${c.crimeDate}</span>
		        <div class="pull-right">
					<a href="${pageContext.request.contextPath}/crime/view/${c.crimeId}" class="btn btn-sm btn-default">
			  			<i class="fa fa-camera"></i>
			  		</a>
		        </div>
		    </div>		
			<hr>
		</c:forEach>	
	<%			
		}	
	%>		
	</div>
</div>


<%@ include file="../common/footer.jsp"%>

