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
    		String useopendata = request.getParameter("useopendata");
    		if(useopendata != null && useopendata.equals("y")) {	
		%>
		<a class="btn btn-success btn-sm" href="${pageContext.request.contextPath}/search?criteria=<%= request.getParameter("criteria") %>&type=<%= request.getParameter("type") %>&list=hm&by=<%= request.getParameter("by") %>">
			<i class="fa fa-map-marker"></i> Use Open Data
		</a>
		<% } else { %>
			<a class="btn btn-danger btn-sm" href="${pageContext.request.contextPath}/search?criteria=<%= request.getParameter("criteria") %>&type=<%= request.getParameter("type") %>&useopendata=y&list=hm&by=<%= request.getParameter("by") %>">
				<i class="fa fa-map-marker"></i> Don't Use Open Data
			</a>
		<% } 
    		String list = request.getParameter("list");
    		if(list != null && list.equals("m")) {	
		%>
			<a class="btn btn-info btn-sm" href="${pageContext.request.contextPath}/search?criteria=<%= request.getParameter("criteria") %>&type=<%= request.getParameter("type") %>&list=hm&by=<%= request.getParameter("by") %>">
				<i class="fa fa-map-marker"></i> View Heat Map
			</a>
			<a class="btn btn-success btn-sm" href="${pageContext.request.contextPath}/search?criteria=<%= request.getParameter("criteria") %>&type=<%= request.getParameter("type") %>&list=m&by=<%= request.getParameter("by") %>">
				<i class="fa fa-map-marker"></i> View Map
			</a>				
			<a class="btn btn-info btn-sm" href="${pageContext.request.contextPath}/search?criteria=<%= request.getParameter("criteria") %>&type=<%= request.getParameter("type") %>&list=l&by=<%= request.getParameter("by") %>">
				<i class="fa fa-list"></i> View List
			</a>
		<%	    			
    		} else if(list != null && list.equals("hm")){
    	%>
			<a class="btn btn-success btn-sm" href="${pageContext.request.contextPath}/search?criteria=<%= request.getParameter("criteria") %>&type=<%= request.getParameter("type") %>&list=hm&by=<%= request.getParameter("by") %>">
				<i class="fa fa-map-marker"></i> View Heat Map
			</a>
			<a class="btn btn-info btn-sm" href="${pageContext.request.contextPath}/search?criteria=<%= request.getParameter("criteria") %>&type=<%= request.getParameter("type") %>&list=m&by=<%= request.getParameter("by") %>">
				<i class="fa fa-map-marker"></i> View Map
			</a>				
			<a class="btn btn-info btn-sm" href="${pageContext.request.contextPath}/search?criteria=<%= request.getParameter("criteria") %>&type=<%= request.getParameter("type") %>&list=l&by=<%= request.getParameter("by") %>">
				<i class="fa fa-list"></i> View List
			</a>    	
    	<%
    		} else {
    	%>
			<a class="btn btn-info btn-sm" href="${pageContext.request.contextPath}/search?criteria=<%= request.getParameter("criteria") %>&type=<%= request.getParameter("type") %>&list=hm&by=<%= request.getParameter("by") %>">
				<i class="fa fa-map-marker"></i> View Heat Map
			</a>    	
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
		} else if(list != null && list.equals("hm")) {	
	%>
	<div class="container" id="map-canvas"></div>
	<script type="text/javascript">
      var taxiData = [
          //https://developers.google.com/maps/documentation/javascript/examples/layer-heatmap
          <%
          List<Crime> crimes = (List<Crime>) request.getAttribute("crimes");
          for(int i = 0; i < crimes.size() ; i++) 
          {
            Crime c = crimes.get(i);
            out.print("new google.maps.LatLng("+c.getLatitude()+", "+c.getLongitude()+")");
            if(i < crimes.size() -1) 
            {
              out.print(",");
            }
          }
        %>
        ];
      
      var map;

      function initialize() {
    	    
          var bounds = new google.maps.LatLngBounds();
        
        var mapOptions = {
          zoom: 15,
          mapTypeId: google.maps.MapTypeId.SATELLITE
        };
      
        map = new google.maps.Map(document.getElementById('map-canvas'),
            mapOptions);
      
        var pointArray = new google.maps.MVCArray(taxiData);
      
        console.log(taxiData);
        
        heatmap = new google.maps.visualization.HeatmapLayer({
          data: pointArray
        });
        
        for(var i=0; i <taxiData.length; i++) {
          console.log(taxiData[i]);
          bounds.extend(taxiData[i]);  
        }   
        
        map.fitBounds(bounds);
        heatmap.setMap(map);
      }
      
      function toggleHeatmap() {
        heatmap.setMap(heatmap.getMap() ? null : map);
      }
      
      function changeGradient() {
        var gradient = [
          'rgba(0, 255, 255, 0)',
          'rgba(0, 255, 255, 1)',
          'rgba(0, 191, 255, 1)',
          'rgba(0, 127, 255, 1)',
          'rgba(0, 63, 255, 1)',
          'rgba(0, 0, 255, 1)',
          'rgba(0, 0, 223, 1)',
          'rgba(0, 0, 191, 1)',
          'rgba(0, 0, 159, 1)',
          'rgba(0, 0, 127, 1)',
          'rgba(63, 0, 91, 1)',
          'rgba(127, 0, 63, 1)',
          'rgba(191, 0, 31, 1)',
          'rgba(255, 0, 0, 1)'
        ]
        heatmap.set('gradient', heatmap.get('gradient') ? null : gradient);
      }
      
      function changeRadius() {
        heatmap.set('radius', heatmap.get('radius') ? null : 20);
      }
      
      function changeOpacity() {
        heatmap.set('opacity', heatmap.get('opacity') ? null : 0.2);
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

