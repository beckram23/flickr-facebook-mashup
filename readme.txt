The following files are submitted along with this - hw9.html, ajax_flickr.java, xd_receiver.html. 

The search button onclick attribute invokes the JavaScript which makes the XMLHttpRequest after validation. 
The request is sent to ajax_flickr that connects to the Google maps, gets the longitude and latitude, appends them to the
flickr url. 
SAX builder is used to parse the XML and json-rpc-1.0 is used to build JSON object.
The JSON is returned as a response. The response on evaluation is stored in a single variable in JavaScript.
The values are fetched from the JSON object and appended to the URL to the images.
The images are displayed in div having id as "updateArea".
Each image tag also has an onclick attribute which is used to display the bigger picture of the selected image.
Also mouse coordinates of the mouse click is obtained to set the position of the bigger image above it.
Once the enlarged image is displayed, the upload button is enabled to upload the image to Facebook.
Using appropriate Facebook methods, a dialog is shown asking whether to publish the image(if the user has already logged 
in) else it initially shows the login page and then asks to publish.
Once published the image with appropriate message appears on the Facebook wall.