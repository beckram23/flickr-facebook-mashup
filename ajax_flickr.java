import java.io.*;
import java.util.*;
import java.lang.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;
import org.json.*;

public class ajax_flickr extends HttpServlet {
double lon, lat;
long ts,tts;
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
	    String s1 = request.getParameter("searchval");
		//String tts = request.getParameter("timestamp");
		//ts = Integer.parseInt(tts);
		tts = System.currentTimeMillis()/1000;
		ts = tts-(1 * 24 * 60 * 60);
		s1 = s1.replace(' ','+');
		String s2 = "http://maps.google.com/maps/geo?q="+s1+"&output=json&oe=utf8&sensor=false&gl=us";
		response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
		//out.println(ts);
		try
   {
      URL url1 = new URL(s2);
      URLConnection connection1 = url1.openConnection();
      connection1.setDoInput(true);
      InputStream inStream1 = connection1.getInputStream();
      BufferedReader input1 = new BufferedReader(new InputStreamReader(inStream1));
       String line1 = "";
	   String line = "";
      while ((line1 = input1.readLine()) != null)
	  {
	  line = line + line1.replaceAll("\n","");
	  }
	  String line2 = "";
	  if(line.matches(".*coordinates.*"))
	  {
	        URL url3 = new URL(s2);
      URLConnection connection3 = url3.openConnection();
      connection3.setDoInput(true);
      InputStream inStream3 = connection3.getInputStream();
      BufferedReader input3 = new BufferedReader(new InputStreamReader(inStream3));
	  while ((line2 = input3.readLine()) != null)
	  {//out.println(line);
	     if(line2.matches(".*coordinates.*"))
         {
		 int l1 = line2.indexOf('[');
		 int l2 = line2.indexOf(']');
		 char buf[] = new char[l2-l1-1]; 
		line2.getChars(l1+1, l2, buf, 0);
		String t1 = new String(buf);
		String[] t2 = t1.split(",");
		lon = Double.parseDouble(t2[0]);
		lat = Double.parseDouble(t2[1]);
		//out.println(t2.length);
		//out.println(lon+" "+lat);
		} 
		//else{out.println("enter a valid city, state");}
		}
		}
		else//{out.println("enter a valid city, state");}
		{lon=10000;lat=10000;}
   }
   catch (Exception e)
   {
      out.println(e.toString());
   }
   String s3="http://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=da506cac3149622fa92b9159b9b5f77d&lat="+lat+"&lon="+lon+"&radius=10&radius_units=km&min_upload_date="+ts;
   //out.println(s3);
   try
   {
     URL url2 = new URL(s3);
URLConnection connection2 = url2.openConnection();
connection2.setAllowUserInteraction(false);
InputStream inStream2 = url2.openStream();
JSONObject jphotos = new JSONObject();
JSONObject jphoto = new JSONObject();
//JSONObject joele = new JSONObject();
//JSONArray jaele = new JSONArray();
//String[] joele = new String[20];
StringBuffer joele = new StringBuffer();
joele.append("[");
try{
SAXBuilder builder = new SAXBuilder(false);
Document doc = builder.build(url2);
//out.println("File: "+url2+"<br>");
Element root = doc.getRootElement();
//out.println("Root element:"+root.getName()+"<br>");
//Element pho = root.getChild("photos");
List photos = root.getChild("photos").getChildren("photo");
int tot1 = photos.size();
if(tot1==0){out.println("Please enter a valid value");}
int c=0;
//Iterator i = photos.iterator();
  //    while (i.hasNext()) {
  for(c=0;c<tot1;c++)
  {
	//  Element p = (Element) i.next();
	     Element p = (Element) photos.get(c);
        //out.println(p.getName()+" "+p.getAttribute("id").getName()+" "+p.getAttribute("id").getValue()+" "+p.getAttribute("secret").getName()+" "+p.getAttribute("secret").getValue()+" "+p.getAttribute("server").getName()+" "+p.getAttribute("server").getValue()+" "+p.getAttribute("farm").getName()+" "+p.getAttribute("farm").getValue()+" "+p.getAttribute("title").getName()+" "+p.getAttribute("title").getValue()+"\n");
		//c++;
		if(c<19)
		{
		joele.append("{\"id\":\""+p.getAttribute("id").getValue()+"\",\"secret\":\""+p.getAttribute("secret").getValue()+"\",\"server\":\""+p.getAttribute("server").getValue()+"\",\"farm\":\""+p.getAttribute("farm").getValue()+"\",\"title\":\""+p.getAttribute("title").getValue()+"\"},");
		}
		if(c==19)
		{
		joele.append("{\"id\":\""+p.getAttribute("id").getValue()+"\",\"secret\":\""+p.getAttribute("secret").getValue()+"\",\"server\":\""+p.getAttribute("server").getValue()+"\",\"farm\":\""+p.getAttribute("farm").getValue()+"\",\"title\":\""+p.getAttribute("title").getValue()+"\"}");
		}
		if(c==20){break;}
		}
		joele.append("]");
		String joele1 = new String(joele);
		JSONArray jaele = new JSONArray(joele1);
		//out.println(joele);
		//jaele.add();
		//out.println(jaele.toString());
		jphoto.put(root.getChild("photos").getChild("photo").getName(),jaele);
		jphotos.put(root.getChild("photos").getName(), jphoto);
		out.println(jphotos.toString());
} catch (JDOMException e2) {
out.println(e2.toString());
}
   }
   catch (Exception e1)
   {
      out.println(e1.toString());
   }
        
		//out.println("<b>"+url+"</b>");
        //out.println("</body>");
        //out.println("</html>");*/
   }
}