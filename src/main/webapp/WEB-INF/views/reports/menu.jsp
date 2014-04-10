<%@ page import="java.util.List" %>
<%@ page import="com.crimealert.model.Summary" %>

<%@ include file="../common/header.jsp"%>

<style>

body {
  overflow-y:scroll;
}

text {
  font: 12px sans-serif;
}

svg {
  display: block;
}

#chart1 svg {
  height: 500px;
  min-width: 200px;
  min-height: 100px;
/*
  margin: 50px;
  Minimum height and width is a good idea to prevent negative SVG dimensions...
  For example width should be =< margin.left + margin.right + 1,
  of course 1 pixel for the entire chart would not be very useful, BUT should not have errors
*/

}
#chart1 {
  margin-top: 200px;
  margin-left: 100px;
}
</style>

<div class="container">
	<%@ include file="../common/left-menu.jsp"%>
	<div class="col-sm-9">
	<div class="form-horizontal" role="form">
	
	  <div class="form-group">
	    <label for="title" class="col-sm-2 control-label">Report Of</label>
	    <div class="col-sm-6">
	      <select id="cmbReportOf" class="form-control" onchange="onReportOfChange()">
	      	<option value="cr">Crime</option>
	      	<option value="co">Complaints</option>
	      </select>
	    </div>
	 </div>
	 
	  <div class="form-group">
	    <label for="title" class="col-sm-2 control-label">Graph Type</label>
	    <div class="col-sm-6">
	      <select id="cmbGraphType" class="form-control" onchange="displayChart()">
	      	<option value="b">Bar</option>
	      	<option value="p">Pie</option>
	      	<option value="s">Scatter</option>
	      </select>
	    </div>
	 </div>	 	
	
	  <div class="form-group" id="divReportBy">
	    <label for="title" class="col-sm-2 control-label">Report By</label>
	    <div class="col-sm-6">
	      <select id="cmbReportBy" class="form-control" onchange="onReportByChange()">
	      	<option value="c">Country</option>
	      	<option value="s">States</option>
	      </select>
	    </div>
	 </div>
	 
	 <div class="form-group" id="divCountry">
	    <label for="title" class="col-sm-2 control-label">Country</label>
	    <div class="col-sm-6">
	      <select id="cmbCountry" class="form-control" onchange="displayStatesChart()">
	      </select>
	    </div>
	 </div>
	 	 
	</div>
</div>
</div>

<div id="chart" >
  <svg style="height: 500px;"></svg>
</div>


<script>

onReportOfChange();
function onReportOfChange() {
	$("#divCountry").hide();
	$("#cmbGraphType").val('b');
	$("#cmbReportBy").val('c');
	
	displayChart();
}

function displayChart() {
	var reportby = $("#cmbReportBy").val();
	var reportof = $("#cmbReportOf").val();
	var graphType = $("#cmbGraphType").val();
	var country = $("#cmbCountry").val();
	
	$("#divReportBy").show();	

	if(graphType === "s") {
		$.get("${pageContext.request.contextPath}/reports/scattter", function( data ) {
			console.log(data);
			drawScatter(data);
		});
		$("#divReportBy").hide();
		$("#divCountry").hide();
		return;
	}
	
	if(reportby === "c") {
		var obj = {
			reportBy: reportby,
			reportOf: reportof
		};	
		
		$.post("${pageContext.request.contextPath}/reports/summary", obj, function( data ) {
			if(graphType === "b") {
				drawBar(data);
			} else {
				drawPie(data);	
			}
		});		
	} else if(reportby === "s") {
		displayStatesChart();	
	}
	
}

$("#divCountry").hide();
onReportByChange();

var chart;

function onReportByChange() {

	$("#divCountry").hide();
	var reportby = $("#cmbReportBy").val();
	if(reportby === 'c') {
		displayChart();
		return;
	}
	$("#divCountry").show();
	
	var obj = {
		reportOf: reportby
	};	
	
	$.post("${pageContext.request.contextPath}/reports/summary", obj, function( data ) {
		
		var countries = data;
		var html = "";
		for (var i = 0; i < countries.length; i++) {
			html = html + "<option value='"+countries[i].key+"'>"+countries[i].key+"</option>";
		}
		$('#cmbCountry').html(html);

	});
	displayStatesChart();
}

function displayStatesChart() {

	var reportof = $("#cmbReportOf").val();
	var country = $("#cmbCountry").val();
	var graphType = $("#cmbGraphType").val();
	
	 var obj = {
		reportOf: reportof,
		country:country
	};	
	
	$.post("${pageContext.request.contextPath}/reports/summary", obj, function( data ) {
		if(graphType === "b") {
			drawBar(data);	
		} else if(graphType === "p") {
			drawPie(data);
		}
	});	
}

function drawPie(data) {
	$("#chart").html("<svg style=\"height: 500px;\"></svg>");
	//Donut chart example
	nv.addGraph(function() {
	  var chart = nv.models.pieChart()
	      .x(function(d) { return d.key; })
	      .y(function(d) { return d.count; })
	      .showLabels(true)     //Display pie labels
	      .labelThreshold(.05)  //Configure the minimum slice size for labels to show up
	      .labelType("percent") //Configure what type of data to show in the label. Can be "key", "value" or "percent"
	      .donut(true)          //Turn on Donut mode. Makes pie chart look tasty!
	      .donutRatio(0.35)     //Configure how big you want the donut hole size to be.
	      ;

	    d3.select("#chart svg")
	        .datum(data)
	        .transition().duration(350)
	        .call(chart);
	    
	    nv.utils.windowResize(chart.update);

	  return chart;
	});

}

function drawBar(data) {
	$("#chart").html("<svg style=\"height: 500px;\"></svg>");
	nv.addGraph(function() {
		  var chart = nv.models.discreteBarChart()
		      .x(function(d) { return d.key; })    //Specify the data accessors.
		      .y(function(d) { return d.count; })
		      .staggerLabels(true)    //Too many bars and not enough room? Try staggering labels.
		      .tooltips(false)        //Don't show tooltips
		      .showValues(true)       //...instead, show the bar value right on top of each bar.
		      .transitionDuration(350)
		      ;

		  d3.select('#chart svg')
		      .datum(exampleData(data))
		      .call(chart);

		  nv.utils.windowResize(chart.update);

		  return chart;
		});	
}

function drawScatter(data) {
	$("#chart").html("<svg style=\"height: 500px;\"></svg>");
	nv.addGraph(function() {
		  var chart = nv.models.scatterChart()
		                .showDistX(true)    //showDist, when true, will display those little distribution lines on the axis.
		                .showDistY(true)
		                .transitionDuration(350)
		                .color(d3.scale.category10().range());

		  //Configure how the tooltip looks.
		  chart.tooltipContent(function(key) {
		      return '<h3>' + key + '</h3>';
		  });

		  //Axis settings
		  chart.xAxis.tickFormat(d3.format('.02f'));
		  chart.yAxis.tickFormat(d3.format('.02f'));

		  //We want to show shapes other than circles.
		  chart.scatter.onlyCircles(false);

		  //var myData = randomData(4,40);
		  console.log(data);
		  
		  d3.select('#chart svg')
		      .datum(data)
		      .call(chart);

		  nv.utils.windowResize(chart.update);

		  return chart;
		});	
}

function randomData(groups, points) { //# groups,# points per group
	  var data = [],
	      shapes = ['circle', 'cross', 'triangle-up', 'triangle-down', 'diamond', 'square'],
	      random = d3.random.normal();

	  for (i = 0; i < groups; i++) {
	    data.push({
	      key: 'Group ' + i,
	      values: []
	    });

	    for (j = 0; j < points; j++) {
	      data[i].values.push({
	        x: random()
	      , y: random()
	      , size: Math.random()   //Configure the size of each scatter point
	      , shape: (Math.random() > 0.95) ? shapes[j % 6] : "circle"  //Configure the shape of each scatter point.
	      });
	    }
	  }

	  return data;
	}


function exampleData(data) {
 return  [ 
    {
      key: "Cumulative Return",
      values: data
    }
  ]
}

</script>

<%@ include file="../common/footer.jsp"%>
	