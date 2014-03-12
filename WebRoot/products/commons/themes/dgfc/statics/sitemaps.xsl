<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" 
                xmlns:html="http://www.w3.org/TR/REC-html40"
                xmlns:sitemap="http://www.sitemaps.org/schemas/sitemap/0.9"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  
  <xsl:output method="html" version="1.0" encoding="utf-8" indent="yes"/>
  
  <!-- Root template -->    
  <xsl:template match="/">
    <html>     
      <head>  
        <title>Google Sitemap File</title>
		<style type="text/css">
		  <![CDATA[
			<!--
			    body{
				background:#7C94A7;
			    }
			    p,td,div,th{
				font: normal 13px/20px Verdana Geneva Arial Helvetica sans-serif;
			    }
			    #container{
				width:60%;
				margin:20px auto;
				text-align:left;
				border:1px solid #CED5DA;
				padding:10px;
				background:#92A6B6;
			    }
			    a{color:#009}
			    #main{
				background:#D0DEE8;
				padding:15px;
			    }
			    #setting{
				width:100%;
			    }
			    #setting th{
				text-align:left;
				padding-left:20px;
				font-weight:normal;
			    }    
			    tr.title{
				background:#7C94A7;
				color:#fff;
				cursor: pointer;
			    }
			    tr.title td{
				padding:5px;
			    }
			    label{font-weight:bold}
			
			p.sml { 
				font-size:0.8em;
				margin-top:0; }
			
			.sortup {
				background-position: right center;
				background-image: url(http://www.google.com/webmasters/sitemaps/images/sortup.gif);
				background-repeat: no-repeat;
				font-style:italic;
				white-space:pre; }
				
			.sortdown {
				background-position: right center;
				background-image: url(http://www.google.com/webmasters/sitemaps/images/sortdown.gif);
				background-repeat: no-repeat;
				font-style:italic;
				white-space:pre; }
			
			table.copyright {
				width:100%;
				border-top:1px solid #ddad08;
				margin-top:1em;
				text-align:center;
				padding-top:1em;
				vertical-align:top; }
			-->
		  ]]>
		</style>
        <link href="gss.css" type="text/css" rel="stylesheet"/>
        <script language="JavaScript">
		  <![CDATA[
			var selectedColor = "blue";
			var defaultColor = "black";
			var hdrRows = 1;
			var numeric = '..';
			var desc = '..';
			var html = '..';
			var freq = '..';
			
			function initXsl(tabName,fileType) {
				hdrRows = 1;
			
			  if(fileType=="sitemap") {
			  	numeric = ".3.";
			  	desc = ".1.";
			  	html = ".0.";
			  	freq = ".2.";
			  	initTable(tabName);
				  setSort(tabName, 3, 1);
			  }
			  else {
			  	desc = ".1.";
			  	html = ".0.";
			  	initTable(tabName);
				  setSort(tabName, 1, 1);
			  }
			
				var theURL = document.getElementById("head1");
				theURL.innerHTML += ' ' + location;
				document.title += ': ' + location;
			}
			
			function initTable(tabName) {
			  var theTab = document.getElementById(tabName);
			  for(r=0;r<hdrRows;r++)
			   for(c=0;c<theTab.rows[r].cells.length;c++)
			     if((r+theTab.rows[r].cells[c].rowSpan)>hdrRows)
			       hdrRows=r+theTab.rows[r].cells[c].rowSpan;
			  for(r=0;r<hdrRows; r++){
			    colNum = 0;
			    for(c=0;c<theTab.rows[r].cells.length;c++, colNum++){
			      if(theTab.rows[r].cells[c].colSpan<2){
			        theCell = theTab.rows[r].cells[c];
			        rTitle = theCell.innerHTML.replace(/<[^>]+>|&nbsp;/g,'');
			        if(rTitle>""){
			          theCell.title = "Change sort order for " + rTitle;
			          var sortParams = 15; // bitmapped: numeric|desc|html|freq
			          if(numeric.indexOf("."+colNum+".")>-1) sortParams -= 1;
			          if(desc.indexOf("."+colNum+".")>-1) sortParams -= 2;
			          if(html.indexOf("."+colNum+".")>-1) sortParams -= 4;
			          if(freq.indexOf("."+colNum+".")>-1) sortParams -= 8;
			          theCell.onclick = new Function("sortTable(this,"+(colNum+r)+","+hdrRows+","+sortParams+")");
			        }
			      } else {
			        colNum = colNum+theTab.rows[r].cells[c].colSpan-1;
			      }
			    }
			  }
			}
			
			function setSort(tabName, colNum, sortDir) {
				var theTab = document.getElementById(tabName);
				theTab.rows[0].sCol = colNum;
				theTab.rows[0].sDir = sortDir;
				if (sortDir) 
					theTab.rows[0].cells[colNum].className='sortdown'
				else
					theTab.rows[0].cells[colNum].className='sortup';
			}
			
			function sortTable(theCell, colNum, hdrRows, sortParams){
			  var typnum = !(sortParams & 1);
			  sDir = !(sortParams & 2);
			  var typhtml = !(sortParams & 4);
			  var typfreq = !(sortParams & 8);
			  var tBody = theCell.parentNode;
			  while(tBody.nodeName!="TBODY"){
			    tBody = tBody.parentNode;
			  }
			  var tabOrd = new Array();
			  if(tBody.rows[0].sCol==colNum) sDir = !tBody.rows[0].sDir;
			  if (tBody.rows[0].sCol>=0)
			    tBody.rows[0].cells[tBody.rows[0].sCol].className='';
			  tBody.rows[0].sCol = colNum;
			  tBody.rows[0].sDir = sDir;
			  if (sDir) 
			  	 tBody.rows[0].cells[colNum].className='sortdown'
			  else 
			     tBody.rows[0].cells[colNum].className='sortup';
			  for(i=0,r=hdrRows;r<tBody.rows.length;i++,r++){
			    colCont = tBody.rows[r].cells[colNum].innerHTML;
			    if(typhtml) colCont = colCont.replace(/<[^>]+>/g,'');
			    if(typnum) {
			      colCont*=1;
			      if(isNaN(colCont)) colCont = 0;
			    }
			    if(typfreq) {
					switch(colCont.toLowerCase()) {
						case "always":  { colCont=0; break; }
						case "hourly":  { colCont=1; break; }
						case "daily":   { colCont=2; break; }
						case "weekly":  { colCont=3; break; }
						case "monthly": { colCont=4; break; }
						case "yearly":  { colCont=5; break; }
						case "never":   { colCont=6; break; }
					}
				}
			    tabOrd[i] = [r, tBody.rows[r], colCont];
			  }
			  tabOrd.sort(compRows);
			  for(i=0,r=hdrRows;r<tBody.rows.length;i++,r++){
			    tabOrd[i][1].style.background=i%2?'':'#E0EAF2';
			    tBody.insertBefore(tabOrd[i][1],tBody.rows[r]);
			  } 
			  window.status = ""; 
			}
			
			function compRows(a, b){
			  if(sDir){
			    if(a[2]>b[2]) return -1;
			    if(a[2]<b[2]) return 1;
			  } else {
			    if(a[2]>b[2]) return 1;
			    if(a[2]<b[2]) return -1;
			  }
			  return 0;
			}

		  ]]>
		</script>
        
      </head>

      <!-- Store in $fileType if we are in a sitemap or in a siteindex -->
      <xsl:variable name="fileType">
        <xsl:choose>
		  <xsl:when test="//sitemap:url">sitemap</xsl:when>
		  <xsl:otherwise>siteindex</xsl:otherwise>
        </xsl:choose>      
      </xsl:variable>            

      <!-- Body -->
      <body onLoad="initXsl('table0','{$fileType}');">  
	<center><div id="container"><div id="main">

      	<!-- Logo -->
            
        <!-- Text and table -->
        <strong id="head1">Sitemap:</strong><hr />
        <xsl:choose>
	      <xsl:when test="$fileType='sitemap'"><xsl:call-template name="sitemapTable"/></xsl:when>
	      <xsl:otherwise><xsl:call-template name="siteindexTable"/></xsl:otherwise>
  		</xsl:choose>
        <br/>
	</div></div>

	</center>
      </body>
    </html>
  </xsl:template>     

  <!-- siteindexTable template -->
  <xsl:template name="siteindexTable">
    <div>Number of sitemaps in this sitemaps index: <xsl:value-of select="count(sitemap:sitemapindex/sitemap:sitemap)"></xsl:value-of></div>          
    <table border="0" width="100%" id="table0">
      <tr class="title">
        <td>Sitemap URL</td>
        <td width="100px">Update</td>
      </tr>
      <xsl:apply-templates select="sitemap:sitemapindex/sitemap:sitemap">
        <xsl:sort select="sitemap:lastmod" order="descending"/>              
      </xsl:apply-templates>  
    </table>            
  </xsl:template>  
  
  <!-- sitemapTable template -->  
  <xsl:template name="sitemapTable">
    <h2>Number of URLs in this Google Sitemap: <xsl:value-of select="count(sitemap:urlset/sitemap:url)"></xsl:value-of></h2>          
    <table border="0" width="100%" id="table0">
	  <tr class="title">
	    <td>Sitemap URL</td>
		<td width="100px">Update</td>
		<td width="100px">Change freq.</td>
		<td width="50px">Priority</td>
	  </tr>
	  <xsl:apply-templates select="sitemap:urlset/sitemap:url">
	    <xsl:sort select="sitemap:priority" order="descending"/>              
	  </xsl:apply-templates>
	</table>  
  </xsl:template>    
  
  <!-- sitemap:url template -->  
  <xsl:template match="sitemap:url">
<xsl:variable name="colour">
<xsl:choose>
<xsl:when test="position() mod 2 ">
background:#E0EAF2
</xsl:when>
</xsl:choose>
</xsl:variable>

    <tr style="{$colour}">
      <td>
        <xsl:variable name="sitemapURL"><xsl:value-of select="sitemap:loc"/></xsl:variable>  
        <a href="{$sitemapURL}" target="_blank" ref="nofollow"><xsl:value-of select="$sitemapURL"></xsl:value-of></a>
      </td>
      <td><xsl:value-of select="sitemap:lastmod"/></td>
      <td><xsl:value-of select="sitemap:changefreq"/></td>
      <td><xsl:value-of select="sitemap:priority"/></td>
    </tr>  
  </xsl:template>
  
  <!-- sitemap:sitemap template -->
  <xsl:template match="sitemap:sitemap">

<xsl:variable name="colour">
<xsl:choose>
<xsl:when test="position() mod 2 ">
background:#E0EAF2
</xsl:when>
</xsl:choose>
</xsl:variable>

    <tr style="{$colour}">
      <td>
        <xsl:variable name="sitemapURL"><xsl:value-of select="sitemap:loc"/></xsl:variable>  
        <a href="{$sitemapURL}"><xsl:value-of select="$sitemapURL"></xsl:value-of></a>
      </td>
      <td><xsl:value-of select="sitemap:lastmod"/></td>
    </tr>  
  </xsl:template>  
  
</xsl:stylesheet>