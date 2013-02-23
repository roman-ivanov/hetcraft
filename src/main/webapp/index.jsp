<html>
<body>
    <h2>Jersey RESTful Web Application!</h2>
    <p>
        <a href="webresources/netcraft/1">get(1)</a>
        <script type="text/javascript">
									function get() {
										window.location = 'webresources/netcraft/'
												+ document.getElementById('v1').value;
									};
									function post() {
										var xmlDocument = "<?xml version=\"1.0\"?><request><v2>"
												+ document.getElementById('v2').value
												+ "</v2><v3>"
												+ document.getElementById('v3').value
												+ "</v3><v4>"
												+ document.getElementById('v4').value
												+ "</v4></request>";
										var httpRequest;
										httpRequest = new XMLHttpRequest();
										httpRequest
												.open(
														'POST',
														"webresources/netcraft/",
														false);
										httpRequest.send(xmlDocument);
										 var result = httpRequest.responseText;
										 alert(result);
									}
								</script>
        <LABEL for="v1"> v1: </LABEL> <INPUT type="text" id="v1"><BR> <INPUT type="submit" value="Send" onclick="get()"> <INPUT
            type="reset"> <BR> <BR> <LABEL for="v2"> v2: </LABEL> <INPUT type="text" id="v2"><BR> <LABEL for="v3"> v3: </LABEL> <INPUT
            type="text" id="v3"><BR> <LABEL for="v4"> v4: </LABEL> <INPUT type="text" id="v4"><BR> <INPUT type="submit" value="Send"
            onclick="post()"> <INPUT type="reset">
    <p>
        Visit the <a href="http://jersey.java.net">Project Jersey website</a> for more information on Jersey!
</body>
</html>
