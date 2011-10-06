The following files were used - hw9.html, ajax_flickr.java, xd_receiver.html. 

The search button onclick attribute invokes the Javascript, that makes the XMLHttpRequest after validation. The Javascript also performs validation function and checks for a “City Name”, “State Code” (without the quotes) before making the request.
HTTP GET method is used.
The request is sent to ajax_flickr that connects to the Google Maps Geocoding JSON output response, gets the longitude and latitude, appends them to the Flickr REST API services URL.
SAX builder is used to parse the XML and json-rpc-1.0 is used to build JSON object.
The JSON string is returned as a  AJAX response. The response on evaluation is stored in a single variable in Javascript.
The values are fetched from the JSON string and URL for the images are formed.
The images are displayed in a div having id as "updateArea" that was initially hidden.
Each image tag also has an onclick attribute that is used to display the enlarged version of the selected image.
Also coordinates of the mouse click event are obtained to set the position of the enlarged image above the selected image.
Once the enlarged image is displayed, the upload button is enabled to upload the image to Facebook.
Using appropriate Facebook methods, a dialog is shown asking whether to publish the image(if the user has already logged in) else it initially shows the login page and then asks to publish.
Once published, the image with appropriate message appears on the Facebook wall.